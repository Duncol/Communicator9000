package com.duncol.connection;
import java.util.Calendar;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class WaitAndSendToAllFrom implements Runnable{
		private BufferedReader reader;
		
		public WaitAndSendToAllFrom(Socket clientSocket){
			try{
				reader = new BufferedReader(
						new InputStreamReader(
								clientSocket.getInputStream()));
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		public void run(){
			try{
				String message;
				System.out.println("Thread: waiting for messages to send...");
				while (true){
					message = reader.readLine();
					while (checkMsg(message)){
						System.out.println("Server: Sending message: " + message );
						sendToAll(message + getCurrentTime());
						message = "";
					}
				}
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		private boolean checkMsg(String msg){
			return msg != null && msg != ""; 
		}
		
		private String getCurrentTime(){
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
			String formattedDate = " [" + sdf.format(cal.getTime()) + "]";
			return formattedDate;
		}
		
		private void sendToAll(String message){
			List<PrintWriter> writers = ServerClientConnector.getWriters();
			for (PrintWriter pr : writers){
				pr.println(message + "\n");
				pr.flush();
			}
		}
	}