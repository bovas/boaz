package com.mycare.actions.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;




public class DatabaseUtils {
	StringBuffer Driver = new StringBuffer();
	StringBuffer Host = new StringBuffer();
	StringBuffer Port = new StringBuffer();
	StringBuffer DatabaseName = new StringBuffer();
	StringBuffer UserName = new StringBuffer();
	StringBuffer Password = new StringBuffer();
	
	private DatabaseUtils getConnectionDetails() throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		//System.out.println(new File(".").getAbsolutePath().replaceAll("\\.","" ) + "/res/GlaceSetupConfiguration.xml");
		//Document doc = parser.parse(new File(".").getAbsolutePath().replaceAll("\\.","" ) + "/resource/PharmacyDatabaseConf.xml");
		Document doc = parser.parse("/home/bovas/PharmacyDownload/resource/PharmacyDatabaseConf.xml");
		NodeList ndlDataBaseConfig 	= doc.getElementsByTagName("Database");
		
		return setDataBase(ndlDataBaseConfig.item(0).getChildNodes());
	}
	public static DatabaseUtils setDataBase(NodeList ndlXMLDataBase) throws Exception
	{
		DatabaseUtils dbModel = new DatabaseUtils();
		//System.out.println("Length :" + ndlXMLDataBase.getLength());
		if(ndlXMLDataBase.getLength() > 0)
		{
			for(int ndlXMLDataBaseCounter = 0 ; ndlXMLDataBaseCounter < ndlXMLDataBase.getLength() ;ndlXMLDataBaseCounter++)
			{
				StringBuffer nodeData = new StringBuffer("");
				//System.out.println("Node Name :" + ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName());
				if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("Driver"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setDriver(nodeData);
						System.out.println(nodeData.toString());
				}
				else if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("Host"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setHost(nodeData);
					//	System.out.println(nodeData.toString());
				}
				else if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("Port"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setPort(nodeData);
					//	System.out.println(nodeData.toString());
				}
				else if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("DatabaseName"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setDatabaseName(nodeData);
					//	System.out.println(nodeData.toString());
				}

				else if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("UserName"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setUserName(nodeData);
					//	System.out.println(nodeData.toString());
				}
				else if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getNodeName().equals("Password"))
				{
					if(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild() != null)
						nodeData.append(ndlXMLDataBase.item(ndlXMLDataBaseCounter).getFirstChild().getNodeValue());
					else
						nodeData.append("");
					dbModel.setPassword(nodeData);
					//	System.out.println(nodeData.toString());
				}


			}
		}
		else
		{
			//		System.out.println("Check Glace Setup Configuration File: Database Server Configuration Entry is Missing");

			throw new Exception("Database Server Configuration Entry is Missing");
		}
		return dbModel;
	}
	public String validateDriver(String driver)
	{
		String errormess="";
		try{
			Class.forName(driver);
		}
		catch(Exception e){
			errormess="\nPlease copy the 'postgresql jar' to JAVA_HOME/jre/lib/ext/ Directory";
			return errormess;
		}

		return errormess;
	}
	public Connection getDBConnection() throws SQLException,Exception
	{
		/*
		 * This method which gets the database connection.
		 */
		DatabaseUtils dbModel =getConnectionDetails();
		Connection dbConnection = null;

		try
		{
			StringBuffer connectionString = new StringBuffer("");

			//System.out.println("Driver::" +dbModel.getDriver().toString().trim());
			
			if(!validateDriver(dbModel.getDriver().toString().trim()).equals(""))
			{
				throw new Exception("Check for Postgre SQL driver");
			}

			Class.forName(dbModel.getDriver().toString().trim());
			Properties  props = new Properties();
			props.setProperty("user",dbModel.getUserName().toString());
			props.setProperty("password",dbModel.getPassword().toString());
			connectionString.append("jdbc:postgresql://");

			if(!dbModel.getHost().toString().trim().equals("") && !dbModel.getPort().toString().trim().equals("") )
			{
				connectionString.append(dbModel.getHost() + ":" + dbModel.getPort() + "/");
			}
			else if(!dbModel.getHost().toString().trim().equals(""))
			{
				connectionString.append(dbModel.getHost() + "/");
			}
			else
			{

			}
			if(dbCheck(connectionString.toString(),dbModel.getDatabaseName().toString(),props))
			{
				connectionString.append(dbModel.getDatabaseName());
				dbConnection = DriverManager.getConnection(connectionString.toString(),props);
			}
			else
			{
				throw new Exception("Could not connect to database");
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return dbConnection;
	}
	public boolean dbCheck(String connectionString, String databaseName, Properties props){

		try{
			if(!databaseName.trim().equals(""))
			{
				Connection Con		=	DriverManager.getConnection(connectionString.toString() + databaseName,props);
				Con.close();
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public StringBuffer getDriver() {
		return Driver;
	}
	public void setDriver(StringBuffer driver) {
		Driver = driver;
	}
	public StringBuffer getHost() {
		return Host;
	}
	public void setHost(StringBuffer host) {
		Host = host;
	}
	public StringBuffer getPort() {
		return Port;
	}
	public void setPort(StringBuffer port) {
		Port = port;
	}
	public StringBuffer getDatabaseName() {
		return DatabaseName;
	}
	public void setDatabaseName(StringBuffer databaseName) {
		DatabaseName = databaseName;
	}
	public StringBuffer getUserName() {
		return UserName;
	}
	public void setUserName(StringBuffer userName) {
		UserName = userName;
	}
	public StringBuffer getPassword() {
		return Password;
	}
	public void setPassword(StringBuffer password) {
		Password = password;
	}
}
