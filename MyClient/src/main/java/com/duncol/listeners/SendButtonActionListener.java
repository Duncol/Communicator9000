package com.duncol.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JTextField;

import com.duncol.guis.ChatGUI;

public class SendButtonActionListener implements ActionListener{
	private String user;
	private PrintWriter writer;
	private JTextField msgInput;
	
	public SendButtonActionListener(String usr, PrintWriter pr, JTextField mi){
		user = usr;
		writer = pr;
		msgInput = mi;
	}
	
	public void actionPerformed(ActionEvent event){
		writer.println(user + ": " + msgInput.getText());
		writer.flush();
		clearMessageInputField();
	}
	
	private void clearMessageInputField(){
		msgInput.setText("");
	}
}