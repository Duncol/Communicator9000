package com.duncol.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerClientConnector {
	private static final List<PrintWriter> writers = new ArrayList<PrintWriter>();
	private ServerSocket servSocket;

	public static List<PrintWriter> getWriters(){
		return writers;
	}
	
	public ServerClientConnector(int port) {
		try{
			this.servSocket = new ServerSocket(port);
			System.out.println("Server socket established");
		}
		catch (IOException ex){
			System.out.println("Unable to establish server socket. Code pink \n");
			ex.printStackTrace();
		}
	}
	
	public void waitForClients(){
		System.out.println("The gates has been opened...");
		while (true){
			try {
				Socket client = servSocket.accept();
				processClient(client);
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void processClient(Socket client){
		writers.add(getClientWriter(client));
		startUpdateFrom(client);
		System.out.println("New client connected: " + client.getPort());
	}

	private PrintWriter getClientWriter(Socket client){
		try{
			return new PrintWriter(client.getOutputStream());
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	private void startUpdateFrom(Socket client){
		new Thread(
				new WaitAndSendToAllFrom(client))
					.start();
	}
	
	private void disposeWriters() {
		for (PrintWriter s : writers){
			s.close();
		}
	}
}