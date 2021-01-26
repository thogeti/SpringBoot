
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NonDrugAllergen.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NonDrugAllergen"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Dust"/&gt;
 *     &lt;enumeration value="Dust Mites"/&gt;
 *     &lt;enumeration value="Egg"/&gt;
 *     &lt;enumeration value="Fish"/&gt;
 *     &lt;enumeration value="Isocyanates"/&gt;
 *     &lt;enumeration value="Lactose"/&gt;
 *     &lt;enumeration value="Latex"/&gt;
 *     &lt;enumeration value="Oils"/&gt;
 *     &lt;enumeration value="Peanut"/&gt;
 *     &lt;enumeration value="Shellfish"/&gt;
 *     &lt;enumeration value="Solvents"/&gt;
 *     &lt;enumeration value="Soy"/&gt;
 *     &lt;enumeration value="Strawberries"/&gt;
 *     &lt;enumeration value="Sulfites"/&gt;
 *     &lt;enumeration value="Tomotoes"/&gt;
 *     &lt;enumeration value="Venoms"/&gt;
 *     &lt;enumeration value="Wheat/Gluten"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NonDrugAllergen")
@XmlEnum
public enum NonDrugAllergen {

    @XmlEnumValue("Dust")
    DUST("Dust"),
    @XmlEnumValue("Dust Mites")
    DUST_MITES("Dust Mites"),
    @XmlEnumValue("Egg")
    EGG("Egg"),
    @XmlEnumValue("Fish")
    FISH("Fish"),
    @XmlEnumValue("Isocyanates")
    ISOCYANATES("Isocyanates"),
    @XmlEnumValue("Lactose")
    LACTOSE("Lactose"),
    @XmlEnumValue("Latex")
    LATEX("Latex"),
    @XmlEnumValue("Oils")
    OILS("Oils"),
    @XmlEnumValue("Peanut")
    PEANUT("Peanut"),
    @XmlEnumValue("Shellfish")
    SHELLFISH("Shellfish"),
    @XmlEnumValue("Solvents")
    SOLVENTS("Solvents"),
    @XmlEnumValue("Soy")
    SOY("Soy"),
    @XmlEnumValue("Strawberries")
    STRAWBERRIES("Strawberries"),
    @XmlEnumValue("Sulfites")
    SULFITES("Sulfites"),
    @XmlEnumValue("Tomotoes")
    TOMOTOES("Tomotoes"),
    @XmlEnumValue("Venoms")
    VENOMS("Venoms"),
    @XmlEnumValue("Wheat/Gluten")
    WHEAT_GLUTEN("Wheat/Gluten");
    private final String value;

    NonDrugAllergen(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NonDrugAllergen fromValue(String v) {
        for (NonDrugAllergen c: NonDrugAllergen.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
