package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.LibTrack.models.Member;
import com.LibTrack.utils.DatabaseConn;

public class MemberDao {

	public String insert(Member member) {

		String result = "data entered successfully";
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		String sql = "insert into LibTrack.Members (Name, Email, Password, PhoneNumber, Address, JoinDate) VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
		String query = "SELECT * FROM LibTrack.Members WHERE Email = ? AND Password = ?";

		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// get member attributes
				Member member = new Member(rs.getString("Name"), rs.getString("Email"), rs.getString("Password"),
						rs.getString("PhoneNumber"), rs.getString("Address"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
