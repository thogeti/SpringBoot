package com.sdm.cdr.exception.api;



import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.ErrorMsgConstants;
import com.sdm.cdr.exception.ExceptionConstants;

public class CustomerPatientAssociationNotExistException  extends CDRException {
	
private static final long serialVersionUID = 1L;
	
public final static String ErrorMessage_CUSTOMER_PATIENT_ASSOCIATION_NOT_EXIST = CustomerPatientAssociationNotExistException.class.getName() ;//+ " : Association Not Exist between customer id '%s' and patient id '%s'";
	public final static String ErrorMessage_CUSTOMER_ENTITY_ASSOCIATION_NOT_EXIST = CustomerPatientAssociationNotExistException.class.getName() + " : Association Not Exist for entityId id '%s' , entitype  '%s' and store '%s'";
	
    public CustomerPatientAssociationNotExistException(String userId, String patientId) 
    {
    	super(ExceptionConstants.ErrorCode_DESC_CUSTOMER_PATIENT_ASSOCIATION_NOT_EXIST,ErrorMsgConstants.getMsg(ErrorMessage_CUSTOMER_PATIENT_ASSOCIATION_NOT_EXIST,null, patientId,null,userId));
    }
    
    public CustomerPatientAssociationNotExistException( String entityId,String entitype,String storenum) 
    {
        super(ExceptionConstants.ErrorCode_DESC_CUSTOMER_PATIENT_ASSOCIATION_NOT_EXIST,String.format(ErrorMessage_CUSTOMER_ENTITY_ASSOCIATION_NOT_EXIST,entityId, entitype,storenum));
    }
}
