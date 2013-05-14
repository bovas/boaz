package com.mycare.actions.utils.jvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WithoutNew implements Cloneable{
	public WithoutNew() {		
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, CloneNotSupportedException, FileNotFoundException, IOException {
		
		WithoutNew withoutNew1 =(WithoutNew)Class.forName("com.mycare.actions.utils.jvm.WithoutNew").newInstance();
				
		WithoutNew withoutNew2 = 	(WithoutNew) withoutNew1.clone();
		
		WithoutNew withoutNew3  = (WithoutNew) new ObjectInputStream(new FileInputStream("/home/bovas/output.txt")).readObject();
		
		
	}	
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
}
