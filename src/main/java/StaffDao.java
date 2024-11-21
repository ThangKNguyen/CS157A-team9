package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import com.LibTrack.models.Staff;
//import com.LibTrack.utils.DatabaseConn;

public class StaffDao {

	public Staff insert(Staff staff) {

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		String sql = "insert into LibTrack.Staff (Name, Password, Role, Email, JoinDate) VALUES(?, ?, ?, ?, ?)";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getPassword());
			ps.setString(3, staff.getRole());
			ps.setString(4, staff.getEmail());
			ps.setString(5, formattedDate);
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
				Staff staff = new Staff(rs.getInt("MemberID"), rs.getString("Name"),
						rs.getString("Password"),rs.getString("Role"), rs.getString("Email"), rs.getString("JoinDate"), rs.getString("PhoneNumber"));
				return staff;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Staff validateStaff(String email, String password) {
	    String query = "SELECT * FROM LibTrack.Members WHERE Email = ? AND Password = ?";

	    try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, email);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            // Include joinDate
	            Staff staff = new Staff(
	                rs.getInt("StaffID"),
	                rs.getString("Name"),
	                rs.getString("Password"),
	                rs.getString("Role"),
	                rs.getString("Email"),
	                rs.getString("JoinDate"),
	                rs.getString("PhoneNumber")  // New field
	            );
	            return staff;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}