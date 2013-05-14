package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class Student1 implements Comparable{
	int rollno;
	String name;
	int age;
	Student1(int rollno,String name,int age){
		this.rollno=rollno;
		this.name=name;
		this.age=age;
	}

	public int compareTo(Object obj){
		//System.out.println("age::"+age);
		Student1 st=(Student1)obj;
		if(age==st.age)
			return 0;
		else if(age>st.age)
			return 1;
		else
			return -1;
	}
	
}
public class Student{
	public static void main(String args[]){

		ArrayList<Student1> al=new ArrayList();
		al.add(new Student1(101,"Vijay",23));
		al.add(new Student1(106,"Ajay",27));
		al.add(new Student1(105,"Jai",21));
		countRunAndMakeAscending(al.toArray(),0,al.size());
		Collections.sort(al);
		Iterator itr=al.iterator();
		while(itr.hasNext()){
			Student1 st=(Student1)itr.next();
			System.out.println(st.rollno+""+st.name+""+st.age);
		}		
	}
	 private static int countRunAndMakeAscending(Object[] a, int lo, int hi) {
	        assert lo < hi;
	        int runHi = lo + 1;
	        if (runHi == hi)
	            return 1;

	        // Find end of run, and reverse range if descending
	        if (((Comparable) a[runHi++]).compareTo(a[lo]) < 0) { // Descending
	            while (runHi < hi && ((Comparable) a[runHi]).compareTo(a[runHi - 1]) < 0)
	                runHi++;
	            //reverseRange(a, lo, runHi);
	        } else {                              // Ascending
	            while (runHi < hi && ((Comparable) a[runHi]).compareTo(a[runHi - 1]) >= 0)
	                runHi++;
	        }

	        return runHi - lo;
	   }
}
