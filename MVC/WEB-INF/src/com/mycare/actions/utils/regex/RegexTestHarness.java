package com.mycare.actions.utils.regex;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args){
        /*Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }*/
        //while (true) {

            Pattern pattern = 
            Pattern.compile("[\\sa-z'&&[^<.*>]]",Pattern.CASE_INSENSITIVE);            
            Matcher matcher = 
            pattern.matcher("bovas's <br> company312312 &*(^&*%^&$%^$% &* }{}{");

            boolean found = false;
            while (matcher.find()) {
            	System.out.print(matcher.group());
                /*System.out.println("I found the text "+matcher.group()+" starting at index "+matcher.start()+" and ending at index "+matcher.end());
                found = true;*/
            }
            if(!found){
            	//System.out.println("No match found.%n");
            }
        }    
}


