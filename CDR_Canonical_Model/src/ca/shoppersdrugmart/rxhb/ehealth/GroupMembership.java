
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupMembership.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupMembership"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="retail"/&gt;
 *     &lt;enumeration value="ltc"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GroupMembership")
@XmlEnum
public enum GroupMembership {

    @XmlEnumValue("retail")
    RETAIL("retail"),
    @XmlEnumValue("ltc")
    LTC("ltc");
    private final String value;

    GroupMembership(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupMembership fromValue(String v) {
        for (GroupMembership c: GroupMembership.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
