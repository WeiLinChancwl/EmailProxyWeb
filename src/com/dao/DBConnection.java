package com.dao;

import java.sql.*;

public class DBConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/emailsys?userUnicode=true&characterEncoding=UTF-8&"
					+ "user=root&password=123456";
	private static String drivername="com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(drivername);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(url);
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void free(Connection connection, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null)
						connection.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
