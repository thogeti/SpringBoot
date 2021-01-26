package com.sdm.cdr.exception;

public class ErrorMsgConstants {
	
	public static final String FFT01="%s :   for  customer Id %s";
	public static final String FFT02="%s :   for  userId  %s";
	public static final String FFT03="%s :   for  store %s , patient id   %s";
	public static final String FFT04="%s :   for  patientId %s";
	public static final String FFT05="%s :	 for store   %s";
	public static final String FFT06="%s :   for  store %s , patient id   %s and userId %s";	
	public static final String FFT07="%s :   for  store %s , patient id  %s  and customer Id %s";
	public static final String FFT08="%s :   for  store %s , patient id   %s, customer Id %s and user Id %s";			
	public static final String FFT09="%s :   for  store %s , customerid   %s";
	public static final String FFT10="%s :   for  patientId %s , userid   %s";
	
    
	 public static String getMsg(String className,String storeNumber, String patientId,String custId,String userId)  {
			try {

				

				if (storeNumber == null && patientId == null && userId == null && custId != null) {
					return String.format(ErrorMsgConstants.FFT01,className, custId);
				}
				if (storeNumber == null && patientId == null && userId != null && custId == null) {
					return String.format(ErrorMsgConstants.FFT02, className, userId);
				}
				if (storeNumber != null && patientId != null && userId == null && custId == null) {
					return String.format(ErrorMsgConstants.FFT03, className,storeNumber, patientId);
				}
				if (storeNumber == null && patientId != null && userId == null && custId == null) {
					return String.format(ErrorMsgConstants.FFT04, className, patientId);
				}
				if (storeNumber != null && patientId == null && userId == null && custId == null) {
					return String.format(ErrorMsgConstants.FFT05, className, storeNumber);
				}
				if (storeNumber != null && patientId != null && userId != null && custId == null) {
					return String.format(ErrorMsgConstants.FFT06, className, storeNumber, patientId, userId);
				}
				if(storeNumber == null && patientId != null && userId != null && custId == null) {
					return String.format(ErrorMsgConstants.FFT10, className, patientId, userId);
				}
				if (storeNumber != null && patientId != null && userId == null && custId != null) {
					return String.format(ErrorMsgConstants.FFT07, className, storeNumber, patientId, custId);
				}
				if (storeNumber != null && patientId != null && userId != null && custId != null) {
					return String.format(ErrorMsgConstants.FFT08, className, storeNumber, patientId, userId, custId);
				}
				

			} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	return className + " : Customer already exists and registered";
	    }
}
