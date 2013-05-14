package com.mycare.actions.test;

import java.io.File;
import java.util.Vector;
import java.util.Hashtable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.*;
import org.apache.commons.net.SocketClient;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Testing extends FTPClient{
	public static void main(String args[])throws Exception{
		String Str= "[\\]";
		System.out.println(Str.replaceAll("\\+","/"));
		String oldNPI="8787867857";
			
			int count = 0;
			int newCount = 0;
			int tempNum = 0;
			String newNPI ;
			String tempNPI = oldNPI;
			tempNPI = oldNPI.substring(0,oldNPI.length()-1);
			for(int i=tempNPI.length()-1;i>=0;i--){
				if(count%2 == 0){
					tempNum = Integer.parseInt(Character.toString(tempNPI.charAt(i)))*2;
					if(tempNum >= 10){
						int tempNumRemainder = tempNum % 10;
						newCount = newCount + tempNumRemainder + 1;
					}
					else{
						newCount = newCount + tempNum;
					}
				}
				else{
					newCount = newCount + Integer.parseInt(Character.toString(tempNPI.charAt(i)));
				}
				System.out.println("newCount"+newCount);
				count++;
			}
			System.out.println("newCount"+newCount);
			newCount = newCount + 24;
			int newMod = newCount % 10;
			int lastDigit = (10 - newMod);
			if(lastDigit == 10)
				lastDigit = 0;
			StringBuffer strBuf = new StringBuffer();
			for(int j= 0; j<oldNPI.length()-1;j++){
				strBuf.append(oldNPI.charAt(j));
			}
			newNPI = strBuf.append(lastDigit).toString();
			System.out.println("NewNPI"+newNPI);
		String str="test's";
		System.out.println("\'"+"========="+str.replaceAll("\'", "&quot;"));
		Testing test=new Testing();
		/*try {*/
			
			//test.connectWIthFTP();		//connection with FTP
			//test.downloadFile();		
			//test.uploadFile("/home/bovas/Downloads/SYNC", "temp/");
		/*} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	private static String strAppend(String value , int flag, int mask){
		System.out.println("logic result"+(flag & mask) );
		return ( (flag & mask) > 0 ) ?   " "+value+" " : " " ;
	}
	public boolean connectWIthFTP() throws SocketException, IOException{
		boolean success = false;
		connect("ftp.secureddatavault.com");		//connecting
		int reply = getReplyCode(); //returning code about connection 
		System.out.println("Connection code--->"+reply);
		if (FTPReply.isPositiveCompletion(reply))	//checking the good connection 220
			success = login("glenwood", "Aloha2GSFTP");
		if (!success)
			disconnect();
		return success;
	}
	public void downloadFile() throws IOException{
		binary();
		changeWorkingDirectory("permanent/pharmacyupdates");
		boolean downloaded = downloadFile("LoginAuthorizationAction.java",(new File(".").getAbsolutePath().replaceAll("\\.","/" )) + "Downloads/pharm_details_download.zip");
		logout();
		System.out.println("Download completed");
	}
	public boolean binary () throws IOException {
		return setFileType(FTP.BINARY_FILE_TYPE);
	}
	public boolean downloadFile (String serverFile, String localFile)
			throws IOException, FTPConnectionClosedException {		
		FileOutputStream out = new FileOutputStream(localFile);
		boolean result = retrieveFile(serverFile, out);
		out.close();
		//result = isSameSize(localFile, serverFile);
		return result;
	}
	public void zipFile(String fileName){
		
	}
	public boolean connectAndLogin (String host, String userName, String password)
			throws  IOException, UnknownHostException, FTPConnectionClosedException {
		userName="glenwood";
		password="ALoha2GSFTP";
		boolean success = false;
		connect(host);
		int reply = getReplyCode();
		if (FTPReply.isPositiveCompletion(reply))
			success = login(userName, password);
		if (!success)
			disconnect();
		return success;
	}
	public boolean uploadFile (String localFile, String serverFile)
			throws IOException, FTPConnectionClosedException {
		File loc = new File(localFile);
		FileInputStream in = new FileInputStream(loc);
		boolean result = storeFile(serverFile, in);
		in.close();		
		//result = isSameSize(localFile, serverFile);
		return result;
		
	}
	
}
