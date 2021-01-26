
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriptionHoldReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrescriptionHoldReason"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Admitted to Hospital"/&gt;
 *     &lt;enumeration value="Allergy to Product"/&gt;
 *     &lt;enumeration value="Intolerance to Product"/&gt;
 *     &lt;enumeration value="Product Interferes with Test"/&gt;
 *     &lt;enumeration value="Duplicate Therapy"/&gt;
 *     &lt;enumeration value="Prescription Clarification Required"/&gt;
 *     &lt;enumeration value="Drug Interaction"/&gt;
 *     &lt;enumeration value="Use Alternative First"/&gt;
 *     &lt;enumeration value="Pregnancy and or Breast Feeding"/&gt;
 *     &lt;enumeration value="Awaiting Wash Out"/&gt;
 *     &lt;enumeration value="Patient Not Available"/&gt;
 *     &lt;enumeration value="Patient Chemical Level Too High"/&gt;
 *     &lt;enumeration value="Surgery Scheduled"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PrescriptionHoldReason")
@XmlEnum
public enum PrescriptionHoldReason {

    @XmlEnumValue("Admitted to Hospital")
    ADMITTED_TO_HOSPITAL("Admitted to Hospital"),
    @XmlEnumValue("Allergy to Product")
    ALLERGY_TO_PRODUCT("Allergy to Product"),
    @XmlEnumValue("Intolerance to Product")
    INTOLERANCE_TO_PRODUCT("Intolerance to Product"),
    @XmlEnumValue("Product Interferes with Test")
    PRODUCT_INTERFERES_WITH_TEST("Product Interferes with Test"),
    @XmlEnumValue("Duplicate Therapy")
    DUPLICATE_THERAPY("Duplicate Therapy"),
    @XmlEnumValue("Prescription Clarification Required")
    PRESCRIPTION_CLARIFICATION_REQUIRED("Prescription Clarification Required"),
    @XmlEnumValue("Drug Interaction")
    DRUG_INTERACTION("Drug Interaction"),
    @XmlEnumValue("Use Alternative First")
    USE_ALTERNATIVE_FIRST("Use Alternative First"),
    @XmlEnumValue("Pregnancy and or Breast Feeding")
    PREGNANCY_AND_OR_BREAST_FEEDING("Pregnancy and or Breast Feeding"),
    @XmlEnumValue("Awaiting Wash Out")
    AWAITING_WASH_OUT("Awaiting Wash Out"),
    @XmlEnumValue("Patient Not Available")
    PATIENT_NOT_AVAILABLE("Patient Not Available"),
    @XmlEnumValue("Patient Chemical Level Too High")
    PATIENT_CHEMICAL_LEVEL_TOO_HIGH("Patient Chemical Level Too High"),
    @XmlEnumValue("Surgery Scheduled")
    SURGERY_SCHEDULED("Surgery Scheduled");
    private final String value;

    PrescriptionHoldReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrescriptionHoldReason fromValue(String v) {
        for (PrescriptionHoldReason c: PrescriptionHoldReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
