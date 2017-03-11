package com.duncol.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DerbyConnectionManager{
	private static Connection conn;
	private static String dbURL;
	private static String driver;
	
	public static Connection getConnectionWith(String dbName){
		if (conn == null){
			dbURL = "jdbc:derby:" + dbName + ";create=true;";
			driver = "org.apache.derby.jdbc.EmbeddedDriver";
			try{
				Class.forName(driver);
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				conn = DriverManager.getConnection(dbURL);
				System.out.println("Derby SQL connection successfuly established");
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return conn;
	}
	
	private static void closeConnection(){
		try{
			if (!conn.isClosed()){
				conn.close();
			}
			if (conn != null){
				conn = null;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
