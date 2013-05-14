package com.mycare.actions.test;

public class DoubleTheBackslash {
	public static void main(String[] args) {
	      /*String[] inputs = {"abc\\\\def", "abc\\def\\\\ghi"};*/
	      String[] inputs = {"abcd\\ef"};
	       
	      showReplacements(inputs, "(?<!\\)\\(?!\\)", "\\\\");
	      /*showReplacements(inputs, "(?<!\\\\)\\\\(?!\\\\)", "$0$0");
	      showReplacements(inputs, "([^\\\\])(\\\\)([^\\\\])", "$1\\\\\\\\$3");
	      showReplacements(inputs, "([^\\\\])(\\\\)([^\\\\])", "$1$2$2$3");*/
	   }
	    
	   private static void showReplacements(String[] inputs,
	         String regex, String replaceWith) {
	      for (String input : inputs) {
	        System.out.println(input);
	         System.out.println(input.replaceAll("\\\\", "\\\\\\\\"));
	        // System.out.println(input.replaceAll(regex, replaceWith));
	        System.out.println();
	      }
	   }
}
