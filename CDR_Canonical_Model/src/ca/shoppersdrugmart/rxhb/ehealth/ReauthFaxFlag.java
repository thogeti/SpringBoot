
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReauthFaxFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReauthFaxFlag"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="false"/&gt;
 *     &lt;enumeration value="Not Specified"/&gt;
 *     &lt;enumeration value="true"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ReauthFaxFlag")
@XmlEnum
public enum ReauthFaxFlag {

    @XmlEnumValue("false")
    FALSE("false"),
    @XmlEnumValue("Not Specified")
    NOT_SPECIFIED("Not Specified"),
    @XmlEnumValue("true")
    TRUE("true");
    private final String value;

    ReauthFaxFlag(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReauthFaxFlag fromValue(String v) {
        for (ReauthFaxFlag c: ReauthFaxFlag.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
