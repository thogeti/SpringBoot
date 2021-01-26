
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DispenseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DispenseType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Cancel Fill From Log"/&gt;
 *     &lt;enumeration value="First Fill"/&gt;
 *     &lt;enumeration value="First Fill Part Fill"/&gt;
 *     &lt;enumeration value="Refill"/&gt;
 *     &lt;enumeration value="Refill Part Fill"/&gt;
 *     &lt;enumeration value="Re-authorized Refill"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DispenseType")
@XmlEnum
public enum DispenseType {

    @XmlEnumValue("Cancel Fill From Log")
    CANCEL_FILL_FROM_LOG("Cancel Fill From Log"),
    @XmlEnumValue("First Fill")
    FIRST_FILL("First Fill"),
    @XmlEnumValue("First Fill Part Fill")
    FIRST_FILL_PART_FILL("First Fill Part Fill"),
    @XmlEnumValue("Refill")
    REFILL("Refill"),
    @XmlEnumValue("Refill Part Fill")
    REFILL_PART_FILL("Refill Part Fill"),
    @XmlEnumValue("Re-authorized Refill")
    RE_AUTHORIZED_REFILL("Re-authorized Refill");
    private final String value;

    DispenseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DispenseType fromValue(String v) {
        for (DispenseType c: DispenseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
