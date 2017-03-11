package com.duncol.factories;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIElementsFactory {
	private String user;
	private boolean access;
	private JFrame frame;
	private JPanel topPanel;
	private JPanel botPanel;
	private JTextField loginInput;
	private JPasswordField passInput;
	private JButton loginButton;
	private JLabel createUserOption;
	
	public JFrame makeFrame(){
		return null;
	}
	
	public JPanel makePanel(){
		return null;
	}
	
	public JTextField makeTextField(){
		return null;
	}
	
	public JPasswordField makePassField(){
		return null;
	}
	
	public JButton makeButton(){
		return null;
	}
	
	public JLabel makeLabel(){
		return null;
	}
}
