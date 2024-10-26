package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.Author;
import com.LibTrack.utils.DatabaseConn;

public class AuthorDao {

	public String getAuthorById(int authorId) {
		String authorName = null;
		String query = "SELECT Name FROM LibTrack.Authors WHERE AuthorID = ?;";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				authorName = rs.getString("Name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorName;
	}

	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<>();
		String query = "SELECT AuthorID, Name FROM LibTrack.Authors;";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("AuthorID"));
				author.setName(rs.getString("Name"));
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}
}