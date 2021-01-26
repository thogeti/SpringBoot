
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllergyTestResult.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AllergyTestResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Mild Reaction"/&gt;
 *     &lt;enumeration value="Minimal Reaction"/&gt;
 *     &lt;enumeration value="Moderate Reaction"/&gt;
 *     &lt;enumeration value="No Reaction"/&gt;
 *     &lt;enumeration value="Severe Reaction"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AllergyTestResult")
@XmlEnum
public enum AllergyTestResult {

    @XmlEnumValue("Mild Reaction")
    MILD_REACTION("Mild Reaction"),
    @XmlEnumValue("Minimal Reaction")
    MINIMAL_REACTION("Minimal Reaction"),
    @XmlEnumValue("Moderate Reaction")
    MODERATE_REACTION("Moderate Reaction"),
    @XmlEnumValue("No Reaction")
    NO_REACTION("No Reaction"),
    @XmlEnumValue("Severe Reaction")
    SEVERE_REACTION("Severe Reaction");
    private final String value;

    AllergyTestResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AllergyTestResult fromValue(String v) {
        for (AllergyTestResult c: AllergyTestResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
