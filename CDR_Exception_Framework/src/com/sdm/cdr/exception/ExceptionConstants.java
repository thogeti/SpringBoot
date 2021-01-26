package com.sdm.cdr.exception;

public class ExceptionConstants {

	public final static String ErrorCode_CODE_NOT_FOUND_FROM_TABLE_CACHE 				= "DAT1000";
	public final static String ErrorCode_KEY_NOT_FOUND_FROM_TABLE_CACHE 				= "DAT1010";
	public final static String ErrorCode_PATIENT_ALREADY_SUBSCRIBED 					= "DAT3010";
	public final static String ErrorCode_PATIENT_NO_RX 									= "DAT3030";
	public final static String ErrorCode_PATIENT_NOT_FOUND 								= "DAT3000";
	public final static String ErrorCode_PATIENT_SUBSCRIPTION_NOT_FOUND 				= "DAT3020";
	public final static String ErrorCode_CUSTOMER_NOT_FOUND 							= "DAT4000";
	public final static String ErrorCode_CUSTOMER_PATIENT_ASSOCIATION_EXIST				= "DAT4010";
	public final static String ErrorCode_DESC_CUSTOMER_PATIENT_ASSOCIATION_NOT_EXIST	= "DAT4020";
	public final static String ErrorCode_DISPENSE_NOT_FOUND								= "DAT4500";
	public final static String ErrorCode_RX_ALREADY_SUBSCRIBED 							= "DAT5000";
	public final static String ErrorCode_Rx_NOT_FOUND 									= "DAT5010";
	public final static String ErrorCode_RX_SUBSCRIPTION_NOT_FOUND 						= "DAT5020";
	public final static String ErrorCode_INVALID_INPUT 									= "DAT5030";
	public final static String ErrorCode_SOURCE_SYSTEM_NOT_VALID 						= "DAT5500";
	public final static String ErrorCode_TX_NO_RX	 									= "DAT6100";	
	public final static String ErrorCode_CONSUMER_ID_MISSING							= "DAT9000";
	public final static String ErrorCode_REACTIONCODE_MISSING							= "DAT9100";
	public final static String ErrorCode_DATA_VALIDATION_ERROR							= "DAT9800";
	public final static String ErrorCode_CHANNELNOTFOUND_ERROR							= "DAT9900";
		
	public final static String ErrorCode_CREATE_CUSTOMER_ERROR							= "DAT4001";
	public final static String ErrorCode_CUSTOMER_ALREADY_EXIST							= "DAT4002";
	public final static String ErrorCode_UPDATE_CUSTOMER_ERROR							= "DAT4202";
	public final static String ErrorCode_CUSTOMER_EXIST_AND_REGISTERED					= "DAT4003";
	public final static String ErrorCode_CUSTOMER_TYPE_NOT_FOUND						= "DAT4004";
	public final static String ErrorCode_CUSTOMER_CHANNEL_NOT_FOUND						= "DAT4005";
	public final static String ErrorCode_REFILL_CHANNEL_NOT_FOUND						= "DAT4006";
	//Drop59 Division Changes
	public final static String ErrorCode_DIVISION_TYPE_NOT_FOUND						= "DAT4007";
		
//	public final static String ErrorCode_PERSON_NOT_FOUND								= "DAT3030";
//	public final static String ErrorCode_INS_COVERAGE_NOT_FOUND							= "DAT3040";
//	public final static String ErrorCode_COVERAGE_CARD_NOT_FOUND						= "DAT3050";
//	public final static String ErrorCode_PATIENT_CONSENT_NOT_FOUND						= "DAT3060";	
//	public final static String ErrorCode_ALLERGY_NOT_FOUND								= "DAT3070";
//	public final static String ErrorCode_ALLERGY_TEST_NOT_FOUND							= "DAT3080";
//	public final static String ErrorCode_REACTION_NOT_FOUND								= "DAT3090";
//	public final static String ErrorCode_LOCATION_NOT_FOUND								= "DAT5040";
//	public final static String ErrorCode_RX_NO_TX	 									= "DAT6000";
//	public final static String ErrorCode_DATA_NOTVALID_FOR_ODMCALL						= "DAT7000";
//	public final static String ErrorCode_DRUG_NOT_FOUND									= "DAT9600";
//	public final static String ErrorCode_PACK_NOT_FOUND									= "DAT9610";
//	public final static String ErrorCode_MANUFACTURER_NOT_FOUND							= "DAT9620";
//	public final static String ErrorCode_COMPOUND_NOT_FOUND								= "DAT9630";
//	public final static String ErrorCode_MOLECULE_NOT_FOUND								= "DAT9640";
//	public final static String ErrorCode_ACTIVE_INGREDIENT_NOT_FOUND					= "DAT9650";
//	public final static String ErrorCode_DOSAGE_FORM_NOT_FOUND							= "DAT9660";
//	public final static String ErrorCode_ADVERSE_DRUG_REACTION_NOT_FOUND				= "DAT9670";
//	public final static String ErrorCode_MANUFACTURER_RECALL_NOT_FOUND					= "DAT9680";
//	public final static String ErrorCode_DRUG_COLOUR_NOT_FOUND							= "DAT9690";
//	public final static String ErrorCode_DATA_NOT_FOUND									= "DAT9700";
	
	//FFT 
	
	public final static String ErrorCode_RefillAcknowledgement_ERROR = "DAT4407";
	public final static String ErrorCode_ASSOCIATE_CUSTOMER	 = "DAT4303";
	public final static String ErrorCode_DISSOCIATE_CUSTOMER = "DAT4404";
// HW4B
	public final static String ErrorCode_HW4B_ERROR = "DAT4409";
	//smartnotification 0425
	public final static String ErrorCode_CONSENT_RES_INVALID_INPUT 									= "DAT4410";
}
