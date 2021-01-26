
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EngagementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EngagementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Guest"/&gt;
 *     &lt;enumeration value="Account"/&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="Accuro"/&gt;
 *     &lt;enumeration value="HWPatient"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EngagementType")
@XmlEnum
public enum EngagementType {


    /**
     * The user was not logged into an account
     * 
     */
    @XmlEnumValue("Guest")
    GUEST("Guest"),

    /**
     * The user was logged into their account
     * 
     */
    @XmlEnumValue("Account")
    ACCOUNT("Account"),

    /**
     * It is not known if the user was logged into their account or this value is not applicable.
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * The user was logged into their account
     * 
     */
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),

    /**
     * Health Watch Patient who availed few features of Digital Service
     * 
     */
    @XmlEnumValue("HWPatient")
    HW_PATIENT("HWPatient");
    private final String value;

    EngagementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EngagementType fromValue(String v) {
        for (EngagementType c: EngagementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
