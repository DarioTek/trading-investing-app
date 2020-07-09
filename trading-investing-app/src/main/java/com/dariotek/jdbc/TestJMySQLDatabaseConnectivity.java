package com.dariotek.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJMySQLDatabaseConnectivity {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/trading_investing?useSSL=false";
		//String user = "root";
		String user = "dariotek";
		String password = "password";
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Connection successful!");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
