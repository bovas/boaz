package com.mycare.actions.preparation;

public class ReverseString {
	public static void main(String[] args) {
		System.out.println(reverse("i am a boy"));
		System.out.println(reverseByBuffer("i am a boy"));
		System.out.println(reverseRecursivly("malayalam"));
		System.out.println(reverseByWord("India is a good country."));
	}
	private static String reverse(String string) {
		StringBuilder builder = new StringBuilder();
		char[] charactors = string.toCharArray();
		for(int i=charactors.length-1;i>=0;i--){
			builder.append(charactors[i]);
		}
		return builder.toString();
	}	
	private static String reverseByBuffer(String string){
		StringBuffer buf  = new StringBuffer(string);
		return buf.reverse().toString();
	}
	private static String reverseRecursivly(String string){		
		if(string.length() < 2)
			return string;
		return reverseRecursivly(string.substring(1))+ string.charAt(0);
	}
	private static String reverseByWord(String string){
		String[] arrayWord = string.split(" ");
		StringBuilder builder= new StringBuilder(); 
		for(int i=0;i< arrayWord.length;i++)
			builder.append(reverse(arrayWord[i])).append(" ");						
		return builder.toString();
	}
}
