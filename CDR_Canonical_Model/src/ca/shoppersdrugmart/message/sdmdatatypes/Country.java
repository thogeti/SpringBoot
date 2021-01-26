
package ca.shoppersdrugmart.message.sdmdatatypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Country.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Country">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Canada"/>
 *     &lt;enumeration value="United States"/>
 *     &lt;enumeration value="Other Countries"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Country", namespace = "http://www.shoppersdrugmart.ca/message/SDMDataTypes")
@XmlEnum
public enum Country {

    @XmlEnumValue("Canada")
    CANADA("Canada"),
    @XmlEnumValue("United States")
    UNITED_STATES("United States"),
    @XmlEnumValue("Other Countries")
    OTHER_COUNTRIES("Other Countries");
    private final String value;

    Country(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Country fromValue(String v) {
        for (Country c: Country.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
