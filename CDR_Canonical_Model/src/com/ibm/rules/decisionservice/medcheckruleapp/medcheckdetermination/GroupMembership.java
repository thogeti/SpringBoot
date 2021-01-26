
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupMembership.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupMembership">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="retail"/>
 *     &lt;enumeration value="ltc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GroupMembership")
@XmlEnum
public enum GroupMembership {

    @XmlEnumValue("retail")
    RETAIL("retail"),
    @XmlEnumValue("ltc")
    LTC("ltc");
    private final String value;

    GroupMembership(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupMembership fromValue(String v) {
        for (GroupMembership c: GroupMembership.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
