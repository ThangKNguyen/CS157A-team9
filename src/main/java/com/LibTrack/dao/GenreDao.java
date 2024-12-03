package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.Genre;
import com.LibTrack.utils.DatabaseConn;

public class GenreDao {
	public List<Genre> getAllGenres() {
		List<Genre> genres = new ArrayList<>();
		String query = "SELECT * FROM LibTrack.Genres";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				genres.add(new Genre(rs.getInt("GenreID"), rs.getString("GenreName")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

	public String getGenreById(int id) {
		String query = "SELECT GenreName from LibTrack.Genres WHERE GenreID = ?";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("GenreName");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
