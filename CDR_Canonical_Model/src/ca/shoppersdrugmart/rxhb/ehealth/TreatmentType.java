
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TreatmentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TreatmentType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Continuous/Chronic"/&gt;
 *     &lt;enumeration value="Short Term/ Acute"/&gt;
 *     &lt;enumeration value="One Time"/&gt;
 *     &lt;enumeration value="As Needed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TreatmentType")
@XmlEnum
public enum TreatmentType {

    @XmlEnumValue("Continuous/Chronic")
    CONTINUOUS_CHRONIC("Continuous/Chronic"),
    @XmlEnumValue("Short Term/ Acute")
    SHORT_TERM_ACUTE("Short Term/ Acute"),
    @XmlEnumValue("One Time")
    ONE_TIME("One Time"),
    @XmlEnumValue("As Needed")
    AS_NEEDED("As Needed");
    private final String value;

    TreatmentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TreatmentType fromValue(String v) {
        for (TreatmentType c: TreatmentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
