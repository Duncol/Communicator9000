package com.duncol.guis;

import com.duncol.containers.InputFieldsContainer;
import com.duncol.listeners.CancelButtonActionListener;
import com.duncol.listeners.CreateButtonActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateUserGUI extends GUI{
	private JDialog frame;
	private JPanel topPanel;
	private JPanel botPanel;
	private JTextField loginInput;
	private JPasswordField passInput;
	private JPasswordField verifyPassInput;
	private JButton createButton;
	private JButton cancelButton;
	
	public CreateUserGUI(){
		establishGUI("Create user: Communicator9000");
	}
	
	@Override
	void createFrame(String frameTitle){
		this.frame = new JDialog(new JFrame(), frameTitle, true);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@Override
	void createPanels(){
		this.topPanel = new JPanel();
		this.botPanel = new JPanel(new FlowLayout());
	}
	
	@Override
	void createElements() {
		this.loginInput = createLoginFieldWithHint();
		this.passInput = createPassFieldWithHint("Password");
		this.verifyPassInput = createPassFieldWithHint("Repeat Password");
		this.createButton = makeCreateButton();
		this.cancelButton = makeCancelButton();
	}

	@Override
	void addElementsToPanels() {
		topPanel.add(this.loginInput);
		topPanel.add(this.passInput);
		topPanel.add(this.verifyPassInput);
		botPanel.add(this.createButton);
		botPanel.add(this.cancelButton);
	}

	@Override
	void addPanels(){
		frame.add(topPanel, BorderLayout.CENTER);
		frame.add(botPanel, BorderLayout.PAGE_END);
	}
	
	@Override
	void setFrame(){
		frame.setSize(320, 140);
	}
	
	@Override
	public void showFrame(){
		frame.setVisible(true);
	}
	
	private JButton makeCancelButton() {
		JButton loginButton = new JButton("Cancel");
		loginButton.addActionListener(new CancelButtonActionListener(this.frame));
		return createButton;
	}
	
	private JButton makeCreateButton(){
		JButton createButton = new JButton("Create");
  		createButton.addActionListener(makeCreateButtonListener());
  		return createButton;
	}
	
	public void addMessage(String m){
		// GUI interface necessary "implementation" (a.k.a nothing)
	}
	
	private InputFieldsContainer packInputFields(){
		return new InputFieldsContainer(loginInput, passInput, verifyPassInput);
	}
	
	private CreateButtonActionListener makeCreateButtonListener(){
		return new CreateButtonActionListener(packInputFields());
	}
}
