package com.duncol.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.duncol.containers.InputFieldsContainer;
import com.duncol.rmi.RemoteService;

public class CreateButtonActionListener implements ActionListener{
	private InputFieldsContainer inputFields;
	
	public CreateButtonActionListener(InputFieldsContainer container){
		this.inputFields = container;
	}
	
	public void actionPerformed(ActionEvent event){
		String login = inputFields.getLogin();
		char[] firstPass = inputFields.getPass();
		char[] secondPass = inputFields.getSecondPass();
		if (checkPasswords(firstPass, secondPass)){
			createUser(login, firstPass);
		}
		else{
			JOptionPane.showMessageDialog(null, 
						"These two passwords need to be identical, you hear me?", 
						"Error: Create user", 
						JOptionPane.ERROR_MESSAGE);
		}
		Arrays.fill(firstPass, '0');
		Arrays.fill(secondPass, '0');
	}
	
	private boolean checkPasswords(char[] firstPass, char[] secondPass){
		return Arrays.equals(firstPass, secondPass);
	}
	
	private void createUser(String user, char[] pass){
		try{
			RemoteService createUserService = 
					(RemoteService) Naming.lookup("rmi://127.0.0.1:1099/createuser");
			createUserService.run(user, pass);
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
	}
}