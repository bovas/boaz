package com.mycare.actions.test;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.PGConnection;

public class TakeBkup {
	public static void main(String[] args) throws Exception {
		String backupPath = "/home/glace/PharmacyUpdate/";
		String tableStr="alert_event";
		String timeStr="2012_12_03";
		boolean backupFlag = false; 
		if(!new File(backupPath).exists())
			backupFlag = new File(backupPath).mkdir();
		else 
			backupFlag = true;
		String qry="";
		File backupFile = new File(backupPath+tableStr+"_bkp_"+timeStr+".txt");
		Class.forName("org.postgresql.Driver");
		Connection dbconn = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/epcs", "glace", "glacenxt");
		System.out.println("backupFlag---"+backupFlag);
		if(backupFlag){
			FileOutputStream fos = new FileOutputStream(backupFile);
			((PGConnection)dbconn).getCopyAPI().copyOut("COPY "+tableStr+" TO STDOUT", fos);
			System.out.println("File written...");
		}
		qry = "truncate "+tableStr; //clearing the data
	}
}
