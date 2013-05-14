package com.mycare.actions.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNewData {
	static String fileSeparator	= System.getProperty("file.separator");
	static String logFile="/home/bovas/FormualryLog_"+new Date().getDate()+new Date().getMonth()+".txt";
	static FileWriter fw=null;
	public static void main(String args[])throws Exception{
		try{			
			fw = new FileWriter(logFile,false);
			if(invokeMethod(args[0],args[1],args[2]))
				System.out.println("Successfully Updated");
			else
				System.out.println("Not Updated");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fw!=null)
				fw.close();
			fw=null;
		}
	}
	private static boolean invokeMethod(String rootFldr,String lastUpdDate,String destination)throws Exception{

		if(!new File(rootFldr).exists())return false;
		
		clearDestination(destination);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date lastUpdatedDate=sdf.parse(lastUpdDate);

		sdf=new SimpleDateFormat("dd-MM-yyyy");
		String[] dirList		= readDirctory(rootFldr);		// reading the root directory of formulary files
		System.out.println("Dir");

		for(int i=0;i<dirList.length;i++){			
			String[] subDirList=readDirctory(rootFldr+fileSeparator+dirList[i]);		//reading the PBM directory
			for(int j=0;j<subDirList.length;j++){			
				String[] fileList=readDirctory(rootFldr+fileSeparator+dirList[i]+fileSeparator+subDirList[j]);	//reading the sub directory (COV,COP,ALT)
				for(int k=0;k<fileList.length;k++){					
					String fileName = rootFldr+fileSeparator+dirList[i]+fileSeparator+subDirList[j]+fileSeparator+fileList[k];
					Date fileDate = new Date(getLastModifd(fileName));												
					System.out.println("File "+ fileName +" is processing.... Last Modified date-->"+sdf.format(fileDate));
					if(lastUpdatedDate.compareTo(fileDate) < 0){
						System.out.println("File is going to Dest");
						moveFileToDest(destination,fileName);
					}						 
				}
			}
		}		
		return true;
	}

	private static String[] readDirctory(String directory) throws Exception{
		File dirList=null;
		try{
			System.out.println(directory);
			dirList=new File(directory);
		}catch (Exception e){
			throw e;
		}
		return dirList.list();
	}
	private static void moveFileToDest(String dest,String fileName)throws Exception{
		File destlocn = new File(dest);
		File srcFile = new File(fileName);				
		if(copy(srcFile,new File(dest+fileSeparator+srcFile.getName()))){			
			try{fw.append("File "+srcFile.getName()+" moved from the location ::"+fileName+"\n");}catch(Exception e){e.printStackTrace();}			
		}

	}
	public static synchronized boolean copy(File src, File dest) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}		
		in.close();
		out.close();
		return true;
	}
	private static long getLastModifd(String fileName)throws Exception{
		File file = new File(fileName);
		return file.lastModified();
	}
	private static boolean clearDestination(String destn)throws Exception{
		File destlocn = new File(destn); 
		if(destlocn.exists()){
			File dirList[] = destlocn.listFiles();
			for(File singFile: dirList)
				singFile.delete();				
			return true;
		}else		
			destlocn.mkdirs();	
		return false;
	}
}
