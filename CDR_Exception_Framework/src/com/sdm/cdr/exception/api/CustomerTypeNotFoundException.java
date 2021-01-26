package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;

public class CustomerTypeNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CUSTOMER_TYPE_NOT_FOUND = CustomerTypeNotFoundException.class.getName() ;
	//+ " : for store '%s' , patient id '%s',  custID = '%s', userID = '%s' ";
	
	
	
	

    public CustomerTypeNotFoundException(String storeNumber, String patientId,String custId,String userId) 
    {
    	//String storeNumber, String patientId,String custId,String userId
    	super(ExceptionConstants.ErrorCode_CUSTOMER_TYPE_NOT_FOUND,ErrorMsgConstants.getMsg(ErrorMessage_CUSTOMER_TYPE_NOT_FOUND,storeNumber,patientId,custId,userId ));

    	//super(ExceptionConstants.ErrorCode_CUSTOMER_TYPE_NOT_FOUND,String.format(ErrorMessage_CUSTOMER_TYPE_NOT_FOUND, storeNumber , patientId , custId ,userId));
    }
    
   
}

