package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class RxNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_Rx_NOT_FOUND = RxNotFoundException.class.getName() + " : Rx not found for store '%s', rx number '%s'";
	
    public RxNotFoundException( String storeNumber, String  rxNumber) 
    {
        super(ExceptionConstants.ErrorCode_Rx_NOT_FOUND, String.format(ErrorMessage_Rx_NOT_FOUND,storeNumber, rxNumber));
    }

}
