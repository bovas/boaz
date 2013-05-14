package com.mycare.actions.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos =new FileOutputStream("/home/bovas/serialization.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);		
		employee ee = new employee();
		ee.setName("bovas");
		oos.writeObject(ee);
		oos.close();
		
		fos =new FileOutputStream("/home/bovas/serialization.txt");
		ObjectOutputStream oos1 = new ObjectOutputStream(fos);
		ee.setName("bovasnew");
		oos1.writeObject(ee);
		oos1.close();
		
		fos.close();
		
		FileInputStream fin = new FileInputStream("/home/bovas/serialization.txt");
		ObjectInputStream ois = new ObjectInputStream(fin);
		employee eeee = (employee) ois.readObject();
		System.out.println("Name:;"+eeee.getName());
	}
}
class employee implements java.io.Serializable{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
