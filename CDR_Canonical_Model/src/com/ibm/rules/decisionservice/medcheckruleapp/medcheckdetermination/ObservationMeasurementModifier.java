
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObservationMeasurementModifier.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ObservationMeasurementModifier">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Stated"/>
 *     &lt;enumeration value="Measured"/>
 *     &lt;enumeration value="Automatic"/>
 *     &lt;enumeration value="Manual"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ObservationMeasurementModifier")
@XmlEnum
public enum ObservationMeasurementModifier {

    @XmlEnumValue("Stated")
    STATED("Stated"),
    @XmlEnumValue("Measured")
    MEASURED("Measured"),
    @XmlEnumValue("Automatic")
    AUTOMATIC("Automatic"),
    @XmlEnumValue("Manual")
    MANUAL("Manual");
    private final String value;

    ObservationMeasurementModifier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObservationMeasurementModifier fromValue(String v) {
        for (ObservationMeasurementModifier c: ObservationMeasurementModifier.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
