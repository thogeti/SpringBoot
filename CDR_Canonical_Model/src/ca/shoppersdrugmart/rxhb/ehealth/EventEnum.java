
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Display Professional Services"/&gt;
 *     &lt;enumeration value="Display Patient Notes"/&gt;
 *     &lt;enumeration value="AbortCancel"/&gt;
 *     &lt;enumeration value="Cancel and Log"/&gt;
 *     &lt;enumeration value="Data Entry Modify"/&gt;
 *     &lt;enumeration value="AdaptRxLogged"/&gt;
 *     &lt;enumeration value="AdjudicatePostponeTx"/&gt;
 *     &lt;enumeration value="ADR Added"/&gt;
 *     &lt;enumeration value="ADR Deleted"/&gt;
 *     &lt;enumeration value="ADR Note Added"/&gt;
 *     &lt;enumeration value="ADR Note Deleted"/&gt;
 *     &lt;enumeration value="ADR Updated"/&gt;
 *     &lt;enumeration value="Allergy Intolerance Note Added"/&gt;
 *     &lt;enumeration value="Allergy Intolerance Note Deleted"/&gt;
 *     &lt;enumeration value="AutoPostpone"/&gt;
 *     &lt;enumeration value="ClaimAdjusted"/&gt;
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Added"/&gt;
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Deleted"/&gt;
 *     &lt;enumeration value="Clinical Profile Allergy Intolerance Updated"/&gt;
 *     &lt;enumeration value="Clinical Profile Medical Condition Added"/&gt;
 *     &lt;enumeration value="Clinical Profile Medical Condition Updated"/&gt;
 *     &lt;enumeration value="Clinical Verification Complete"/&gt;
 *     &lt;enumeration value="CMPT"/&gt;
 *     &lt;enumeration value="CompleteCounselling"/&gt;
 *     &lt;enumeration value="CompoundDataChanged"/&gt;
 *     &lt;enumeration value="Consent Managed"/&gt;
 *     &lt;enumeration value="ContactCreated"/&gt;
 *     &lt;enumeration value="CVAccepted"/&gt;
 *     &lt;enumeration value="CVDeclined"/&gt;
 *     &lt;enumeration value="Data Entry Complete Log"/&gt;
 *     &lt;enumeration value="Data Entry Complete New"/&gt;
 *     &lt;enumeration value="Data Entry Complete Post"/&gt;
 *     &lt;enumeration value="Data Entry Complete Refill"/&gt;
 *     &lt;enumeration value="DataEntrySubmitted"/&gt;
 *     &lt;enumeration value="Defer"/&gt;
 *     &lt;enumeration value="Discontinue Rx Post"/&gt;
 *     &lt;enumeration value="DiscontinueRx"/&gt;
 *     &lt;enumeration value="Display Linkable Prescription Profile"/&gt;
 *     &lt;enumeration value="Display Linked Prescription Details"/&gt;
 *     &lt;enumeration value="Display Medical Condition History"/&gt;
 *     &lt;enumeration value="Display Medical Condition List"/&gt;
 *     &lt;enumeration value="Display Medication Profile"/&gt;
 *     &lt;enumeration value="Display Patient Observation"/&gt;
 *     &lt;enumeration value="Display Patient Reactions"/&gt;
 *     &lt;enumeration value="Display Prescription Details"/&gt;
 *     &lt;enumeration value="Display Provincial Patient"/&gt;
 *     &lt;enumeration value="Display Provincial Patient List"/&gt;
 *     &lt;enumeration value="Display Provincial Prescriber List"/&gt;
 *     &lt;enumeration value="Display Provincial Provider List"/&gt;
 *     &lt;enumeration value="DrugDataChanged"/&gt;
 *     &lt;enumeration value="DUR Management Completed"/&gt;
 *     &lt;enumeration value="DURExecuted"/&gt;
 *     &lt;enumeration value="DVAccepted"/&gt;
 *     &lt;enumeration value="DVDeclined"/&gt;
 *     &lt;enumeration value="Electronic Import"/&gt;
 *     &lt;enumeration value="Get Allergy Intolerance Profile"/&gt;
 *     &lt;enumeration value="Get Allergy Intolerance Record History"/&gt;
 *     &lt;enumeration value="Get Patient Comprehensive Profile"/&gt;
 *     &lt;enumeration value="Link Patient"/&gt;
 *     &lt;enumeration value="Medical Condition Note Added"/&gt;
 *     &lt;enumeration value="Medical Condition Note Deleted"/&gt;
 *     &lt;enumeration value="Patient Added"/&gt;
 *     &lt;enumeration value="Patient Deleted"/&gt;
 *     &lt;enumeration value="Patient Linked"/&gt;
 *     &lt;enumeration value="Patient Merged"/&gt;
 *     &lt;enumeration value="Patient Note Added"/&gt;
 *     &lt;enumeration value="Patient Note Deleted"/&gt;
 *     &lt;enumeration value="Patient Observation Added"/&gt;
 *     &lt;enumeration value="Patient Observation Deleted"/&gt;
 *     &lt;enumeration value="Patient Observation Note Added"/&gt;
 *     &lt;enumeration value="Patient Order Complete"/&gt;
 *     &lt;enumeration value="Patient Order Complete Delete"/&gt;
 *     &lt;enumeration value="Patient Updated"/&gt;
 *     &lt;enumeration value="PatientCounsellingCompleted"/&gt;
 *     &lt;enumeration value="PatientDataChanged"/&gt;
 *     &lt;enumeration value="PCAccepted"/&gt;
 *     &lt;enumeration value="PCDeclined"/&gt;
 *     &lt;enumeration value="PharmacistRenewable"/&gt;
 *     &lt;enumeration value="PostponeTx"/&gt;
 *     &lt;enumeration value="PrescriberDataChanged"/&gt;
 *     &lt;enumeration value="Provincial Patient Added"/&gt;
 *     &lt;enumeration value="RebillClaims"/&gt;
 *     &lt;enumeration value="RefillCreated"/&gt;
 *     &lt;enumeration value="Refusal to Fill"/&gt;
 *     &lt;enumeration value="Refusal To Fill Deleted"/&gt;
 *     &lt;enumeration value="Rx Cancel Complete Delete"/&gt;
 *     &lt;enumeration value="Rx Discontinue"/&gt;
 *     &lt;enumeration value="Rx Log Modified"/&gt;
 *     &lt;enumeration value="Rx Modified Complete Delete"/&gt;
 *     &lt;enumeration value="Rx Note Added"/&gt;
 *     &lt;enumeration value="Rx Note Deleted"/&gt;
 *     &lt;enumeration value="Rx Resumed"/&gt;
 *     &lt;enumeration value="Rx Transferred"/&gt;
 *     &lt;enumeration value="RxAdapted"/&gt;
 *     &lt;enumeration value="RxDataChanged"/&gt;
 *     &lt;enumeration value="RxIntakeSubmitted"/&gt;
 *     &lt;enumeration value="RxLogged"/&gt;
 *     &lt;enumeration value="RxPickUpCompleted"/&gt;
 *     &lt;enumeration value="RxPosted"/&gt;
 *     &lt;enumeration value="RxTransfer"/&gt;
 *     &lt;enumeration value="Search Location"/&gt;
 *     &lt;enumeration value="Search Patient"/&gt;
 *     &lt;enumeration value="Search Pharmacy"/&gt;
 *     &lt;enumeration value="SSF"/&gt;
 *     &lt;enumeration value="StoreDataChanged"/&gt;
 *     &lt;enumeration value="SubmitFilling"/&gt;
 *     &lt;enumeration value="Tx Cancel Complete"/&gt;
 *     &lt;enumeration value="Tx Cancel Complete Delete"/&gt;
 *     &lt;enumeration value="Tx Cancel Complete Post"/&gt;
 *     &lt;enumeration value="Tx Modify Complete Post"/&gt;
 *     &lt;enumeration value="Tx Note Added"/&gt;
 *     &lt;enumeration value="Tx Note Deleted"/&gt;
 *     &lt;enumeration value="TxCancelled"/&gt;
 *     &lt;enumeration value="TxDataChanged"/&gt;
 *     &lt;enumeration value="TxModifyCompleted"/&gt;
 *     &lt;enumeration value="Unauth"/&gt;
 *     &lt;enumeration value="UnauthProcessCreated"/&gt;
 *     &lt;enumeration value="VarianceAdjusted"/&gt;
 *     &lt;enumeration value="Auto Rx Discontinue"/&gt;
 *     &lt;enumeration value="Display Patient Allergies"/&gt;
 *     &lt;enumeration value="Display Patient ADRs"/&gt;
 *     &lt;enumeration value="Display Drug Profile"/&gt;
 *     &lt;enumeration value="Display Device Profile"/&gt;
 *     &lt;enumeration value="RefillFailed"/&gt;
 *     &lt;enumeration value="RefillSuccess"/&gt;
 *     &lt;enumeration value="ReauthFailed"/&gt;
 *     &lt;enumeration value="ReauthSuccess"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EventEnum")
@XmlEnum
public enum EventEnum {

    @XmlEnumValue("Display Professional Services")
    DISPLAY_PROFESSIONAL_SERVICES("Display Professional Services"),
    @XmlEnumValue("Display Patient Notes")
    DISPLAY_PATIENT_NOTES("Display Patient Notes"),
    @XmlEnumValue("AbortCancel")
    ABORT_CANCEL("AbortCancel"),
    @XmlEnumValue("Cancel and Log")
    CANCEL_AND_LOG("Cancel and Log"),
    @XmlEnumValue("Data Entry Modify")
    DATA_ENTRY_MODIFY("Data Entry Modify"),
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
    VARIANCE_ADJUSTED("VarianceAdjusted"),
    @XmlEnumValue("Auto Rx Discontinue")
    AUTO_RX_DISCONTINUE("Auto Rx Discontinue"),
    @XmlEnumValue("Display Patient Allergies")
    DISPLAY_PATIENT_ALLERGIES("Display Patient Allergies"),
    @XmlEnumValue("Display Patient ADRs")
    DISPLAY_PATIENT_AD_RS("Display Patient ADRs"),
    @XmlEnumValue("Display Drug Profile")
    DISPLAY_DRUG_PROFILE("Display Drug Profile"),
    @XmlEnumValue("Display Device Profile")
    DISPLAY_DEVICE_PROFILE("Display Device Profile"),
    @XmlEnumValue("RefillFailed")
    REFILL_FAILED("RefillFailed"),
    @XmlEnumValue("RefillSuccess")
    REFILL_SUCCESS("RefillSuccess"),
    @XmlEnumValue("ReauthFailed")
    REAUTH_FAILED("ReauthFailed"),
    @XmlEnumValue("ReauthSuccess")
    REAUTH_SUCCESS("ReauthSuccess");
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
