
package ca.shoppersdrugmart.rxhb.drx.subscriptionservicev2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubscriptionEntityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubscriptionEntityType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Rx"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SubscriptionEntityType")
@XmlEnum
public enum SubscriptionEntityType {

    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Rx")
    RX("Rx");
    private final String value;

    SubscriptionEntityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubscriptionEntityType fromValue(String v) {
        for (SubscriptionEntityType c: SubscriptionEntityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
