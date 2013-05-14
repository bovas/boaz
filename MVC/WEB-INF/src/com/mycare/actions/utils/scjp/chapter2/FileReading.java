package com.mycare.actions.utils.scjp.chapter2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {
	public static void main(String[] args) throws IOException {
		//FileInputStream fis = new FileInputStream(new File("/home/bovas/MUEPrescription.txt"));
		FileReader reader = new FileReader(new File("/home/bovas/MUEPrescription.txt"));
		BufferedReader br = new BufferedReader(reader);
		FileOutputStream fos =new FileOutputStream(new File("/home/bovas/copyresult.txt"));
		new File("/home/bovas/copyresult.txt").createNewFile();
		String str;
		while((str = br.readLine())!=null){
			System.out.println(str);
			//fos.write(str);
		}
	}
}
