
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdministrationUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AdministrationUnit"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Drops"/&gt;
 *     &lt;enumeration value="Nasal Drops"/&gt;
 *     &lt;enumeration value="Ophthalmic Drops"/&gt;
 *     &lt;enumeration value="Oral Drops"/&gt;
 *     &lt;enumeration value="Otic Drops"/&gt;
 *     &lt;enumeration value="Applicatorful"/&gt;
 *     &lt;enumeration value="Puff"/&gt;
 *     &lt;enumeration value="Scoops"/&gt;
 *     &lt;enumeration value="Sprays"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AdministrationUnit")
@XmlEnum
public enum AdministrationUnit {

    @XmlEnumValue("Drops")
    DROPS("Drops"),
    @XmlEnumValue("Nasal Drops")
    NASAL_DROPS("Nasal Drops"),
    @XmlEnumValue("Ophthalmic Drops")
    OPHTHALMIC_DROPS("Ophthalmic Drops"),
    @XmlEnumValue("Oral Drops")
    ORAL_DROPS("Oral Drops"),
    @XmlEnumValue("Otic Drops")
    OTIC_DROPS("Otic Drops"),
    @XmlEnumValue("Applicatorful")
    APPLICATORFUL("Applicatorful"),
    @XmlEnumValue("Puff")
    PUFF("Puff"),
    @XmlEnumValue("Scoops")
    SCOOPS("Scoops"),
    @XmlEnumValue("Sprays")
    SPRAYS("Sprays");
    private final String value;

    AdministrationUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AdministrationUnit fromValue(String v) {
        for (AdministrationUnit c: AdministrationUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
