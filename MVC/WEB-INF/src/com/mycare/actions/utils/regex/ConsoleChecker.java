package com.mycare.actions.utils.regex;

import java.io.Console;

public class ConsoleChecker {
	public static void main(String[] args) {
		Console console = sun.misc.SharedSecrets.getJavaIOAccess().console();		
		System.out.println(console);
	}
}
