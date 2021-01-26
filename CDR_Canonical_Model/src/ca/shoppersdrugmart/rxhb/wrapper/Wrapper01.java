package ca.shoppersdrugmart.rxhb.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.HW4BEventUpsert;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.HW4BEventUpsertResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PatientUpsert;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PatientUpsertResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PrescriberUpsertResponse;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.PrescriptionUpsertResponse;
import ca.shoppersdrugmart.cdr.notificationService.NotificationRequest;
import ca.shoppersdrugmart.cdr.notificationService.NotificationResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.AssociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.AssociateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.CreateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.DissociateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.DissociateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.GetCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomer;
import ca.shoppersdrugmart.rxhb.drx.customerservice.UpdateCustomerResponse;
import ca.shoppersdrugmart.rxhb.drx.dispensing.Refill;
import ca.shoppersdrugmart.rxhb.drx.dispensing.RefillResponse;
import ca.shoppersdrugmart.rxhb.drx.dispensing.Renew;
import ca.shoppersdrugmart.rxhb.drx.dispensing.RenewResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatient;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByPatientId;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByPatientIdResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByQueryCriteria;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientByQueryCriteriaResponse;
import ca.shoppersdrugmart.rxhb.drx.getpatient.GetPatientResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetDispenseAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetDispenseAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPatientAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPatientAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescription;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionAdherenceCalendar;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionAdherenceCalendarResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationprofile.GetPrescriptionResponse;
import ca.shoppersdrugmart.rxhb.drx.medicationreview.GetMedicationReviewRequest;
import ca.shoppersdrugmart.rxhb.drx.medicationreview.GetMedicationReviewResponse;
import ca.shoppersdrugmart.rxhb.drx.preference.EntityPreference;
import ca.shoppersdrugmart.rxhb.drx.preference.EntityPreferenceResponse;
import ca.shoppersdrugmart.rxhb.drx.rxtransfer.RxTransfer;
import ca.shoppersdrugmart.rxhb.drx.rxtransfer.RxTransferResponse;
import ca.shoppersdrugmart.rxhb.ehealth.businessevent.BusinessEventPayload;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyCentralBusinessEvent;


/*
@revision 
TAG  Date	     Vendor       Name 	         Change
---- -----------  -----------  -----------   -------------------
VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
                                             This Wrapper01 class helps to save Payload requests/responses
                                             in CDR archive directory. To enable Payload archiving place
                                             CDRArchiveDIR=/app/weblogic/CDR/Archive5   in CDR.config  
                                             Use "CDRArchiveDIR" parameter only in CDR dit/sit/uat
                                             Remove(or comment) "CDRArchiveDIR" parameter in CDR Prod
                                             because Payload archiving degradates CDR performance.
                                             
                                             
VL97_023 2018-08-24            Vlad Eidinov                                         
*/


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Wrapper01")
@XmlType(name = "Wrapper01", propOrder = {
  "pharmacyCentralBusinessEvent",
  "businessEventPayload",
  "patientUpsertRequest",
  "patientUpsertResponse",
  "prescriberUpsertResponse",//TE97_023
  "patientRxNumRequest",
  "patientResponse",
  "patientIdRequest",
  "patientIdResponse",
  "patientByQueryCriteriaRequest",
  "patientByQueryCriteriaResponse",
  "getPrescriptionRequest",
  "getPrescriptionResponse",
  "prescriptionUpsertResponse",
  "patientAdherenceCalendarRequest",
  "patientAdherenceCalendarResponse",
  "prescriptionAdherenceCalendarRequest",
  "prescriptionAdherenceCalendarResponse",
"dispenseAdherenceCalendarRequest",
  "dispenseAdherenceCalendarResponse",
  "notificationRequest",
  "notificationResponse",
 "getCusotmerRequest",
  "getCustomerResponse",
 "createCustomerRequest",
 "createCustomerResponse" ,
 "associateCustomerRequest",
 "associateCustomerResponse",
 "updateCustomerRequest",
 "updateCustomerResponse",
 "dissociateCustomerRequest",
 "dissociateCustomerResponse",
 "refillDispenseRequest",
 "refillDispenseResponse",
 "renewRequest",
 "renewResponse",
 "entityPreferenceRequest",
 "entityPreferenceResponse",
 "rxTransferRequest",
// "rxTransferResponse"
//HW4BService #HW4B changes Praveen T
 "rxTransferResponse",
 "hw4BUpsertRequest",
 "hW4BEventUpsertResponse",
 "getMedicationReviewRequest",
 "getMedicationReviewResponse"
})


public class Wrapper01 {
       protected PharmacyCentralBusinessEvent      pharmacyCentralBusinessEvent = null;
       protected BusinessEventPayload              businessEventPayload = null;
       
       protected PatientUpsert                     patientUpsertRequest = null;
       protected PatientUpsertResponse             patientUpsertResponse = null;
       
       protected GetPatient                        patientRxNumRequest = null;
       protected GetPatientResponse                patientResponse = null;
       
       protected GetPatientByPatientId             patientIdRequest = null;
       protected GetPatientByPatientIdResponse     patientIdResponse = null;
       
       protected GetPatientByQueryCriteria         patientByQueryCriteriaRequest = null;
       protected GetPatientByQueryCriteriaResponse patientByQueryCriteriaResponse = null;
       
       protected GetPrescription                   getPrescriptionRequest = null;
       protected GetPrescriptionResponse           getPrescriptionResponse = null;
       protected PrescriptionUpsertResponse        prescriptionUpsertResponse = null;
//     -------------------------------------------------------------------------------       
       
       
       protected GetPatientAdherenceCalendar         patientAdherenceCalendarRequest = null;
       protected GetPatientAdherenceCalendarResponse patientAdherenceCalendarResponse = null;

       protected GetPrescriptionAdherenceCalendar            prescriptionAdherenceCalendarRequest = null;
       protected GetPrescriptionAdherenceCalendarResponse    prescriptionAdherenceCalendarResponse = null;
        protected GetDispenseAdherenceCalendar           dispenseAdherenceCalendarRequest = null;
       protected GetDispenseAdherenceCalendarResponse   dispenseAdherenceCalendarResponse = null;
//     -------------------------------------------------------------------------------
        
       protected NotificationRequest                     notificationRequest = null;
       protected NotificationResponse             notificationResponse = null;
       
       protected PrescriberUpsertResponse    prescriberUpsertResponse= null;//TE97_023
       
       protected GetCustomer           getCusotmerRequest = null;
       protected GetCustomerResponse     getCustomerResponse = null;
       
       protected  CreateCustomer   createCustomerRequest = null;
       protected  CreateCustomerResponse createCustomerResponse = null;
       
       protected AssociateCustomer associateCustomerRequest =null;
       protected AssociateCustomerResponse associateCustomerResponse = null;
       
       protected UpdateCustomer  updateCustomerRequest =null;
       protected UpdateCustomerResponse updateCustomerResponse =null;
       
       protected DissociateCustomer dissociateCustomerRequest=null;
       protected DissociateCustomerResponse dissociateCustomerResponse =null;
       
       protected Refill                     refillDispenseRequest = null;
       protected RefillResponse             refillDispenseResponse = null;
       
       protected Renew           renewRequest = null;
       protected RenewResponse     renewResponse= null;
       //preference
       protected EntityPreference entityPreferenceRequest =null;
       protected EntityPreferenceResponse entityPreferenceResponse = null;
       
       protected RxTransfer  rxTransferRequest = null;
       protected RxTransferResponse rxTransferResponse =null;
       
       //HW4BService #HW4B changes Praveen T
       protected HW4BEventUpsert hw4BUpsertRequest = null;
       protected HW4BEventUpsertResponse hW4BEventUpsertResponse = null;
       
       
       protected  GetMedicationReviewRequest  getMedicationReviewRequest=null;
       protected GetMedicationReviewResponse  getMedicationReviewResponse=null;
       public Wrapper01() {
       }
       
       public PharmacyCentralBusinessEvent getPharmacyCentralBusinessEvent() {
    	      return pharmacyCentralBusinessEvent;
       }

       public void setPharmacyCentralBusinessEvent(PharmacyCentralBusinessEvent value) {
              this.pharmacyCentralBusinessEvent = value;
       }

       
       public BusinessEventPayload getBusinessEventPayload() {
              return businessEventPayload;
       }

       public void setBusinessEventPayload(BusinessEventPayload value) {
              this.businessEventPayload = value;
       }
       
       
       public PatientUpsert getPatientUpsertRequest() {
              return patientUpsertRequest;
       }

       public void setPatientUpsertRequest(PatientUpsert value) {
              this.patientUpsertRequest = value;
       }
       
       
       
       public PatientUpsertResponse getPatientUpsertResponse() {
              return patientUpsertResponse;
       }

       public void setPatientUpsertResponse(PatientUpsertResponse value) {
              this.patientUpsertResponse = value;
       }
       
       
       
       public GetPatient getPatientByRxNum() {
              return patientRxNumRequest;
       }

       public void setPatientByRxNum(GetPatient value) {
              this.patientRxNumRequest = value;
       }
       
       
       public GetPatientByPatientId getPatientByPatientId() {
              return patientIdRequest;
       }

       public void setPatientByPatientId(GetPatientByPatientId value) {
              this.patientIdRequest = value;
       }
       

       
	   public GetPatientByPatientIdResponse getPatientByPatientIdResponse() {
              return patientIdResponse;
       }

       public void setPatientByPatientIdResponse(GetPatientByPatientIdResponse value) {
              this.patientIdResponse = value;
       }
       
       
       
       public GetPatientResponse getPatientResponse() {
              return patientResponse;
       }

       public void setGetPatientResponse(GetPatientResponse value) {
              this.patientResponse = value;
       }

       
       
       public GetPatientByQueryCriteria getPatientByQueryCriteriaRequest() {
              return patientByQueryCriteriaRequest;
       }

       public void setPatientByQueryCriteriaRequest(GetPatientByQueryCriteria value) {
              this.patientByQueryCriteriaRequest = value;
       }
       
       
       
       public GetPatientByQueryCriteriaResponse getPatientByQueryCriteriaResponse() {
              return patientByQueryCriteriaResponse;
       }

       public void setGetPatientByQueryCriteriaResponse(GetPatientByQueryCriteriaResponse value) {
              this.patientByQueryCriteriaResponse = value;
       }
       

       public GetPrescription getPrescriptionRequest() {
              return getPrescriptionRequest;
       }

       public void setPrescriptionRequest(GetPrescription value) {
              this.getPrescriptionRequest = value;
       }
       
       
       public GetPrescriptionResponse getPrescriptionResponse() {
              return getPrescriptionResponse;
       }

       public void setPrescriptionResponse(GetPrescriptionResponse value) {
              this.getPrescriptionResponse = value;
       }


       public PrescriptionUpsertResponse getPrescriptionUpsertResponse() {
              return prescriptionUpsertResponse;
       }

       public void setPrescriptionUpsertResponse(PrescriptionUpsertResponse value) {
              this.prescriptionUpsertResponse = value;
       }

       
       public GetPatientAdherenceCalendar getPatientAdherenceCalendar() {
              return patientAdherenceCalendarRequest;
       }

       public void setPatientAdherenceCalendar(GetPatientAdherenceCalendar value) {
              this.patientAdherenceCalendarRequest = value;
       }
    
    
       public GetPatientAdherenceCalendarResponse getPatientAdherenceCalendarResponse() {
              return patientAdherenceCalendarResponse;
       }

     

	public GetCustomerResponse getGetCustomerResponse() {
		return getCustomerResponse;
	}

	public void setGetCustomerResponse(GetCustomerResponse getCustomerResponse) {
		this.getCustomerResponse = getCustomerResponse;
	}

	

	

       public void setPatientAdherenceCalendarResponse(GetPatientAdherenceCalendarResponse value) {
              this.patientAdherenceCalendarResponse = value;
       }

       
       
       public GetPrescriptionAdherenceCalendar getPrescriptionAdherenceCalendar() {
              return prescriptionAdherenceCalendarRequest;
       }

       public void setPrescriptionAdherenceCalendar(GetPrescriptionAdherenceCalendar value) {
              this.prescriptionAdherenceCalendarRequest = value;
       }
       
       
       
       public GetPrescriptionAdherenceCalendarResponse getPrescriptionAdherenceCalendarResponse() {
              return prescriptionAdherenceCalendarResponse;
       }

       public void setPrescriptionAdherenceCalendarResponse(GetPrescriptionAdherenceCalendarResponse value) {
              this.prescriptionAdherenceCalendarResponse = value;
       }


       public GetDispenseAdherenceCalendar getDispenseAdherenceCalendar() {
              return dispenseAdherenceCalendarRequest;
       }

       public void setDispenseAdherenceCalendar(GetDispenseAdherenceCalendar value) {
              this.dispenseAdherenceCalendarRequest = value;
       }
    
    
       public GetDispenseAdherenceCalendarResponse getDispenseAdherenceCalendarResponse() {
              return dispenseAdherenceCalendarResponse;
       }

       public void setDispenseAdherenceCalendarResponse(GetDispenseAdherenceCalendarResponse value) {
              this.dispenseAdherenceCalendarResponse = value;
       }
       
       

      	public NotificationResponse getNotificationResponse() {
      		return notificationResponse;
      	}

      	public void setNotificationResponse(NotificationResponse notificationResponse) {
      		this.notificationResponse = notificationResponse;
      	}
      	
      	
      	
   	
    //TE97_024
    
    public PrescriberUpsertResponse getPrescriberUpsertResponse() {
                    return prescriberUpsertResponse;
    }

    public void setPrescriberUpsertResponse(PrescriberUpsertResponse prescriberUpsertResponse) {
                    this.prescriberUpsertResponse = prescriberUpsertResponse;
    }
    //TE97_024
       
	public AssociateCustomer getAssociateCustomerRequest() {
		return associateCustomerRequest;
	}

	public void setAssociateCustomerRequest(AssociateCustomer associateCustomerRequest) {
		this.associateCustomerRequest = associateCustomerRequest;
	}

	public AssociateCustomerResponse getAssociateCustomerResponse() {
		return associateCustomerResponse;
	}

	public void setAssociateCustomerResponse(AssociateCustomerResponse associateCustomerResponse) {
		this.associateCustomerResponse = associateCustomerResponse;
	}

	public UpdateCustomer getUpdateCustomerRequest() {
		return updateCustomerRequest;
	}

	public void setUpdateCustomerRequest(UpdateCustomer updateCustomerRequest) {
		this.updateCustomerRequest = updateCustomerRequest;
	}

	public UpdateCustomerResponse getUpdateCustomerResponse() {
		return updateCustomerResponse;
	}

	public void setUpdateCustomerResponse(UpdateCustomerResponse updateCustomerResponse) {
		this.updateCustomerResponse = updateCustomerResponse;
	}
	 public CreateCustomer getCreateCustomerRequest() {
			return createCustomerRequest;
		}

		public void setCreateCustomerRequest(CreateCustomer createCustomerRequest) {
			this.createCustomerRequest = createCustomerRequest;
		}

		public CreateCustomerResponse getCreateCustomerResponse() {
			return createCustomerResponse;
		}

		public void setCreateCustomerResponse(CreateCustomerResponse createCustomerResponse) {
			this.createCustomerResponse = createCustomerResponse;
		}

		public DissociateCustomer getDissociateCustomerRequest() {
			return dissociateCustomerRequest;
		}

		public void setDissociateCustomerRequest(DissociateCustomer dissociateCustomerRequest) {
			this.dissociateCustomerRequest = dissociateCustomerRequest;
		}

		public DissociateCustomerResponse getDissociateCustomerResponse() {
			return dissociateCustomerResponse;
		}

		public void setDissociateCustomerResponse(DissociateCustomerResponse dissociateCustomerResponse) {
			this.dissociateCustomerResponse = dissociateCustomerResponse;
		}

		public GetCustomer getGetCusotmerRequest() {
			return getCusotmerRequest;
		}

		public void setGetCusotmerRequest(GetCustomer getCusotmerRequest) {
			this.getCusotmerRequest = getCusotmerRequest;
		}

		
		public Refill getRefillDispenseRequest() {
			return refillDispenseRequest;
		}

		public void setRefillDispenseRequest(Refill refillDispenseRequest) {
			this.refillDispenseRequest = refillDispenseRequest;
		}

		public RefillResponse getRefillDispenseResponse() {
			return refillDispenseResponse;
		}

		public void setRefillDispenseResponse(RefillResponse refillDispenseResponse) {
			this.refillDispenseResponse = refillDispenseResponse;
		}

		public Renew getRenewRequest() {
			return renewRequest;
		}

		public void setRenewRequest(Renew renewRequest) {
			this.renewRequest = renewRequest;
		}

		public RenewResponse getRenewResponse() {
			return renewResponse;
		}

		public void setRenewResponse(RenewResponse renewResponse) {
			this.renewResponse = renewResponse;
		}
	//Preference  Praveen T
		public EntityPreference getEntityPreferenceRequest() {
			return entityPreferenceRequest;
		}

		public RxTransfer getRxTransferRequest() {
			return rxTransferRequest;
		}

		public void setEntityPreferenceRequest(EntityPreference entityPreferenceRequest) {
			this.entityPreferenceRequest = entityPreferenceRequest;
		}
		public void setRxTransferRequest(RxTransfer rxTransferRequest) {
			this.rxTransferRequest = rxTransferRequest;
		}

		public EntityPreferenceResponse getEntityPreferenceResponse() {
			return entityPreferenceResponse;
		}
		public RxTransferResponse getRxTransferResponse() {
			return rxTransferResponse;
		}

		public void setEntityPreferenceResponse(EntityPreferenceResponse entityPreferenceResponse) {
			this.entityPreferenceResponse = entityPreferenceResponse;
		}
		public void setRxTransferResponse(RxTransferResponse rxTransferResponse) {
			this.rxTransferResponse = rxTransferResponse;
		}
		
		public NotificationRequest getNotificationRequest() {
			return notificationRequest;
		}

		public void setNotificationRequest(NotificationRequest notificationRequest) {
			this.notificationRequest = notificationRequest;
		}
       //HW4BService #HW4B changes Praveen T
		public HW4BEventUpsert getHw4BUpsertRequest() {
			return hw4BUpsertRequest;
		}

		public void setHw4BUpsertRequest(HW4BEventUpsert hw4bUpsertRequest) {
			hw4BUpsertRequest = hw4bUpsertRequest;
		}
       
		public HW4BEventUpsertResponse gethW4BEventUpsertResponse() {
			return hW4BEventUpsertResponse;
		}

		public void sethW4BEventUpsertResponse(HW4BEventUpsertResponse hW4BEventUpsertResponse) {
			this.hW4BEventUpsertResponse = hW4BEventUpsertResponse;
		}
       //HW4BService #HW4B changes Praveen T

		public GetMedicationReviewRequest getGetMedicationReviewRequest() {
			return getMedicationReviewRequest;
		}

		public void setGetMedicationReviewRequest(GetMedicationReviewRequest getMedicationReviewRequest) {
			this.getMedicationReviewRequest = getMedicationReviewRequest;
		}

		public GetMedicationReviewResponse getGetMedicationReviewResponse() {
			return getMedicationReviewResponse;
		}

		public void setGetMedicationReviewResponse(GetMedicationReviewResponse getMedicationReviewResponse) {
			this.getMedicationReviewResponse = getMedicationReviewResponse;
		}
}


