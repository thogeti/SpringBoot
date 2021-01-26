package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SourceSystem.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SourceSystem"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DigitalRx"/&gt;
 *     &lt;enumeration value="DigitalPharmacyValueAddedService"/&gt;
 *     &lt;enumeration value="DigitalPharmacy"/&gt;
 *     &lt;enumeration value="PatientContactCenter"/&gt;
 *     &lt;enumeration value="Store"/&gt;
 *     &lt;enumeration value="IVR"/&gt;
 *     &lt;enumeration value="Prescriber"/&gt;
 *     &lt;enumeration value="eHealth"/&gt;
 *     &lt;enumeration value="Accuro"/&gt;
 *     &lt;enumeration value="PatientConsent"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SourceSystem")
@XmlEnum
public enum SourceSystem {

    @XmlEnumValue("DigitalRx")
    DIGITAL_RX("DigitalRx"),
    @XmlEnumValue("DigitalPharmacyValueAddedService")
    DIGITAL_PHARMACY_VALUE_ADDED_SERVICE("DigitalPharmacyValueAddedService"),
    @XmlEnumValue("DigitalPharmacy")
    DIGITAL_PHARMACY("DigitalPharmacy"),
    @XmlEnumValue("PatientContactCenter")
    PATIENT_CONTACT_CENTER("PatientContactCenter"),
    @XmlEnumValue("Store")
    STORE("Store"),
    IVR("IVR"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),
    @XmlEnumValue("eHealth")
    E_HEALTH("eHealth"),
    @XmlEnumValue("Accuro")
    ACCURO("Accuro"),
    @XmlEnumValue("PatientConsent")
    PATIENT_CONSENT("PatientConsent");
    private final String value;

    SourceSystem(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SourceSystem fromValue(String v) {
        for (SourceSystem c: SourceSystem.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
