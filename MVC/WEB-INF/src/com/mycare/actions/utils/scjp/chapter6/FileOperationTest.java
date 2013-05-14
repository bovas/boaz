package com.mycare.actions.utils.scjp.chapter6;

import java.io.File;
import java.io.IOException;

public class FileOperationTest {
	public static void main(String[] args) throws IOException {
		File file = new File("/home/bovas/filetest.txt");
		if(!file.exists())
			file.createNewFile();
		
	}
}
