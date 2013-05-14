package com.mycare.actions.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionsTest1 {

	public static void main(String[] args) {
		String str="hello    man how are you *&^&*%^*&%^&% <html> 126378612798631";
		System.out.println("After white space remove:: "+(str = trimWhiteSpace(str)));
		System.out.println("Words only::"+ (str = simplePatternCheck(str)));
	}
	static String simplePatternCheck(String str){	//| (\\w+)										
		Pattern pattern = Pattern.compile("^<.*>");
		Matcher matcher = pattern.matcher(str);
		StringBuilder builder= new StringBuilder();
		while(matcher.find()){			
			String wordString = matcher.group();
			System.out.println(wordString);
			if(wordString.matches("\\D+"))
				builder.append(wordString+" ");
		}		
		return builder.toString();
	}
	static String trimWhiteSpace(String str){
		return str = str.replaceAll("\\s+", " ");		//White space removing
	}
}
