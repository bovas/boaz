package com.mycare.actions.utils.scjp.chapter9;

import static java.lang.System.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java7Features implements Closeable{	
	public static byte a=0b1111111;
	private static int integer = 10812_12;
	public static void main(String[] args)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		out.println(a);
		out.println(integer);
		Class myClass = Class.forName("org.postgresql.Driver");
		Java7Features java7 = (Java7Features)myClass.newInstance();
		switch("1"){
			case "1": out.println("New Java...");
		}
		try(InputStream is = new FileInputStream("");InputStream is1 = new FileInputStream("");Java7Features java71=new Java7Features();){
			new FileOutputStream("");													
		}catch(IllegalThreadStateException | IOException e){			
			out.println(e.getClass().getName());
		}finally{
			out.println("Finally");
		}			
		String newStr = new String();		
		Map<String,List<String>> list=new HashMap<>();
		Map newMap = new HashMap<String,String>();
	}
	private static File doSome()throws IllegalArgumentException{		
		out.println("Called me???");
		return new File("");
	}
	public void close() throws IOException {		
		
	}
}
