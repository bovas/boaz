package com.mycare.actions.jaxbsrc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JaxbTest1 {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("com.mycare.actions.jaxbsrc");
		Marshaller ms = jc.createMarshaller();
	//	ms.marshal(jaxbElement, result);
	}
}
