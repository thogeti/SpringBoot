
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QueryReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Patient Request"/&gt;
 *     &lt;enumeration value="Patient Care"/&gt;
 *     &lt;enumeration value="Research"/&gt;
 *     &lt;enumeration value="Legal"/&gt;
 *     &lt;enumeration value="Practice Review"/&gt;
 *     &lt;enumeration value="Regulatory Review"/&gt;
 *     &lt;enumeration value="Validation Review"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "QueryReason")
@XmlEnum
public enum QueryReason {

    @XmlEnumValue("Patient Request")
    PATIENT_REQUEST("Patient Request"),
    @XmlEnumValue("Patient Care")
    PATIENT_CARE("Patient Care"),
    @XmlEnumValue("Research")
    RESEARCH("Research"),
    @XmlEnumValue("Legal")
    LEGAL("Legal"),
    @XmlEnumValue("Practice Review")
    PRACTICE_REVIEW("Practice Review"),
    @XmlEnumValue("Regulatory Review")
    REGULATORY_REVIEW("Regulatory Review"),
    @XmlEnumValue("Validation Review")
    VALIDATION_REVIEW("Validation Review");
    private final String value;

    QueryReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QueryReason fromValue(String v) {
        for (QueryReason c: QueryReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
