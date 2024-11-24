package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.BorrowingHistoryItem;
import com.LibTrack.utils.DatabaseConn;

public class BorrowingHistoryDao {
	public List<BorrowingHistoryItem> getBorrowingHistoryByMemberId(int memberId) {
		String query = "SELECT bh.BorrowID, b.BookID, b.Title, bh.BorrowDate, bh.DueDate, bh.ReturnDate, bh.Status "
				+ "FROM LibTrack.Borrowing_History bh " + "JOIN Books b ON bh.BookID = b.BookID "
				+ "WHERE bh.MemberID = ?";
		List<BorrowingHistoryItem> borrowingHistory = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int borrowId = rs.getInt("BorrowID");
					int bookId = rs.getInt("BookID");
					String title = rs.getString("Title");
					String borrowDate = rs.getDate("BorrowDate").toString();
					String dueDate = rs.getDate("DueDate").toString();
					Object returnDateObj = rs.getDate("ReturnDate");
					String returnDate;
					if (returnDateObj != null) {
						returnDate = returnDateObj.toString();
					} else {
						returnDate = "";
					}
					String status = rs.getString("Status");

					BorrowingHistoryItem item = new BorrowingHistoryItem(borrowId, bookId, title, borrowDate, dueDate,
							returnDate, status);
					borrowingHistory.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowingHistory;
	}

	public int getBookIdFromBorrowId(int borrowId) {
		String query = "SELECT BookID FROM LibTrack.Borrowing_History WHERE BorrowID = ?";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, borrowId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int bookId = rs.getInt("BookID");
				return bookId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean markBookAsReturned(int borrowId) {
		String query = "UPDATE LibTrack.Borrowing_History SET Status = 'Returned', ReturnDate = CURRENT_DATE WHERE BorrowID = ?";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			// Set the borrowId parameter in the query
			ps.setInt(1, borrowId);

			// Execute the update query
			int rowsAffected = ps.executeUpdate();

			// If at least one row was updated, return true
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return false if the update failed
		return false;
	}

	public boolean createBorrowingRecord(int memberId, int bookId, Date dueDate) {
		String query = "INSERT INTO LibTrack.Borrowing_History (BookID, MemberID, BorrowDate, DueDate, Status) "
				+ "VALUES (?, ?, CURRENT_DATE, ?, 'Borrowed')";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			// Set parameters for the query
			ps.setInt(1, bookId); // Set the bookId
			ps.setInt(2, memberId); // Set the memberId
			ps.setDate(3, dueDate); // Set the dueDate

			// Execute the update
			int rowsAffected = ps.executeUpdate();

			// If the insertion was successful, return true
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception
		}

		// Return false if the insertion failed
		return false;
	}

}
