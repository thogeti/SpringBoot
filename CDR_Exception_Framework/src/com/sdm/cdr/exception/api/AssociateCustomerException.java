package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class AssociateCustomerException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_ASSOCIATE_CUSTOMER = AssociateCustomerException.class.getName() + " : Error while creating Customer with customer ID '%s' in store '%s' : '%s' ";
	
    public AssociateCustomerException(String custkey, String ptntkey , String message) 
    {
        super(ExceptionConstants.ErrorCode_ASSOCIATE_CUSTOMER,String.format(ErrorCode_ASSOCIATE_CUSTOMER, custkey , ptntkey , message ));
    }
}
