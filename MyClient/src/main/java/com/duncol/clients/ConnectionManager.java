package com.duncol.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public ConnectionManager(String URL, int port){
		tryConnectToServer(URL, port);
	}
	
	public BufferedReader getReader() {
		return reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}
	
	private void tryConnectToServer(String ip, int servSocket) {
		try{
			socket = new Socket(ip, servSocket);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
		}
		catch (IOException ex){
			System.out.println("Unable to connect to specified server. Code pink");
			ex.printStackTrace();
		}
	}

	public void closeConnection() {
		try{
			socket.close();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
