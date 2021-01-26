package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class CreateCustomerException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_CREATE_CUSTOMER_ERROR = CreateCustomerException.class.getName() + " : Error while creating Customer with Customer/User ID '%s' in store '%s' : '%s' ";
	
    public CreateCustomerException(String storeNumber, String customerID , String message) 
    {
        super(ExceptionConstants.ErrorCode_CREATE_CUSTOMER_ERROR,String.format(ErrorCode_CREATE_CUSTOMER_ERROR, customerID , storeNumber , message ));
    }
}
