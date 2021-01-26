
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PopulationStrategyEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PopulationStrategyEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ByRefWithTree"/&gt;
 *     &lt;enumeration value="ByRefWithEntity"/&gt;
 *     &lt;enumeration value="ByValWithTree"/&gt;
 *     &lt;enumeration value="ByValWithEntity"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PopulationStrategyEnum")
@XmlEnum
public enum PopulationStrategyEnum {

    @XmlEnumValue("ByRefWithTree")
    BY_REF_WITH_TREE("ByRefWithTree"),
    @XmlEnumValue("ByRefWithEntity")
    BY_REF_WITH_ENTITY("ByRefWithEntity"),
    @XmlEnumValue("ByValWithTree")
    BY_VAL_WITH_TREE("ByValWithTree"),
    @XmlEnumValue("ByValWithEntity")
    BY_VAL_WITH_ENTITY("ByValWithEntity");
    private final String value;

    PopulationStrategyEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PopulationStrategyEnum fromValue(String v) {
        for (PopulationStrategyEnum c: PopulationStrategyEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
