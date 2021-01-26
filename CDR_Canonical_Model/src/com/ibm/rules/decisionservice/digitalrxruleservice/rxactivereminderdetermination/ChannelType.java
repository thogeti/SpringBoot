
package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChannelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DigitalPharmacy"/>
 *     &lt;enumeration value="PatientContactCenter"/>
 *     &lt;enumeration value="Store"/>
 *     &lt;enumeration value="IVR"/>
 *     &lt;enumeration value="Prescriber"/>
 *     &lt;enumeration value="eHealth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChannelType")
@XmlEnum
public enum ChannelType {


    /**
     * The request originated from the Digital Rx channel
     * 
     */
    @XmlEnumValue("DigitalPharmacy")
    DIGITAL_PHARMACY("DigitalPharmacy"),

    /**
     * The request originated from the PCC channel
     * 
     */
    @XmlEnumValue("PatientContactCenter")
    PATIENT_CONTACT_CENTER("PatientContactCenter"),

    /**
     * The request originated in the Store
     * 
     */
    @XmlEnumValue("Store")
    STORE("Store"),

    /**
     * The request originated from the IVR channel
     * 
     */
    IVR("IVR"),

    /**
     * The request originated from the Prescriber
     * 
     */
    @XmlEnumValue("Prescriber")
    PRESCRIBER("Prescriber"),

    /**
     * The request originated from the eHealth channel
     * 
     */
    @XmlEnumValue("eHealth")
    E_HEALTH("eHealth");
    private final String value;

    ChannelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChannelType fromValue(String v) {
        for (ChannelType c: ChannelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
