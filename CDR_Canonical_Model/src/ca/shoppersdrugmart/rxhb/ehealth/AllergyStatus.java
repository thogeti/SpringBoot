
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllergyStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AllergyStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Suspected"/&gt;
 *     &lt;enumeration value="Confirmed"/&gt;
 *     &lt;enumeration value="Resolved-Unconfirmed"/&gt;
 *     &lt;enumeration value="Resolved-Confirmed"/&gt;
 *     &lt;enumeration value="Refuted-Unconfirmed"/&gt;
 *     &lt;enumeration value="Refuted-Confirmed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AllergyStatus")
@XmlEnum
public enum AllergyStatus {

    @XmlEnumValue("Suspected")
    SUSPECTED("Suspected"),
    @XmlEnumValue("Confirmed")
    CONFIRMED("Confirmed"),
    @XmlEnumValue("Resolved-Unconfirmed")
    RESOLVED_UNCONFIRMED("Resolved-Unconfirmed"),
    @XmlEnumValue("Resolved-Confirmed")
    RESOLVED_CONFIRMED("Resolved-Confirmed"),
    @XmlEnumValue("Refuted-Unconfirmed")
    REFUTED_UNCONFIRMED("Refuted-Unconfirmed"),
    @XmlEnumValue("Refuted-Confirmed")
    REFUTED_CONFIRMED("Refuted-Confirmed");
    private final String value;

    AllergyStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AllergyStatus fromValue(String v) {
        for (AllergyStatus c: AllergyStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
