package com.mycare.actions.utils.scjp.chapter7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ComparableCheck {
	public static void main(String[] args) throws IOException {
		ArrayList<DVDInfo> dvdList = new ArrayList<DVDInfo>();
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/bovas/music.txt")));
		String s;
		while((s = br.readLine())!=null){			
			String[] str =s.split("\\s+");
			String title="",genre="",actor="";
			int len=str.length;
			for(int count=0;count<len;count++){
				System.out.println(str[count]+"---><"+len);
				if(count<len-2){
					if(!str[count].trim().equals(""))
						title+=str[count]+" ";
				}
				else if(count<len-1){
					genre=str[count].replaceAll("/", " ");					
				}
				else if(count==len-1){
					actor=str[count];
					DVDInfo temp = new DVDInfo(title,genre,actor);
					dvdList.add(temp);
				}
			}
		}
		System.out.println(dvdList);
	}
}
class DVDInfo{
	String title,genre,actor;
	public DVDInfo(String string, String string2, String string3) {
		title = string;
		genre = string2;
		actor = string3;
	}
	@Override
	public String toString() {
		return title+" "+genre+" "+actor+"\t";
	}
}