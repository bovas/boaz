package com.mycare.actions.test.IOOperations;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalizations {
	public static void main(String[] args) {
		Locale locale = new Locale("fr","FR");
		ResourceBundle rs = ResourceBundle.getBundle("com.mycare.actions.test.IOOperations.InputProperties",locale);
		System.out.println(rs.getString("greetings"));
		System.out.println(rs.getString("question1"));
		System.out.println(rs.getString("question2"));
	}
}
