package com.LibTrack.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
	private static String dburl = "jdbc:mysql://localhost:3306/LibTrack";
	private static String dbuname = "root";
	private static String dbpassword = "MP5wL+r+L"; //CS157A@SJSU

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, dbuname, dbpassword);

	}

}
