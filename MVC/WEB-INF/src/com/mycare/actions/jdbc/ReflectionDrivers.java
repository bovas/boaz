package com.mycare.actions.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReflectionDrivers {
	public static void main(String[] args) throws SQLException {		
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.5:3306/drugdb","bovas","drug");		
		Connection conn1 = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/local_version","glace","glacenxt");
		System.out.println(conn);
		System.out.println(conn1);
	}
}
