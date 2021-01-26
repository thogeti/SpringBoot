
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MetricsUnitOfMeasure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MetricsUnitOfMeasure">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pounds"/>
 *     &lt;enumeration value="KG"/>
 *     &lt;enumeration value="Meters"/>
 *     &lt;enumeration value="CM"/>
 *     &lt;enumeration value="Inches"/>
 *     &lt;enumeration value="Feet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MetricsUnitOfMeasure")
@XmlEnum
public enum MetricsUnitOfMeasure {

    @XmlEnumValue("Pounds")
    POUNDS("Pounds"),
    KG("KG"),
    @XmlEnumValue("Meters")
    METERS("Meters"),
    CM("CM"),
    @XmlEnumValue("Inches")
    INCHES("Inches"),
    @XmlEnumValue("Feet")
    FEET("Feet");
    private final String value;

    MetricsUnitOfMeasure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MetricsUnitOfMeasure fromValue(String v) {
        for (MetricsUnitOfMeasure c: MetricsUnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
