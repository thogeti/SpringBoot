package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class SourceSystemNotValidException extends CDRException {

	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_SOURCE_SYSTEM_NOT_VALID  = SourceSystemNotValidException.class.getName() + " : source system '%s' has not been registered";
	
    public SourceSystemNotValidException( String sourceSys) 
    {
        super(ExceptionConstants.ErrorCode_SOURCE_SYSTEM_NOT_VALID , String.format(ErrorMessage_SOURCE_SYSTEM_NOT_VALID ,sourceSys));
    }

}
