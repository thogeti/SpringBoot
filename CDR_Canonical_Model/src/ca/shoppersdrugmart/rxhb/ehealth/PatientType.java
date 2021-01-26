
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="regular"/&gt;
 *     &lt;enumeration value="animal"/&gt;
 *     &lt;enumeration value="pharmacy"/&gt;
 *     &lt;enumeration value="prescriber/OfficeUse"/&gt;
 *     &lt;enumeration value="healthCareFacility"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PatientType")
@XmlEnum
public enum PatientType {

    @XmlEnumValue("regular")
    REGULAR("regular"),
    @XmlEnumValue("animal")
    ANIMAL("animal"),
    @XmlEnumValue("pharmacy")
    PHARMACY("pharmacy"),
    @XmlEnumValue("prescriber/OfficeUse")
    PRESCRIBER_OFFICE_USE("prescriber/OfficeUse"),
    @XmlEnumValue("healthCareFacility")
    HEALTH_CARE_FACILITY("healthCareFacility");
    private final String value;

    PatientType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientType fromValue(String v) {
        for (PatientType c: PatientType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
