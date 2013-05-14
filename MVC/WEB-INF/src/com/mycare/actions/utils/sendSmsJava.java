package com.mycare.actions.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class sendSmsJava {
	public static void main(String[] args) {

		try
		{
			String MATRIXURL = "http://www.smsmatrix.com/matrix";
			String PHONE = "9994723478";
			String USERNAME = "bovas@hotmail.com";
			String PASSWORD = "pass72727";
			String TXT = "This is a test, pls ignore";

			String q = "username=" + URLEncoder.encode (USERNAME, "UTF-8");
			q += "&" + "password=" + URLEncoder.encode (PASSWORD, "UTF-8");
			q += "&" + "phone=" + PHONE;
			q += "&" + "txt=" + URLEncoder.encode (TXT, "UTF-8");

			URL url = new URL (MATRIXURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput (true);
			OutputStreamWriter wr = new OutputStreamWriter (conn.getOutputStream());
			wr.write (q);
			wr.flush();

			BufferedReader rd = new BufferedReader (new InputStreamReader (conn.getInputStream()));
			String line;
			System.out.println ("Matrix API Response :");
			while ((line = rd.readLine()) != null) { System.out.println (line); }
			wr.close();
			rd.close();
		} catch (Exception e) { }
	}
}
