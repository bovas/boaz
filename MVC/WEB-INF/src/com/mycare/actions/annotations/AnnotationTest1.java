package com.mycare.actions.annotations;

/**@
 * @author
 * 	Name = "bovas",
 *  Created date = "01/08/2013",
 *	Modified date = "01/09/2013"
 */
public class AnnotationTest1 implements AnnotationInterface{	
	String value="";
	public void testMethod(){
		
	}
	
	
	/**@category
	 * @param
	 * @see
	 * @serialData
	 * @since
	 */
	@SuppressWarnings(value="unused")
	public void suppressWarnings(){		
		String value="";
	}
	
	@Override
	public void mySuperMethod(){}
	
	@CustomAnnotation(customInt = 0, customStringMethod = "",customDefaults=10)
	public void getCustomAnnotation(){
		
	}
}
