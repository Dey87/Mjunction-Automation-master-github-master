package com.baseClasses;

public class AutomationException extends RuntimeException{
	private String errorMsg;
	
	public AutomationException(String paramString)
	  {
	    System.out.println("Error Message : " + paramString);
	    this.errorMsg = paramString;
	    
	    throw new AssertionError("Error Message : " + paramString);
	    
	  }
}
