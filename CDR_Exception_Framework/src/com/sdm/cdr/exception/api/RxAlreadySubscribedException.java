package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

/*
@revision 
TAG  Date	      Vendor       Name 	     Change
---- -----------  -----------  -----------   -----------------------------------------
VL99 2018-02-12   NTT Data     Vlad Eidinov  QHR Accuro Project

*/


public class RxAlreadySubscribedException extends CDRException {
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage__RX_ALREADY_SUBSCRIBED = RxAlreadySubscribedException.class.getName() + " : Rx subscription already existed for '%s',  store '%s', rx number '%s'";
	
    public RxAlreadySubscribedException(String SrceSysName, String storeNumber, String rxNumber) { 
        super(ExceptionConstants.ErrorCode_RX_ALREADY_SUBSCRIBED, String.format(ErrorMessage__RX_ALREADY_SUBSCRIBED, SrceSysName, storeNumber, rxNumber));
    }

    public RxAlreadySubscribedException(int storeNumber, int rxNumber) { 
        super(ExceptionConstants.ErrorCode_RX_ALREADY_SUBSCRIBED, String.format(ErrorMessage__RX_ALREADY_SUBSCRIBED ,storeNumber, rxNumber));
    }
}
