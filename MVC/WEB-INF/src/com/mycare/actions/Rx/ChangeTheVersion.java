package com.mycare.actions.Rx;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ChangeTheVersion {	
	public static HashMap potentialcodetable =null;
	public static void main(String[] args) {
		//File file = new File("/var/backup/Medication_43232_2013-01-05T09-46-36.0Z.XML");
		File file = new File("/var/backup/Medication_null_43637_2013-01-23T19-10-27.0Z.XML ");
		Document doc = ChangeTheVersion.convertFileToDocument(file);
		ChangeTheVersion version = new ChangeTheVersion();
		String result = version.changeTheVersionOfXml(doc);
		System.out.println(result);
	}	
	public String changeTheVersionOfXml(Document doc){
	//public String changeTheVersionOfXml(String fileContent){
		//Document doc = convertStringToDocument(fileContent);
		File newRxtempfile = new File("/var/backup/NewMedication10_6.xml");
		File refillResponsetempfile = new File("/var/backup/RefillResponse10_6.xml");
		
		Document newVersionDoc = null;
		String documentFormat = ChangeTheVersion.getDocumentFormat(doc);
		if(documentFormat.equals("newrx")){
			VersionChangeForNewRx version = new VersionChangeForNewRx();				
			version.collectValues(doc);
			Document templateDoc =  ChangeTheVersion.convertFileToDocument(newRxtempfile);
			newVersionDoc = version.changeToNewVersion(templateDoc);
		}else if(documentFormat.equals("refill")){
			VersionChangeForRefill version = new VersionChangeForRefill();				
			version.collectValues(doc);
			Document templateDoc =  ChangeTheVersion.convertFileToDocument(refillResponsetempfile);
			newVersionDoc = version.changeToNewVersion(templateDoc);
		}
		return convertDOMToString(newVersionDoc);
	}
	private static String getDocumentFormat(Document doc) {		
		NodeList newRxNode= doc.getElementsByTagName("NewRx");
		if(newRxNode.getLength() > 0)
			return "newrx";
		NodeList refillRespNode= doc.getElementsByTagName("RefillResponse");
		if(refillRespNode.getLength() > 0)
			return "refill";
		return "";
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
	public static Document formXML(Document template,Node nd,Hashtable data) {
		/*
		 * This method inserts the data in the Hashtable into the template document's corresponding tags and forms the 
		 * final XML document.
		 */
		//GlaceOutLoggingStream.console("Template:"+template.getFirstChild());
		boolean formationprocess	= true;

		try{		  
			String parentnode   = null;
			//		String parentnodeid = null;

			NodeList datalist		= (NodeList)nd.getChildNodes();

			for (int i=0;i < datalist.getLength();i++){
				String parentnodeid = null;
				Node currentnode = datalist.item(i);			
				String nodename = currentnode.getNodeName();
				//GlaceOutLoggingStream.console("NodeName:"+nodename);

				if (!nodename.equals("#text")){
					boolean haschild = currentnode.hasChildNodes();

					if (haschild){

						parentnode 	 	 = nodename;
						Node parentatt	 = currentnode.getAttributes().getNamedItem("name");

						if (parentatt != null){
							parentnodeid = currentnode.getAttributes().getNamedItem("name").getNodeValue();
						}
						System.out.println(parentnode+"::"+parentnodeid);
						formXML(template,currentnode,data);

						if (parentnodeid != null){
							NodeList Parent = template.getElementsByTagName(parentnode);				
							removeEmptyNodes(Parent,parentnodeid);
						}
					}

					else {

						Node att = currentnode.getAttributes().getNamedItem("minoccurs");
						Node dbfield = currentnode.getAttributes().getNamedItem("dbfield");

						Element E = (Element)currentnode;						
						if (dbfield != null){

							String Textnodevalue = (String)data.get(dbfield.getNodeValue());													
							if ((Textnodevalue ==  null)|| (Textnodevalue.length() == 0)){
								if(att!=null){ 
									if (att.getNodeValue().equals("12")){
										// throw the Exception if it is Required Node 
										String unAvailableData = nodename;
										throw new Exception(unAvailableData);
									}
									else{						    
										// Remove the node if it is not Required node
										E.getParentNode().removeChild(E);							
									}
								}
								else{
									E.getParentNode().removeChild(E);
								}
							}

							else{
								currentnode.appendChild(template.createTextNode(Textnodevalue.trim()));
							}					
						}

						else{
							E.getParentNode().removeChild(E);
						}

						//E.removeAttribute("minoccurs");
						E.removeAttribute("dbfield");
					}

				}

			}		

		}

		catch(Exception e){
			formationprocess = false;
			e.printStackTrace();
		}  

		if (formationprocess == true){
			//GlaceOutLoggingStream.console(""+template.getFirstChild());	
			return template;

		}
		else{
			return null;
		}
	}
	public static void removeEmptyNodes(NodeList Parent,String parentnodeid){

		NodeList parentchild = null;
		Node currentparent = null;

		for (int j=0;j < Parent.getLength();j++){
			Node nameattr = Parent.item(j).getAttributes().getNamedItem("name");
			if (nameattr != null){
				if (nameattr.getNodeValue().equals(parentnodeid)){
					currentparent = Parent.item(j);
					parentchild = currentparent.getChildNodes();
				}
			}
		}

		int flag = 0; 		
		if(parentchild!=null){
			for (int k=0;k < parentchild.getLength();k++){
				if (!parentchild.item(k).getNodeName().equals("#text")){
					flag = 1;
				}
			}
		}

		Element E = (Element)currentparent;

		if (flag == 0){					
			E.getParentNode().removeChild(E);
		}		
		else{				
			E.removeAttribute("name");
		}		  
	}public static String convertDOMToString(Document DOMDocument){
		/*
		 * This method converts Document to String.
		 */

		String result = "" + DOMDocument.getFirstChild();

		return result;
	}
	public static Document convertStringToDocument(String fileContent){
		
		Document template = null;
		
		InputSource is2	  =	new InputSource();
		is2.setByteStream(new ByteArrayInputStream(fileContent.getBytes()));
		is2.setEncoding("UTF-8");
					
		DocumentBuilderFactory docBuilderFactory =	DocumentBuilderFactory.newInstance();
		
		try{
			
			DocumentBuilder docBuilder	=	docBuilderFactory.newDocumentBuilder();
			template =	docBuilder.parse(is2);
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return template;
	}
	public static Object Nz(Object param1,String param2){
		if(param1==null)
			return param2;
		return param1;
	}
	public static Object nz(String param1,String param2){
		if(param1==null || param1.equals(""))
			return param2;
		return param1;
	}
	public static String formatDate(String dateStr){
		if(dateStr.trim().length()==8)
			dateStr = dateStr.substring(0, 4)+"-"+dateStr.substring(4,6)+"-"+dateStr.substring(6);
		return dateStr;
	}
	public ChangeTheVersion(){
		potentialcodetable = new HashMap();
		potentialcodetable.put("00","C38046");  
		potentialcodetable.put("GR","C48155");
		potentialcodetable.put("BG","C48474"); 
		potentialcodetable.put("BO","C48477"); 
		potentialcodetable.put("BX","C48478"); 
		potentialcodetable.put("AV","C48480");  
		potentialcodetable.put("CQ","C48481");
		potentialcodetable.put("CH","C48484");
		potentialcodetable.put("FO","C48494"); 
		potentialcodetable.put("Y7","C69124");  
		potentialcodetable.put("IH","C62275");  
		potentialcodetable.put("KT","C48504"); 
		potentialcodetable.put("LT","C48505");  
		potentialcodetable.put("UU","C48506");
		potentialcodetable.put("ME","C28253"); 
		potentialcodetable.put("ML","C28254"); 
		potentialcodetable.put("12","C48521");  
		potentialcodetable.put("PT","C48529"); 
		potentialcodetable.put("AR","C48539"); 
		potentialcodetable.put("SZ","C48540"); 
		potentialcodetable.put("Y2","C48541");  
		potentialcodetable.put("U2","C48542"); 
		potentialcodetable.put("Y3","C48544");  
		potentialcodetable.put("TB","C48549"); 
		potentialcodetable.put("VI","C48551");   
		potentialcodetable.put("X4","C29012");  
		potentialcodetable.put("PH","C62653"); 
		potentialcodetable.put("UN","C44278");
	}
	public static String getPotentialCode(String code){
		return (String) ChangeTheVersion.Nz(potentialcodetable.get(code),"");
	}
}
