package com.f313.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public final class JdbcUtils {

//	private final static Connection connection;
	
	private JdbcUtils() {

	}
/*
	static{
		connection = createConnection();
	}
	
	public static Connection getConnection(){
		return connection;
	}*/
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/air";
			String user = "root";
			String password = "123456789";
			//System.out.println(DriverManager.getConnection(url).toString());
			conn = DriverManager.getConnection(url, user, password);
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/air?"
             //               + "user=root & password=3.14159265cmcm");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void free(Statement st, Connection conn) {

		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
