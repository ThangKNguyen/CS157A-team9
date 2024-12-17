package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.LibTrack.models.Staff;
import com.LibTrack.utils.DatabaseConn;

public class StaffDao {

	public Staff insert(Staff staff) {

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		String sql = "insert into LibTrack.Staff (Name, Role, Email, HireDate, PhoneNumber, Password) VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getRole());
			ps.setString(3, staff.getEmail());
			ps.setString(4, formattedDate);
			ps.setString(5, staff.getPhone());
			ps.setString(6, staff.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		String sql2 = "insert into LibTrack.Members (Name, Email, Password, PhoneNumber, Address, JoinDate) VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(sql2)) {
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getEmail());
			ps.setString(3, staff.getPassword());
			ps.setString(4, staff.getPhone());
			ps.setString(5, staff.getAddress());
			ps.setString(6, formattedDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return staff;
	}

	public Staff getStaffById(int id) {
		String query = "SELECT * FROM LibTrack.Staff WHERE StaffID = ?";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff(rs.getInt("StaffID"), rs.getString("Name"), rs.getString("Password"),
						rs.getString("Role"), rs.getString("Email"), rs.getString("HireDate"));
				return staff;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Staff validateStaff(String email, String password) {
		String query = "SELECT * FROM LibTrack.Staff WHERE Email = ? AND Password = ?";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff(rs.getInt("StaffID"), rs.getString("Name"), rs.getString("Password"),
						rs.getString("Role"), rs.getString("Email"), rs.getString("HireDate"));
				return staff;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}