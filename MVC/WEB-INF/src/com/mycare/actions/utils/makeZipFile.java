package com.mycare.actions.utils;

import java.io.*;
import java.util.zip.*;

class  makeZipFile {
    public static void main(String[] args) {
        if (args.length != 2) {
				System.out.println("Usage: java makeZipFile [files to be zipped] [filename after zip] ");
            return;
        }
        try {
            String filename = args[0];
            String zipfilename = args[1];
            System.out.println("filename\t"+filename);
            System.out.println("\nzipfilename\t"+zipfilename);
            makeZipFile list = new makeZipFile( );
            list.doZip(filename,zipfilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doZip(String filename,String zipfilename) {
        try {
            byte[] buf = new byte[1024];
            FileInputStream fis = new FileInputStream(filename);
            fis.read(buf,0,buf.length);

            CRC32 crc = new CRC32();
            ZipOutputStream s = new ZipOutputStream(
                    (OutputStream)new FileOutputStream(zipfilename));

            s.setLevel(6);

            ZipEntry entry = new ZipEntry(filename);
            entry.setSize((long)buf.length);
            crc.reset();
            crc.update(buf);
            entry.setCrc( crc.getValue());
            s.putNextEntry(entry);
            s.write(buf, 0, buf.length);
            s.finish();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
