package com.mycare.actions.test;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.NumberFormat;

public class CheckMemory {
	public static void main(String[] args) {
		 Runtime runtime = Runtime.getRuntime();

		    NumberFormat format = NumberFormat.getInstance();

		    StringBuilder sb = new StringBuilder();
		    long maxMemory = runtime.maxMemory();
		    long allocatedMemory = runtime.totalMemory();
		    long freeMemory = runtime.freeMemory();

		    sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
		    sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
		    sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
		    sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "<br/>");
		    System.out.println(sb.toString());
		    long diskSize = new File("/").getTotalSpace();
		    System.out.println(diskSize/(1024*1024*1024*1024));
		    long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		    System.out.println(memorySize/(1024*1024*1024*1024));
	}
}
