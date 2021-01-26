
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RouteOfAdmin.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RouteOfAdmin"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Buccal"/&gt;
 *     &lt;enumeration value="Combination"/&gt;
 *     &lt;enumeration value="Dental"/&gt;
 *     &lt;enumeration value="Does Not Apply"/&gt;
 *     &lt;enumeration value="Epidural"/&gt;
 *     &lt;enumeration value="External"/&gt;
 *     &lt;enumeration value="Hemodialysis"/&gt;
 *     &lt;enumeration value="Implant"/&gt;
 *     &lt;enumeration value="In Vitro"/&gt;
 *     &lt;enumeration value="Inhalation"/&gt;
 *     &lt;enumeration value="Injection"/&gt;
 *     &lt;enumeration value="Intra-Arterial"/&gt;
 *     &lt;enumeration value="Intra-Articular"/&gt;
 *     &lt;enumeration value="Intracavernosal"/&gt;
 *     &lt;enumeration value="Intradermal"/&gt;
 *     &lt;enumeration value="Intraintestinal"/&gt;
 *     &lt;enumeration value="Intramuscular"/&gt;
 *     &lt;enumeration value="Intraocular"/&gt;
 *     &lt;enumeration value="Intraperitoneal"/&gt;
 *     &lt;enumeration value="Intrapleural"/&gt;
 *     &lt;enumeration value="Intrathecal"/&gt;
 *     &lt;enumeration value="Intrauterine"/&gt;
 *     &lt;enumeration value="Intravenous"/&gt;
 *     &lt;enumeration value="Intravesical"/&gt;
 *     &lt;enumeration value="Intravitreal"/&gt;
 *     &lt;enumeration value="Irrigation"/&gt;
 *     &lt;enumeration value="Mouth/Throat"/&gt;
 *     &lt;enumeration value="Mucous Membrane"/&gt;
 *     &lt;enumeration value="Nasal"/&gt;
 *     &lt;enumeration value="Not Applicable"/&gt;
 *     &lt;enumeration value="Opthalmic"/&gt;
 *     &lt;enumeration value="Oral"/&gt;
 *     &lt;enumeration value="Otic"/&gt;
 *     &lt;enumeration value="Perfusion"/&gt;
 *     &lt;enumeration value="Rectal"/&gt;
 *     &lt;enumeration value="Subcutaneous"/&gt;
 *     &lt;enumeration value="Sublingual"/&gt;
 *     &lt;enumeration value="Topical"/&gt;
 *     &lt;enumeration value="Transdermal"/&gt;
 *     &lt;enumeration value="Translingual"/&gt;
 *     &lt;enumeration value="Urethral"/&gt;
 *     &lt;enumeration value="Vaginal"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
