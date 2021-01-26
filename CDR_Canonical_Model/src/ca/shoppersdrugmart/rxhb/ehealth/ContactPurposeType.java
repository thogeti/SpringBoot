
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContactPurposeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContactPurposeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BUSINESS"/&gt;
 *     &lt;enumeration value="PRIMARY"/&gt;
 *     &lt;enumeration value="GROUP HOME"/&gt;
 *     &lt;enumeration value="RETIREMENT HOME"/&gt;
 *     &lt;enumeration value="NURSING HOME"/&gt;
 *     &lt;enumeration value="OTHER"/&gt;
 *     &lt;enumeration value="HOME"/&gt;
 *     &lt;enumeration value="VISIT ADDRESS"/&gt;
 *     &lt;enumeration value="POSTAL ADDRESS"/&gt;
 *     &lt;enumeration value="TEMPORARY ADDRESS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ContactPurposeType")
@XmlEnum
public enum ContactPurposeType {

    BUSINESS("BUSINESS"),
    PRIMARY("PRIMARY"),
    @XmlEnumValue("GROUP HOME")
    GROUP_HOME("GROUP HOME"),
    @XmlEnumValue("RETIREMENT HOME")
    RETIREMENT_HOME("RETIREMENT HOME"),
    @XmlEnumValue("NURSING HOME")
    NURSING_HOME("NURSING HOME"),
    OTHER("OTHER"),
    HOME("HOME"),
    @XmlEnumValue("VISIT ADDRESS")
    VISIT_ADDRESS("VISIT ADDRESS"),
    @XmlEnumValue("POSTAL ADDRESS")
    POSTAL_ADDRESS("POSTAL ADDRESS"),
    @XmlEnumValue("TEMPORARY ADDRESS")
    TEMPORARY_ADDRESS("TEMPORARY ADDRESS");
    private final String value;

    ContactPurposeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContactPurposeType fromValue(String v) {
        for (ContactPurposeType c: ContactPurposeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
