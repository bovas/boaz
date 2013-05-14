package com.mycare.actions.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCheck {
	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/local_version","glace","glacenxt");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select now()::date");
		if(rs.next())
			System.out.println(rs.getString(1));
		con.close();
		System.out.println("Connection check successfully...");
	}
}
