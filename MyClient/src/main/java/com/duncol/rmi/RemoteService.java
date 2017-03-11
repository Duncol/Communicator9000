package com.duncol.rmi;

import java.rmi.Remote;

public interface RemoteService extends Remote{
	public boolean run(String user, char[] pass);
}
