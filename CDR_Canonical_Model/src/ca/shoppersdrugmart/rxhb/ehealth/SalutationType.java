
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for salutationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="salutationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Doctor"/&gt;
 *     &lt;enumeration value="Fr"/&gt;
 *     &lt;enumeration value="Miss"/&gt;
 *     &lt;enumeration value="Mister"/&gt;
 *     &lt;enumeration value="Mrs."/&gt;
 *     &lt;enumeration value="Miss"/&gt;
 *     &lt;enumeration value="MW."/&gt;
 *     &lt;enumeration value="NP"/&gt;
 *     &lt;enumeration value="NP"/&gt;
 *     &lt;enumeration value="Registered Pharmacist"/&gt;
 *     &lt;enumeration value="Reverant"/&gt;
 *     &lt;enumeration value="Senior"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "salutationType")
@XmlEnum
public enum SalutationType {

    @XmlEnumValue("Doctor")
    DOCTOR("Doctor"),
    @XmlEnumValue("Fr")
    FR("Fr"),
    @XmlEnumValue("Miss")
    MISS("Miss"),
    @XmlEnumValue("Mister")
    MISTER("Mister"),
    @XmlEnumValue("Mrs.")
    MRS("Mrs."),
    @XmlEnumValue("MW.")
    MW("MW."),
    NP("NP"),
    @XmlEnumValue("Registered Pharmacist")
    REGISTERED_PHARMACIST("Registered Pharmacist"),
    @XmlEnumValue("Reverant")
    REVERANT("Reverant"),
    @XmlEnumValue("Senior")
    SENIOR("Senior");
    private final String value;

    SalutationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SalutationType fromValue(String v) {
        for (SalutationType c: SalutationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
