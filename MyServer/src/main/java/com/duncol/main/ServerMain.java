package com.duncol.main;

import com.duncol.connection.ServerClientConnector;
import com.duncol.rmi.RMIManager;

public class ServerMain {
	private static ServerClientConnector connector;
	static{
		connector = new ServerClientConnector(8081);
	}
	
	public static void main(String[] args) {
		ServerMain.runServer();
	}
	
	private static void runServer() {
		RMIManager.launchRMI();
		connector.waitForClients();
	}
}
