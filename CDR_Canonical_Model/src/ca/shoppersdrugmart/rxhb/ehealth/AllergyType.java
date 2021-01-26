
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllergyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AllergyType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Drug"/&gt;
 *     &lt;enumeration value="Allergy"/&gt;
 *     &lt;enumeration value="Intolerance"/&gt;
 *     &lt;enumeration value="Food"/&gt;
 *     &lt;enumeration value="Environmental"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AllergyType")
@XmlEnum
public enum AllergyType {

    @XmlEnumValue("Drug")
    DRUG("Drug"),
    @XmlEnumValue("Allergy")
    ALLERGY("Allergy"),
    @XmlEnumValue("Intolerance")
    INTOLERANCE("Intolerance"),
    @XmlEnumValue("Food")
    FOOD("Food"),
    @XmlEnumValue("Environmental")
    ENVIRONMENTAL("Environmental");
    private final String value;

    AllergyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AllergyType fromValue(String v) {
        for (AllergyType c: AllergyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
