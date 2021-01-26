package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.ExceptionConstants;

public class ConsumerIdMissingException extends CDRInternalException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CONSUMER_ID_MISSING = ConsumerIdMissingException.class.getName() + " ConsumerId is missing in canonical for entity : %s  ";
	
    public ConsumerIdMissingException( String canonicalEntity ) 
    {
        super(ExceptionConstants.ErrorCode_CONSUMER_ID_MISSING,String.format(ErrorMessage_CONSUMER_ID_MISSING , canonicalEntity )  );
        
    }
    
}
