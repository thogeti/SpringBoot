package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class PatientNoRxException extends CDRException {

	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_PATIENT_NO_RX = PatientNoRxException.class.getName() + " : patient %s for store %s does have any prescription ";
	
    public PatientNoRxException(String storeNumber, String patientId  ) 
    {
        super(ExceptionConstants.ErrorCode_PATIENT_NO_RX, String.format(ErrorMessage_PATIENT_NO_RX , patientId, storeNumber));
    }
}
