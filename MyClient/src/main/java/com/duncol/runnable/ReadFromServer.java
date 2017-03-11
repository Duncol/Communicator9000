package com.duncol.runnable;

import java.io.IOException;

import com.duncol.clients.ConnectionManager;
import com.duncol.guis.ChatGUI;
import com.duncol.guis.LoginGUI;

class ReadFromServer implements Runnable{
	private ChatGUI chat;
	private ConnectionManager conMan;
	
	public void run(){
		String inputMessage;
		System.out.println("New Thread started");
		try{
			while (true){
				while ((inputMessage = conMan.getReader().readLine()) != null){
					chat.addMessage(inputMessage);
				}
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
}