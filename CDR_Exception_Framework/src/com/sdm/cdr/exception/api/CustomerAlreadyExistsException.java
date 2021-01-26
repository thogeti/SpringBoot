package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;

public class CustomerAlreadyExistsException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_CUSTOMER_ALREADY_EXIST = CustomerAlreadyExistsException.class.getName();
	//+ " : Customer already exists for customer ID '%s' in store '%s'";
	
    public CustomerAlreadyExistsException(String storeNumber, String customerID) 
    {
    	//String storeNumber, String patientId,String custId,String userId
    	super(ExceptionConstants.ErrorCode_CUSTOMER_ALREADY_EXIST,ErrorMsgConstants.getMsg(ErrorCode_CUSTOMER_ALREADY_EXIST,storeNumber,null,customerID,null ));

       // super(ExceptionConstants.ErrorCode_CUSTOMER_ALREADY_EXIST,String.format(ErrorCode_CUSTOMER_ALREADY_EXIST, customerID , storeNumber ));
    }
}
