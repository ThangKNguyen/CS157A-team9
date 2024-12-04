package com.LibTrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.LibTrack.models.Member;
import com.LibTrack.utils.DatabaseConn;

public class MemberDao {

	public Member insert(Member member) {

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
			e.printStackTrace();
			return null;
		}
		return member;
	}
	
	public List<Member> getMembers() {
		String query = "SELECT * FROM LibTrack.Members WHERE MemberID = ?";
		List<Member> members = new ArrayList<>();
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("Name");
				String email = rs.getString("Email");
				String password = rs.getString("Password");
				String phone = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				String date = rs.getString("JoinDate");
				members.add(new Member(name, email, password, phone, address, date));
				return members;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member getMemberById(int id) {
		String query = "SELECT * FROM LibTrack.Members WHERE MemberID = ?";
		try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Member member = new Member(rs.getInt("MemberID"), rs.getString("Name"), rs.getString("Email"),
						rs.getString("Password"), rs.getString("PhoneNumber"), rs.getString("Address"), rs.getString("JoinDate"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member validateMember(String email, String password) {
	    String query = "SELECT * FROM LibTrack.Members WHERE Email = ? AND Password = ?";

	    try (Connection con = DatabaseConn.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, email);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            // Include joinDate
	            Member member = new Member(
	                rs.getInt("MemberID"),
	                rs.getString("Name"),
	                rs.getString("Email"),
	                rs.getString("Password"),
	                rs.getString("PhoneNumber"),
	                rs.getString("Address"),
	                rs.getString("JoinDate")  // New field
	            );
	            return member;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
