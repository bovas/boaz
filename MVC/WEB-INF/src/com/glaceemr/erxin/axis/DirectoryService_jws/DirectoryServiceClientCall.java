package com.glaceemr.erxin.axis.DirectoryService_jws;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class DirectoryServiceClientCall {
	public static void main(String[] args) throws RemoteException, ServiceException {
		DirectoryServiceServiceLocator locator = new DirectoryServiceServiceLocator();
		String encodedValue = locator.getDirectoryService().getEncodedCredentials();
		System.out.println(encodedValue);
	}
}
