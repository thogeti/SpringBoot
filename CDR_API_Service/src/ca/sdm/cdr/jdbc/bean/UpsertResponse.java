package ca.sdm.cdr.jdbc.bean;

import generated.RxHBBusinessEventActionEnum;

public class UpsertResponse {

	RxHBBusinessEventActionEnum rxHBBusinessEventActionEnum;
	Long prescriptionKey ;
	Long patientKey ;
	Long storeKey ;
	Long linkedPrescriptionKey ; //TE97_FIX 
	//Praveen T added for SmartNotification 
	Boolean autoRefillFlag;
	
	public UpsertResponse() {
        this.autoRefillFlag = false;   //SetUp default value of autoRefillFlag
	}
	public Boolean getAutoRefillFlag() {
		return autoRefillFlag;
	}
	public void setAutoRefillFlag(Boolean autoRefillFlag) {
		this.autoRefillFlag = autoRefillFlag;
	}
	
	//Praveen T added for SmartNotification 
	public Long getStoreKey() {
		return storeKey;
	}
	public void setStoreKey(Long storeKey) {
		this.storeKey = storeKey;
	}
	public RxHBBusinessEventActionEnum getRxHBBusinessEventActionEnum() {
		return rxHBBusinessEventActionEnum;
	}
	public void setRxHBBusinessEventActionEnum(RxHBBusinessEventActionEnum rxHBBusinessEventActionEnum) {
		this.rxHBBusinessEventActionEnum = rxHBBusinessEventActionEnum;
	}
	public Long getPrescriptionKey() {
		return prescriptionKey;
	}
	public void setPrescriptionKey(Long prescriptionKey) {
		this.prescriptionKey = prescriptionKey;
	}
	public Long getPatientKey() {
		return patientKey;
	}
	public void setPatientKey(Long patientKey) {
		this.patientKey = patientKey;
	}
	
	//TE97_FIX  Start
	public Long getLinkedPrescriptionKey() {
		return linkedPrescriptionKey;
	}
	public void setLinkedPrescriptionKey(Long linkedPrescriptionKey) {
		this.linkedPrescriptionKey = linkedPrescriptionKey;
	}
	
	
}
