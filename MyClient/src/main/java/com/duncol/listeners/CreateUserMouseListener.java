package com.duncol.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.duncol.guis.CreateUserGUI;

public class CreateUserMouseListener implements MouseListener{

	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		JLabel source = (JLabel) e.getSource();
		source.setForeground(Color.BLACK);
	}
	
	public void mouseEntered(MouseEvent e) {
		JLabel source = (JLabel) e.getSource();
		source.setForeground(Color.BLUE);
	}
	
	public void mouseClicked(MouseEvent e) {
		CreateUserGUI cug = new CreateUserGUI();
		cug.showFrame();
	}
}
