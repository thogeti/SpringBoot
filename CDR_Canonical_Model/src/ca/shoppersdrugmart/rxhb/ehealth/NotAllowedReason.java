
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotAllowedReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NotAllowedReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="Allergy/Intolerance"/&gt;
 *     &lt;enumeration value="Patient Choice"/&gt;
 *     &lt;enumeration value="Therapeutic Characteristics"/&gt;
 *     &lt;enumeration value="Compliance Concern"/&gt;
 *     &lt;enumeration value="Clinical Trial"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NotAllowedReason")
@XmlEnum
public enum NotAllowedReason {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Allergy/Intolerance")
    ALLERGY_INTOLERANCE("Allergy/Intolerance"),
    @XmlEnumValue("Patient Choice")
    PATIENT_CHOICE("Patient Choice"),
    @XmlEnumValue("Therapeutic Characteristics")
    THERAPEUTIC_CHARACTERISTICS("Therapeutic Characteristics"),
    @XmlEnumValue("Compliance Concern")
    COMPLIANCE_CONCERN("Compliance Concern"),
    @XmlEnumValue("Clinical Trial")
    CLINICAL_TRIAL("Clinical Trial");
    private final String value;

    NotAllowedReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NotAllowedReason fromValue(String v) {
        for (NotAllowedReason c: NotAllowedReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
