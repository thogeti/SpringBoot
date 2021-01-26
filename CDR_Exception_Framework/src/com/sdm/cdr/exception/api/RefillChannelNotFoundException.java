package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class RefillChannelNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_REFILL_CHANNEL_NOT_FOUND = RefillChannelNotFoundException.class.getName() + " : for store '%s', RxNum '%s',  CorrelID = '%s' ";
	
	

    public RefillChannelNotFoundException(String storeNumber, String rxnum,String corrId) 
    {
        super(ExceptionConstants.ErrorCode_REFILL_CHANNEL_NOT_FOUND,String.format(ErrorMessage_REFILL_CHANNEL_NOT_FOUND, storeNumber ,rxnum ,corrId));
    }
    
   
}

