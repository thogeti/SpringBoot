
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessClassification.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BusinessClassification"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Business"/&gt;
 *     &lt;enumeration value="Consent"/&gt;
 *     &lt;enumeration value="Business Data"/&gt;
 *     &lt;enumeration value="System"/&gt;
 *     &lt;enumeration value="DUR"/&gt;
 *     &lt;enumeration value="All Done"/&gt;
 *     &lt;enumeration value="Held"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "BusinessClassification")
@XmlEnum
public enum BusinessClassification {

    @XmlEnumValue("Business")
    BUSINESS("Business"),
    @XmlEnumValue("Consent")
    CONSENT("Consent"),
    @XmlEnumValue("Business Data")
    BUSINESS_DATA("Business Data"),
    @XmlEnumValue("System")
    SYSTEM("System"),
    DUR("DUR"),
    @XmlEnumValue("All Done")
    ALL_DONE("All Done"),
    @XmlEnumValue("Held")
    HELD("Held");
    private final String value;

    BusinessClassification(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BusinessClassification fromValue(String v) {
        for (BusinessClassification c: BusinessClassification.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
