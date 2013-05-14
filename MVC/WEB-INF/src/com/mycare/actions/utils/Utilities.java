package com.mycare.actions.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

public class Utilities {
	public static ArrayList<HashMap<String, String>> changeRStoAL(ResultSet rs) throws Exception{
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> row;
		int rowCount=0;
		ResultSetMetaData rsmt = rs.getMetaData();
		int cols= rsmt.getColumnCount();
		while(rs.next()){
			row=new HashMap<String,String>();
			for(int count=1;count<=cols;count++){
				if(rs.getString(cols) != null && rs.getString(cols) != "")
					row.put(rsmt.getColumnName(count),rs.getString(count));
				else
					row.put(rsmt.getColumnName(count),"");
			}
			result.add(rowCount++,row);
		}
		if(rs!=null)
			rs.close();
		rs = null;
		return result;
	}
	public static String Nz(String xVal1,String xVal2){
		if(xVal1==null)
			return xVal2;
		else			
			return xVal1;
	} 
	public static void writeIntoAjaxResult(Object o,HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json; charset=UTF-8");
		resp.setHeader("Cache-Control","no-cache");
		PrintWriter stream = resp.getWriter();
		if(o!=null)
			stream.write(o.toString());
		if(stream!=null)
			stream.close();
		stream = null;		
	}	
}
