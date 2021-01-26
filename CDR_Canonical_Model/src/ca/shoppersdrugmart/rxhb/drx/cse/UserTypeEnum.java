
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Employee"/&gt;
 *     &lt;enumeration value="Guest"/&gt;
 *     &lt;enumeration value="PreRegCustomer"/&gt;
 *     &lt;enumeration value="UnverifiedCaregiver"/&gt;
 *     &lt;enumeration value="VerifiedCaregiver"/&gt;
 *     &lt;enumeration value="VerifiedCustomer"/&gt;
 *     &lt;enumeration value="UnverifiedCustomer"/&gt;
 *     &lt;enumeration value="Customer"/&gt;
 *     &lt;enumeration value="RegisteredCustomer"/&gt;
 *     &lt;enumeration value="Caregiver"/&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UserTypeEnum")
@XmlEnum
public enum UserTypeEnum {

    @XmlEnumValue("Employee")
    EMPLOYEE("Employee"),
    @XmlEnumValue("Guest")
    GUEST("Guest"),
    @XmlEnumValue("PreRegCustomer")
    PRE_REG_CUSTOMER("PreRegCustomer"),
    @XmlEnumValue("UnverifiedCaregiver")
    UNVERIFIED_CAREGIVER("UnverifiedCaregiver"),
    @XmlEnumValue("VerifiedCaregiver")
    VERIFIED_CAREGIVER("VerifiedCaregiver"),
    @XmlEnumValue("VerifiedCustomer")
    VERIFIED_CUSTOMER("VerifiedCustomer"),
    @XmlEnumValue("UnverifiedCustomer")
    UNVERIFIED_CUSTOMER("UnverifiedCustomer"),
    @XmlEnumValue("Customer")
    CUSTOMER("Customer"),
    @XmlEnumValue("RegisteredCustomer")
    REGISTERED_CUSTOMER("RegisteredCustomer"),
    @XmlEnumValue("Caregiver")
    CAREGIVER("Caregiver"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    UserTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserTypeEnum fromValue(String v) {
        for (UserTypeEnum c: UserTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
