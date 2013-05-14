package com.mycare.actions.test;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class WriteFile {	
    public static void main(String[] args) throws Exception {
		FileReader fr=null;
    	
		String strFileList="";
    	
		fr  = new FileReader(new File("/media/Projects/GlaceShared_Mine/PharmacyDownload/uploads/file.xml"));    	
    	
    	BufferedReader br = new BufferedReader(fr); 
    	String s; 
    	while((s = br.readLine()) != null) { 
    		strFileList+=s; 
    	} 
		File f = new File("/home/bovas/newFileResult.xml");


        FileWriter fw=new FileWriter(f);
        fw.write(strFileList);
			if(fw != null)
		fw.close();
    }
}
