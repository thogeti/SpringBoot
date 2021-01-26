
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="regular"/>
 *     &lt;enumeration value="animal"/>
 *     &lt;enumeration value="pharmacy"/>
 *     &lt;enumeration value="prescriber/OfficeUse"/>
 *     &lt;enumeration value="healthCareFacility"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PatientType")
@XmlEnum
public enum PatientType {

    @XmlEnumValue("regular")
    REGULAR("regular"),
    @XmlEnumValue("animal")
    ANIMAL("animal"),
    @XmlEnumValue("pharmacy")
    PHARMACY("pharmacy"),
    @XmlEnumValue("prescriber/OfficeUse")
    PRESCRIBER_OFFICE_USE("prescriber/OfficeUse"),
    @XmlEnumValue("healthCareFacility")
    HEALTH_CARE_FACILITY("healthCareFacility");
    private final String value;

    PatientType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientType fromValue(String v) {
        for (PatientType c: PatientType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
