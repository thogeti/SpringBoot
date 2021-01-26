package ca.sdm.cdr.jdbc.enumeration;

//import javax.xml.bind.annotation.XmlEnumValue;

//import ca.shoppersdrugmart.rxhb.ehealth.NoteCategory;

/*
@revision 
TAG  Date	      Vendor       Name 	        Change
---- -----------  -----------  -----------   -------------------
TE99 2018-02-12   NTT Data                   QHR Accuro Project
   
*/



public class CDREnumerations {

	/* NoteEnum enumeration */
	public enum NoteTypeTable {
		
	    PATIENT_NOTE("Ptnt","PtntNt"), 
	    PATIENT_ALLERGY_NOTE("PtntAlrgy" , "PtntAlrgyNt"), 
	    PATIENT_MEDICAL_CONDITION_NOTE("PtntMdclCndtn", "PtntMdclCndtnNt"),
	    PATIENT_METRICS_NOTE("PtntMtrcs","PtntMtrcsNt"),
	    REACTION_NOTE("Rctn", "RctnNt"),
	    ADVERSE_DRUG_REACTION_NOTE("PtntAdvrsDrgRctn", "PtntAdvrsDrgRctnNt"),
	    PRESCRIPTION_NOTE("Rx", "RxNt"),
	    PROFESSIONALSERVICE_NOTE("PrfsnlSvc","PrfsnlSvcNt"),
	    DISPENSE_NOTE("Tx","TxNt");
	  
		private String noteTableName;
	    private String tableName;
	  
	    public String getTableName(){
	        return this.tableName;
	        
	    }
	    public String getNoteTableName(){
	        return this.noteTableName;
	        
	    }
	    
	    private NoteTypeTable(String tableName , String noteTableName){
	        this.tableName = tableName;
	        this.noteTableName = noteTableName ;
	    }
	    
	};
	/* PersonRoleType enumeration */
	public enum PersonRoleType
	{
		RECORDER("Recorder"), SUPERVISOR("Supervisor"), DISPENSER("Dispenser") ,REPORTER("Reporter"), PATIENT("Patient")
		, PRESCRIBER("Prescriber") , RESPONSIBLE("Responsible"), INITIATOR("Initiator"), MEDICALPRACTITIONER("MedicalPractitioner"),PREREGCUSTOMER("PreRegCustomer"),REGCUSTOMER("Customer"),CAREGIVER("Caregiver");
		  
	    private String roleType;
	  
	    public String getRoleType(){
	        return this.roleType;
	    }
	    private PersonRoleType(String roleType){
	        this.roleType= roleType;
	    }
	};
	
	
	public enum PatientVitalType
	{
		
		Respiratory_Rate("Respiratory Rate"), Glucose("Glucose"), Height("Height") , Weight("Weight") , HeartRate("Heart Rate"),
		Body_Temperature("Body Temperature"), Systolic_Blood_Pressure("Systolic Blood Pressure") , Diastolic_Blood_Pressure ("Diastolic Blood Pressure");
	  
		private final String value;
	  
	
	    private PatientVitalType(String value){
	        this.value = value;
	    }
	
	    public String value() {
	        return value;
	    }
	    
	    public static PatientVitalType fromValue(String v) {
	        for (PatientVitalType c: PatientVitalType.values()) {
	            if (c.value.equals(v)) {
	                return c;
	            }
	        }
	        throw new IllegalArgumentException(v);
	    }	    
	};
	
	public enum ContactMethodType
	{
		EmailAddress("Email Address"), PostalAddress("Postal Address"), Telecom("Telecom");
		  
		private final String value;
		  
		
	    private ContactMethodType(String value){
	        this.value = value;
	    }
	
	    public String value() {
	        return value;
	    }
	    
	    public static ContactMethodType fromValue(String v) {
	        for (ContactMethodType c: ContactMethodType.values()) {
	            if (c.value.equals(v)) {
	                return c;
	            }
	        }
	        throw new IllegalArgumentException(v);
	    }	    
	};
	
	public enum ContactMethodRoleType
	{
		PATIENT("Patient"), PERSON("Person"), LOCATION("Location");
		private final String value;
		
		private ContactMethodRoleType(String value){
			this.value = value;
		}

		public String value() {
			return value;
		}

		public static ContactMethodRoleType fromValue(String v) {
			for (ContactMethodRoleType c: ContactMethodRoleType.values()) 
			{
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}	  

};
	

	public enum TelecomType {
      TELEPHONE("T"), FAX("F") , ALTERNATEPHONE("A");                    //      TE99 Deprecated
//		TELEPHONE("P"), FAX("F"), ALTERNATEPHONE("A");  // TE99 FOR ALTERNATE PHONE NUMBER
  
		private final String value;
		  
		
	    private TelecomType(String value){
	        this.value = value;
	    }
	
	    public String value() {
	        return value;
	    }
	    
	    public static TelecomType fromValue(String v) {
	        for (TelecomType c: TelecomType.values()) {
	            if (c.value.equals(v)) {
	                return c;
	            }
	        }
	        throw new IllegalArgumentException(v);
	    }	    
	};
	
}
