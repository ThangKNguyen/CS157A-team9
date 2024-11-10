package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.ReviewItem;
import com.LibTrack.utils.DatabaseConn;

public class ReviewDao {
	public List<ReviewItem> getAllReviews() {
		String query = "SELECT " + "    Reviews.ReviewID AS reviewId, " + "    Books.Title AS bookTitle, "
				+ "    Members.Name AS memberName, " + "    Reviews.ReviewDate AS reviewDate, "
				+ "    Reviews.ReviewText AS reviewText " + "FROM " + "    LibTrack.Reviews " + "JOIN "
				+ "    LibTrack.Books ON Reviews.BookID = Books.BookID " + "JOIN "
				+ "    LibTrack.Members ON Reviews.MemberID = Members.MemberID;";

		List<ReviewItem> reviews = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int reviewId = rs.getInt("reviewId");
					String bookTitle = rs.getString("bookTitle");
					String memberName = rs.getString("memberName");
					String reviewDate = rs.getString("reviewDate"); // Consider rs.getDate() if you want a Date object
					String reviewText = rs.getString("reviewText");

					// Create a new ReviewItem object
					ReviewItem reviewItem = new ReviewItem(reviewId, bookTitle, memberName, reviewDate, reviewText);

					// Add it to the list
					reviews.add(reviewItem);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}
}
