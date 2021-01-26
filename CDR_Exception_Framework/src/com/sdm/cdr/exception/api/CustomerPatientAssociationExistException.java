package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;


public class CustomerPatientAssociationExistException extends CDRException {
	
private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_CUSTOMER_PATIENT_ASSOCIATION_EXIST = CustomerPatientAssociationExistException.class.getName();
	//+ " : customer '%s' already associated with store number '%s', patient id '%s'";
	
    public CustomerPatientAssociationExistException(String customerId, String patientId, String storeNumber) 
    {
    	//String storeNumber, String patientId,String custId,String userId
        super(ExceptionConstants.ErrorCode_CUSTOMER_PATIENT_ASSOCIATION_EXIST,ErrorMsgConstants.getMsg(ErrorMessage_CUSTOMER_PATIENT_ASSOCIATION_EXIST,storeNumber,patientId,customerId,null ));

    }
}
