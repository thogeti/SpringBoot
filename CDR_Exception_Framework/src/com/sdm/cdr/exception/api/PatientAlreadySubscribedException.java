package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL34 2017-12-21   NTT Data     Vlad Eidinov  Launcher class for CDR StandAlone project
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project

*/

public class PatientAlreadySubscribedException extends CDRException {
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_PATIENT_ALREADY_SUBSCRIBED = PatientAlreadySubscribedException.class.getName() + " : patient subscription already existed for '%s', store '%s', patient id '%s'";
	
    public PatientAlreadySubscribedException(String SrceSysName, String storeNumber, String patientId) { 
        super(ExceptionConstants.ErrorCode_PATIENT_ALREADY_SUBSCRIBED,String.format(ErrorMessage_PATIENT_ALREADY_SUBSCRIBED, SrceSysName, storeNumber, patientId));
    }
}
