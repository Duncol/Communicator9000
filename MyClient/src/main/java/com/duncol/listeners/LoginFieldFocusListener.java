package com.duncol.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

import javax.swing.JTextField;

public class LoginFieldFocusListener implements FocusListener{
	private String hint;
	private JTextField loginInput; 
	
	public LoginFieldFocusListener(JTextField loginInput){
		this.loginInput = loginInput;
		this.hint = "Login";
	}
	
	private boolean focusCheckText(){
		if (Objects.equals(loginInput.getText(), hint) || Objects.equals(loginInput.getText(), "")){
			return true;
		}
		return false;
	}

	public void focusGained(FocusEvent e){
		if (focusCheckText()){
			loginInput.setForeground(Color.BLACK);
			loginInput.setText("");
		}
	}
	public void focusLost(FocusEvent e){
		if (focusCheckText()){
			loginInput.setForeground(Color.GRAY);
			loginInput.setText(hint);
		}
	}
}
