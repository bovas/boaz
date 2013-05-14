package com.mycare.actions.test;

import java.io.*;
import java.util.List;

import org.apache.axis.components.compiler.CompilerError;
import org.apache.axis.components.compiler.CompilerFactory;
import org.apache.axis.MessageContext;
import org.apache.axis.AxisEngine;

public class CompileJava {
	public static void main(String[] args){
		try {
			getThreadClassLoader();
			String realpath = "/usr/share/apache-tomcat-7.0.25/webapps/axis/AdvanceERxService.jws";
			String extension = ".jws";
			if (extension == null)
				extension = ".jws";

			if ((realpath != null) && (realpath.endsWith(extension)))
			{
			  String jwsFile = realpath;
			  String rel = "/usr/share/apache-tomcat-7.0.25/webapps/axis/AdvanceERxService";

			  File f2 = new File(jwsFile);
			  if (!f2.exists()) {
			    throw new FileNotFoundException(rel);
			  }
			  
			  if (rel.charAt(0) == '/') {
			    rel = rel.substring(1);
			  }

			  int lastSlash = rel.lastIndexOf('/');
			  String dir = null;

			  if (lastSlash > 0) {
			    dir = rel.substring(0, lastSlash);
			  }

			  String file = rel.substring(lastSlash + 1);

			  String outdir = "/usr/share/apache-tomcat-7.0.25/webapps/axis/WEB-INF/jwsClasses/";
			  if (outdir == null) outdir = ".";

			  if (dir != null) {
			    outdir = outdir + File.separator + dir;
			  }

			  File outDirectory = new File(outdir);
			  if (!outDirectory.exists()) {
			    outDirectory.mkdirs();
			  }
			  
			  
			  String jFile = outdir + File.separator + file.substring(0, file.length() - extension.length() + 1) + "java";

			  String cFile = outdir + File.separator + file.substring(0, file.length() - extension.length() + 1) + "class";

			    System.out.println("jFile: " + jFile);
			    System.out.println("cFile: " + cFile);
			    System.out.println("outdir: " + outdir);
			  

			  File f1 = new File(cFile);

			  String clsName = null;

			  if (clsName == null) clsName = f2.getName();
			  if ((clsName != null) && (clsName.charAt(0) == '/')) {
			    clsName = clsName.substring(1);
			  }
			  clsName = clsName.substring(0, clsName.length() - extension.length());
			  clsName = clsName.replace('/', '.');

			  

			  if ((!f1.exists()) || (f2.lastModified() > f1.lastModified()))
			  {	       
			    FileReader fr = new FileReader(jwsFile);
			    FileWriter fw = new FileWriter(jFile);
			    char[] buf = new char[4096];
			    int rc;
			    while ((rc = fr.read(buf, 0, 4095)) >= 0)
			      fw.write(buf, 0, rc);
			    fw.close();
			    fr.close();
			  
			    org.apache.axis.components.compiler.Compiler compiler = CompilerFactory.getCompiler();
			    compiler.setClasspath("/usr/share/apache-tomcat-7.0.25/webapps/axis/WEB-INF/jwsClasses");
			    compiler.setDestination(outdir);
			    compiler.addFile(jFile);
			    
			    boolean result = compiler.compile();
			    System.out.println(result);
			    if(!result){
			    List errors = compiler.getErrors();
		          int count = errors.size();
		          StringBuffer message = new StringBuffer();
		          System.out.println(count);
		          for (int i = 0; i < count; i++) {
		            CompilerError error = (CompilerError)errors.get(i);
		            if (i > 0) message.append("\n");
		            message.append("Line ");
		            message.append(error.getStartLine());
		            message.append(", column ");
		            message.append(error.getStartColumn());
		            message.append(": ");
		            message.append(error.getMessage());
		          }
		          System.out.println(message.toString());
			    }
			  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void getThreadClassLoader(){		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		System.out.println(System.getProperty("java.class.path"));		
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("com.ibm.websphere.servlet.application.classpath"));
		System.out.println(System.getProperty("ws.ext.dirs"));		
	}
}
