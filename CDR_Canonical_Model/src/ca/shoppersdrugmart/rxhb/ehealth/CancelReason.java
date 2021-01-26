
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CancelReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CancelReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AlteredDecision"/&gt;
 *     &lt;enumeration value="EnteredInError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CancelReason")
@XmlEnum
public enum CancelReason {

    @XmlEnumValue("AlteredDecision")
    ALTERED_DECISION("AlteredDecision"),
    @XmlEnumValue("EnteredInError")
    ENTERED_IN_ERROR("EnteredInError");
    private final String value;

    CancelReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CancelReason fromValue(String v) {
        for (CancelReason c: CancelReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
