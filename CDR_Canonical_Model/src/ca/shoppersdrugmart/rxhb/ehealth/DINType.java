
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DINType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DINType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="opinions"/&gt;
 *     &lt;enumeration value="din"/&gt;
 *     &lt;enumeration value="npn"/&gt;
 *     &lt;enumeration value="pseudoDin"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DINType")
@XmlEnum
public enum DINType {

    @XmlEnumValue("opinions")
    OPINIONS("opinions"),
    @XmlEnumValue("din")
    DIN("din"),
    @XmlEnumValue("npn")
    NPN("npn"),
    @XmlEnumValue("pseudoDin")
    PSEUDO_DIN("pseudoDin");
    private final String value;

    DINType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DINType fromValue(String v) {
        for (DINType c: DINType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
