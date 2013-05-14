package com.mycare.actions.utils.regex;



public class TrailingSpace {
    public static void main(String[] args) {
	String text = "     tattarrattat     ";

	//
	// Using a regular expression to remove only the trailing white space in
	// a string
	//
	text = text.replaceAll("^\\s+", "");
	System.out.println("Text:" + text+":");
	        StringBuffer buffer=new StringBuffer();
	        int sz = text.length();
	        for (int i=0; i<sz; i++) {
	            if(!Character.isWhitespace(text.charAt(i) ) ) {
	                buffer.append(text.charAt(i) );
	            }
	        }
	        System.out.println("::"+buffer.toString()+"::");

    }
}
