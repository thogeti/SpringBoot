package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class PatientNoEligibleRxException  extends CDRException{
private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_PATIENT_NO_ELIGIBLE_RX = PatientNoEligibleRxException.class.getName() + " :There is no eligible refillable Rx(es) for the   patient %s for store %s  ";
	
    public PatientNoEligibleRxException(String storeNumber, String patientId  ) 
    {
        super(ExceptionConstants.ErrorCode_PATIENT_NO_RX, String.format(ErrorMessage_PATIENT_NO_ELIGIBLE_RX , patientId, storeNumber));
    }

}
