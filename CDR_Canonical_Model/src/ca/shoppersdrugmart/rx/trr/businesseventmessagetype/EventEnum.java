
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AbortCancel"/>
 *     &lt;enumeration value="Data Entry Modify"/>
 *     &lt;enumeration value="Cancel and Log"/>
 *     &lt;enumeration value="AdaptRxLogged"/>
 *     &lt;enumeration value="AdjudicatePostponeTx"/>
 *     &lt;enumeration value="ADR Added"/>
 *     &lt;enumeration value="ADR Deleted"/>
 *     &lt;enumeration value="ADR Note Added"/>
 *     &lt;enumeration value="ADR Note Deleted"/>
 *     &lt;enumeration value="ADR Updated"/>
 *     &lt;enumeration value="Allergy Intolerance Note Added"/>
 *     &lt;enumeration value="Allergy Intolerance Note Deleted"/>
 *     &lt;enumeration value="AutoPostpone"/>
 *     &lt;enumeration value="ClaimAdjusted"/>
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Added"/>
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Deleted"/>
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Updated"/>
 *     &lt;enumeration value="Clinical Profile Medical Condition Added"/>
 *     &lt;enumeration value="Clinical Profile Medical Condition Updated"/>
 *     &lt;enumeration value="Clinical Verification Complete"/>
 *     &lt;enumeration value="CMPT"/>
 *     &lt;enumeration value="CompleteCounselling"/>
 *     &lt;enumeration value="CompoundDataChanged"/>
 *     &lt;enumeration value="Consent Managed"/>
 *     &lt;enumeration value="ContactCreated"/>
 *     &lt;enumeration value="CVAccepted"/>
 *     &lt;enumeration value="CVDeclined"/>
 *     &lt;enumeration value="Data Entry Complete Log"/>
 *     &lt;enumeration value="Data Entry Complete New"/>
 *     &lt;enumeration value="Data Entry Complete Post"/>
 *     &lt;enumeration value="Data Entry Complete Refill"/>
 *     &lt;enumeration value="DataEntrySubmitted"/>
 *     &lt;enumeration value="Defer"/>
 *     &lt;enumeration value="Discontinue Rx Post"/>
 *     &lt;enumeration value="DiscontinueRx"/>
 *     &lt;enumeration value="Display Linkable Prescription Profile"/>
 *     &lt;enumeration value="Display Linked Prescription Details"/>
 *     &lt;enumeration value="Display Medical Condition History"/>
 *     &lt;enumeration value="Display Medical Condition List"/>
 *     &lt;enumeration value="Display Medication Profile"/>
 *     &lt;enumeration value="Display Patient Observation"/>
 *     &lt;enumeration value="Display Patient Reactions"/>
 *     &lt;enumeration value="Display Prescription Details"/>
 *     &lt;enumeration value="Display Provincial Patient"/>
 *     &lt;enumeration value="Display Provincial Patient List"/>
 *     &lt;enumeration value="Display Provincial Prescriber List"/>
 *     &lt;enumeration value="Display Provincial Provider List"/>
 *     &lt;enumeration value="DrugDataChanged"/>
 *     &lt;enumeration value="DUR Management Completed"/>
 *     &lt;enumeration value="DURExecuted"/>
 *     &lt;enumeration value="DVAccepted"/>
 *     &lt;enumeration value="DVDeclined"/>
 *     &lt;enumeration value="Electronic Import"/>
 *     &lt;enumeration value="Get Allergy Intolerance Profile"/>
 *     &lt;enumeration value="Get Allergy Intolerance Record History"/>
 *     &lt;enumeration value="Get Patient Comprehensive Profile"/>
 *     &lt;enumeration value="Link Patient"/>
 *     &lt;enumeration value="Medical Condition Note Added"/>
 *     &lt;enumeration value="Medical Condition Note Deleted"/>
 *     &lt;enumeration value="Patient Added"/>
 *     &lt;enumeration value="Patient Deleted"/>
 *     &lt;enumeration value="Patient Linked"/>
 *     &lt;enumeration value="Patient Merged"/>
 *     &lt;enumeration value="Patient Note Added"/>
 *     &lt;enumeration value="Patient Note Deleted"/>
 *     &lt;enumeration value="Patient Observation Added"/>
 *     &lt;enumeration value="Patient Observation Deleted"/>
 *     &lt;enumeration value="Patient Observation Note Added"/>
 *     &lt;enumeration value="Patient Order Complete"/>
 *     &lt;enumeration value="Patient Order Complete Delete"/>
 *     &lt;enumeration value="Patient Updated"/>
 *     &lt;enumeration value="PatientCounsellingCompleted"/>
 *     &lt;enumeration value="PatientDataChanged"/>
 *     &lt;enumeration value="PCAccepted"/>
 *     &lt;enumeration value="PCDeclined"/>
 *     &lt;enumeration value="PharmacistRenewable"/>
 *     &lt;enumeration value="PostponeTx"/>
 *     &lt;enumeration value="PrescriberDataChanged"/>
 *     &lt;enumeration value="Provincial Patient Added"/>
 *     &lt;enumeration value="RebillClaims"/>
 *     &lt;enumeration value="RefillCreated"/>
 *     &lt;enumeration value="Refusal to Fill"/>
 *     &lt;enumeration value="Refusal To Fill Deleted"/>
 *     &lt;enumeration value="Rx Cancel Complete Delete"/>
 *     &lt;enumeration value="Rx Discontinue"/>
 *     &lt;enumeration value="Rx Log Modified"/>
 *     &lt;enumeration value="Rx Modified Complete Delete"/>
 *     &lt;enumeration value="Rx Note Added"/>
 *     &lt;enumeration value="Rx Note Deleted"/>
 *     &lt;enumeration value="Rx Resumed"/>
 *     &lt;enumeration value="Rx Transferred"/>
 *     &lt;enumeration value="RxAdapted"/>
 *     &lt;enumeration value="RxDataChanged"/>
 *     &lt;enumeration value="RxIntakeSubmitted"/>
 *     &lt;enumeration value="RxLogged"/>
 *     &lt;enumeration value="RxPickUpCompleted"/>
 *     &lt;enumeration value="RxPosted"/>
 *     &lt;enumeration value="RxTransfer"/>
 *     &lt;enumeration value="Search Location"/>
 *     &lt;enumeration value="Search Patient"/>
 *     &lt;enumeration value="Search Pharmacy"/>
 *     &lt;enumeration value="SSF"/>
 *     &lt;enumeration value="StoreDataChanged"/>
 *     &lt;enumeration value="SubmitFilling"/>
 *     &lt;enumeration value="Tx Cancel Complete"/>
 *     &lt;enumeration value="Tx Cancel Complete Delete"/>
 *     &lt;enumeration value="Tx Cancel Complete Post"/>
 *     &lt;enumeration value="Tx Modify Complete Post"/>
 *     &lt;enumeration value="Tx Note Added"/>
 *     &lt;enumeration value="Tx Note Deleted"/>
 *     &lt;enumeration value="TxCancelled"/>
 *     &lt;enumeration value="TxDataChanged"/>
 *     &lt;enumeration value="TxModifyCompleted"/>
 *     &lt;enumeration value="Unauth"/>
 *     &lt;enumeration value="UnauthProcessCreated"/>
 *     &lt;enumeration value="VarianceAdjusted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventEnum")
@XmlEnum
public enum EventEnum {

    @XmlEnumValue("AbortCancel")
    ABORT_CANCEL("AbortCancel"),
    @XmlEnumValue("Data Entry Modify")
    DATA_ENTRY_MODIFY("Data Entry Modify"),
    @XmlEnumValue("Cancel and Log")
    CANCEL_AND_LOG("Cancel and Log"),
    @XmlEnumValue("AdaptRxLogged")
    ADAPT_RX_LOGGED("AdaptRxLogged"),
    @XmlEnumValue("AdjudicatePostponeTx")
    ADJUDICATE_POSTPONE_TX("AdjudicatePostponeTx"),
    @XmlEnumValue("ADR Added")
    ADR_ADDED("ADR Added"),
    @XmlEnumValue("ADR Deleted")
    ADR_DELETED("ADR Deleted"),
    @XmlEnumValue("ADR Note Added")
    ADR_NOTE_ADDED("ADR Note Added"),
    @XmlEnumValue("ADR Note Deleted")
    ADR_NOTE_DELETED("ADR Note Deleted"),
    @XmlEnumValue("ADR Updated")
    ADR_UPDATED("ADR Updated"),
    @XmlEnumValue("Allergy Intolerance Note Added")
    ALLERGY_INTOLERANCE_NOTE_ADDED("Allergy Intolerance Note Added"),
    @XmlEnumValue("Allergy Intolerance Note Deleted")
    ALLERGY_INTOLERANCE_NOTE_DELETED("Allergy Intolerance Note Deleted"),
    @XmlEnumValue("AutoPostpone")
    AUTO_POSTPONE("AutoPostpone"),
    @XmlEnumValue("ClaimAdjusted")
    CLAIM_ADJUSTED("ClaimAdjusted"),
    @XmlEnumValue("Clinical Profile Allergy Intolerance Added")
    CLINICAL_PROFILE_ALLERGY_INTOLERANCE_ADDED("Clinical Profile Allergy Intolerance Added"),
    @XmlEnumValue("Clinical Profile Allergy Intolerance Deleted")
    CLINICAL_PROFILE_ALLERGY_INTOLERANCE_DELETED("Clinical Profile Allergy Intolerance Deleted"),
    @XmlEnumValue("Clinical Profile Allergy Intolerance Updated")
    CLINICAL_PROFILE_ALLERGY_INTOLERANCE_UPDATED("Clinical Profile Allergy Intolerance Updated"),
    @XmlEnumValue("Clinical Profile Medical Condition Added")
    CLINICAL_PROFILE_MEDICAL_CONDITION_ADDED("Clinical Profile Medical Condition Added"),
    @XmlEnumValue("Clinical Profile Medical Condition Updated")
    CLINICAL_PROFILE_MEDICAL_CONDITION_UPDATED("Clinical Profile Medical Condition Updated"),
    @XmlEnumValue("Clinical Verification Complete")
    CLINICAL_VERIFICATION_COMPLETE("Clinical Verification Complete"),
    CMPT("CMPT"),
    @XmlEnumValue("CompleteCounselling")
    COMPLETE_COUNSELLING("CompleteCounselling"),
    @XmlEnumValue("CompoundDataChanged")
    COMPOUND_DATA_CHANGED("CompoundDataChanged"),
    @XmlEnumValue("Consent Managed")
    CONSENT_MANAGED("Consent Managed"),
    @XmlEnumValue("ContactCreated")
    CONTACT_CREATED("ContactCreated"),
    @XmlEnumValue("CVAccepted")
    CV_ACCEPTED("CVAccepted"),
    @XmlEnumValue("CVDeclined")
    CV_DECLINED("CVDeclined"),
    @XmlEnumValue("Data Entry Complete Log")
    DATA_ENTRY_COMPLETE_LOG("Data Entry Complete Log"),
    @XmlEnumValue("Data Entry Complete New")
    DATA_ENTRY_COMPLETE_NEW("Data Entry Complete New"),
    @XmlEnumValue("Data Entry Complete Post")
    DATA_ENTRY_COMPLETE_POST("Data Entry Complete Post"),
    @XmlEnumValue("Data Entry Complete Refill")
    DATA_ENTRY_COMPLETE_REFILL("Data Entry Complete Refill"),
    @XmlEnumValue("DataEntrySubmitted")
    DATA_ENTRY_SUBMITTED("DataEntrySubmitted"),
    @XmlEnumValue("Defer")
    DEFER("Defer"),
    @XmlEnumValue("Discontinue Rx Post")
    DISCONTINUE_RX_POST("Discontinue Rx Post"),
    @XmlEnumValue("DiscontinueRx")
    DISCONTINUE_RX("DiscontinueRx"),
    @XmlEnumValue("Display Linkable Prescription Profile")
    DISPLAY_LINKABLE_PRESCRIPTION_PROFILE("Display Linkable Prescription Profile"),
    @XmlEnumValue("Display Linked Prescription Details")
    DISPLAY_LINKED_PRESCRIPTION_DETAILS("Display Linked Prescription Details"),
    @XmlEnumValue("Display Medical Condition History")
    DISPLAY_MEDICAL_CONDITION_HISTORY("Display Medical Condition History"),
    @XmlEnumValue("Display Medical Condition List")
    DISPLAY_MEDICAL_CONDITION_LIST("Display Medical Condition List"),
    @XmlEnumValue("Display Medication Profile")
    DISPLAY_MEDICATION_PROFILE("Display Medication Profile"),
    @XmlEnumValue("Display Patient Observation")
    DISPLAY_PATIENT_OBSERVATION("Display Patient Observation"),
    @XmlEnumValue("Display Patient Reactions")
    DISPLAY_PATIENT_REACTIONS("Display Patient Reactions"),
    @XmlEnumValue("Display Prescription Details")
    DISPLAY_PRESCRIPTION_DETAILS("Display Prescription Details"),
    @XmlEnumValue("Display Provincial Patient")
    DISPLAY_PROVINCIAL_PATIENT("Display Provincial Patient"),
    @XmlEnumValue("Display Provincial Patient List")
    DISPLAY_PROVINCIAL_PATIENT_LIST("Display Provincial Patient List"),
    @XmlEnumValue("Display Provincial Prescriber List")
    DISPLAY_PROVINCIAL_PRESCRIBER_LIST("Display Provincial Prescriber List"),
    @XmlEnumValue("Display Provincial Provider List")
    DISPLAY_PROVINCIAL_PROVIDER_LIST("Display Provincial Provider List"),
    @XmlEnumValue("DrugDataChanged")
    DRUG_DATA_CHANGED("DrugDataChanged"),
    @XmlEnumValue("DUR Management Completed")
    DUR_MANAGEMENT_COMPLETED("DUR Management Completed"),
    @XmlEnumValue("DURExecuted")
    DUR_EXECUTED("DURExecuted"),
    @XmlEnumValue("DVAccepted")
    DV_ACCEPTED("DVAccepted"),
    @XmlEnumValue("DVDeclined")
    DV_DECLINED("DVDeclined"),
    @XmlEnumValue("Electronic Import")
    ELECTRONIC_IMPORT("Electronic Import"),
    @XmlEnumValue("Get Allergy Intolerance Profile")
    GET_ALLERGY_INTOLERANCE_PROFILE("Get Allergy Intolerance Profile"),
    @XmlEnumValue("Get Allergy Intolerance Record History")
    GET_ALLERGY_INTOLERANCE_RECORD_HISTORY("Get Allergy Intolerance Record History"),
    @XmlEnumValue("Get Patient Comprehensive Profile")
    GET_PATIENT_COMPREHENSIVE_PROFILE("Get Patient Comprehensive Profile"),
    @XmlEnumValue("Link Patient")
    LINK_PATIENT("Link Patient"),
    @XmlEnumValue("Medical Condition Note Added")
    MEDICAL_CONDITION_NOTE_ADDED("Medical Condition Note Added"),
    @XmlEnumValue("Medical Condition Note Deleted")
    MEDICAL_CONDITION_NOTE_DELETED("Medical Condition Note Deleted"),
    @XmlEnumValue("Patient Added")
    PATIENT_ADDED("Patient Added"),
    @XmlEnumValue("Patient Deleted")
    PATIENT_DELETED("Patient Deleted"),
    @XmlEnumValue("Patient Linked")
    PATIENT_LINKED("Patient Linked"),
    @XmlEnumValue("Patient Merged")
    PATIENT_MERGED("Patient Merged"),
    @XmlEnumValue("Patient Note Added")
    PATIENT_NOTE_ADDED("Patient Note Added"),
    @XmlEnumValue("Patient Note Deleted")
    PATIENT_NOTE_DELETED("Patient Note Deleted"),
    @XmlEnumValue("Patient Observation Added")
    PATIENT_OBSERVATION_ADDED("Patient Observation Added"),
    @XmlEnumValue("Patient Observation Deleted")
    PATIENT_OBSERVATION_DELETED("Patient Observation Deleted"),
    @XmlEnumValue("Patient Observation Note Added")
    PATIENT_OBSERVATION_NOTE_ADDED("Patient Observation Note Added"),
    @XmlEnumValue("Patient Order Complete")
    PATIENT_ORDER_COMPLETE("Patient Order Complete"),
    @XmlEnumValue("Patient Order Complete Delete")
    PATIENT_ORDER_COMPLETE_DELETE("Patient Order Complete Delete"),
    @XmlEnumValue("Patient Updated")
    PATIENT_UPDATED("Patient Updated"),
    @XmlEnumValue("PatientCounsellingCompleted")
    PATIENT_COUNSELLING_COMPLETED("PatientCounsellingCompleted"),
    @XmlEnumValue("PatientDataChanged")
    PATIENT_DATA_CHANGED("PatientDataChanged"),
    @XmlEnumValue("PCAccepted")
    PC_ACCEPTED("PCAccepted"),
    @XmlEnumValue("PCDeclined")
    PC_DECLINED("PCDeclined"),
    @XmlEnumValue("PharmacistRenewable")
    PHARMACIST_RENEWABLE("PharmacistRenewable"),
    @XmlEnumValue("PostponeTx")
    POSTPONE_TX("PostponeTx"),
    @XmlEnumValue("PrescriberDataChanged")
    PRESCRIBER_DATA_CHANGED("PrescriberDataChanged"),
    @XmlEnumValue("Provincial Patient Added")
    PROVINCIAL_PATIENT_ADDED("Provincial Patient Added"),
    @XmlEnumValue("RebillClaims")
    REBILL_CLAIMS("RebillClaims"),
    @XmlEnumValue("RefillCreated")
    REFILL_CREATED("RefillCreated"),
    @XmlEnumValue("Refusal to Fill")
    REFUSAL_TO_FILL("Refusal to Fill"),
    @XmlEnumValue("Refusal To Fill Deleted")
    REFUSAL_TO_FILL_DELETED("Refusal To Fill Deleted"),
    @XmlEnumValue("Rx Cancel Complete Delete")
    RX_CANCEL_COMPLETE_DELETE("Rx Cancel Complete Delete"),
    @XmlEnumValue("Rx Discontinue")
    RX_DISCONTINUE("Rx Discontinue"),
    @XmlEnumValue("Rx Log Modified")
    RX_LOG_MODIFIED("Rx Log Modified"),
    @XmlEnumValue("Rx Modified Complete Delete")
    RX_MODIFIED_COMPLETE_DELETE("Rx Modified Complete Delete"),
    @XmlEnumValue("Rx Note Added")
    RX_NOTE_ADDED("Rx Note Added"),
    @XmlEnumValue("Rx Note Deleted")
    RX_NOTE_DELETED("Rx Note Deleted"),
    @XmlEnumValue("Rx Resumed")
    RX_RESUMED("Rx Resumed"),
    @XmlEnumValue("Rx Transferred")
    RX_TRANSFERRED("Rx Transferred"),
    @XmlEnumValue("RxAdapted")
    RX_ADAPTED("RxAdapted"),
    @XmlEnumValue("RxDataChanged")
    RX_DATA_CHANGED("RxDataChanged"),
    @XmlEnumValue("RxIntakeSubmitted")
    RX_INTAKE_SUBMITTED("RxIntakeSubmitted"),
    @XmlEnumValue("RxLogged")
    RX_LOGGED("RxLogged"),
    @XmlEnumValue("RxPickUpCompleted")
    RX_PICK_UP_COMPLETED("RxPickUpCompleted"),
    @XmlEnumValue("RxPosted")
    RX_POSTED("RxPosted"),
    @XmlEnumValue("RxTransfer")
    RX_TRANSFER("RxTransfer"),
    @XmlEnumValue("Search Location")
    SEARCH_LOCATION("Search Location"),
    @XmlEnumValue("Search Patient")
    SEARCH_PATIENT("Search Patient"),
    @XmlEnumValue("Search Pharmacy")
    SEARCH_PHARMACY("Search Pharmacy"),
    SSF("SSF"),
    @XmlEnumValue("StoreDataChanged")
    STORE_DATA_CHANGED("StoreDataChanged"),
    @XmlEnumValue("SubmitFilling")
    SUBMIT_FILLING("SubmitFilling"),
    @XmlEnumValue("Tx Cancel Complete")
    TX_CANCEL_COMPLETE("Tx Cancel Complete"),
    @XmlEnumValue("Tx Cancel Complete Delete")
    TX_CANCEL_COMPLETE_DELETE("Tx Cancel Complete Delete"),
    @XmlEnumValue("Tx Cancel Complete Post")
    TX_CANCEL_COMPLETE_POST("Tx Cancel Complete Post"),
    @XmlEnumValue("Tx Modify Complete Post")
    TX_MODIFY_COMPLETE_POST("Tx Modify Complete Post"),
    @XmlEnumValue("Tx Note Added")
    TX_NOTE_ADDED("Tx Note Added"),
    @XmlEnumValue("Tx Note Deleted")
    TX_NOTE_DELETED("Tx Note Deleted"),
    @XmlEnumValue("TxCancelled")
    TX_CANCELLED("TxCancelled"),
    @XmlEnumValue("TxDataChanged")
    TX_DATA_CHANGED("TxDataChanged"),
    @XmlEnumValue("TxModifyCompleted")
    TX_MODIFY_COMPLETED("TxModifyCompleted"),
    @XmlEnumValue("Unauth")
    UNAUTH("Unauth"),
    @XmlEnumValue("UnauthProcessCreated")
    UNAUTH_PROCESS_CREATED("UnauthProcessCreated"),
    @XmlEnumValue("VarianceAdjusted")
    VARIANCE_ADJUSTED("VarianceAdjusted");
    private final String value;

    EventEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventEnum fromValue(String v) {
        for (EventEnum c: EventEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
