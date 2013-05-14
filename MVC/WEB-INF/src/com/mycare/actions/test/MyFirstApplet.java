package com.mycare.actions.test;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MyFirstApplet extends JApplet{
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {						
			public void run() {
				JLabel label = new JLabel();		
			}
		});
	}
}
