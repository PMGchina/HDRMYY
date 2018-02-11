package com.beamofsoul.springboot.management.util;

@FunctionalInterface
public interface Callback {
	
	static final String CALLBACK_PLACEHOLDER = "CALLBACK_PLACEHOLDER";
	
	public void doCallback(Object... objects); 
}
