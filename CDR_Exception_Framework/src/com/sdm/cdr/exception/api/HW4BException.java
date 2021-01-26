package com.sdm.cdr.exception.api;


import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ExceptionConstants;
public class HW4BException  extends CDRException{
	
private static final long serialVersionUID = 1L;
	
	public final static String ErrorCode_UPSERT_HW4B = HW4BException.class.getName() + " : Error while creating HW4BEvent for  customer ID '%s' in store '%s' : '%s' ";
	
    public HW4BException(String custkey, String ptntkey , String message) 
    {
        super(ExceptionConstants.ErrorCode_HW4B_ERROR,String.format(ErrorCode_UPSERT_HW4B, custkey , ptntkey , message ));
    }

}
