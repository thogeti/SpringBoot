
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NoteType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Prescription"/>
 *     &lt;enumeration value="Dispense"/>
 *     &lt;enumeration value="Allergy"/>
 *     &lt;enumeration value="Drug"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NoteType")
@XmlEnum
public enum NoteType {

    @XmlEnumValue("Prescription")
    PRESCRIPTION("Prescription"),
    @XmlEnumValue("Dispense")
    DISPENSE("Dispense"),
    @XmlEnumValue("Allergy")
    ALLERGY("Allergy"),
    @XmlEnumValue("Drug")
    DRUG("Drug");
    private final String value;

    NoteType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NoteType fromValue(String v) {
        for (NoteType c: NoteType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
