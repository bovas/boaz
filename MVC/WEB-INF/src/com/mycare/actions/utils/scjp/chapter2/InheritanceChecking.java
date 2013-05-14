package com.mycare.actions.utils.scjp.chapter2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class InheritanceChecking {
	public static void main(String[] args) throws IOException {
		Jammers jam = new Floozels();
		Klakker kl = new Quizels();
		FileOutputStream fos = new FileOutputStream(new File("/home/glace/object.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		ListIterator<List> ls;
		Iterator it;
	}
}
interface Jammers{
	Set<Quizels> q = null;
}
class Floozels implements Jammers{
	List<Floozets> fl;
} 
class Quizels implements Klakker{
	
}
interface Klakker{
	
}
class Floozets{
	
}

