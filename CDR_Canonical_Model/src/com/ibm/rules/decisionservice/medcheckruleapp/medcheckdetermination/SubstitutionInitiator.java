
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubstitutionInitiator.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubstitutionInitiator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Prescriber"/>
 *     &lt;enumeration value="Patient"/>
 *     &lt;enumeration value="Pharmacist"/>
 *     &lt;enumeration value="Exist"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SubstitutionInitiator")
@XmlEnum
public enum SubstitutionInitiator {

    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Pharmacist")
    PHARMACIST("Pharmacist"),
    @XmlEnumValue("Exist")
    EXIST("Exist");
    private final String value;

    SubstitutionInitiator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubstitutionInitiator fromValue(String v) {
        for (SubstitutionInitiator c: SubstitutionInitiator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
