package com.duncol.listeners.key;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.duncol.guis.CreateUserGUI;
import com.duncol.guis.GUI;
import com.duncol.guis.LoginGUI;
import com.duncol.listeners.CreateButtonActionListener;
import com.duncol.listeners.LoginButtonActionListener;

public class EnterPressedKeyListener implements KeyListener{
	ActionListener executor;

	public EnterPressedKeyListener(GUI gui){
		if (gui instanceof LoginGUI){
			this.executor = new LoginButtonActionListener((LoginGUI) gui);
		}
		else if (gui instanceof CreateUserGUI){
			this.executor = new CreateButtonActionListener((CreateUserGUI) gui);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
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
