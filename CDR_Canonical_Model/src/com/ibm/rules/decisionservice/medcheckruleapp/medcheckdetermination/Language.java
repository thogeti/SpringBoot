
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Language.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Language"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="English"/&gt;
 *     &lt;enumeration value="French"/&gt;
 *     &lt;enumeration value="Arabic"/&gt;
 *     &lt;enumeration value="Armenian"/&gt;
 *     &lt;enumeration value="Chinese"/&gt;
 *     &lt;enumeration value="Danish"/&gt;
 *     &lt;enumeration value="Dutch"/&gt;
 *     &lt;enumeration value="German"/&gt;
 *     &lt;enumeration value="Greek"/&gt;
 *     &lt;enumeration value="Gujrati"/&gt;
 *     &lt;enumeration value="Hebrew"/&gt;
 *     &lt;enumeration value="Hindi"/&gt;
 *     &lt;enumeration value="Hungarian"/&gt;
 *     &lt;enumeration value="Indonesian"/&gt;
 *     &lt;enumeration value="Italian"/&gt;
 *     &lt;enumeration value="Japanese"/&gt;
 *     &lt;enumeration value="Korean"/&gt;
 *     &lt;enumeration value="Polish"/&gt;
 *     &lt;enumeration value="Portugese"/&gt;
 *     &lt;enumeration value="Macedonian"/&gt;
 *     &lt;enumeration value="Punjabi"/&gt;
 *     &lt;enumeration value="Russian"/&gt;
 *     &lt;enumeration value="Spanish"/&gt;
 *     &lt;enumeration value="Slovenian"/&gt;
 *     &lt;enumeration value="Swahili"/&gt;
 *     &lt;enumeration value="Swedish"/&gt;
 *     &lt;enumeration value="Thai"/&gt;
 *     &lt;enumeration value="Turkish"/&gt;
 *     &lt;enumeration value="Vietnamese"/&gt;
 *     &lt;enumeration value="Ukrainian"/&gt;
 *     &lt;enumeration value="Urdu"/&gt;
 *     &lt;enumeration value="Others"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Language")
@XmlEnum
public enum Language {

    @XmlEnumValue("English")
    ENGLISH("English"),
    @XmlEnumValue("French")
    FRENCH("French"),
    @XmlEnumValue("Arabic")
    ARABIC("Arabic"),
    @XmlEnumValue("Armenian")
    ARMENIAN("Armenian"),
    @XmlEnumValue("Chinese")
    CHINESE("Chinese"),
    @XmlEnumValue("Danish")
    DANISH("Danish"),
    @XmlEnumValue("Dutch")
    DUTCH("Dutch"),
    @XmlEnumValue("German")
    GERMAN("German"),
    @XmlEnumValue("Greek")
    GREEK("Greek"),
    @XmlEnumValue("Gujrati")
    GUJRATI("Gujrati"),
    @XmlEnumValue("Hebrew")
    HEBREW("Hebrew"),
    @XmlEnumValue("Hindi")
    HINDI("Hindi"),
    @XmlEnumValue("Hungarian")
    HUNGARIAN("Hungarian"),
    @XmlEnumValue("Indonesian")
    INDONESIAN("Indonesian"),
    @XmlEnumValue("Italian")
    ITALIAN("Italian"),
    @XmlEnumValue("Japanese")
    JAPANESE("Japanese"),
    @XmlEnumValue("Korean")
    KOREAN("Korean"),
    @XmlEnumValue("Polish")
    POLISH("Polish"),
    @XmlEnumValue("Portugese")
    PORTUGESE("Portugese"),
    @XmlEnumValue("Macedonian")
    MACEDONIAN("Macedonian"),
    @XmlEnumValue("Punjabi")
    PUNJABI("Punjabi"),
    @XmlEnumValue("Russian")
    RUSSIAN("Russian"),
    @XmlEnumValue("Spanish")
    SPANISH("Spanish"),
    @XmlEnumValue("Slovenian")
    SLOVENIAN("Slovenian"),
    @XmlEnumValue("Swahili")
    SWAHILI("Swahili"),
    @XmlEnumValue("Swedish")
    SWEDISH("Swedish"),
    @XmlEnumValue("Thai")
    THAI("Thai"),
    @XmlEnumValue("Turkish")
    TURKISH("Turkish"),
    @XmlEnumValue("Vietnamese")
    VIETNAMESE("Vietnamese"),
    @XmlEnumValue("Ukrainian")
    UKRAINIAN("Ukrainian"),
    @XmlEnumValue("Urdu")
    URDU("Urdu"),
    @XmlEnumValue("Others")
    OTHERS("Others");
    private final String value;

    Language(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Language fromValue(String v) {
        for (Language c: Language.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
