
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NoteCategory"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="diagnosticImage"/&gt;
 *     &lt;enumeration value="general"/&gt;
 *     &lt;enumeration value="immunization"/&gt;
 *     &lt;enumeration value="laboratory"/&gt;
 *     &lt;enumeration value="medication"/&gt;
 *     &lt;enumeration value="patientInstruction"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NoteCategory")
@XmlEnum
public enum NoteCategory {

    @XmlEnumValue("diagnosticImage")
    DIAGNOSTIC_IMAGE("diagnosticImage"),
    @XmlEnumValue("general")
    GENERAL("general"),
    @XmlEnumValue("immunization")
    IMMUNIZATION("immunization"),
    @XmlEnumValue("laboratory")
    LABORATORY("laboratory"),
    @XmlEnumValue("medication")
    MEDICATION("medication"),
    @XmlEnumValue("patientInstruction")
    PATIENT_INSTRUCTION("patientInstruction");
    private final String value;

    NoteCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NoteCategory fromValue(String v) {
        for (NoteCategory c: NoteCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
