package com.duncol.clients;

import com.duncol.guis.GUI;
import com.duncol.guis.LoginGUI;

public class ClientoUno implements ElCliento {
	private GUI loginGUI;
	
	public void run(){
		this.loginGUI = new LoginGUI();
		loginGUI.showFrame();
	}
}
