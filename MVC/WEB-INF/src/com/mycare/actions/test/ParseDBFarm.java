package com.mycare.actions.test;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseDBFarm
{
	public final static int SHAREDPATH=1;
	public final static int ACCOUNTURL=2;
	private NodeList list;
	public int count=0;
	public void parse(String DBFarmPath)throws Exception
	{
		/*
		 * This method is used to parse the DBFarm XML document.
		 */
		DocumentBuilderFactory DBF=DocumentBuilderFactory.newInstance();
		DocumentBuilder DB=DBF.newDocumentBuilder();
		File f=new File(DBFarmPath);
		Document doc=DB.parse(f);
		list=doc.getElementsByTagName("account");
	}	
	public String getValue(String accountId,int requiredValue)
	{
		/*
		 * This method gets the sharedpath value for the corresponding accountId. 
		 */
		for(int i=0;i<list.getLength();i++)
		{
			String AccountId="";
			String returnValue="";
			Node accNode=list.item(i);
			NodeList accChilds=accNode.getChildNodes();
			for(int j=0;j<accChilds.getLength();j++)
			{				
				Node node=accChilds.item(j);
				System.out.println(node.getNodeName());
				if(node.getNodeName().equals("account-id"))
				{
					if(node.getFirstChild().getNodeValue().equals(accountId))
					{
						AccountId=node.getFirstChild().getNodeValue();
					}
					else
						break;
				}
				
				if(requiredValue==SHAREDPATH)
				{
					if(node.getNodeName().equals("shared-folder"))
					{
						returnValue=node.getFirstChild().getNodeValue();
						break;
					}
				}
				else if(requiredValue==ACCOUNTURL)
				{
					if(node.getNodeName().equals("account-url"))
					{
						returnValue=node.getFirstChild().getNodeValue();
						break;
					}
				}
			}
			System.out.println((count++)+"Account URL:"+returnValue);
			if(AccountId.equalsIgnoreCase(accountId))
				return returnValue;
		}
		return "";
	}
	public static void main(String[] args){
		ParseDBFarm farm = new ParseDBFarm();
		try {
			farm.parse("/home/bovas/Downloads/DBFarm.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(farm.getValue("RKMARP", 1));
	}
		
}