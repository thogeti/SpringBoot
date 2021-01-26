package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.ExceptionConstants;

public class KeyNotFoundFromTableCacheException extends CDRInternalException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_KEY_NOT_FOUND_FROM_TABLE_CACHE = KeyNotFoundFromTableCacheException.class.getName() + " : code '%s' not found from table '%s' ";
	
    public KeyNotFoundFromTableCacheException(String tableName, String code) 
    {
        super(ExceptionConstants.ErrorCode_KEY_NOT_FOUND_FROM_TABLE_CACHE,String.format(ErrorMessage_KEY_NOT_FOUND_FROM_TABLE_CACHE,code, tableName ));
        
    }
    
    
    
}
