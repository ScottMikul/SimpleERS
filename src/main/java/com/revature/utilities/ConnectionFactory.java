package com.revature.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	private static Connection conn;
	public static Connection getConnection() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try {
			conn = DriverManager.getConnection(
					System.getenv("awsdb"),
					System.getenv("dbname") ,
					System.getenv("dbpass")
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(System.getenv("awsdb"));
		System.out.println(System.getenv("dbname"));
		System.out.println(System.getenv("dbpass"));


			//System.out.println(conn.createStatement());
		try {
			conn = ConnectionFactory.getConnection();
			final String SQL = "select * from employee";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(SQL);
			res.next();
			System.out.println(res.getString(2));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
