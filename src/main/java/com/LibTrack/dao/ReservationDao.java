package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.ReservationItem;
import com.LibTrack.utils.DatabaseConn;

public class ReservationDao {
	public List<ReservationItem> getReservationsByMemberId(int memberId) {
		String query = "SELECT Books.Title AS BookTitle, " + "Reservations.ReservationDate, " + "Reservations.Status "
				+ "FROM LibTrack.Reservations " + "JOIN LibTrack.Books ON Reservations.BookID = Books.BookID "
				+ "WHERE Reservations.MemberID = ?;";
		List<ReservationItem> reservations = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String title = rs.getString("BookTitle");
					String reservationDate = rs.getString("ReservationDate");
					String status = rs.getString("Status");

					ReservationItem item = new ReservationItem(title, reservationDate, status);
					reservations.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
    public boolean createReservation(int memberId, int bookId) {
        String insertQuery = "INSERT INTO Reservations (BookID, MemberID, ReservationDate, Status) VALUES (?, ?, NOW(), 'Active')";
        String updateBookQuery = "UPDATE Books SET Availability = 'Reserved' WHERE BookID = ? AND Availability = 'Borrowed'";

        try (Connection con = DatabaseConn.getConnection();
             PreparedStatement insertStmt = con.prepareStatement(insertQuery);
             PreparedStatement updateStmt = con.prepareStatement(updateBookQuery)) {

            // Update book availability
            updateStmt.setInt(1, bookId);
            int rowsUpdated = updateStmt.executeUpdate();

            // If book availability updated successfully, create reservation
            if (rowsUpdated > 0) {
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, memberId);
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if operation fails
    }
    
    public int getActiveReservationMember(int bookId) {
        String query = "SELECT MemberID FROM Reservations WHERE BookID = ? AND Status = 'Active'";
        try (Connection con = DatabaseConn.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MemberID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // No active reservation found
    }

    public boolean fulfillReservation(int bookId, int memberId) {
        String query = "UPDATE Reservations SET Status = 'Fulfilled' WHERE BookID = ? AND MemberID = ?";
        try (Connection con = DatabaseConn.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            return ps.executeUpdate() > 0; // Return true if at least one row updated
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if the operation fails
    }

}

