package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;

public class TxNoRxException extends CDRException {

	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_TX_NO_RX = TxNoRxException.class.getName() + " : dispense does not belong to any prescription for store '%s', tx number '%s' and is not qualified for get adherence calendar";
	
    public TxNoRxException(String storeNumber, String  txNumber) 
    {
        super(ExceptionConstants.ErrorCode_TX_NO_RX, String.format(ErrorMessage_TX_NO_RX,storeNumber, txNumber));
    }

}
