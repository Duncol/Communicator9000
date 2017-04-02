package com.duncol.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.duncol.guis.CreateUserGUI;
import com.duncol.rmi.RemoteService;

public class CreateButtonActionListener implements ActionListener{
	private CreateUserGUI parentGUI;
	
	public CreateButtonActionListener(CreateUserGUI parentGUI){
		this.parentGUI = parentGUI;
	}
	
	public void actionPerformed(ActionEvent event){
		String login = parentGUI.getLogin();
		char[] firstPass = parentGUI.getPass();
		char[] secondPass = parentGUI.getVerifyPass();
		if (checkPasswords(firstPass, secondPass)){
			createUser(login, firstPass);
		}
		else{
			JOptionPane.showMessageDialog(
						null, 
						"These two passwords need to be identical, you hear me?", 
						"Error", 
						JOptionPane.ERROR_MESSAGE);
		}
		flushPasswords(firstPass, secondPass);
	}
	
	private boolean checkPasswords(char[] firstPass, char[] secondPass){
		return Arrays.equals(firstPass, secondPass);
	}
	
	private void createUser(String user, char[] pass){
		try{
			RemoteService createUserService = 
					(RemoteService) Naming.lookup("rmi://192.168.0.104:1099/createuser");
			boolean result = createUserService.run(user, pass);
			if (result == false){
				JOptionPane.showMessageDialog(
						null,
						"Unable to create user. Try other user name",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(
						null,
						"User '" + user + "' created successfuly!",
						"Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
			parentGUI.resetAllFields();
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
	}
	
	private void flushPasswords(char[] fp, char[] sp){
		Arrays.fill(fp, '0');
		Arrays.fill(sp, '0');
	}
}