package com.mycare.actions.test.IOOperations;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamChecking {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("/home/bovas/input.txt");
		FileWriter fw = new FileWriter("/home/bovas/output.txt");
		int i;
		long startTime = System.currentTimeMillis();
		System.out.println("Starting time::"+ startTime);
		while((i=fr.read())!=-1){			
			fw.write(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Ending time::"+ endTime);
		System.out.println("Total time "+ (endTime - startTime)+"ms");
		if(fr!=null)
			fr.close();
		if(fw!=null)
			fw.close();
	}
}	
