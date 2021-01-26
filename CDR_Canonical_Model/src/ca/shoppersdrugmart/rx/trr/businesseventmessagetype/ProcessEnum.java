
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProcessEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RxIntake"/>
 *     &lt;enumeration value="DataVerification"/>
 *     &lt;enumeration value="ClinicalVerification"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Refill"/>
 *     &lt;enumeration value="Patient"/>
 *     &lt;enumeration value="Reauth"/>
 *     &lt;enumeration value="Prescriber"/>
 *     &lt;enumeration value="AdaptRx"/>
 *     &lt;enumeration value="AutoPostpone"/>
 *     &lt;enumeration value="CMPT"/>
 *     &lt;enumeration value="Compound"/>
 *     &lt;enumeration value="Contact"/>
 *     &lt;enumeration value="DataEntry"/>
 *     &lt;enumeration value="Defer"/>
 *     &lt;enumeration value="Drug"/>
 *     &lt;enumeration value="Event-Framework"/>
 *     &lt;enumeration value="Filling"/>
 *     &lt;enumeration value="PatientCounselling"/>
 *     &lt;enumeration value="PharmacistRenewal"/>
 *     &lt;enumeration value="PickUpAssist"/>
 *     &lt;enumeration value="PostponeTx"/>
 *     &lt;enumeration value="ProductVerification"/>
 *     &lt;enumeration value="RebillClaims"/>
 *     &lt;enumeration value="RxTransfer"/>
 *     &lt;enumeration value="Store"/>
 *     &lt;enumeration value="Opioid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProcessEnum")
@XmlEnum
public enum ProcessEnum {

    @XmlEnumValue("RxIntake")
    RX_INTAKE("RxIntake"),
    @XmlEnumValue("DataVerification")
    DATA_VERIFICATION("DataVerification"),
    @XmlEnumValue("ClinicalVerification")
    CLINICAL_VERIFICATION("ClinicalVerification"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Refill")
    REFILL("Refill"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Reauth")
    REAUTH("Reauth"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("AdaptRx")
    ADAPT_RX("AdaptRx"),
    @XmlEnumValue("AutoPostpone")
    AUTO_POSTPONE("AutoPostpone"),
    CMPT("CMPT"),
    @XmlEnumValue("Compound")
    COMPOUND("Compound"),
    @XmlEnumValue("Contact")
    CONTACT("Contact"),
    @XmlEnumValue("DataEntry")
    DATA_ENTRY("DataEntry"),
    @XmlEnumValue("Defer")
    DEFER("Defer"),
    @XmlEnumValue("Drug")
    DRUG("Drug"),
    @XmlEnumValue("Event-Framework")
    EVENT_FRAMEWORK("Event-Framework"),
    @XmlEnumValue("Filling")
    FILLING("Filling"),
    @XmlEnumValue("PatientCounselling")
    PATIENT_COUNSELLING("PatientCounselling"),
    @XmlEnumValue("PharmacistRenewal")
    PHARMACIST_RENEWAL("PharmacistRenewal"),
    @XmlEnumValue("PickUpAssist")
    PICK_UP_ASSIST("PickUpAssist"),
    @XmlEnumValue("PostponeTx")
    POSTPONE_TX("PostponeTx"),
    @XmlEnumValue("ProductVerification")
    PRODUCT_VERIFICATION("ProductVerification"),
    @XmlEnumValue("RebillClaims")
    REBILL_CLAIMS("RebillClaims"),
    @XmlEnumValue("RxTransfer")
    RX_TRANSFER("RxTransfer"),
    @XmlEnumValue("Store")
    STORE("Store"),
    @XmlEnumValue("Opioid")
    OPIOID("Opioid");
    private final String value;

    ProcessEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessEnum fromValue(String v) {
        for (ProcessEnum c: ProcessEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
