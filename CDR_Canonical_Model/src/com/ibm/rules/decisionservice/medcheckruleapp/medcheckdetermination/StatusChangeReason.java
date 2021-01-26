
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusChangeReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusChangeReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="error correction"/>
 *     &lt;enumeration value="information change"/>
 *     &lt;enumeration value="new information"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusChangeReason")
@XmlEnum
public enum StatusChangeReason {

    @XmlEnumValue("error correction")
    ERROR_CORRECTION("error correction"),
    @XmlEnumValue("information change")
    INFORMATION_CHANGE("information change"),
    @XmlEnumValue("new information")
    NEW_INFORMATION("new information");
    private final String value;

    StatusChangeReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusChangeReason fromValue(String v) {
        for (StatusChangeReason c: StatusChangeReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
