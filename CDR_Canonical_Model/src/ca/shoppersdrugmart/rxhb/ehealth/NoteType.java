
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NoteType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Prescription"/&gt;
 *     &lt;enumeration value="Dispense"/&gt;
 *     &lt;enumeration value="Allergy"/&gt;
 *     &lt;enumeration value="Drug"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NoteType")
@XmlEnum
public enum NoteType {

    @XmlEnumValue("Prescription")
    PRESCRIPTION("Prescription"),
    @XmlEnumValue("Dispense")
    DISPENSE("Dispense"),
    @XmlEnumValue("Allergy")
    ALLERGY("Allergy"),
    @XmlEnumValue("Drug")
    DRUG("Drug");
    private final String value;

    NoteType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NoteType fromValue(String v) {
        for (NoteType c: NoteType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
