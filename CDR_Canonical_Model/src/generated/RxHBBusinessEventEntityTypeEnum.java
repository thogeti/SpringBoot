
package generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RxHBBusinessEventEntityTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RxHBBusinessEventEntityTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Allergy"/&gt;
 *     &lt;enumeration value="Compound"/&gt;
 *     &lt;enumeration value="Consent"/&gt;
 *     &lt;enumeration value="Dispense"/&gt;
 *     &lt;enumeration value="Dispenser"/&gt;
 *     &lt;enumeration value="Drug"/&gt;
 *     &lt;enumeration value="Note"/&gt;
 *     &lt;enumeration value="Pack"/&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Prescriber"/&gt;
 *     &lt;enumeration value="Prescription"/&gt;
 *     &lt;enumeration value="Store"/&gt;
 *     &lt;enumeration value="AdverseDrugReaction"/&gt;
 *     &lt;enumeration value="PharmacyChannel"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RxHBBusinessEventEntityTypeEnum")
@XmlEnum
public enum RxHBBusinessEventEntityTypeEnum {

    @XmlEnumValue("Allergy")
    ALLERGY("Allergy"),
    @XmlEnumValue("Compound")
    COMPOUND("Compound"),
    @XmlEnumValue("Consent")
    CONSENT("Consent"),
    @XmlEnumValue("Dispense")
    DISPENSE("Dispense"),
    @XmlEnumValue("Dispenser")
    DISPENSER("Dispenser"),
    @XmlEnumValue("Drug")
    DRUG("Drug"),
    @XmlEnumValue("Note")
    NOTE("Note"),
    @XmlEnumValue("Pack")
    PACK("Pack"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("Prescription")
    PRESCRIPTION("Prescription"),
    @XmlEnumValue("Store")
    STORE("Store"),
    @XmlEnumValue("AdverseDrugReaction")
    ADVERSE_DRUG_REACTION("AdverseDrugReaction"),
    @XmlEnumValue("PharmacyChannel")
    PHARMACY_CHANNEL("PharmacyChannel");
    private final String value;

    RxHBBusinessEventEntityTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RxHBBusinessEventEntityTypeEnum fromValue(String v) {
        for (RxHBBusinessEventEntityTypeEnum c: RxHBBusinessEventEntityTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
