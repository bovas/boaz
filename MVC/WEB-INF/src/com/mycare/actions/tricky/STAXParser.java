package com.mycare.actions.tricky;


import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class STAXParser extends DefaultHandler{
	String tempStr="";
	Hashtable<String,String> hs = new Hashtable<>();
	public static void main(String[] args) throws Exception {
		File file = new File("/media/Mine/Demo/31-08-2012/Tets.xml");
		STAXParser parser = new STAXParser();
		parser.checkDomParser(file);
		parser.checkSaxParser(file);
		parser.checkStaxParser(file);
	}
	private void checkDomParser(File file)throws Exception{		
		DocumentBuilderFactory builder= DocumentBuilderFactory.newInstance();
		DocumentBuilder buildr = builder.newDocumentBuilder();		
		Document doc = buildr.parse(file);
		System.out.println(doc);
	}
	private void checkSaxParser(File file)throws Exception{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		STAXParser parser1 = new STAXParser();
		parser.parse(file, parser1);
		System.out.println("SaxResult:::"+parser1.hs);
	}		
	private void checkStaxParser(File file)throws Exception{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		Reader readr = new FileReader(file);
		XMLStreamReader xmlReader = factory.createXMLStreamReader(readr);
		while(xmlReader.hasNext())
			System.out.println(xmlReader.next());
	}
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		tempStr="";		
	}
	@Override
	public void characters(char[] ch, int start, int length)throws SAXException {
		tempStr = new String(ch,start,length);
	}
	@Override
	public void endElement(String uri, String localName, String qName)throws SAXException {		
		if(!tempStr.trim().equals(""))			
			hs.put(qName, tempStr);					
	}
}
