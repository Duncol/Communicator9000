package com.duncol.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Arrays;

import javax.swing.JOptionPane;
import com.duncol.guis.LoginGUI;
import com.duncol.guis.ChatGUI;
import com.duncol.rmi.RemoteService;

public class LoginButtonActionListener implements ActionListener{
	private LoginGUI lGUI;
	
	public LoginButtonActionListener(LoginGUI parentGUI){
		this.lGUI = parentGUI;
	}
	
	public void actionPerformed(ActionEvent event){
		tryEnterChat();
	}
	
	private void tryEnterChat(){
		String login = lGUI.getLogin();
		char[] password = lGUI.getPassword();
		if (passCheck(login, password)){
			safetyOverwrite(password);
			if (!isLogged(login)){
				lGUI.closeFrame();
				new ChatGUI(login);
			}
			else{
				showError("User with a given name is already logged!");
				lGUI.resetAllFields();
			}
		}
		else{
			showError("Wrong username or password. Try again");
			lGUI.resetPassField();
		}
	}
	
	private void showError(String message){
		JOptionPane.showMessageDialog(null, message);
		lGUI.resetPassField();
	}
	
	private boolean isLogged(String user){
		boolean result = true;
		try{
			RemoteService isLoggedService = 
					(RemoteService) Naming.lookup("rmi://192.168.0.104:1099/islogged");
			result = isLoggedService.run(user, null);
			return result;
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private void safetyOverwrite(char[] password) {
		Arrays.fill(password, '0');
	}
	
	private boolean passCheck(String user, char[] pass){
		boolean check = false;
		try{
			RemoteService passCheckService = 
					(RemoteService) Naming.lookup("rmi://192.168.0.104:1099/passcheck");
			check = passCheckService.run(user, pass);
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
		return check;
	}
}