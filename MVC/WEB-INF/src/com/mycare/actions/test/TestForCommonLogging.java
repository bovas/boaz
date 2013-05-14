package com.mycare.actions.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestForCommonLogging {	
	private static final Log log = LogFactory.getLog(TestForCommonLogging.class);
    public static void main(String[] args) {    	
    	System.out.println(log);
    	log.debug( "Here is a debug message");
    	if (log.isDebugEnabled()) {
    		  log.error("Error - the file cannot be found: ");
    	}
	}
}
