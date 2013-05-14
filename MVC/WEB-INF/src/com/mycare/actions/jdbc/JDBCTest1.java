package com.mycare.actions.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest1 {
	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/local_version","glace","glacenxt");		
		PreparedStatement psmt = conn.prepareStatement("select login_users_username from login_users where login_users_id in(?,?)");
		psmt.setInt(1, 1);
		psmt.setInt(2, 1);		
		ResultSet set = psmt.executeQuery();
		if(set.next())
			System.out.println(set.getRow());
				
	}
}
