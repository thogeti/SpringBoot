
package ca.shoppersdrugmart.message.errorxml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorSeverity.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorSeverity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Fatal"/>
 *     &lt;enumeration value="Critical"/>
 *     &lt;enumeration value="Minor"/>
 *     &lt;enumeration value="Informational"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorSeverity")
@XmlEnum
public enum ErrorSeverity {


    /**
     * Most Severe - Fatal
     * 
     */
    @XmlEnumValue("Fatal")
    FATAL("Fatal"),

    /**
     * Quite Severe - Critical
     * 
     */
    @XmlEnumValue("Critical")
    CRITICAL("Critical"),

    /**
     * Moderately Severe - Minor
     * 
     */
    @XmlEnumValue("Minor")
    MINOR("Minor"),

    /**
     * Least Severe - Informational
     * 
     */
    @XmlEnumValue("Informational")
    INFORMATIONAL("Informational");
    private final String value;

    ErrorSeverity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorSeverity fromValue(String v) {
        for (ErrorSeverity c: ErrorSeverity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
