package com.mycare.actions.Rx;

import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VersionChangeForNewRx {
	Hashtable totalTable = new Hashtable();
	public Document changeToNewVersion(Document templateDoc) {
		Node nd = templateDoc.getFirstChild();
		Document newVersionDoc = ChangeTheVersion.formXML(templateDoc, nd, totalTable);
		return newVersionDoc;
	}
	public void collectValues(Document doc) {
		collectHeaderValues(doc);
		collectPharmacyValues(doc);
		collectPrescriberValues(doc);
		collectPatientDetails(doc);
		collectMedicationDetails(doc);
		collectBenefitsDetails(doc);
		collectOtherHeaderDetails(doc);		
		System.out.println(totalTable.toString());
	}

	private void collectOtherHeaderDetails(Document doc) {
		NodeList ponNodes = doc.getElementsByTagName("PrescriberOrderNumber");
		NodeList rxRefNodes = doc.getElementsByTagName("RxReferenceNumber");
		if(ponNodes.getLength() > 0)
			totalTable.put("pon",ponNodes.item(0).getChildNodes().item(0).getNodeValue());
		if(rxRefNodes.getLength() > 0)
			totalTable.put("rxrefno",rxRefNodes.item(0).getChildNodes().item(0).getNodeValue());
		
		String prescriberEmail = (String) ChangeTheVersion.Nz(totalTable.get("prescriberEmail"),"");
		String prescriberAddressline2 = (String) ChangeTheVersion.Nz(totalTable.get("prescriberAddressLine2"),"");
		String pharmacyAddressline2 = (String) ChangeTheVersion.Nz(totalTable.get("pharmacyAddressLine2"),"");
		String patientAddressline2 = (String) ChangeTheVersion.Nz(totalTable.get("patientAddressLine2"),"");
		String vendorName = (String) ChangeTheVersion.Nz(totalTable.get("VendorName"),"");
		String appName = (String) ChangeTheVersion.Nz(totalTable.get("AppName"),"");
		String applicationVersion = (String) ChangeTheVersion.Nz(totalTable.get("ApplicationVersion"),"");
		if(!prescriberEmail.trim().equals(""))
			totalTable.put("prescriberEmailQualifier","EM");
		if(!prescriberAddressline2.trim().equals(""))
			totalTable.put("presplacelocationqualifier","AD2");
		if(!pharmacyAddressline2.trim().equals(""))
			totalTable.put("phrmplacelocationqualifier","AD2");
		if(!patientAddressline2.trim().equals(""))
			totalTable.put("patplacelocationqualifier","AD2");

		if(vendorName.trim().equals(""))
			totalTable.put("VendorName","Glenwood Systems LLC.,");
		if(appName.trim().equals(""))
			totalTable.put("AppName","GlaceEMR");
		if(applicationVersion.trim().equals(""))
			totalTable.put("ApplicationVersion","4.5");
		
		totalTable.put("codelistqualifier", "38");
		totalTable.put("unitsourcecode", "AC");
	}

	private void collectBenefitsDetails(Document doc) {
		Node benefitsNode = doc.getElementsByTagName("BenefitsCoordination").item(0);
		if(benefitsNode!=null){
			NodeList benefitsChildNodes = benefitsNode.getChildNodes();
			for(int count=0;count < benefitsChildNodes.getLength(); count++){
				Node currentNode = benefitsChildNodes.item(count);
				if(currentNode.getNodeName().equals("PayerIdentification")){
					NodeList softwareNodes = currentNode.getChildNodes();
					for(int count1=0;count1<softwareNodes.getLength();count1++){
						if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
							totalTable.put(softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
					}
				}else{
					if(currentNode.getNodeType()!=Node.TEXT_NODE)
						totalTable.put(currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
				}			
			}					
		}
	}

	private void collectMedicationDetails(Document doc) {
		Node medicationNode = doc.getElementsByTagName("MedicationPrescribed").item(0);
		NodeList medicationChildNodes = medicationNode.getChildNodes();
		for(int count=0;count < medicationChildNodes.getLength(); count++){
			Node currentNode = medicationChildNodes.item(count);
			if(currentNode.getNodeName().equals("DrugCoded")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put(softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
				}
			}else if(currentNode.getNodeName().equals("Quantity")){
				NodeList quantityNodes = currentNode.getChildNodes();
				for(int count1=0;count1<quantityNodes.getLength();count1++){
					if(quantityNodes .item(count1).getNodeType()==Node.ELEMENT_NODE){
						if(quantityNodes .item(count1).getNodeName().equals("Qualifier"))
							totalTable.put("quantityqualifier",quantityNodes.item(count1).getChildNodes().item(0).getNodeValue());
						else if(quantityNodes .item(count1).getNodeName().equals("Value"))
							totalTable.put("quantityvalue",ChangeTheVersion.getPotentialCode(quantityNodes.item(count1).getChildNodes().item(0).getNodeValue()));
					}							
				}				
			}else if(currentNode.getNodeName().equals("Refills")){
				NodeList quantityNodes = currentNode.getChildNodes();
				for(int count1=0;count1<quantityNodes.getLength();count1++){
					if(quantityNodes .item(count1).getNodeType()==Node.ELEMENT_NODE){
						if(quantityNodes .item(count1).getNodeName().equals("Qualifier"))
							totalTable.put("refillsqualifier",quantityNodes.item(count1).getChildNodes().item(0).getNodeValue());
						else if(quantityNodes .item(count1).getNodeName().equals("Quantity"))
							totalTable.put("refillsvalue",quantityNodes.item(count1).getChildNodes().item(0).getNodeValue());
					}							
				}				
			}else{
				if(currentNode.getNodeType()!=Node.TEXT_NODE){
					if(currentNode.getNodeName().equals("WrittenDate")){
						String dateValue = currentNode.getChildNodes().item(0).getNodeValue();
						totalTable.put(currentNode.getNodeName(),ChangeTheVersion.formatDate(dateValue));
					}else
						totalTable.put(currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
				}
			}			
		}										
	}

	private void collectPatientDetails(Document doc) {
		Node patientNode = doc.getElementsByTagName("Patient").item(0);
		NodeList patientChildNodes = patientNode.getChildNodes();
		for(int count=0;count < patientChildNodes.getLength(); count++){
			Node currentNode = patientChildNodes.item(count);
			if(currentNode.getNodeName().equals("Address") || currentNode.getNodeName().equals("Name")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put("patient"+softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
				}
			}else if(currentNode.getNodeName().equals("Identification")){
				NodeList softwareNodes = currentNode.getChildNodes();
				int payerIdCount=1;
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE){
						if(softwareNodes.item(count1).getNodeName().equals("PayerID"))
							totalTable.put("patientpayerid"+ (payerIdCount++) ,softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
						else 
							totalTable.put("patient"+softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
					}
				}
			}else if(currentNode.getNodeName().equals("PhoneNumbers")){
				NodeList softwareNodes = currentNode.getChildNodes();
				int phoneNoCount = 1;
				for(int count3=0;count3<softwareNodes.getLength();count3++){
					Node phoneNumberNode = softwareNodes.item(count3);										
					if(phoneNumberNode .getNodeType()==Node.ELEMENT_NODE && phoneNumberNode.getNodeName().equals("Phone"))
					{
						NodeList phoneNumberChilds = phoneNumberNode.getChildNodes();
						for(int phonenocount=0;phonenocount < phoneNumberChilds.getLength();phonenocount++){
							Node phoneNumberChildNode = phoneNumberChilds.item(phonenocount);							
							if(phoneNumberChildNode.getNodeType()!=Node.TEXT_NODE){								
								if(phoneNumberChildNode.getNodeName().equals("Number"))
									totalTable.put("patientphoneno"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
								else if(phoneNumberChildNode.getNodeName().equals("Qualifier")){
									totalTable.put("patientphonequalifier"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
									phoneNoCount++;
								}
							}
								
						}
					}
				}
			}else{
				if(currentNode.getNodeType()!=Node.TEXT_NODE){
					if(currentNode.getNodeName().equals("DateOfBirth")){
						String dateValue = currentNode.getChildNodes().item(0).getNodeValue();
						totalTable.put("patient"+currentNode.getNodeName(),ChangeTheVersion.formatDate(dateValue));
					}else
						totalTable.put("patient"+currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
				}
			}			
		}								
	}

	private void collectPrescriberValues(Document doc) {
		Node prescriberNode = doc.getElementsByTagName("Prescriber").item(0);
		NodeList prescriberChildNodes = prescriberNode.getChildNodes();
		for(int count=0;count < prescriberChildNodes.getLength(); count++){
			Node currentNode = prescriberChildNodes.item(count);
			if(currentNode.getNodeName().equals("Address") || currentNode.getNodeName().equals("Name") || currentNode.getNodeName().equals("Identification")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put("prescriber"+softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
				}
			}else if(currentNode.getNodeName().equals("PhoneNumbers")){
				NodeList softwareNodes = currentNode.getChildNodes();
				int phoneNoCount = 1;
				for(int count3=0;count3<softwareNodes.getLength();count3++){
					Node phoneNumberNode = softwareNodes.item(count3);										
					if(phoneNumberNode .getNodeType()==Node.ELEMENT_NODE && phoneNumberNode.getNodeName().equals("Phone"))
					{
						NodeList phoneNumberChilds = phoneNumberNode.getChildNodes();
						for(int phonenocount=0;phonenocount < phoneNumberChilds.getLength();phonenocount++){
							Node phoneNumberChildNode = phoneNumberChilds.item(phonenocount);							
							if(phoneNumberChildNode.getNodeType()!=Node.TEXT_NODE){								
								if(phoneNumberChildNode.getNodeName().equals("Number"))
									totalTable.put("prescriberphoneno"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
								else if(phoneNumberChildNode.getNodeName().equals("Qualifier")){
									totalTable.put("prescriberphonequalifier"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
									phoneNoCount++;
								}
							}
								
						}
					}
				}
			}else{
				if(currentNode.getNodeType()!=Node.TEXT_NODE)
					totalTable.put("prescriber"+currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
			}			
		}						
	}

	private void collectPharmacyValues(Document doc) {
		Node pharmacyNode = doc.getElementsByTagName("Pharmacy").item(0);
		NodeList pharmacyChildNodes = pharmacyNode.getChildNodes();
		for(int count=0;count < pharmacyChildNodes.getLength(); count++){
			Node currentNode = pharmacyChildNodes.item(count);
			if(currentNode.getNodeName().equals("Address") || currentNode.getNodeName().equals("Identification")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put("pharmacy"+softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
				}
			}else if(currentNode.getNodeName().equals("Pharmacist")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count2=0;count2<softwareNodes.getLength();count2++){
					if(softwareNodes.item(count2).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put("pharmacist"+softwareNodes.item(count2).getNodeName(),softwareNodes.item(count2).getChildNodes().item(0).getNodeValue());
				}
			}else if(currentNode.getNodeName().equals("PhoneNumbers")){
				NodeList softwareNodes = currentNode.getChildNodes();
				int phoneNoCount = 1;
				for(int count3=0;count3<softwareNodes.getLength();count3++){
					Node phoneNumberNode = softwareNodes.item(count3);					
					if(phoneNumberNode .getNodeType()==Node.ELEMENT_NODE && phoneNumberNode.getNodeName().equals("Phone"))
					{
						NodeList phoneNumberChilds = phoneNumberNode.getChildNodes();
						for(int phonenocount=0;phonenocount < phoneNumberChilds.getLength();phonenocount++){
							Node phoneNumberChildNode = phoneNumberChilds.item(phonenocount);
							if(phoneNumberChildNode.getNodeType()!=Node.TEXT_NODE){
								if(phoneNumberChildNode.getNodeName().equals("Number"))
									totalTable.put("pharmacyphoneno"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
								else if(phoneNumberChildNode.getNodeName().equals("Qualifier")){
									totalTable.put("pharmacyphonequalifier"+phoneNoCount,phoneNumberChildNode.getChildNodes().item(0).getNodeValue());
									phoneNoCount++;
								}
							}
								
						}
					}
				}
			}else{
				if(currentNode.getNodeType()!=Node.TEXT_NODE)
					totalTable.put("pharmacy"+currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
			}			
		}				
	}

	private void collectHeaderValues(Document doc) {
		Node headerNode = doc.getElementsByTagName("Header").item(0);
		NodeList headerChildNodes = headerNode.getChildNodes();
		for(int count=0;count < headerChildNodes.getLength(); count++){
			Node currentNode = headerChildNodes.item(count);
			if(currentNode.getNodeName().equals("To")){
				String toIdValue = currentNode.getChildNodes().item(0).getNodeValue();
				toIdValue = toIdValue.substring(toIdValue.indexOf(":")+1,toIdValue.indexOf("."));
				totalTable.put("ncpdpid", toIdValue);
			}else if(currentNode.getNodeName().equals("From")){
				String fromIdValue = currentNode.getChildNodes().item(0).getNodeValue();
				fromIdValue = fromIdValue.substring(fromIdValue.indexOf(":")+1,fromIdValue.indexOf("."));
				totalTable.put("docMailId", fromIdValue);
			}else if(currentNode.getNodeName().equals("AppVersion")){
				NodeList softwareNodes = currentNode.getChildNodes();
				for(int count1=0;count1<softwareNodes.getLength();count1++){
					if(softwareNodes.item(count1).getNodeType()==Node.ELEMENT_NODE)
						totalTable.put(softwareNodes.item(count1).getNodeName(),softwareNodes.item(count1).getChildNodes().item(0).getNodeValue());
				}
			}else{
				if(currentNode.getNodeType()==Node.ELEMENT_NODE)
					totalTable.put(currentNode.getNodeName(),currentNode.getChildNodes().item(0).getNodeValue());
			}			
		}		
	}
}
