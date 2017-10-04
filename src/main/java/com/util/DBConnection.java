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
					"jdbc:postgresql://ec2-54-83-58-17.compute-1.amazonaws.com.:5432/d84m9dg8otnkdk?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
					"agpalwhlbgugom", "a803ffae71a7dbed4ac0e17726fd9a073c9e29fbcbff7ef99c63b9e04f5b3e1e");
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