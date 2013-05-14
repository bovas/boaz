package com.mycare.actions.utils.scjp.chapter7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FilesTest {
	public static void main(String[] args) throws Exception {		
		File file = new File("/home/bovas/FileConcept.txt");		
		System.out.println("File existing ::"+file.exists());
		unwrapPackage();		
	}
	static void unwrapPackage() throws IOException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("1.System search");
		System.out.println("2.Custom search");		
		String option = scanner.nextLine();		
		System.out.println("Package name:");				
		String packageName=scanner.nextLine();
		packageName = packageName.replaceAll("\\.", "/");
		if(option.equals("1")){
			String bootedLibs = System.getProperty("sun.boot.class.path");
			StringTokenizer tokenizer = new StringTokenizer(bootedLibs,":");
			while(tokenizer.hasMoreTokens()){
				String bootedJar = tokenizer.nextToken();
				if(bootedJar.endsWith(".jar") && new File(bootedJar).exists()){
					getJarEntries(bootedJar,packageName);
				}
			}
			
		}else if(option.equals("2")){
			System.out.println("Jar name with path:");		
			String jarName = scanner.nextLine();
			if(new File(jarName).exists())
				getJarEntries(jarName,packageName);
		}else{
			System.exit(1);
		}
		if(scanner!=null)
			scanner.close();
	}
	static void getJarEntries(String jarName,String packageName) throws IOException{
		System.out.println();
		System.out.println("jarName::"+jarName);
		System.out.println("packageName::"+packageName);
		ZipFile zipFile = new JarFile(jarName);		
		Enumeration zipEntries = zipFile.entries();
		while(zipEntries.hasMoreElements()){
			ZipEntry entry = (ZipEntry) zipEntries.nextElement();
			String className = entry.getName();
			if(className.startsWith(packageName)){
				System.out.println(className);
			}
		}
	}
	static void printSystemProps(){
		Properties prop = System.getProperties();
		Set set = prop.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
