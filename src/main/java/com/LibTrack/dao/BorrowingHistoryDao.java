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
	
	 public List<Integer> getBorrowedBookIdsByMemberId(int memberId) {
	        String query = "SELECT BookID FROM Borrowing_History WHERE MemberID = ? AND Status = 'Borrowed'";
	        List<Integer> bookIds = new ArrayList<>();
	        try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, memberId);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    bookIds.add(rs.getInt("BookID"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bookIds;
	    }
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
	    String markReturnedQuery = "UPDATE Borrowing_History SET Status = 'Returned', ReturnDate = CURRENT_DATE WHERE BorrowID = ?";
	    String checkReservationQuery = "SELECT MemberID FROM Reservations WHERE BookID = ? AND Status = 'Active'";
	    String fulfillReservationQuery = "UPDATE Reservations SET Status = 'Fulfilled' WHERE BookID = ? AND MemberID = ?";
	    String createBorrowingRecordQuery = "INSERT INTO Borrowing_History (BookID, MemberID, BorrowDate, DueDate, Status) "
	            + "VALUES (?, ?, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY), 'Borrowed')";
	    String updateBookAvailabilityQuery = "UPDATE Books SET Availability = 'Borrowed' WHERE BookID = ?";
	    String getBookIdQuery = "SELECT BookID FROM Borrowing_History WHERE BorrowID = ?";

	    try (Connection con = DatabaseConn.getConnection();
	         PreparedStatement markReturnedStmt = con.prepareStatement(markReturnedQuery);
	         PreparedStatement checkReservationStmt = con.prepareStatement(checkReservationQuery);
	         PreparedStatement fulfillReservationStmt = con.prepareStatement(fulfillReservationQuery);
	         PreparedStatement createBorrowingRecordStmt = con.prepareStatement(createBorrowingRecordQuery);
	         PreparedStatement updateBookAvailabilityStmt = con.prepareStatement(updateBookAvailabilityQuery);
	         PreparedStatement getBookIdStmt = con.prepareStatement(getBookIdQuery)) {

	        // Step 1: Get the BookID for the borrow record
	        getBookIdStmt.setInt(1, borrowId);
	        int bookId = -1;
	        try (ResultSet rs = getBookIdStmt.executeQuery()) {
	            if (rs.next()) {
	                bookId = rs.getInt("BookID");
	            } else {
	                return false; // BookID not found
	            }
	        }

	        // Step 2: Mark the book as returned
	        markReturnedStmt.setInt(1, borrowId);
	        markReturnedStmt.executeUpdate();

	        // Step 3: Check if the book is reserved
	        checkReservationStmt.setInt(1, bookId);
	        int reservedMemberId = -1;
	        try (ResultSet rs = checkReservationStmt.executeQuery()) {
	            if (rs.next()) {
	                reservedMemberId = rs.getInt("MemberID");
	            }
	        }

	        if (reservedMemberId != -1) {
	            // Step 4: Fulfill the reservation
	            fulfillReservationStmt.setInt(1, bookId);
	            fulfillReservationStmt.setInt(2, reservedMemberId);
	            fulfillReservationStmt.executeUpdate();

	            // Step 5: Create a borrowing record for the reserved member
	            createBorrowingRecordStmt.setInt(1, bookId);
	            createBorrowingRecordStmt.setInt(2, reservedMemberId);
	            createBorrowingRecordStmt.executeUpdate();

	            // Step 6: Update the book availability to "Borrowed"
	            updateBookAvailabilityStmt.setInt(1, bookId);
	            updateBookAvailabilityStmt.executeUpdate();
	        }

	        return true; // Operation completed successfully

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Return false if any operation fails
	    }
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
