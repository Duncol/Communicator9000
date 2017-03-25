package com.duncol.guis;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.duncol.runnable.IncomingMessageThread;
import com.duncol.clients.ConnectionManager;
import com.duncol.listeners.SendButtonActionListener;

public class ChatGUI extends GUI{
	private String user;
	private JFrame frame;
	private JPanel panel;
	private JTextArea msgArea;
	private JTextField msgInput;
	private PrintWriter writer;
	private BufferedReader reader;
	private ConnectionManager conMan;
	
	public ChatGUI(String user){
		this.user = user;
		this.conMan = new ConnectionManager("192.168.0.104", 8080);
		writer = conMan.getWriter();
		reader = conMan.getReader();
		establishGUI("Communicator9000 - Logged as: " + user);
		new Thread(new IncomingMessageThread(reader, msgArea))
						.start();
	}
	
	@Override
	void createFrame(String frameTitle){
		frame = new JFrame(frameTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	void createPanels() {
		panel = new JPanel();
	}
	
	@Override
	void createElements() {
		createMsgArea();
		createMsgInput();
	}

	private void createMsgInput() {
		msgInput = new JTextField(50);
	}

	private void createMsgArea() {
		msgArea = new JTextArea(10, 50);
		msgArea.setLineWrap(true);
		msgArea.setWrapStyleWord(true);
		msgArea.setEditable(false);
		addScrollBarToMessageArea();
	}

	private void addScrollBarToMessageArea() {
		JScrollPane scrollBar = new JScrollPane(this.msgArea);
		scrollBar.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	@Override
	void addElementsToPanels() {
		addMsgArea();
		addMsgInput();
		addButtons();
	}
	
	private void addMsgInput() {
		panel.add(msgInput);
	}

	private void addMsgArea() {
		panel.add(msgArea);
	}

	@Override
	void addPanels() {
		frame.add(panel);
	}

	@Override
	void setFrame() {
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	@Override
	public void showFrame(){
		frame.setVisible(true);
	}
	
	public String getUser() {
		return user;
	}

	public String getMsgInput() {
		return msgInput.getText();
	}

	public void addMessage(String m){
		msgArea.append(m);
	}

	private void addButtons() {
		makeSendButton();
	}

	private void makeSendButton() {
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(
				new SendButtonActionListener(user, writer, msgInput));
		panel.add(sendButton);
		panel.add(this.msgArea);
	}
}