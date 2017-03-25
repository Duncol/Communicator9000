package com.duncol.listeners.key;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;

import com.duncol.guis.CreateUserGUI;
import com.duncol.guis.GUI;
import com.duncol.listeners.CancelButtonActionListener;

public class EscPressedKeyListener implements KeyListener{
	ActionListener executor;

	public EscPressedKeyListener(GUI gui){
		if (gui instanceof CreateUserGUI){
			JDialog frame = ((CreateUserGUI) gui).getFrame();
			this.executor = new CancelButtonActionListener(frame);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			executor.actionPerformed(null);
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
