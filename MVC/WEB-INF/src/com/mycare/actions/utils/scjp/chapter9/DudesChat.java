package com.mycare.actions.utils.scjp.chapter9;

class Dudes {

	static long flag = 0;

	synchronized void chat(long id){

	if(flag == 0) flag = id;

	for(int x = 1; x < 3; x++) {		
		if(flag == id) System.out.println(Thread.currentThread().getId()+" yo ");

		else System.out.println(Thread.currentThread().getId()+" dude ");

	}

}
}
public class DudesChat implements Runnable {

	static Dudes d;

	public static void main(String[] args) {

		new DudesChat().go();

	}

	void go() {

		d = new Dudes();
		new Thread(new DudesChat()).start();

		new Thread(new DudesChat()).start();

		}

		public void run() {

		d.chat(Thread.currentThread().getId());		
		}
}

