
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BenefitsCardholderRelationship.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BenefitsCardholderRelationship">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT KNOWN"/>
 *     &lt;enumeration value="PRIMARY"/>
 *     &lt;enumeration value="SPOUSE"/>
 *     &lt;enumeration value="CHILD UNDERAGE"/>
 *     &lt;enumeration value="CHILD OVERAGE"/>
 *     &lt;enumeration value="DISABLED DEPENDANT"/>
 *     &lt;enumeration value="DEPENDANT STUDENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BenefitsCardholderRelationship")
@XmlEnum
public enum BenefitsCardholderRelationship {

    @XmlEnumValue("NOT KNOWN")
    NOT_KNOWN("NOT KNOWN"),
    PRIMARY("PRIMARY"),
    SPOUSE("SPOUSE"),
    @XmlEnumValue("CHILD UNDERAGE")
    CHILD_UNDERAGE("CHILD UNDERAGE"),
    @XmlEnumValue("CHILD OVERAGE")
    CHILD_OVERAGE("CHILD OVERAGE"),
    @XmlEnumValue("DISABLED DEPENDANT")
    DISABLED_DEPENDANT("DISABLED DEPENDANT"),
    @XmlEnumValue("DEPENDANT STUDENT")
    DEPENDANT_STUDENT("DEPENDANT STUDENT");
    private final String value;

    BenefitsCardholderRelationship(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BenefitsCardholderRelationship fromValue(String v) {
        for (BenefitsCardholderRelationship c: BenefitsCardholderRelationship.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
