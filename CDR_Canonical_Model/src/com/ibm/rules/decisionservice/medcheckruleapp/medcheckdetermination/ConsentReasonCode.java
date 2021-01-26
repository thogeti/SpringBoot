
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsentReasonCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConsentReasonCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Accepted"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="Declined with Ask Later"/>
 *     &lt;enumeration value="Not Recorded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConsentReasonCode")
@XmlEnum
public enum ConsentReasonCode {

    @XmlEnumValue("Accepted")
    ACCEPTED("Accepted"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("Declined with Ask Later")
    DECLINED_WITH_ASK_LATER("Declined with Ask Later"),
    @XmlEnumValue("Not Recorded")
    NOT_RECORDED("Not Recorded");
    private final String value;

    ConsentReasonCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConsentReasonCode fromValue(String v) {
        for (ConsentReasonCode c: ConsentReasonCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
