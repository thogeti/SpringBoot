package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class UpdateCustomerException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_UPDATE_CUSTOMER_ERROR = UpdateCustomerException.class.getName() + " : Error while updating Customer with Customer/User ID '%s'  ";
	
    public UpdateCustomerException( String customerID , String message) 
    {
        super(ExceptionConstants.ErrorCode_UPDATE_CUSTOMER_ERROR,String.format(ErrorCode_UPDATE_CUSTOMER_ERROR, customerID , message ));
    }
}
