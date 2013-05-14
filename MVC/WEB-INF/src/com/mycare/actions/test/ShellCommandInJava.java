package com.mycare.actions.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellCommandInJava {
	public static void main(String[] args) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("host -t a "+args[0]);
			BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer sb = new StringBuffer();			
			String line = br.readLine();
			sb.append(line);
			System.out.println();
			while (line != null) {
				line = br.readLine();
				sb.append(line);
			}
		} catch (Exception e) {

		}
	}
}
