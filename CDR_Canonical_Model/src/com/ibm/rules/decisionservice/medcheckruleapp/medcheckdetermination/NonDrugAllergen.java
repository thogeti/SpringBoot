
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NonDrugAllergen.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NonDrugAllergen">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Dust"/>
 *     &lt;enumeration value="Dust Mites"/>
 *     &lt;enumeration value="Egg"/>
 *     &lt;enumeration value="Fish"/>
 *     &lt;enumeration value="Isocyanates"/>
 *     &lt;enumeration value="Lactose"/>
 *     &lt;enumeration value="Latex"/>
 *     &lt;enumeration value="Oils"/>
 *     &lt;enumeration value="Peanut"/>
 *     &lt;enumeration value="Shellfish"/>
 *     &lt;enumeration value="Solvents"/>
 *     &lt;enumeration value="Soy"/>
 *     &lt;enumeration value="Strawberries"/>
 *     &lt;enumeration value="Sulfites"/>
 *     &lt;enumeration value="Tomotoes"/>
 *     &lt;enumeration value="Venoms"/>
 *     &lt;enumeration value="Wheat/Gluten"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
