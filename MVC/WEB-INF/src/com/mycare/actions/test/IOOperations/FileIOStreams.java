package com.mycare.actions.test.IOOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOStreams {
	public static void main(String[] args) throws IOException {
		FileInputStream fin = new FileInputStream("/home/bovas/input.txt");
		FileOutputStream fop = new FileOutputStream("/home/bovas/output.txt");		
		int tempStr;
		long startTime = System.currentTimeMillis();
		System.out.println("Starting time::"+ startTime);
		while((tempStr=fin.read())!=-1){			
			fop.write(tempStr);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Ending time::"+ endTime);
		System.out.println("Total time "+ (endTime - startTime)+"ms");
		if(fin!=null)
			fin.close();
		if(fop!=null)
			fop.close();
	}
}
