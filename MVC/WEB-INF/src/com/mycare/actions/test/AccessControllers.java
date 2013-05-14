package com.mycare.actions.test;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;


public class AccessControllers {
	  static long getLastModified(final File f) {
	      return ((Long)
	              AccessController.doPrivileged(new PrivilegedAction() {
	                  public Object run() {
	                      return new Long(f.lastModified());
	                  }
	              })).longValue();
	  }
	  public static void main(String[] args) {
		  System.out.println(new File("/home/bovas/Desktop/Scrollex1.html").lastModified());
		System.out.println(getLastModified(new File("/home/bovas/Desktop/Scrollex1.html")));
	}
	  
	}
