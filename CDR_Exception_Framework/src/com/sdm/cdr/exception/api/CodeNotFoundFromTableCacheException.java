package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.ExceptionConstants;

public class CodeNotFoundFromTableCacheException extends CDRInternalException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CODE_NOT_FOUND_FROM_TABLE_CACHE = CodeNotFoundFromTableCacheException.class.getName() + " : Key '%s' not found from table '%s' ";
	
    public CodeNotFoundFromTableCacheException( String tableName, Long key ) 
    {
        super(ExceptionConstants.ErrorCode_CODE_NOT_FOUND_FROM_TABLE_CACHE,String.format(ErrorMessage_CODE_NOT_FOUND_FROM_TABLE_CACHE,key.toString(), tableName )  );
        
    }
}
