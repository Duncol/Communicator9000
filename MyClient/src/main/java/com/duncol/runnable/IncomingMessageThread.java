package com.duncol.runnable;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class IncomingMessageThread implements Runnable{
	private BufferedReader parentReader;
	private JTextArea parentMsgArea;
	
	public IncomingMessageThread(BufferedReader r, JTextArea ma){
		parentReader = r;
		parentMsgArea = ma;
	}
	
	public void run() {
		waitForMessages();
	}
	
	private void waitForMessages(){
		while (true){
			checkAndSend();
		}
	}
	
	private synchronized void checkAndSend(){
		String msg;
		try{
			msg = parentReader.readLine();
			while (checkMsg(msg)){
				System.out.println("Message recieved: '" + msg + "'");
				parentMsgArea.append(msg + "\n");
				msg = "";
			}	
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	private boolean checkMsg(String msg){
		return msg != null && !msg.equals("");
	}
}