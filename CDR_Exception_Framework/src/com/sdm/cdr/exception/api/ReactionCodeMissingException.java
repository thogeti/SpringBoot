package com.sdm.cdr.exception.api;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.ExceptionConstants;

public class ReactionCodeMissingException extends CDRInternalException {

	private static final long serialVersionUID = 1L;
	
	public final static String ErrorMessage_REACTIONCODE_MISSING = ReactionCodeMissingException.class.getName() + " ReactionCode is missing in Reaction entity ";
	
    public ReactionCodeMissingException( ) 
    {
        super(ExceptionConstants.ErrorCode_REACTIONCODE_MISSING, ErrorMessage_REACTIONCODE_MISSING   );
        
    }
    
}
