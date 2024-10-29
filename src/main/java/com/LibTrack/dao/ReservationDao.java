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
}
