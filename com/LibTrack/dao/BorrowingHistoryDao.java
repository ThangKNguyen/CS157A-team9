package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.BorrowingHistoryItem;
import com.LibTrack.utils.DatabaseConn;

public class BorrowingHistoryDao {
	public List<BorrowingHistoryItem> getBorrowingHistoryByMemberId(int memberId) {
		String query = "SELECT bh.BorrowID, b.Title, bh.BorrowDate, bh.DueDate, bh.ReturnDate, bh.Status "
				+ "FROM LibTrack.Borrowing_History bh " + "JOIN Books b ON bh.BookID = b.BookID "
				+ "WHERE bh.MemberID = ?";
		List<BorrowingHistoryItem> borrowingHistory = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int borrowId = rs.getInt("BorrowID");
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

					BorrowingHistoryItem item = new BorrowingHistoryItem(borrowId, title, borrowDate, dueDate,
							returnDate, status);
					borrowingHistory.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowingHistory;
	}
}
