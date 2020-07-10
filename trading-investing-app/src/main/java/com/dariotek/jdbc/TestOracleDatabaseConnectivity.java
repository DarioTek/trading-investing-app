package com.dariotek.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestOracleDatabaseConnectivity {

	public static void main(String[] args) {
		
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME=XEPDB1)))";
            System.out.println("jdbcurl=" + dbURL);
            String strUserID = "trading_investing";
            String strPassword = "oracle";
            con=DriverManager.getConnection(dbURL,strUserID,strPassword);
            System.out.println("Connected to the database.");
            Statement stmt=con.createStatement();
            System.out.println("Executing query");
            ResultSet rs=stmt.executeQuery("SELECT 1 FROM DUAL");
            while(rs.next())
                System.out.println(rs.getInt("1"));
            con.close();
        }catch(Exception e){ System.out.println(e);}
        finally {
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }		

	}

}
