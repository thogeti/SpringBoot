package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;

public class DissociateCustomerException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_DISSOCIATE_CUSTOMER = DissociateCustomerException.class.getName(); //+ " : Error while creating Customer with customer ID '%s' in store '%s' : '%s' ";
	
    public DissociateCustomerException(String storeNumber, String patientId,String custId,String userId ) 
    {
        super(ExceptionConstants.ErrorCode_DISSOCIATE_CUSTOMER,ErrorMsgConstants.getMsg(ErrorCode_DISSOCIATE_CUSTOMER, storeNumber , patientId , custId,userId ));
    }
}
