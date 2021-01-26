
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RouteOfAdmin.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RouteOfAdmin">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Buccal"/>
 *     &lt;enumeration value="Combination"/>
 *     &lt;enumeration value="Dental"/>
 *     &lt;enumeration value="Does Not Apply"/>
 *     &lt;enumeration value="Epidural"/>
 *     &lt;enumeration value="External"/>
 *     &lt;enumeration value="Hemodialysis"/>
 *     &lt;enumeration value="Implant"/>
 *     &lt;enumeration value="In Vitro"/>
 *     &lt;enumeration value="Inhalation"/>
 *     &lt;enumeration value="Injection"/>
 *     &lt;enumeration value="Intra-Arterial"/>
 *     &lt;enumeration value="Intra-Articular"/>
 *     &lt;enumeration value="Intracavernosal"/>
 *     &lt;enumeration value="Intradermal"/>
 *     &lt;enumeration value="Intraintestinal"/>
 *     &lt;enumeration value="Intramuscular"/>
 *     &lt;enumeration value="Intraocular"/>
 *     &lt;enumeration value="Intraperitoneal"/>
 *     &lt;enumeration value="Intrapleural"/>
 *     &lt;enumeration value="Intrathecal"/>
 *     &lt;enumeration value="Intrauterine"/>
 *     &lt;enumeration value="Intravenous"/>
 *     &lt;enumeration value="Intravesical"/>
 *     &lt;enumeration value="Intravitreal"/>
 *     &lt;enumeration value="Irrigation"/>
 *     &lt;enumeration value="Mouth/Throat"/>
 *     &lt;enumeration value="Mucous Membrane"/>
 *     &lt;enumeration value="Nasal"/>
 *     &lt;enumeration value="Not Applicable"/>
 *     &lt;enumeration value="Opthalmic"/>
 *     &lt;enumeration value="Oral"/>
 *     &lt;enumeration value="Otic"/>
 *     &lt;enumeration value="Perfusion"/>
 *     &lt;enumeration value="Rectal"/>
 *     &lt;enumeration value="Subcutaneous"/>
 *     &lt;enumeration value="Sublingual"/>
 *     &lt;enumeration value="Topical"/>
 *     &lt;enumeration value="Transdermal"/>
 *     &lt;enumeration value="Translingual"/>
 *     &lt;enumeration value="Urethral"/>
 *     &lt;enumeration value="Vaginal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RouteOfAdmin")
@XmlEnum
public enum RouteOfAdmin {

    @XmlEnumValue("Buccal")
    BUCCAL("Buccal"),
    @XmlEnumValue("Combination")
    COMBINATION("Combination"),
    @XmlEnumValue("Dental")
    DENTAL("Dental"),
    @XmlEnumValue("Does Not Apply")
    DOES_NOT_APPLY("Does Not Apply"),
    @XmlEnumValue("Epidural")
    EPIDURAL("Epidural"),
    @XmlEnumValue("External")
    EXTERNAL("External"),
    @XmlEnumValue("Hemodialysis")
    HEMODIALYSIS("Hemodialysis"),
    @XmlEnumValue("Implant")
    IMPLANT("Implant"),
    @XmlEnumValue("In Vitro")
    IN_VITRO("In Vitro"),
    @XmlEnumValue("Inhalation")
    INHALATION("Inhalation"),
    @XmlEnumValue("Injection")
    INJECTION("Injection"),
    @XmlEnumValue("Intra-Arterial")
    INTRA_ARTERIAL("Intra-Arterial"),
    @XmlEnumValue("Intra-Articular")
    INTRA_ARTICULAR("Intra-Articular"),
    @XmlEnumValue("Intracavernosal")
    INTRACAVERNOSAL("Intracavernosal"),
    @XmlEnumValue("Intradermal")
    INTRADERMAL("Intradermal"),
    @XmlEnumValue("Intraintestinal")
    INTRAINTESTINAL("Intraintestinal"),
    @XmlEnumValue("Intramuscular")
    INTRAMUSCULAR("Intramuscular"),
    @XmlEnumValue("Intraocular")
    INTRAOCULAR("Intraocular"),
    @XmlEnumValue("Intraperitoneal")
    INTRAPERITONEAL("Intraperitoneal"),
    @XmlEnumValue("Intrapleural")
    INTRAPLEURAL("Intrapleural"),
    @XmlEnumValue("Intrathecal")
    INTRATHECAL("Intrathecal"),
    @XmlEnumValue("Intrauterine")
    INTRAUTERINE("Intrauterine"),
    @XmlEnumValue("Intravenous")
    INTRAVENOUS("Intravenous"),
    @XmlEnumValue("Intravesical")
    INTRAVESICAL("Intravesical"),
    @XmlEnumValue("Intravitreal")
    INTRAVITREAL("Intravitreal"),
    @XmlEnumValue("Irrigation")
    IRRIGATION("Irrigation"),
    @XmlEnumValue("Mouth/Throat")
    MOUTH_THROAT("Mouth/Throat"),
    @XmlEnumValue("Mucous Membrane")
    MUCOUS_MEMBRANE("Mucous Membrane"),
    @XmlEnumValue("Nasal")
    NASAL("Nasal"),
    @XmlEnumValue("Not Applicable")
    NOT_APPLICABLE("Not Applicable"),
    @XmlEnumValue("Opthalmic")
    OPTHALMIC("Opthalmic"),
    @XmlEnumValue("Oral")
    ORAL("Oral"),
    @XmlEnumValue("Otic")
    OTIC("Otic"),
    @XmlEnumValue("Perfusion")
    PERFUSION("Perfusion"),
    @XmlEnumValue("Rectal")
    RECTAL("Rectal"),
    @XmlEnumValue("Subcutaneous")
    SUBCUTANEOUS("Subcutaneous"),
    @XmlEnumValue("Sublingual")
    SUBLINGUAL("Sublingual"),
    @XmlEnumValue("Topical")
    TOPICAL("Topical"),
    @XmlEnumValue("Transdermal")
    TRANSDERMAL("Transdermal"),
    @XmlEnumValue("Translingual")
    TRANSLINGUAL("Translingual"),
    @XmlEnumValue("Urethral")
    URETHRAL("Urethral"),
    @XmlEnumValue("Vaginal")
    VAGINAL("Vaginal");
    private final String value;

    RouteOfAdmin(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RouteOfAdmin fromValue(String v) {
        for (RouteOfAdmin c: RouteOfAdmin.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
