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
			enableAccess(login);
			safetyOverwrite(password);
			lGUI.closeFrame();
			new ChatGUI(login);
		}
		else{
			JOptionPane.showMessageDialog(null, "Wrong username or password. Try again");
			lGUI.clearPassInput();
		}
	}
	
	private void safetyOverwrite(char[] password) {
		Arrays.fill(password, '0');
	}

	private void enableAccess(String login) {
		lGUI.setUser(login);
		lGUI.setAccess(true);
	}

	private boolean passCheck(String user, char[] pass){
		boolean check = false;
		try{
			RemoteService passCheckService = 
					(RemoteService) Naming.lookup("rmi://127.0.0.1:1099/passcheck");
			check = passCheckService.run(user, pass);
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
		return check;
	}
}