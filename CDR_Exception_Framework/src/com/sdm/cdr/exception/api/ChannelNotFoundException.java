package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.ExceptionConstants;

public class ChannelNotFoundException extends CDRInternalException {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_ChanneNotFound = ChannelNotFoundException.class.getName() + " channel data not found in file : %s for channel : %s  ";
	
    public ChannelNotFoundException(String fileName ,String channel) 
    {
        super(ExceptionConstants.ErrorCode_CHANNELNOTFOUND_ERROR,String.format(ErrorMessage_ChanneNotFound,fileName , channel )  );
        
    }
}
    