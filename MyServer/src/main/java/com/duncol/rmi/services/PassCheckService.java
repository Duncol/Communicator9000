package com.duncol.rmi.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.duncol.rmi.RemoteService;
import com.duncol.security.PasswordAssistant;
import com.duncol.sql.UsersDAO;

public class PassCheckService extends UnicastRemoteObject implements RemoteService{
	private static PassCheckService passService;
	private UsersDAO userDAO;
	private PasswordAssistant passAss;
	
	public PassCheckService(String dbName) throws RemoteException{
		super(0);
		this.userDAO = UsersDAO.getDAO(dbName);
		this.passAss = new PasswordAssistant("PBKDF2WithHmacSHA1");
	}
	
	public static void prepareServiceFor(String dbName){
		if (passService == null){
			try{
				passService = new PassCheckService(dbName);
				passService.addService();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	
	public boolean run(String userName, char[] pass){
		boolean check = false;
		try{
			String[] saltHash = userDAO.getSaltHash(userName);
			check = passAss.validatePassword(pass, saltHash);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return check;
	}
	
	private void addService(){
		try{
			Naming.rebind("passcheck", this);
			System.out.println("PassCheckService established!");
		}
		catch (MalformedURLException ex){
			ex.printStackTrace();
		}
		catch (RemoteException ex){
			ex.printStackTrace();
		}
	}
}