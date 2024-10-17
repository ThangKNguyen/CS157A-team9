package com.LibTrack.dao;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.LibTrack.models.Member;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	private String dburl="jdbc:mysql://localhost:3306/LibTrack";
	private String dbuname="root";
	private String dbpassword="CS157A@SJSU";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbdriver) {
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuname, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	public String insert(Member member) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		String result = "data entered successfully";

		// Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

		String sql = "insert into LibTrack.Members (Name, Email, Password, PhoneNumber, Address, JoinDate) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getAddress());
			ps.setString(6, formattedDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			result = "Data not entered";
			e.printStackTrace();
		}
		return result;
	}
	public Member getMemberByEmailAndPassword(String email, String password) {
		loadDriver(dbdriver);
		Connection con = getConnection();
		
		String query = "SELECT * FROM LibTrack.Members WHERE Email = ? AND Password = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// get member attributes
				Member member = new Member(rs.getString("Name"), rs.getString("Email"), rs.getString("Password"), rs.getString("PhoneNumber"), rs.getString("Address"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
