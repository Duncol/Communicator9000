package com.duncol.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAO{
	private final String nameColumn = "Name";
	private final String hashedPassColumn = "HashedPass";
	private final String passSaltColumn = "PassSalt";
	
	private static UsersDAO DAO;
	private Connection con;
	
	//toSpring();
	private UsersDAO(String dbName){
		initConnection(dbName);
	}

	private void initConnection(String dbName) {
		try{
			this.con = DerbyConnectionManager.getConnectionWith(dbName);
			//dropTable("Users");
			createUsersTable("Users");
		}
		catch (Exception ex){
			System.out.println();
		}
	}
	
	public static synchronized UsersDAO getDAO(String dbName){
		if (DAO == null){
			DAO = new UsersDAO(dbName);
		}
		return DAO;
	}
	
	private void createUsersTable(String tableName){
		try{
			PreparedStatement ps = this.con.prepareStatement(
											"CREATE TABLE " + tableName + "("
										+ " Name VARCHAR(30) PRIMARY KEY,"
										+ " HashedPass VARCHAR(48) NOT NULL,"
										+ " PassSalt VARCHAR(48) NOT NULL)");
			ps.execute();
			System.out.println("Table '" + tableName + "' created");
		}
		catch (SQLException ex){
			if (ex.getSQLState().equals("X0Y32")){
				System.out.println(
					"Table with a given name already exists. Let me use it");
			}
			else{
				ex.printStackTrace();
			}
		}
	}
	
	public boolean createUser(String name, String salt, String hashPass){
		try{
			PreparedStatement ps = this.con.prepareStatement(
					"INSERT INTO Users(" 
							+ nameColumn + ", " + hashedPassColumn + ", " + passSaltColumn + ")"
							+ " VALUES (?, ?, ?)");
			ps.setString(1, name.toLowerCase());
			ps.setString(2, hashPass);
			ps.setString(3, salt);
			ps.execute();
			System.out.println("User " + name + " created successfuly!");
			return true;
		}
		catch (SQLException ex){
			if (ex.getSQLState().equals("23505")){
				System.out.println("User with a given name already exists");
			}
			else{
				ex.printStackTrace();
			}
			return false;
		}
	}
	
	public String[] getSaltHash(String userName){ 
		try{	
			String[] saltHash = new String[2];
			PreparedStatement ps = this.con.prepareStatement(
											"SELECT HashedPass, PassSalt"
										+ " FROM Users"
										+ " WHERE Name = ?");
			ps.setString(1, userName.toLowerCase());
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()){
				saltHash[0] = rSet.getString("HashedPass");
				saltHash[1] = rSet.getString("PassSalt");
			}
			return saltHash;
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getAllUsernames(){
		ArrayList<String> users = new ArrayList<String>();
		try{
			PreparedStatement ps = this.con.prepareStatement(
									"SELECT Name"
								+ " FROM Users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				users.add(rs.getString(1));
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return users;
	}

	private void dropTable(String tableName){
		try{
			PreparedStatement ps = this.con.prepareStatement(
											"DROP TABLE " + tableName + "");
			ps.execute();
			System.out.println("Table '" + tableName + "' has ben droped");
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
	}
}
