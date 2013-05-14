package com.mycare.actions.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
public class ChannelChecking {
	JSch jsch = new JSch();
	Session session = null;
	Channel channel = null;
	ChannelSftp c = null;

	String ftpHost = "";
	int ftpPort = 22;
	String ftpUserName = "";
	String ftpPassword = "";
	String ftpRemoteDirectory = "/inbound/";
	String fileToTransmit = "c:\\1778296216-OFFICEALLY-06302008.txt";

	public ChannelChecking(String host,String username,String password) {
		ftpHost = host;
		ftpUserName = username;
		ftpPassword = password;
	}

	public void connectTo() throws Exception{
		try {
			session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
			session.setPassword(ftpPassword);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			c = (ChannelSftp)channel;
		} catch (Exception e) {
			System.err.println("Unable to connect to FTP server: "+e.toString());
			throw e;
		}            
	}   
	public void setRemoteDir(String dir){
		ftpRemoteDirectory = dir;
	}
	public void setLocalFileName(String fileName){
		fileToTransmit = fileName;    		 
	}
	public void ChangeRemoteDir()throws Exception{
		try{
			c.cd(ftpRemoteDirectory);
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public void sendFile() throws Exception{
		try {
			File f = new File(fileToTransmit);
			c.put(new FileInputStream(f), f.getName());

		} catch (Exception e) {
			System.err.println("Storing remote file failed. "+e.toString());
			throw e;
		}
	}

	public void getFiles(String destPath) throws Exception{
		Vector files = c.ls(ftpRemoteDirectory);
		if (files.size() == 0) {
		}
		else {
			for (int i=0; i<files.size(); i++) {
				com.jcraft.jsch.ChannelSftp.LsEntry lsEntry = (com.jcraft.jsch.ChannelSftp.LsEntry) files.get(i);

				if (!lsEntry.getFilename().equals(".") && !lsEntry.getFilename().equals("..")) {
					String outputFileName = destPath+""+lsEntry.getFilename();
					File f = new File(outputFileName);                    
					c.get(lsEntry.getFilename(), new FileOutputStream(f));
				}
			}
		}
	}
	public void closeServer() throws Exception{
		try {
			c.quit();
		} catch (Exception exc) {
			System.err.println("Unable to disconnect from FTP server. " + exc.toString());
		}            
	}    
	public static void main(String args[]) throws Exception{
		ChannelChecking cc = new ChannelChecking("192.168.2.4", "tomcat", "tomcat");			
		cc.connectTo();
		cc.setRemoteDir("/home/glace/");
		cc.ChangeRemoteDir();
		cc.setLocalFileName("/home/bovas/erxconf.txt");
		cc.sendFile();
		cc.closeServer();		
	}
}