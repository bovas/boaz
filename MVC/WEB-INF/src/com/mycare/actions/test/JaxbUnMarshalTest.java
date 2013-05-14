package com.mycare.actions.test;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mycare.actions.jaxbsrc.Message;
import com.mycare.actions.jaxbsrc.RefillRequest;
 
public class JaxbUnMarshalTest {
	public static void main(String[] args) {
 
	 try {
 
		//File file = new File("/home/bovas/jaxbtest.xml");
		 File file = new File("/media/Mine/Backup/RefillRequest/refillrequest.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance("com.mycare.actions.jaxbsrc"); 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();				
		JAXBElement obj=(JAXBElement)jaxbUnmarshaller.unmarshal(file);
		System.out.println(obj.toString());
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}