
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductSelectorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProductSelectorType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Prescriber"/&gt;
 *     &lt;enumeration value="Patient"/&gt;
 *     &lt;enumeration value="Pharmacist"/&gt;
 *     &lt;enumeration value="Exist"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProductSelectorType")
@XmlEnum
public enum ProductSelectorType {

    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Pharmacist")
    PHARMACIST("Pharmacist"),
    @XmlEnumValue("Exist")
    EXIST("Exist");
    private final String value;

    ProductSelectorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProductSelectorType fromValue(String v) {
        for (ProductSelectorType c: ProductSelectorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
