package com.sdm.cdr.exception.api;

/*
@revision 
TAG  Date	     Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project
*/


import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class RxSubscriptionNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_RX_SUBSCRIPTION_NOT_FOUND = RxSubscriptionNotFoundException.class.getName() + " : Rx subscription not found for '%s',  store '%s', rx number '%s'";
	public final static String ErrorMessage_RX_SUBSCRIPTION_NOT_FOUND_BY_RXKEY = RxSubscriptionNotFoundException.class.getName() + " : Rx subscription not found for RxKey '%s'";
	
    public RxSubscriptionNotFoundException(String SrceSysName, String storeNumber, String rxNumber  ) {
        super(ExceptionConstants.ErrorCode_RX_SUBSCRIPTION_NOT_FOUND,   String.format(ErrorMessage_RX_SUBSCRIPTION_NOT_FOUND, SrceSysName, storeNumber, rxNumber));
    }

    public RxSubscriptionNotFoundException(Long rxKey ) { 
        super(ExceptionConstants.ErrorCode_RX_SUBSCRIPTION_NOT_FOUND,String.format(ErrorMessage_RX_SUBSCRIPTION_NOT_FOUND_BY_RXKEY,rxKey ));
    }

}
