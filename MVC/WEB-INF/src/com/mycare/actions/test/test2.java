package com.mycare.actions.test;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Properties;

public class test2 {	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("hello how");
		System.out.println(sb.indexOf("how"));
		long poweredValue = (long)Math.pow(2, 30);
		long activeStatus=0;
		System.out.println(poweredValue);
		System.out.println((activeStatus&poweredValue)==poweredValue);
		 Properties sysProps = (Properties) AccessController.doPrivileged(
		            new PrivilegedAction() {
		                public Object run() {
		                    try {
		                        return System.getProperties();
		                    } catch (SecurityException e) {		                       
		                        return null;
		                    }
		                }
		            }
		        );
		 Enumeration e= sysProps.keys();
		 while(e.hasMoreElements()){
			 String key = e.nextElement().toString();
			 System.out.println(key+"---"+sysProps.get(key));
		 }
	}
}
