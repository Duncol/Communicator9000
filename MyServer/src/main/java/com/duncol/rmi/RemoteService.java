package com.duncol.rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public interface RemoteService extends Remote{
	public boolean run(String user, char[] pass) throws RemoteException;
}
