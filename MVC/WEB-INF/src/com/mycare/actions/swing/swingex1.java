package com.mycare.actions.swing;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;

public class swingex1  extends JFrame{
	public static void main(String args[]){
		JFrame jf= new JFrame();	//making a window
		JButton jb = new JButton();	//making a component
		jf.getContentPane().add(BorderLayout.EAST,jb); //adding comp to wind
		jf.setSize(300,300);
		jf.setVisible(true);
		JPanel jp1 =new JPanel();
		JPanel jp2 =new JPanel();
		jp2.add(new JButton("Button1"));
		jp2.add(new JButton("Button2"));
		jp2.add(new JButton("Button3"));
		jp1.add(jp2);
		jp1.setVisible(true);
	}
}