package com.mycare.actions.test;

import java.lang.reflect.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.io.*;
import org.apache.commons.codec.net.BCodec;
import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.*;

public class test1 {
	 public static void main(String args[])throws Exception {
		 
		 /*File f=new File("/home/bovas/test");
		 boolean status=removeDirectory(f);
		 System.out.println(status);
		 */
		// System.out.println(System.getProperty("java.home"));
		 Integer c=100,d=100;
		 System.out.println(c==d);
		 Properties props = System.getProperties();
		 Enumeration names = props.propertyNames();
	        while (names.hasMoreElements()) {
	          String name = (String)names.nextElement();
	          String value = props.getProperty(name);
	          System.out.println(name+"::"+value);
	      }
	        System.out.println(System.getProperty("org.apache.commons.logging.LogFactory"));
		 //String x=null;
		 /*String y ="";
		 String x = "";
		// System.out.println(y.length());
		 String result="@!@0@!@@!@";
		 String result1[] = result.split("@!@");
		 for(int count=0; count<result1.length;count++)
			 System.out.println(count+"---->"+result1[count]);*/
		  /*  String statement = "CDT|A|COPAY-103|11|A|R||||0.2||0|9999999.99||1|4";

		    String tokens[] = null;
		    Pattern p  = Pattern.compile("\\|");		    
		    tokens = p.split(statement,-2);	
		   String tokens1="";
		   String temp[]=new String[18];
			for(int j=0;j<16;j++){
				try{
					temp[j]=tokens[j];
				}catch(ArrayIndexOutOfBoundsException e){
					e.printStackTrace();
					temp[j]="";
				}System.out.println(temp[j]);
				}				
				
		  }*/
	/*public static void main(String args[]){
		test1 test = new test1();
		test2 t2 = new test2();		
		//test.getModi(t2.getClass());
		
	}
	public void getModi(Class cs){				
		int modifier = cs.getModifiers();
		System.out.println(modifier);
		System.out.println("abstract--->"+Modifier.ABSTRACT);
		System.out.println("static----->"+Modifier.STATIC);
		System.out.println("public----->"+Modifier.PUBLIC);
		System.out.println("protected----->"+Modifier.PROTECTED);
		System.out.println("private----->"+Modifier.PRIVATE);
		System.out.println("NATIVE----->"+Modifier.NATIVE);
		System.out.println("VOLATILE----->"+Modifier.VOLATILE);
		System.out.println("FINAL----->"+Modifier.FINAL);
		System.out.println("INTERFACE----->"+Modifier.INTERFACE);
		System.out.println("STRICT----->"+Modifier.STRICT);
		System.out.println("TRANSIENT----->"+Modifier.TRANSIENT);
		System.out.println("SYNCHRONIZED----->"+Modifier.SYNCHRONIZED);		
	}*/
}
	 public static boolean removeDirectory(File directory) {
	      File entry = new File("/home/bovas/test/test.xml");
	      if(entry.exists())
	    	  System.out.println(entry.delete());		  
		  return directory.delete();
		}
		public String getAdvanceeRxEncodedCredentials()
		{
			String returnValue="";
		/*	try
			{
				String str=new String("SURESCRIPTS-XHUB:TEITB4R26Q".getBytes(),"UTF-8");
				byte toEncode[]=str.getBytes();
				byte result[]=this.doEncoding(toEncode);
				returnValue="Basic "+new String(result);
			}catch(java.io.UnsupportedEncodingException e){e.printStackTrace();}
			catch(org.apache.commons.codec.EncoderException ee){ee.printStackTrace();}
		*/	return returnValue;
		}
}
/*class test2{
	
}*/
/*class Base
{ 
    Base()
    {
        System.out.print("Base");
    }
}
public class test1 extends java.util.TreeSet
{
	 public static void main(String args[])
	    {
		  java.util.TreeSet t = new java.util.TreeSet();
	        t.clear();
	    }
	 public void clear() 
	    {
	        TreeMap m = new TreeMap();
	        m.clear();
	    }
}*/