package com.duncol.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelButtonActionListener implements ActionListener{
	private JDialog frame;
	
	public CancelButtonActionListener(JDialog frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent ae){
		frame.dispose();
	}
}
