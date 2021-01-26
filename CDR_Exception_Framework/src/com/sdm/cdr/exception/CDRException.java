package com.sdm.cdr.exception;

public class CDRException extends Exception {

	protected String errorCode;
	protected String exceptionName ;
	
  
	public CDRException()
    {
    	super();
    }
    
    public CDRException(String errorCode , final String msg ) 
    {
        super(msg);
        this.errorCode = errorCode;
        
        Object obj = this;
        
        this.exceptionName = obj.getClass().getSimpleName();  
        		
        		//exceptionName ;
    }
	
    public String getErrorCode()
    {
    	return errorCode;
    }
    
    public String getExceptionName() {
  		return exceptionName;
  	}

  	public void setExceptionName(String exceptionName) {
  		this.exceptionName = exceptionName;
  	}

    
}
