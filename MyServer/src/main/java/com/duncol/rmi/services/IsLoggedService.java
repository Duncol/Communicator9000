package com.duncol.rmi.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.duncol.rmi.RemoteService;

public class IsLoggedService extends UnicastRemoteObject implements RemoteService {
	private static final long serialVersionUID = 1L;
	private static IsLoggedService service;
	private final ArrayList<String> currentUsers;
	
	private IsLoggedService(String dbName) throws RemoteException{
		this.currentUsers = new ArrayList<String>();
	}
	
	public static void prepareServiceFor(String dbName){
		if (service == null){
			try{
				service = new IsLoggedService(dbName);
				service.addService();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}	
	}
	
	public boolean run(String user, char[] pass) throws RemoteException {
		for (String u : currentUsers){
			if (u.equalsIgnoreCase(user)){
				return true;
			}
		}
		currentUsers.add(user);
		return false;
	}

	private void addService(){
		try{
			Naming.rebind("islogged", this);
			System.out.println("IsLoggedService established!");
		}
		catch (MalformedURLException ex){
			ex.printStackTrace();
		}
		catch (RemoteException ex){
			ex.printStackTrace();
		}
	}
}
