package com.mycare.actions.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Addressline {
	public static void main(String[] args) {
		File fil=new File("/media/Projects/GlaceShared/Messages/OutgoingMessages/Medication_epcs-1345-062620121106PM_2012-06-26T17-07-51.0Z.XML");
		Document doc=Addressline.convertFileToDocument(fil);
		System.out.println("String-->"+Addressline.convertDOMToString(doc));
	}
	public static Document convertFileToDocument(File Filename){
		
		//This method converts an XML file to an XML Document.
		
		 InputSource is2 = null;
		 Document template = null;
			 
		  try{
			String xmldata = "";
			//GlaceOutLoggingStream.console(Filename.getAbsolutePath());
			FileReader fr = new FileReader(Filename);
			BufferedReader br = new BufferedReader(fr);
			String content = "";
			
			while (content != null){
				xmldata = xmldata + content;
				content = br.readLine();
			}
			System.out.println(xmldata);
			is2	=	new InputSource();
			is2.setByteStream(new ByteArrayInputStream(xmldata.getBytes()));
			is2.setEncoding("UTF-8");
						
			DocumentBuilderFactory docBuilderFactory	=	DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder				    =	docBuilderFactory.newDocumentBuilder();
				  
			template =	docBuilder.parse(is2);			
			
		  }
		  catch(IOException e){
			  e.printStackTrace();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return template;
	 }
	public static String convertDOMToString(Document DOMDocument){
	    /*
	     * This method converts Document to String.
	     */
		
	    String result = "" + DOMDocument.getFirstChild();
	    System.out.println(result.toString());
	    return result;
	}
}
