package com.dariotek.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestOracleInsert {

	public static void main(String[] args) {
		Connection conn = null;
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME=XEPDB1)))";
            System.out.println("jdbcurl=" + dbURL);
            String strUserID = "trading_investing";
            String strPassword = "oracle";
            conn=DriverManager.getConnection(dbURL,strUserID,strPassword);

            Statement st = conn.createStatement(); 
            st.executeUpdate("INSERT INTO WATCH_LIST VALUES ('MY_LIST', 'DIS')"); 

            conn.close(); 
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 

	}

}
