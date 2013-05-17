package com.mycare.actions.utils.scjp.chapter7;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LazyTest {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
		File file  = new File("/home/bovas/Downloads/NISTMsgs");
		File[] fileName = file.listFiles();
		for(File singleFile : fileName){
			Document dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(singleFile);
			NodeList ndList = dom.getElementsByTagName("DrugDescription");	
			NodeList ndList1 = dom.getElementsByTagName("Directions");
			NodeList ndList2 = dom.getElementsByTagName("DaysSupply");
			NodeList ndList3 = dom.getElementsByTagName("Refills");
			NodeList ndList4 = dom.getElementsByTagName("Quantity");
			System.out.println("File Name::"+singleFile.getAbsolutePath());
			System.out.println("Drug name  -  "+ndList.item(0).getChildNodes().item(0).getNodeValue());
			System.out.println("Directions - "+ndList1.item(0).getChildNodes().item(0).getNodeValue());
			if(ndList2.getLength() > 0)
				System.out.println("Days supply - "+ndList2.item(0).getChildNodes().item(0).getNodeValue());
			if(ndList3.getLength() > 0)
				System.out.println("Refills - "+ndList3.item(0).getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
			if(ndList4.getLength() > 0)
				System.out.println("Quantity - "+ndList4.item(0).getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
			System.out.println();
		}
	}
}
