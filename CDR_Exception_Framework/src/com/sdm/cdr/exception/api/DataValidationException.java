package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class DataValidationException extends CDRException{

	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_DATA_VALIDATION_ERROR = DataValidationException.class.getName() + " : Data validation error - %s";
	
    public DataValidationException(String error) 
    {
        super(ExceptionConstants.ErrorCode_DATA_VALIDATION_ERROR,String.format(ErrorMessage_DATA_VALIDATION_ERROR, error));
    }

}
