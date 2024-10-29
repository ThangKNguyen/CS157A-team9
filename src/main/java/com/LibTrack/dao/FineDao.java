package com.LibTrack.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					String dueDate = rs.getDate("FinesDueDate").toString();
					String status = rs.getString("PaidStatus");

					FineItem item = new FineItem(title, amount, status, issuedDate, dueDate);
					fines.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fines;
	}
}
