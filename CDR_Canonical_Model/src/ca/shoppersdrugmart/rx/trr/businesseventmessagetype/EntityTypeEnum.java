
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EntityTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Consent"/>
 *     &lt;enumeration value="Patient"/>
 *     &lt;enumeration value="Prescriber"/>
 *     &lt;enumeration value="Store"/>
 *     &lt;enumeration value="Address"/>
 *     &lt;enumeration value="Rx"/>
 *     &lt;enumeration value="Tx"/>
 *     &lt;enumeration value="Drug"/>
 *     &lt;enumeration value="Compound"/>
 *     &lt;enumeration value="Note"/>
 *     &lt;enumeration value="Allergy"/>
 *     &lt;enumeration value="Patient_Coverage"/>
 *     &lt;enumeration value="Drug_Upc"/>
 *     &lt;enumeration value="User"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EntityTypeEnum")
@XmlEnum
public enum EntityTypeEnum {

    @XmlEnumValue("Consent")
    CONSENT("Consent"),
    @XmlEnumValue("Patient")
    PATIENT("Patient"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("Store")
    STORE("Store"),
    @XmlEnumValue("Address")
    ADDRESS("Address"),
    @XmlEnumValue("Rx")
    RX("Rx"),
    @XmlEnumValue("Tx")
    TX("Tx"),
    @XmlEnumValue("Drug")
    DRUG("Drug"),
    @XmlEnumValue("Compound")
    COMPOUND("Compound"),
    @XmlEnumValue("Note")
    NOTE("Note"),
    @XmlEnumValue("Allergy")
    ALLERGY("Allergy"),
    @XmlEnumValue("Patient_Coverage")
    PATIENT_COVERAGE("Patient_Coverage"),
    @XmlEnumValue("Drug_Upc")
    DRUG_UPC("Drug_Upc"),
    @XmlEnumValue("User")
    USER("User");
    private final String value;

    EntityTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EntityTypeEnum fromValue(String v) {
        for (EntityTypeEnum c: EntityTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
