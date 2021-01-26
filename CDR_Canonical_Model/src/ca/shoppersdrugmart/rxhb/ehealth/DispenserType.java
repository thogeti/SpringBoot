
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DispenserType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DispenserType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pharmacist"/&gt;
 *     &lt;enumeration value="Pharmacy Assistant"/&gt;
 *     &lt;enumeration value="Central Support"/&gt;
 *     &lt;enumeration value="Cashier"/&gt;
 *     &lt;enumeration value="Regulated Technician"/&gt;
 *     &lt;enumeration value="Pharmacy Student"/&gt;
 *     &lt;enumeration value="Pharmacy Intern"/&gt;
 *     &lt;enumeration value="Store Administrator"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DispenserType")
@XmlEnum
public enum DispenserType {

    @XmlEnumValue("Pharmacist")
    PHARMACIST("Pharmacist"),
    @XmlEnumValue("Pharmacy Assistant")
    PHARMACY_ASSISTANT("Pharmacy Assistant"),
    @XmlEnumValue("Central Support")
    CENTRAL_SUPPORT("Central Support"),
    @XmlEnumValue("Cashier")
    CASHIER("Cashier"),
    @XmlEnumValue("Regulated Technician")
    REGULATED_TECHNICIAN("Regulated Technician"),
    @XmlEnumValue("Pharmacy Student")
    PHARMACY_STUDENT("Pharmacy Student"),
    @XmlEnumValue("Pharmacy Intern")
    PHARMACY_INTERN("Pharmacy Intern"),
    @XmlEnumValue("Store Administrator")
    STORE_ADMINISTRATOR("Store Administrator");
    private final String value;

    DispenserType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DispenserType fromValue(String v) {
        for (DispenserType c: DispenserType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
