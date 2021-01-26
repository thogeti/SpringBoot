
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubstitutionReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubstitutionReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Continuing Therapy"/&gt;
 *     &lt;enumeration value="Formulary Policy"/&gt;
 *     &lt;enumeration value="Out of Stock"/&gt;
 *     &lt;enumeration value="Regulatory Requirement"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SubstitutionReason")
@XmlEnum
public enum SubstitutionReason {

    @XmlEnumValue("Continuing Therapy")
    CONTINUING_THERAPY("Continuing Therapy"),
    @XmlEnumValue("Formulary Policy")
    FORMULARY_POLICY("Formulary Policy"),
    @XmlEnumValue("Out of Stock")
    OUT_OF_STOCK("Out of Stock"),
    @XmlEnumValue("Regulatory Requirement")
    REGULATORY_REQUIREMENT("Regulatory Requirement");
    private final String value;

    SubstitutionReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubstitutionReason fromValue(String v) {
        for (SubstitutionReason c: SubstitutionReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
