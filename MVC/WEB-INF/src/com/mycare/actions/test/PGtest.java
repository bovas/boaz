package com.mycare.actions.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.PGConnection;

public class PGtest {
	public static void main(String[] args) throws SQLException, Exception {
		DatabaseUtils dbUtils = new DatabaseUtils();
		Connection con = dbUtils.getDBConnection();
		FileOutputStream fo = new FileOutputStream("/home/bovas/Downloads/pharm_details_download.txt");
		((PGConnection)con).getCopyAPI().copyOut("COPY pharm_details_download TO STDOUT",fo );
	}
}
