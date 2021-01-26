package com.sdm.cdr.exception;

import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

public class CDRInternalException extends CDRException {

private static final long serialVersionUID = 1L;
	
	
    public CDRInternalException( String errorCode , final String msg )  
    {
        super(errorCode , msg );
        
    }
    
    
}
