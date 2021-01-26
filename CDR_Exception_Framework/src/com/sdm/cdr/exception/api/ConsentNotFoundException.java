package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;

public class ConsentNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CONSENT_NOT_FOUND = ConsentNotFoundException.class.getName() + " : Error while creating Consent  customer ID '%s' with contype '%s' : '%s' ";
	
	

    public ConsentNotFoundException(Long patientKey,String consumerid,String contype, String message) 
    {
    	 super(ExceptionConstants.ErrorCode_CONSENT_RES_INVALID_INPUT,String.format(ErrorMessage_CONSENT_NOT_FOUND, patientKey.toString() , consumerid,contype , message ));
    }
    
   
}
