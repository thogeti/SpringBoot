package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class RefillAcknowledgementException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_RefillAcknowledgement_ERROR = RefillAcknowledgementException.class.getName() + " :  Acknowledgement failed because TXDRX record is missing";
	
    public RefillAcknowledgementException(String corrId,Integer rxnum,String storeNumber) 
    {
        super(ExceptionConstants.ErrorCode_RefillAcknowledgement_ERROR,String.format(ErrorCode_RefillAcknowledgement_ERROR, corrId ,rxnum, storeNumber ));
    }
}
