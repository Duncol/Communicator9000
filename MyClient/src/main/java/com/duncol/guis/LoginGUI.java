package com.duncol.guis;

import com.duncol.listeners.CreateUserMouseListener;
import com.duncol.listeners.LoginButtonActionListener;
import com.duncol.listeners.key.EnterPressedKeyListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class LoginGUI extends GUI{
	private JFrame frame;
	private JPanel topPanel;
	private JPanel botPanel;
	private JTextField loginInput;
	private JPasswordField passInput;
	private JButton loginButton;
	private JLabel createUserLabel;
	
	public LoginGUI(){
		establishGUI("Login to: Communicator9000");
	}
	
	@Override
	void createFrame(String frameTitle){
		this.frame = new JFrame(frameTitle);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	void createPanels() {
		this.topPanel = new JPanel();
		this.botPanel = new JPanel();
	}

	@Override
	void addElementsToPanels() {
		topPanel.add(loginInput);
		topPanel.add(passInput);
		botPanel.add(loginButton);
		botPanel.add(createUserLabel);
	}

	@Override
	void createElements() {
		loginInput = createLoginFieldWithHint();
		passInput = createPassFieldWithHint("Password");
		loginButton = makeLoginButton();
		createUserLabel = makeCreateUserLabel();
	}

	@Override
	void addPanels() {
		frame.add(topPanel, BorderLayout.CENTER);
		frame.add(botPanel, BorderLayout.PAGE_END);
	}
	
	@Override
	void setFrame(){
		frame.setSize(320, 115);
	}
	
	@Override
	public void showFrame(){
		frame.setVisible(true);
	}
	
	private JLabel makeCreateUserLabel(){
		JLabel createUser = new JLabel("Create new user...");
		createUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createUser.addMouseListener(new CreateUserMouseListener());
		return createUser;
	}

	private JButton makeLoginButton() {
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginButtonActionListener(this));
		return loginButton;
	}
	
	public void closeFrame(){
		this.frame.dispose();
	}
	
	public String getLogin(){
		return loginInput.getText();
	}
	
	public char[] getPassword(){
		return passInput.getPassword();
	}
	
	public void resetLoginField(){
		this.loginInput.setForeground(Color.GRAY);
		this.loginInput.setText("Login");
	}
	
	public void resetPassField(){
		this.passInput.setForeground(Color.GRAY);
		this.passInput.setEchoChar((char)0);
		this.passInput.setText("Password");
	}
	
	public void resetAllFields(){
		resetLoginField();
		resetPassField();
	}
}