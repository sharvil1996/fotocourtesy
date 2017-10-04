package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:postgresql://ec2-107-22-235-167.compute-1.amazonaws.com:5432/d8ldkj1qams63b?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
					"dnycfpxxjiojfx", "c21683de800f16f5159d1c6ef825a848c84734065f02b23440ffc1faf29b6475");
			// Connection conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/photography",
			// "root", "root");
			if (conn != null) {

				return conn;
			}
		} catch (SQLException e) {
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
