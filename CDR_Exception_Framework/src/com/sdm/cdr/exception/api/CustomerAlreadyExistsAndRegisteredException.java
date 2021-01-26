package com.sdm.cdr.exception.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;


public class CustomerAlreadyExistsAndRegisteredException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_CUSTOMER_EXIST_AND_REGISTERED = CustomerAlreadyExistsAndRegisteredException.class.getName();// + " : Customer already exists and Registered for store '%s' , patient id '%s',  custID = '%s', userID = '%s'  ";
	
    public CustomerAlreadyExistsAndRegisteredException(String storeNumber, String patientId,String custId,String userId) 
    {
        super(ExceptionConstants.ErrorCode_CUSTOMER_EXIST_AND_REGISTERED,ErrorMsgConstants.getMsg(ErrorCode_CUSTOMER_EXIST_AND_REGISTERED, storeNumber ,patientId , custId ,userId ));
    }
    
   
    
}
