package com.mycare.actions.utils.scjp.chapter7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class StreamTesting {
	public static void main(String[] args) throws Exception {
		File file = new File("/home/bovas/FileConcept.txt");
		
		FileReader fr = new FileReader(file);
		int a;
		while((a = fr.read())!=-1){
			System.out.print((char)a);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		FileWriter fw = new FileWriter(file,true);
		fw.write("String writing");
		if(fw!=null){
			fw.close();
			fw = null;
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
		bw.write("");
	}
}
