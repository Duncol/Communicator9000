package com.duncol.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.duncol.rmi.services.CreateUserService;
import com.duncol.rmi.services.IsLoggedService;
import com.duncol.rmi.services.PassCheckService;

public class RMIManager {
	private static RMIManager manager;
	
	private RMIManager(){
		launchRMIRegistry();
		launchRMIServices();
	}
	
	public static void launchRMI(){
		if (manager == null){
			manager = new RMIManager();
		}
		else{
			System.out.println("RMI registry and services are already initiated!");
		}
	}
	
	private void launchRMIRegistry(){
		try{
			LocateRegistry.createRegistry(1099);
			System.out.println("Registry created!");
		}
		catch (RemoteException ex){
			System.out.println("Cannot create RMI registry");
		}
	}
	
	private void launchRMIServices(){
		PassCheckService.prepareServiceFor("chatusers");
		CreateUserService.prepareServiceFor("chatusers");
		IsLoggedService.prepareServiceFor("chatusers");
	}
}
