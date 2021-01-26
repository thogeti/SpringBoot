package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;


public class InvalidInputException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_INVALID_INPUT = InvalidInputException.class.getName() + " : Invalid Input. please verify your input request ";
	
    public InvalidInputException(  ) 
    {
        super(ExceptionConstants.ErrorCode_INVALID_INPUT,ErrorMessage_INVALID_INPUT);
        
    }
    
    public InvalidInputException(String customErrMsg) 
    {
        super(ExceptionConstants.ErrorCode_INVALID_INPUT, customErrMsg);
        
    }    

}
