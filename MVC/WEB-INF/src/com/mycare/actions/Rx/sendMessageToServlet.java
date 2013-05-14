package com.mycare.actions.Rx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class sendMessageToServlet {
	public static void main(String[] args)throws Exception {
		System.out.println("1.SendMessage");
		System.out.println("2. Edit and Send the file");
		Scanner scan = new Scanner(System.in);
		String nxtLine="";
		if(scan.hasNextLine())
			nxtLine = scan.nextLine();
		if(nxtLine.equals("1")){
			askDetailsAndPost(null);
		}else if(nxtLine.equals("2")){
			System.out.println("Enter the file name:");
			String fileName = scan.nextLine();			
			scanTheFile(fileName);
		}
		if(scan!=null)
			scan.close();
	}
	static void postMessageToServer(String url, String accountId, String fileName) throws IOException{		
		MultiThreadedHttpConnectionManager connectionManager = 	new MultiThreadedHttpConnectionManager();
      	HttpClient httpClient=new HttpClient(connectionManager);
      	PostMethod post=new PostMethod(url);
      	InputStream fs = new FileInputStream(new File(fileName));
      	RequestEntity entity=new InputStreamRequestEntity(fs,"text/xml; charset=utf-8");	
		post.setRequestEntity(entity);
		post.setRequestHeader("Accept", "text/xml");
		post.setRequestHeader("Cache-Control", "no-cache");
		post.setRequestHeader("SOAPAction", "");
		post.addRequestHeader("accountId", accountId);	      	
		httpClient.executeMethod(post);		
		String result=post.getResponseBodyAsString();
		System.out.println(result);
	}
	static void scanTheFile(String fileName) throws IOException{		
		System.out.println("Copy the content and change it.");
		Scanner fileScanner = new Scanner(new File(fileName));
		while(fileScanner.hasNextLine()){
			System.out.println(fileScanner.nextLine());
		}		
		if(fileScanner!=null)
			fileScanner.close();
		fileScanner = new Scanner(System.in);
		String fileContent="";
		if(fileScanner.hasNextLine())
			fileContent = fileScanner.nextLine();
		if(!fileContent.equals("")){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			String newFileName = "/tmp/MessageFile"+Calendar.YEAR+"_"+Calendar.MONTH+"_"+Calendar.DAY_OF_MONTH+"_"+Calendar.HOUR_OF_DAY+"_"+Calendar.MINUTE+"_"+Calendar.SECOND;
			FileWriter fw = new FileWriter(newFileName);
			fw.write(fileContent);
			askDetailsAndPost(newFileName);
			if(new File(newFileName).exists())
				new File(newFileName).delete();
			if(fw!=null)
				fw.close();
		}
		if(fileScanner!=null)
			fileScanner.close();
	}
	static void askDetailsAndPost(String ScannedfileName) throws IOException{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the URL");
		String url = scan.nextLine();
		System.out.println("Enter the account id:");
		String accountId = scan.nextLine();		
		String fileName =ScannedfileName;
		if(fileName==null){
			System.out.println("Enter the file name");
			fileName = scan.nextLine();
		}
		if(scan!=null)
			scan.close();
		postMessageToServer(url,accountId,fileName);
	}
}