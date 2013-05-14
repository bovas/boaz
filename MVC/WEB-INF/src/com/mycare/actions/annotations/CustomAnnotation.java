package com.mycare.actions.annotations;

public @interface CustomAnnotation{
	String customStringMethod();
	int customInt();
	int customDefaults() default 1;
	String customDefaultStr() default "N/A";
}
