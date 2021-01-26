
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefusalReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RefusalReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Abuse"/&gt;
 *     &lt;enumeration value="Falsified/Invalid"/&gt;
 *     &lt;enumeration value="Inappropriate Therapy"/&gt;
 *     &lt;enumeration value="Incomplete"/&gt;
 *     &lt;enumeration value="Not Current"/&gt;
 *     &lt;enumeration value="Order Stopped"/&gt;
 *     &lt;enumeration value="Unavailable"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RefusalReason")
@XmlEnum
public enum RefusalReason {

    @XmlEnumValue("Abuse")
    ABUSE("Abuse"),
    @XmlEnumValue("Falsified/Invalid")
    FALSIFIED_INVALID("Falsified/Invalid"),
    @XmlEnumValue("Inappropriate Therapy")
    INAPPROPRIATE_THERAPY("Inappropriate Therapy"),
    @XmlEnumValue("Incomplete")
    INCOMPLETE("Incomplete"),
    @XmlEnumValue("Not Current")
    NOT_CURRENT("Not Current"),
    @XmlEnumValue("Order Stopped")
    ORDER_STOPPED("Order Stopped"),
    @XmlEnumValue("Unavailable")
    UNAVAILABLE("Unavailable");
    private final String value;

    RefusalReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RefusalReason fromValue(String v) {
        for (RefusalReason c: RefusalReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
