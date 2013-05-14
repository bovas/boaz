package com.mycare.actions.utils.scjp.chapter9;

import java.lang.reflect.Method;
import java.util.Map;

public class AnalyzeThreads extends Thread{
	public static void main(String[] args) throws ClassNotFoundException {		
		/*Class threadClass = Thread.class;
		Method mthod[] = threadClass.getMethods();
		for(Method mthodNew:mthod)
			System.out.println(mthodNew.getName());*/
		
		AnalyzeThreads analyze = new AnalyzeThreads(); 
		Thread thread= new Thread(analyze);
		
		//thread.setDaemon(true);
		
		thread.setPriority(MAX_PRIORITY);
		
		thread.start();
		
		//thread.run();	//running the run method of the target
		
		System.out.println(thread.toString());	//overridden of toString - Thread name+priority+Threads orignal name
		
		System.out.println("Is interrupted :: "+thread.isInterrupted());  //Checks whether its interrupted
		
		System.out.println("Current Thread:: "+Thread.currentThread());  //current thread
		
		System.out.println("Thread name::"+thread.getName());	//Name of the thread
		
		ThreadGroup group= thread.getThreadGroup();
		//System.out.println("Group name::"+group.getName());	//Name of the thread group
		
		StackTraceElement[] elt =  thread.getStackTrace();
		for(StackTraceElement element:elt)
			System.out.println("Stack Trace::"+element.getFileName());
		
		//assert Thread.holdsLock(analyze);
		System.out.println(Thread.holdsLock(analyze));		//whether it holds the lock on object
		
		//Thread.dumpStack(); //Printing stack trace with new Exception only for debugging
		
		System.out.println("Active threads:;"+Thread.activeCount());	//for debugging and monitoring
		
		thread.checkAccess();
		
		//thread.destroy();	//remove the method
		
		Thread[] threadArray = {new Thread(),new Thread()};
		Thread.enumerate(threadArray);
		
		Map<Thread,StackTraceElement[]> stackTraceMap=Thread.getAllStackTraces();
		
	}
	@Override
	public synchronized void run() {		
		System.out.println("I am in running state");
		try {
			System.out.println("Going to sleep");
			//Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
