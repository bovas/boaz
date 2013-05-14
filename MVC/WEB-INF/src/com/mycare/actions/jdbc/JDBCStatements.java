package com.mycare.actions.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatements {
	public static void main(String[] args) throws Exception {
	DataBaseUtils dbUtils = new DataBaseUtils();	
	Connection conn = dbUtils.getPostgresConnection();
	PreparedStatement pstmt = conn.prepareStatement("select count(*) from login_users where login_users_username ilike ?");
	pstmt.setString(1, "demodoctor");
	ResultSet rs2 =pstmt.executeQuery();
	
	if(rs2.next())
		System.out.println("Prpeared "+rs2.getString(1));
	
	CallableStatement callstmt = conn.prepareCall("select getracecodes(3)");	
	ResultSet rs1 =callstmt.executeQuery();
	if(rs1.next())
		System.out.println("Callstmt "+rs1.getString(1));
	
	if(rs1!=null){
		rs1.close();
	}if(pstmt!=null)
		pstmt.close();
		checkResultSet();
	}
	static void checkResultSet() throws ClassNotFoundException, SQLException{		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/local_version","glace","glacenxt");
		Statement stmt = conn.createStatement();
		Statement stmt1 = conn.createStatement();
		ResultSet set1 = stmt.executeQuery("select doc_presc_id as col from doc_presc order by 1 limit 1");
		ResultSet set = stmt.executeQuery("select login_users_id as col from login_users order by 1 limit 1");
		
		if(set1.next())
			System.out.println(set1.getString("col"));
		
				
		if(set.next())
			System.out.println(set.getString("col"));
	}
}
