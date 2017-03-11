package com.duncol.guis;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.duncol.listeners.LoginFieldFocusListener;
import com.duncol.listeners.PassFieldFocusListener;

public abstract class GUI {
	private JFrame frame;
	
	final void establishGUI(String frameTitle){
		createFrame(frameTitle);
		createPanels();
		createElements();
		addElementsToPanels();
		addPanels();
		setFrame();
		showFrame();
	}
	
	abstract void createFrame(String frameTitle);
	
	abstract void createPanels();
	
	abstract void createElements();

	abstract void addElementsToPanels();
	
	abstract void addPanels();
	
	abstract void setFrame();
	
	abstract public void showFrame();
	
	JTextField createLoginFieldWithHint(){
		JTextField field = new JTextField("Login", 20);
		field.setForeground(Color.GRAY);
		field.addFocusListener(new LoginFieldFocusListener(field));
		return field;
	}
	
	JPasswordField createPassFieldWithHint(String hint){
		JPasswordField field = new JPasswordField(hint, 20);
		field.setForeground(Color.GRAY);
		field.setEchoChar((char)0);
		field.addFocusListener(new PassFieldFocusListener(hint, field));
		return field;
	}
}