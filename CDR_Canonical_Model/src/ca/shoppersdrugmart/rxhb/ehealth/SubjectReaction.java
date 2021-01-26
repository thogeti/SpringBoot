
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubjectReaction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubjectReaction"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="No Reaction"/&gt;
 *     &lt;enumeration value="Headache"/&gt;
 *     &lt;enumeration value="Dizziness"/&gt;
 *     &lt;enumeration value="Skin Redness"/&gt;
 *     &lt;enumeration value="Itchy Skin"/&gt;
 *     &lt;enumeration value="Rash"/&gt;
 *     &lt;enumeration value="Hives"/&gt;
 *     &lt;enumeration value="Swelling"/&gt;
 *     &lt;enumeration value="Abdominal Discomfort"/&gt;
 *     &lt;enumeration value="Nausea"/&gt;
 *     &lt;enumeration value="Vomiting"/&gt;
 *     &lt;enumeration value="Difficulty Breathing"/&gt;
 *     &lt;enumeration value="Impaired Vision"/&gt;
 *     &lt;enumeration value="Heart Palpitations"/&gt;
 *     &lt;enumeration value="Seizure"/&gt;
 *     &lt;enumeration value="Paralysis"/&gt;
 *     &lt;enumeration value="Unconsciousness"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *     &lt;enumeration value="Not Applicable"/&gt;
 *     &lt;enumeration value="Fatal"/&gt;
 *     &lt;enumeration value="Insomnia"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SubjectReaction")
@XmlEnum
public enum SubjectReaction {

    @XmlEnumValue("No Reaction")
    NO_REACTION("No Reaction"),
    @XmlEnumValue("Headache")
    HEADACHE("Headache"),
    @XmlEnumValue("Dizziness")
    DIZZINESS("Dizziness"),
    @XmlEnumValue("Skin Redness")
    SKIN_REDNESS("Skin Redness"),
    @XmlEnumValue("Itchy Skin")
    ITCHY_SKIN("Itchy Skin"),
    @XmlEnumValue("Rash")
    RASH("Rash"),
    @XmlEnumValue("Hives")
    HIVES("Hives"),
    @XmlEnumValue("Swelling")
    SWELLING("Swelling"),
    @XmlEnumValue("Abdominal Discomfort")
    ABDOMINAL_DISCOMFORT("Abdominal Discomfort"),
    @XmlEnumValue("Nausea")
    NAUSEA("Nausea"),
    @XmlEnumValue("Vomiting")
    VOMITING("Vomiting"),
    @XmlEnumValue("Difficulty Breathing")
    DIFFICULTY_BREATHING("Difficulty Breathing"),
    @XmlEnumValue("Impaired Vision")
    IMPAIRED_VISION("Impaired Vision"),
    @XmlEnumValue("Heart Palpitations")
    HEART_PALPITATIONS("Heart Palpitations"),
    @XmlEnumValue("Seizure")
    SEIZURE("Seizure"),
    @XmlEnumValue("Paralysis")
    PARALYSIS("Paralysis"),
    @XmlEnumValue("Unconsciousness")
    UNCONSCIOUSNESS("Unconsciousness"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Not Applicable")
    NOT_APPLICABLE("Not Applicable"),
    @XmlEnumValue("Fatal")
    FATAL("Fatal"),
    @XmlEnumValue("Insomnia")
    INSOMNIA("Insomnia");
    private final String value;

    SubjectReaction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubjectReaction fromValue(String v) {
        for (SubjectReaction c: SubjectReaction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
