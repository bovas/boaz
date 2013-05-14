package com.mycare.actions.reflection;

import java.lang.reflect.*;
import java.util.HashSet;

public class reflection{
  public static void main(String str[]){
  HashSet set = new HashSet();
  Class classObj = set.getClass();
  Method[] methods = classObj.getMethods();
  for (int i = 0; i < methods.length; i++) {
 String methodName = methods[i].getName();
  System.out.println("Name: " + methodName);
  String returnString =
 methods[i].getReturnType().getName();
  System.out.println("Return Type: " + returnString);
  Class[] parameterTypes =
 methods[i].getParameterTypes();
  System.out.print("Parameter Types: ");
  for (int k = 0; k < parameterTypes.length; k ++) {
 String parameterName =
  parameterTypes[k].getName();
 System.out.print(" " + parameterName);
 }
 System.out.println();
  }
 }
}