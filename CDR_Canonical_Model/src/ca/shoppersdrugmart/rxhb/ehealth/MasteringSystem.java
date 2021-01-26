
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MasteringSystem.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MasteringSystem"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="healthwatch"/&gt;
 *     &lt;enumeration value="hdm"/&gt;
 *     &lt;enumeration value="crx"/&gt;
 *     &lt;enumeration value="cpms"/&gt;
 *     &lt;enumeration value="storeprofile"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MasteringSystem")
@XmlEnum
public enum MasteringSystem {

    @XmlEnumValue("healthwatch")
    HEALTHWATCH("healthwatch"),
    @XmlEnumValue("hdm")
    HDM("hdm"),
    @XmlEnumValue("crx")
    CRX("crx"),
    @XmlEnumValue("cpms")
    CPMS("cpms"),
    @XmlEnumValue("storeprofile")
    STOREPROFILE("storeprofile");
    private final String value;

    MasteringSystem(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MasteringSystem fromValue(String v) {
        for (MasteringSystem c: MasteringSystem.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
