
package generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RxHBBusinessEventProcessEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RxHBBusinessEventProcessEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RxIntake"/&gt;
 *     &lt;enumeration value="DataVerification"/&gt;
 *     &lt;enumeration value="ClinicalVerification"/&gt;
 *     &lt;enumeration value="Fax"/&gt;
 *     &lt;enumeration value="Refill"/&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Reauth"/&gt;
 *     &lt;enumeration value="Prescriber"/&gt;
 *     &lt;enumeration value="AdaptRx"/&gt;
 *     &lt;enumeration value="AutoPostpone"/&gt;
 *     &lt;enumeration value="CMPT"/&gt;
 *     &lt;enumeration value="Compound"/&gt;
 *     &lt;enumeration value="Contact"/&gt;
 *     &lt;enumeration value="DataEntry"/&gt;
 *     &lt;enumeration value="Defer"/&gt;
 *     &lt;enumeration value="Drug"/&gt;
 *     &lt;enumeration value="Event-Framework"/&gt;
 *     &lt;enumeration value="Filling"/&gt;
 *     &lt;enumeration value="PatientCounselling"/&gt;
 *     &lt;enumeration value="PharmacistRenewal"/&gt;
 *     &lt;enumeration value="PickUpAssist"/&gt;
 *     &lt;enumeration value="PostponeTx"/&gt;
 *     &lt;enumeration value="ProductVerification"/&gt;
 *     &lt;enumeration value="RebillClaims"/&gt;
 *     &lt;enumeration value="RxTransfer"/&gt;
 *     &lt;enumeration value="Store"/&gt;
 *     &lt;enumeration value="Opioid"/&gt;
 *     &lt;enumeration value="PurgeProcess"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RxHBBusinessEventProcessEnum")
@XmlEnum
public enum RxHBBusinessEventProcessEnum {

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
    OPIOID("Opioid"),
    @XmlEnumValue("PurgeProcess")
    PURGE_PROCESS("PurgeProcess");
    private final String value;

    RxHBBusinessEventProcessEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RxHBBusinessEventProcessEnum fromValue(String v) {
        for (RxHBBusinessEventProcessEnum c: RxHBBusinessEventProcessEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
