
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubjectReaction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubjectReaction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="No Reaction"/>
 *     &lt;enumeration value="Headache"/>
 *     &lt;enumeration value="Dizziness"/>
 *     &lt;enumeration value="Skin Redness"/>
 *     &lt;enumeration value="Itchy Skin"/>
 *     &lt;enumeration value="Rash"/>
 *     &lt;enumeration value="Hives"/>
 *     &lt;enumeration value="Swelling"/>
 *     &lt;enumeration value="Abdominal Discomfort"/>
 *     &lt;enumeration value="Nausea"/>
 *     &lt;enumeration value="Vomiting"/>
 *     &lt;enumeration value="Difficulty Breathing"/>
 *     &lt;enumeration value="Impaired Vision"/>
 *     &lt;enumeration value="Heart Palpitations"/>
 *     &lt;enumeration value="Seizure"/>
 *     &lt;enumeration value="Paralysis"/>
 *     &lt;enumeration value="Unconsciousness"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Not Applicable"/>
 *     &lt;enumeration value="Fatal"/>
 *     &lt;enumeration value="Insomnia"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
