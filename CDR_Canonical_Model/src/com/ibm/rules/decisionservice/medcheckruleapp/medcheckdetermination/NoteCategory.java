
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NoteCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="diagnosticImage"/>
 *     &lt;enumeration value="general"/>
 *     &lt;enumeration value="immunization"/>
 *     &lt;enumeration value="laboratory"/>
 *     &lt;enumeration value="medication"/>
 *     &lt;enumeration value="patientInstruction"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
