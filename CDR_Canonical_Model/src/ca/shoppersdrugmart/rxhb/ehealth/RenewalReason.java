
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RenewalReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Appointment missed/cancelled"/&gt;
 *     &lt;enumeration value="Insufficient refills given"/&gt;
 *     &lt;enumeration value="Patient unaware Rx was depleted/expired"/&gt;
 *     &lt;enumeration value="Upcoming appointment"/&gt;
 *     &lt;enumeration value="Seamless Care"/&gt;
 *     &lt;enumeration value="Maintence"/&gt;
 *     &lt;enumeration value="Extending Refills"/&gt;
 *     &lt;enumeration value="Interim Supply"/&gt;
 *     &lt;enumeration value="Emergency"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RenewalReason")
@XmlEnum
public enum RenewalReason {

    @XmlEnumValue("Appointment missed/cancelled")
    APPOINTMENT_MISSED_CANCELLED("Appointment missed/cancelled"),
    @XmlEnumValue("Insufficient refills given")
    INSUFFICIENT_REFILLS_GIVEN("Insufficient refills given"),
    @XmlEnumValue("Patient unaware Rx was depleted/expired")
    PATIENT_UNAWARE_RX_WAS_DEPLETED_EXPIRED("Patient unaware Rx was depleted/expired"),
    @XmlEnumValue("Upcoming appointment")
    UPCOMING_APPOINTMENT("Upcoming appointment"),
    @XmlEnumValue("Seamless Care")
    SEAMLESS_CARE("Seamless Care"),
    @XmlEnumValue("Maintence")
    MAINTENCE("Maintence"),
    @XmlEnumValue("Extending Refills")
    EXTENDING_REFILLS("Extending Refills"),
    @XmlEnumValue("Interim Supply")
    INTERIM_SUPPLY("Interim Supply"),
    @XmlEnumValue("Emergency")
    EMERGENCY("Emergency");
    private final String value;

    RenewalReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RenewalReason fromValue(String v) {
        for (RenewalReason c: RenewalReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
