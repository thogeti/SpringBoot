
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EngagementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EngagementType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Guest"/>
 *     &lt;enumeration value="Account"/>
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Accuro"/>
 *     &lt;enumeration value="HWPatient"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EngagementType")
@XmlEnum
public enum EngagementType {

    @XmlEnumValue("Guest")
    GUEST("Guest"),
    @XmlEnumValue("Account")
    ACCOUNT("Account"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),
    @XmlEnumValue("HWPatient")
    HW_PATIENT("HWPatient");
    private final String value;

    EngagementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EngagementType fromValue(String v) {
        for (EngagementType c: EngagementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
