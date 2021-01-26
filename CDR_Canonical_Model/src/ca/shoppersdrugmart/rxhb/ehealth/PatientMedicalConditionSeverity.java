
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientMedicalConditionSeverity.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientMedicalConditionSeverity"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Active"/&gt;
 *     &lt;enumeration value="Completed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PatientMedicalConditionSeverity")
@XmlEnum
public enum PatientMedicalConditionSeverity {


    /**
     * Tracking of the condition is active
     * 
     */
    @XmlEnumValue("Active")
    ACTIVE("Active"),

    /**
     * monitoring of the condition has ceased
     * 
     */
    @XmlEnumValue("Completed")
    COMPLETED("Completed");
    private final String value;

    PatientMedicalConditionSeverity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientMedicalConditionSeverity fromValue(String v) {
        for (PatientMedicalConditionSeverity c: PatientMedicalConditionSeverity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
