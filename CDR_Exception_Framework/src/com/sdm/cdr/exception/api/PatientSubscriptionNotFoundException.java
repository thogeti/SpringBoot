package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project

*/


public class PatientSubscriptionNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_PATIENT_SUBSCRIPTION_NOT_FOUND = PatientSubscriptionNotFoundException.class.getName() + " : patient subscription not found for '%s', store '%s', patient id '%s'";
	public final static String ErrorMessage_PATIENT_SUBSCRIPTION_NOT_FOUND_BY_PATIENTKEY = PatientSubscriptionNotFoundException.class.getName() + " : patient subscription not found for patientKey '%s'";
	
    public PatientSubscriptionNotFoundException(String SrceSysName, String storeNumber, String patientId) { 
        super(ExceptionConstants.ErrorCode_PATIENT_SUBSCRIPTION_NOT_FOUND, String.format(ErrorMessage_PATIENT_SUBSCRIPTION_NOT_FOUND, SrceSysName, storeNumber, patientId));
    }

    public PatientSubscriptionNotFoundException(Long patientKey) { 
        super(ExceptionConstants.ErrorCode_PATIENT_SUBSCRIPTION_NOT_FOUND, String.format(ErrorMessage_PATIENT_SUBSCRIPTION_NOT_FOUND_BY_PATIENTKEY, patientKey));
    }
}
