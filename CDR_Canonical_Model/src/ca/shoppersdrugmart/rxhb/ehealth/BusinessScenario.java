
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessScenario.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BusinessScenario"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="log"/&gt;
 *     &lt;enumeration value="create"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "BusinessScenario")
@XmlEnum
public enum BusinessScenario {

    @XmlEnumValue("log")
    LOG("log"),
    @XmlEnumValue("create")
    CREATE("create");
    private final String value;

    BusinessScenario(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BusinessScenario fromValue(String v) {
        for (BusinessScenario c: BusinessScenario.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
