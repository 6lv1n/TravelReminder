package com.travelreminder.android22.Exceptions;


public class NoCooException extends Exception {

	private static final long serialVersionUID = 2619920862738701661L;
	
	public NoCooException() {}  

	public NoCooException(String message) {  
		super(message); 
	}  

	public NoCooException(Throwable cause) {  
		super(cause); 
	}  
 
	public NoCooException(String message, Throwable cause) {  
		super(message, cause); 
	} 
	
}
