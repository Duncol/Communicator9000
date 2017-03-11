package com.duncol.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

import javax.swing.JPasswordField;

public class PassFieldFocusListener implements FocusListener{
	private String hint;
	private JPasswordField passInput;
	
	public PassFieldFocusListener(String hint, JPasswordField passInput){
		this.hint = hint;
		this.passInput = passInput;
	}
	
	private boolean focusCheck(){
		char[] emptyChar = new char[]{};
		char[] charHint = hint.toCharArray();
		char[] charInput = passInput.getPassword();
		if (Arrays.equals(charInput, charHint) || Arrays.equals(charInput, emptyChar)){
			Arrays.fill(charInput, '0');
			return true;
		}
		Arrays.fill(charInput, '0');
		return false;
	}
	
	public void focusGained(FocusEvent e){
		if (focusCheck()){
			passInput.setEchoChar('*');
			passInput.setForeground(Color.BLACK);
			passInput.setText("");
		}
	}
	public void focusLost(FocusEvent e){
		if (focusCheck()){
			passInput.setEchoChar((char)0);
			passInput.setForeground(Color.GRAY);
			passInput.setText(hint);
		}
	}
}
