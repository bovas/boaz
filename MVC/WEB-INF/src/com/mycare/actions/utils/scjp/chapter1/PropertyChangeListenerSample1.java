package com.mycare.actions.utils.scjp.chapter1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class PropertyChangeListenerSample1{
	private int[] testGrades;
	private PropertyChangeSupport support=new PropertyChangeSupport(this); 
	public int getTestGrades(int index) {
		return testGrades[index];
	}

	public void setTestGrades(int index,int indexValue) {
		this.testGrades[index] = indexValue;
		support.firePropertyChange("testGrades",this.testGrades[index],indexValue);
	}
	public void setSize(int size) {
		this.testGrades = new int[size];
	}
	public void propertyChange(PropertyChangeEvent evt){
		System.out.println("Property Changed for ::"+evt.getPropertyName());
		System.out.println("Old value :: Property Changed for ::"+evt.getOldValue());
		System.out.println("New Value :: Property Changed for ::"+evt.getNewValue());
	}
	public void addPropertyChangeListener(PropertyChangeListener listener){
		System.out.println("Value is going to be bound");
		support.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener){
		System.out.println("Value is going to be unbound");
		support.removePropertyChangeListener(listener);
	}
}
