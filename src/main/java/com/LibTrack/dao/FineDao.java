package com.LibTrack.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.FineItem;
import com.LibTrack.utils.DatabaseConn;

public class FineDao {
	public List<FineItem> getFinesByMemberId(int memberId) {
		String query = "SELECT Books.Title AS BookTitle, " + "Fines.FineAmount, " + "Fines.PaidStatus, "
				+ "Fines.IssuedDate, " + "Fines.FinesDueDate " + "FROM LibTrack.Fines "
				+ "JOIN LibTrack.Borrowing_History ON Fines.BorrowID = Borrowing_History.BorrowID "
				+ "JOIN LibTrack.Books ON Borrowing_History.BookID = Books.BookID " + "WHERE Fines.MemberID = ?;";
		List<FineItem> fines = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String title = rs.getString("BookTitle");
					BigDecimal amount = rs.getBigDecimal("FineAmount");
					String issuedDate = rs.getDate("IssuedDate").toString();
					String status = rs.getString("PaidStatus");

					FineItem item = new FineItem(title, amount, status, issuedDate, null);
					fines.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fines;
	}

	public void calculateFines() {
		String query = "SELECT BorrowID, MemberID, DueDate, ReturnDate FROM LibTrack.Borrowing_History WHERE Status = 'Borrowed'";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LocalDate dueDate = rs.getDate("DueDate").toLocalDate();
				LocalDate returnDate = rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate()
						: LocalDate.now();
				int overdueDays = Period.between(dueDate, returnDate).getDays();

				if (overdueDays > 0) {
					BigDecimal fineAmount = new BigDecimal(overdueDays * 0.50); // Assuming $0.50 per day
					addOrUpdateFine(rs.getInt("BorrowID"), rs.getInt("MemberID"), fineAmount);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addOrUpdateFine(int borrowId, int memberId, BigDecimal fineAmount) {
		String checkFineQuery = "SELECT FineID, FineAmount FROM LibTrack.Fines WHERE MemberID = ? AND BorrowID = ? AND PaidStatus = 'Unpaid'";
		String updateFineQuery = "UPDATE LibTrack.Fines SET FineAmount = ? WHERE FineID = ?";
		String insertFineQuery = "INSERT INTO LibTrack.Fines (MemberID, BorrowID, FineAmount, PaidStatus, IssuedDate) VALUES (?, ?, ?, 'Unpaid', CURDATE())";

		try (Connection con = DatabaseConn.getConnection()) {
			// Check if there is an existing unpaid fine for this member and borrowing
			// record
			PreparedStatement checkPs = con.prepareStatement(checkFineQuery);
			checkPs.setInt(1, memberId);
			checkPs.setInt(2, borrowId);
			ResultSet rs = checkPs.executeQuery();

			if (rs.next()) {
				// Fine exists, update it
				int fineId = rs.getInt("FineID");
				BigDecimal existingFineAmount = rs.getBigDecimal("FineAmount");
				BigDecimal updatedFineAmount = existingFineAmount.add(fineAmount);

				PreparedStatement updatePs = con.prepareStatement(updateFineQuery);
				updatePs.setBigDecimal(1, updatedFineAmount);
				updatePs.setInt(2, fineId);
				updatePs.executeUpdate();
			} else {
				// Fine does not exist, insert a new record
				PreparedStatement insertPs = con.prepareStatement(insertFineQuery);
				insertPs.setInt(1, memberId);
				insertPs.setInt(2, borrowId);
				insertPs.setBigDecimal(3, fineAmount);
				insertPs.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void payFine(int memberId, BigDecimal amountToPay) {
	    String selectFinesQuery = "SELECT FineID, FineAmount FROM LibTrack.Fines WHERE MemberID = ? AND PaidStatus = 'Unpaid' ORDER BY IssuedDate";
	    String updateFineQuery = "UPDATE LibTrack.Fines SET FineAmount = ?, PaidStatus = ? WHERE FineID = ?";

	    try (Connection con = DatabaseConn.getConnection();
	         PreparedStatement selectPs = con.prepareStatement(selectFinesQuery)) {
	        
	        selectPs.setInt(1, memberId);
	        ResultSet rs = selectPs.executeQuery();

	        while (rs.next() && amountToPay.compareTo(BigDecimal.ZERO) > 0) {
	            int fineId = rs.getInt("FineID");
	            BigDecimal fineAmount = rs.getBigDecimal("FineAmount");

	            if (amountToPay.compareTo(fineAmount) >= 0) {
	                // Full payment for this fine
	                try (PreparedStatement updatePs = con.prepareStatement(updateFineQuery)) {
	                    updatePs.setBigDecimal(1, BigDecimal.ZERO);
	                    updatePs.setString(2, "Paid");
	                    updatePs.setInt(3, fineId);
	                    updatePs.executeUpdate();
	                }
	                amountToPay = amountToPay.subtract(fineAmount);
	            } else {
	                // Partial payment for this fine
	                try (PreparedStatement updatePs = con.prepareStatement(updateFineQuery)) {
	                    updatePs.setBigDecimal(1, fineAmount.subtract(amountToPay));
	                    updatePs.setString(2, "Unpaid");
	                    updatePs.setInt(3, fineId);
	                    updatePs.executeUpdate();
	                }
	                amountToPay = BigDecimal.ZERO;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
