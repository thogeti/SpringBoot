
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientVerificationEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientVerificationEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Delta Store"/&gt;
 *     &lt;enumeration value="DRx"/&gt;
 *     &lt;enumeration value="Mapple"/&gt;
 *     &lt;enumeration value="Kroll Store"/&gt;
 *     &lt;enumeration value="HWNG Store"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PatientVerificationEnum")
@XmlEnum
public enum PatientVerificationEnum {

    @XmlEnumValue("Delta Store")
    DELTA_STORE("Delta Store"),
    @XmlEnumValue("DRx")
    D_RX("DRx"),
    @XmlEnumValue("Mapple")
    MAPPLE("Mapple"),
    @XmlEnumValue("Kroll Store")
    KROLL_STORE("Kroll Store"),
    @XmlEnumValue("HWNG Store")
    HWNG_STORE("HWNG Store");
    private final String value;

    PatientVerificationEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientVerificationEnum fromValue(String v) {
        for (PatientVerificationEnum c: PatientVerificationEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
