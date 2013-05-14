package com.mycare.actions.Rx;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlVersionChange {
	public static void main(String[] args) {
		File file = new File("/var/backup/Medication_43232_2013-01-05T09-46-36.0Z.XML");
		Document fileDoc = XmlVersionChange.convertFileToDocument(file);
		xmlVersionChangeForNewRx(fileDoc);
	}
	
	private static void xmlVersionChangeForNewRx(Document fileDoc) {
		String fileString = XmlVersionChange.convertDOMToString(fileDoc);
		changeTheHeader(fileString);
	}

	private static void changeTheHeader(String fileString) {
		int headerStart = fileString.indexOf("<Header>");
		int headerEnd = fileString.indexOf("</Header>");
		String headerTag = fileString.substring(headerStart,headerEnd);
		
		int toStart = fileString.indexOf("<To>")+("<To>").length();		
		int toEnd = fileString.indexOf("</To>");
		String toIdValue = fileString.substring(toStart, toEnd);
		toIdValue = toIdValue.substring(toIdValue.indexOf(":")+1,toIdValue.indexOf("."));
		String oldToTag = fileString.substring(toStart-4,toEnd+4);
		String newTagValue = "<To>"+toIdValue+"</To>";
		
	}

	private static Document convertFileToDocument(File Filename){

		//This method converts an XML file to an XML Document.

		InputSource is2 = null;
		Document template = null;

		try{
			String xmldata = "";			
			FileReader fr = new FileReader(Filename);
			BufferedReader br = new BufferedReader(fr);
			String content = "";

			while (content != null){
				xmldata = xmldata + content;
				content = br.readLine();
			}

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
	private static String convertDOMToString(Document DOMDocument){
	    /*
	     * This method converts Document to String.
	     */
		
	    String result = "" + DOMDocument.getFirstChild();
	    
	    return result;
	}

}
