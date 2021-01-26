
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefDataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RefDataType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="professionalService"/&gt;
 *     &lt;enumeration value="post"/&gt;
 *     &lt;enumeration value="prescription"/&gt;
 *     &lt;enumeration value="patient"/&gt;
 *     &lt;enumeration value="dispense"/&gt;
 *     &lt;enumeration value="allergy"/&gt;
 *     &lt;enumeration value="adverseDrugReaction"/&gt;
 *     &lt;enumeration value="note"/&gt;
 *     &lt;enumeration value="patientMedicalCondition"/&gt;
 *     &lt;enumeration value="patientObservation"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RefDataType")
@XmlEnum
public enum RefDataType {

    @XmlEnumValue("professionalService")
    PROFESSIONAL_SERVICE("professionalService"),
    @XmlEnumValue("post")
    POST("post"),
    @XmlEnumValue("prescription")
    PRESCRIPTION("prescription"),
    @XmlEnumValue("patient")
    PATIENT("patient"),
    @XmlEnumValue("dispense")
    DISPENSE("dispense"),
    @XmlEnumValue("allergy")
    ALLERGY("allergy"),
    @XmlEnumValue("adverseDrugReaction")
    ADVERSE_DRUG_REACTION("adverseDrugReaction"),
    @XmlEnumValue("note")
    NOTE("note"),
    @XmlEnumValue("patientMedicalCondition")
    PATIENT_MEDICAL_CONDITION("patientMedicalCondition"),
    @XmlEnumValue("patientObservation")
    PATIENT_OBSERVATION("patientObservation");
    private final String value;

    RefDataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RefDataType fromValue(String v) {
        for (RefDataType c: RefDataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
