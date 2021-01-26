package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;



public class CustomerChannelNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CUSTOMER_CHANNEL_NOT_FOUND = CustomerChannelNotFoundException.class.getName();// + " : for store '%s' , patient id '%s',  custID = '%s', userID = '%s' ";
	
	

    public CustomerChannelNotFoundException(String storeNumber, String patientId,String custId,String userId) 
    {
    	super(ExceptionConstants.ErrorCode_CUSTOMER_CHANNEL_NOT_FOUND,ErrorMsgConstants.getMsg(ErrorMessage_CUSTOMER_CHANNEL_NOT_FOUND,storeNumber,patientId ,custId ,userId));
    }
    
   
}

