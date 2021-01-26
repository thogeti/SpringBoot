
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
///This class created on 31/05/2018 for notification service.


/**
 * <p>Java class for Notifiacation Service.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <xsd:element name="EventName">
                     <xsd:complexType>
                        <xsd:choice>
                            <xsd:element name="Drug"    type="xsd:string"/>
						               <xsd:element name="Rx"      type="xsd:string"/>
						               <xsd:element name="Tx"      type="xsd:string"/>
						               <xsd:element name="Patient" type="xsd:string"/>
						               <xsd:element name="Purge"   type="xsd:string"/>
                        </xsd:choice>
                     </xsd:complexType>
 * </pre>
 * 
 */



@XmlType(name = "NotificationEnum")
@XmlEnum
public enum EventName {

    @XmlEnumValue("Drug")
    DRUG("DrugData"),
    @XmlEnumValue("Rx")
    RX("RxData"),
    @XmlEnumValue("Patient")
    PATIENT("PatientData"),
    @XmlEnumValue("Tx")
    TX("TxData"),
    @XmlEnumValue("Purge")
    PURGE("Purge"),
    @XmlEnumValue("Prescriber")
    PRESCRIBER("PrescriberData")
   ;
    
    private final String value;

    EventName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventName fromValue(String v) {
        for (EventName c: EventName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
