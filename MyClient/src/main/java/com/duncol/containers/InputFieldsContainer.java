package com.duncol.containers;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InputFieldsContainer {
	private JTextField loginInput;
	private JPasswordField passInput;
	private JPasswordField verifyPassInput;
	
	public InputFieldsContainer(JTextField l, JPasswordField p, JPasswordField vp){
		loginInput = l;
		passInput = p;
		verifyPassInput = vp;
	}
	
	public String getLogin(){
		return loginInput.getText();
	}
	
	public char[] getPass(){
		return passInput.getPassword();
	}
	
	public char[] getSecondPass(){
		return verifyPassInput.getPassword();
	}
}
