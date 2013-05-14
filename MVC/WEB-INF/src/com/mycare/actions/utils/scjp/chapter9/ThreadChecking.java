package com.mycare.actions.utils.scjp.chapter9;

public class ThreadChecking extends Thread{
	public static void main(String[] args) throws InterruptedException {		
		/*StackTraceElement[] tracedElts =  new Exception().getStackTrace();
		String Message="";
		for(StackTraceElement elt : tracedElts){
			Message+="ClassName: "+elt.getClassName()+"\n";
			Message+="FileName: "+elt.getFileName()+"\n";
			Message+="LineNumber: "+elt.getLineNumber()+"\n";
			Message+="MethodName: "+elt.getMethodName()+"\n";
		}		
		System.out.println(Message);*/			
		/*for(int i=0;i<10;i++){			
			ThreadChecking threadCheck = new ThreadChecking();
			//threadCheck.start();			
			System.out.println("Count::"+i+"---"+threadCheck.isAlive()+" <----> "+threadCheck.getState());
		}*/
		System.out.println("Current thread Name::"+Thread.currentThread().getName());
		//Thread mythread=new Thread(new MyRunnable());
		new Thread(new ThreadChecking()).start();
		//mythread.setName("Bovas's thread");
		//mythread.start();
	}		
	public void run() {	
		try {
			for(int count=0;count<3;++count)
				System.out.println(count+"..");
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("This is my thread method");
	}	
}
