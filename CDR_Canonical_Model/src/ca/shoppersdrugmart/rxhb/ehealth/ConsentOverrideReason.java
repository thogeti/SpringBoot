
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsentOverrideReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConsentOverrideReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Emergency"/&gt;
 *     &lt;enumeration value="Professional Judgement"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ConsentOverrideReason")
@XmlEnum
public enum ConsentOverrideReason {

    @XmlEnumValue("Emergency")
    EMERGENCY("Emergency"),
    @XmlEnumValue("Professional Judgement")
    PROFESSIONAL_JUDGEMENT("Professional Judgement");
    private final String value;

    ConsentOverrideReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConsentOverrideReason fromValue(String v) {
        for (ConsentOverrideReason c: ConsentOverrideReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
