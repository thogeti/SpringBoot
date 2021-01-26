package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class PatientNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_PATIENT_NOT_FOUND_BY_ID = PatientNotFoundException.class.getName() + " : patient not found for store '%s', patient id '%s'";
	
	public final static String ErrorMessage_PATIENT_NOT_FOUND_BY_RX = PatientNotFoundException.class.getName() + " : patient not found for store '%s', Rx id '%s'";
	public final static String ErrorMessage_PATIENT_NOT_FOUND_BY_TX = PatientNotFoundException.class.getName() + " : patient not found for store '%s', Tx id '%s'";
	
	public final static String ErrorMessage_PATIENT_NOT_FOUND = PatientNotFoundException.class.getName() + " : patient not found for store '%s'";


    public PatientNotFoundException(String storeNumber, String patientId  ) 
    {
        super(ExceptionConstants.ErrorCode_PATIENT_NOT_FOUND, String.format(ErrorMessage_PATIENT_NOT_FOUND_BY_ID, storeNumber, patientId));
    }

    public PatientNotFoundException(String errorMessage) 
    {
        super(ExceptionConstants.ErrorCode_PATIENT_NOT_FOUND,   errorMessage);
    }
}
