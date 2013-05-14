package com.mycare.actions.test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class SwingEx1 {
	public static void main(String args[]){
	JFrame jf= new JFrame();	//making a window		
	JPanel jp1 =new JPanel();
	JPanel jp2 =new JPanel();
	jp2.add(new JButton("Button1"));
	jp2.add(new JButton("Button2"));
	jp2.add(new JButton("Button3"));
	jp1.add(jp2);
	JButton jb = new JButton("Click me");	//making a component
	jb.add(jp1);
	jf.getContentPane().add(BorderLayout.CENTER,jb); //adding comp to wind
	jf.setSize(300, 300);
	jf.setVisible(true);
	}
}
