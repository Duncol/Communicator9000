package com.duncol.rmi.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.duncol.rmi.RemoteService;
import com.duncol.security.PasswordAssistant;
import com.duncol.sql.UsersDAO;

public class PassCheckService extends UnicastRemoteObject implements RemoteService{
	private static final long serialVersionUID = 1L;
	private static PassCheckService service;
	private final UsersDAO usersDAO;
	private final PasswordAssistant passAss;
	
	private PassCheckService(String dbName) throws RemoteException{
		super(0);
		this.usersDAO = UsersDAO.getDAO(dbName);
		this.passAss = new PasswordAssistant("PBKDF2WithHmacSHA1");
	}
	
	public static void prepareServiceFor(String dbName){
		if (service == null){
			try{
				service = new PassCheckService(dbName);
				service.addService();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	
	public boolean run(String userName, char[] pass){
		boolean check = false;
		try{
			String[] saltHash = usersDAO.getSaltHash(userName);
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