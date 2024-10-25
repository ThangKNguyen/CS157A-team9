package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.Category;
import com.LibTrack.utils.DatabaseConn;

public class CategoryDao {
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();
		String query = "SELECT * FROM LibTrack.Categories";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				categories.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public String getCategoryById(int id) {
		String query = "SELECT CategoryName from LibTrack.Categories WHERE CategoryID = ?";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("CategoryName");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
