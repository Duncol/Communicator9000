package com.duncol.rmi.services;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import com.duncol.rmi.RemoteService;
import com.duncol.security.PasswordAssistant;
import com.duncol.sql.DAO;
import com.duncol.sql.UsersDAO;

public class CreateUserService extends UnicastRemoteObject implements RemoteService{
	private static final long serialVersionUID = 1L;
	private static CreateUserService service;
	private final UsersDAO usersDAO;
	private final PasswordAssistant passAss;
	
	private CreateUserService(String dbName) throws RemoteException{
		super(0);
		this.usersDAO = UsersDAO.getDAO(dbName);
		this.passAss = new PasswordAssistant("PBKDF2WithHmacSHA1");
	}
	
	public static synchronized CreateUserService prepareServiceFor(String dbName){
		if (service == null){
			try{
				service = new CreateUserService(dbName);
				service.addToRegistry();
			}
			catch (RemoteException ex){
				ex.printStackTrace();
			}
		}
		return service;
	}
	
	private void addToRegistry() {
		try{
			Naming.rebind("createuser", this);
			System.out.println("CreateUserService established!");
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public boolean run(String name, char[] pass) throws RemoteException{
		try{
			String[] saltHash = passAss.createHashWithSalt(pass);
			String salt = saltHash[1];
			String hash = saltHash[0];
			boolean status = usersDAO.createUser(name, salt, hash);
			return status;
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
