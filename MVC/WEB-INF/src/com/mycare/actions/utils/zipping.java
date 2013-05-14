package com.mycare.actions.utils;

import java.io.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.util.zip.*;
public class  zipping{
	public static void main(String args[])throws Exception{
String filename1 ="E:/Chocolate Cake.bmp";
String filename2="E:/Da Vinci.bmp";
String filename3="E:/Birthday Cake.bmp";
String[] filenames = new String[]{filename1,filename2,filename3};

// Create a buffer for reading the files
byte[] buf = new byte[1024];

try {
    // Create the ZIP file
    String outFilename = "F:/outfile.zip";
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));

    // Compress the files
    for (int i=0; i<filenames.length; i++) {
        FileInputStream in = new FileInputStream(filenames[i]);

        // Add ZIP entry to output stream.
        out.putNextEntry(new ZipEntry(filenames[i]));

        // Transfer bytes from the file to the ZIP file
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        // Complete the entry
        out.closeEntry();
        in.close();
    }

    // Complete the ZIP file
    out.close();
} catch (IOException e) {
	System.out.println("Error------->"+e);
}

}
}