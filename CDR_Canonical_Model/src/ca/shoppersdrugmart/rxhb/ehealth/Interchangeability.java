
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Interchangeability.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Interchangeability"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Formulary"/&gt;
 *     &lt;enumeration value="Generic"/&gt;
 *     &lt;enumeration value="Therapeutic"/&gt;
 *     &lt;enumeration value="None"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Interchangeability")
@XmlEnum
public enum Interchangeability {

    @XmlEnumValue("Formulary")
    FORMULARY("Formulary"),
    @XmlEnumValue("Generic")
    GENERIC("Generic"),
    @XmlEnumValue("Therapeutic")
    THERAPEUTIC("Therapeutic"),
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    Interchangeability(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Interchangeability fromValue(String v) {
        for (Interchangeability c: Interchangeability.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
