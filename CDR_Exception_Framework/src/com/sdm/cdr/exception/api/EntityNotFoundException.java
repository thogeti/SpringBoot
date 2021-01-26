package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;


public class EntityNotFoundException extends CDRException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_Rx_NOT_FOUND = RxNotFoundException.class.getName() + " : Rx not found for store '%s', rx number '%s'";
	
	/**
	 * 
	 * @param storeNumber
	 * @param consumerId
	 */
    public EntityNotFoundException(String errorCode, String storeNumber, String  consumerId) 
    {
        super(errorCode, String.format(ErrorMessage_Rx_NOT_FOUND,storeNumber, consumerId));
    }

}
