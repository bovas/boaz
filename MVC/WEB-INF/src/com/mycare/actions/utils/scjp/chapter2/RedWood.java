package com.mycare.actions.utils.scjp.chapter2;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RedWood extends Tree implements Serializable/*,Externalizable*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8184174126114160706L;
	private String myname;
	private String state;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		RedWood rw1 = new RedWood();
		rw1.myname="Krrish";
		rw1.state="initial";
		new File("/home/glace/object.txt").createNewFile();
		FileOutputStream fos = new FileOutputStream(new File("/home/glace/object.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(rw1);
		
		
		InputStream in = new FileInputStream(new File("/home/glace/object.txt"));
		ObjectInputStream ois = new ObjectInputStream(in);
		RedWood rw = (RedWood)ois.readObject();
		System.out.println(rw.myname +"  "+rw.state);
		//new RedWood().go();
	}
	public void go(){
		//RedWood r1 =(RedWood) new Tree();
	}
	public void callMyMethod(Tree t1,RedWood r1){
		
	}	
	public void writeExternal(ObjectOutput out) throws IOException {		
		out.writeObject(this.state);
		//out.writeObject(this.myname);
	}	
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		state =(String) in.readObject();
		myname =(String) in.readObject();
	}	
}
class Tree{
	
}
