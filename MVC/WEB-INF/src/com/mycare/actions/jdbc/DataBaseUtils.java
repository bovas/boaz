package com.mycare.actions.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataBaseUtils {
	DataSource dsn;
	PreparedStatement prepareStmt;
	CallableStatement callableStmt;	
	public DataBaseUtils() {
		
	}
	public Connection getPostgresConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/local_version","glace","glacenxt");
		return conn;
	}
	public void createContextConnection()throws Exception{
		Context ctxt =new InitialContext();
		dsn = (DataSource) ctxt.lookup("java:comp/env/jdbc/glace");
	}
	public ResultSet executeQuery(String query) throws SQLException{
		ResultSet rset = null;
		Statement stmt = dsn.getConnection().createStatement();
		rset = stmt.executeQuery(query);
		return rset;				
	}
	public void preparePreparedStatement(String query) throws SQLException{
		prepareStmt = dsn.getConnection().prepareStatement(query);
	}
	public void prepareCallableStatement(String query) throws SQLException{
		callableStmt = dsn.getConnection().prepareCall(query);		
	}
	public void setIntValue(int index,int value) throws SQLException{
		prepareStmt.setInt(index, value);
	}
	public void setStringValue(int index,String value) throws SQLException{
		prepareStmt.setString(index, value);
	}
	public void setDateValue(int index,Date value) throws SQLException{
		prepareStmt.setDate(index, value);
	}
	public void executePreparedStatement(){		
			
	}
}
