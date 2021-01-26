package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;


public class DispenseNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_DISPENSE_NOT_FOUND  = DispenseNotFoundException.class.getName() + " : Dispense Number : '%s' not found for store : '%s' ";
	
    public DispenseNotFoundException( String storeNumber, String  txNumber) 
    {
        super(ExceptionConstants.ErrorCode_DISPENSE_NOT_FOUND , String.format(ErrorMessage_DISPENSE_NOT_FOUND , txNumber, storeNumber));
    }

}
