package com.duncol.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DerbyConnectionManager{
	private static Connection conn;
	private static String dbURL;
	private static String dbName;
	private final static String DRIVER;
	static{
		DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	}
	
	public static Connection getConnectionWith(String db){
		if (db != dbName){
			if (conn != null){
				closeConnection();
			}
			dbName = db;
			dbURL = "jdbc:derby:" + db + ";create=true;";
			
			try{
				Class.forName(DRIVER);
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
	
	public static void closeConnection(){
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
