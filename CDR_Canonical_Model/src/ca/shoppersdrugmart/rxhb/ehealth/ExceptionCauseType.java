
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExceptionCauseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExceptionCauseType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Prescription"/&gt;
 *     &lt;enumeration value="Dispense"/&gt;
 *     &lt;enumeration value="Patient Observation"/&gt;
 *     &lt;enumeration value="Allergy/Intolerance"/&gt;
 *     &lt;enumeration value="Medical Condition"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExceptionCauseType")
@XmlEnum
public enum ExceptionCauseType {

    @XmlEnumValue("Prescription")
    PRESCRIPTION("Prescription"),
    @XmlEnumValue("Dispense")
    DISPENSE("Dispense"),
    @XmlEnumValue("Patient Observation")
    PATIENT_OBSERVATION("Patient Observation"),
    @XmlEnumValue("Allergy/Intolerance")
    ALLERGY_INTOLERANCE("Allergy/Intolerance"),
    @XmlEnumValue("Medical Condition")
    MEDICAL_CONDITION("Medical Condition");
    private final String value;

    ExceptionCauseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExceptionCauseType fromValue(String v) {
        for (ExceptionCauseType c: ExceptionCauseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
