package com.duncol.main;

import com.duncol.clients.ClientoUno;
import com.duncol.clients.ElCliento;

public class ClientMain {
	public static void main(String[] args) {
		ElCliento client = new ClientoUno();
		client.run();
	}
}