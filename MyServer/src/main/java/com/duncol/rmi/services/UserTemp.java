package com.duncol.rmi.services;

import com.duncol.security.PasswordAssistant;

public class UserTemp {
	private final String name;
	private final String hash;
	private final String salt;
	
	public UserTemp(String name, String hash, String salt) {
		this.name = name;
		this.hash = hash;
		this.salt = salt;
	}

	public String getName() {
		return name;
	}
	
	public String getHash() {
		return hash;
	}

	public String getSalt() {
		return salt;
	}
}
