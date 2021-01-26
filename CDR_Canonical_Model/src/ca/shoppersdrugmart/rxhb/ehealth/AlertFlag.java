
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlertFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertFlag"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FCPR"/&gt;
 *     &lt;enumeration value="FPDN"/&gt;
 *     &lt;enumeration value="REPH"/&gt;
 *     &lt;enumeration value="CPNA"/&gt;
 *     &lt;enumeration value="CPAS"/&gt;
 *     &lt;enumeration value="PHNA"/&gt;
 *     &lt;enumeration value="PHAS"/&gt;
 *     &lt;enumeration value="POHB"/&gt;
 *     &lt;enumeration value="PBHB"/&gt;
 *     &lt;enumeration value="SPPA"/&gt;
 *     &lt;enumeration value="SPPD"/&gt;
 *     &lt;enumeration value="SPAA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AlertFlag")
@XmlEnum
public enum AlertFlag {


    /**
     * Fill current prescription
     * 
     */
    FCPR,

    /**
     * Fill current prescription and do not ask again
     * 
     */
    FPDN,

    /**
     * Refer to pharmacist
     * 
     */
    REPH,

    /**
     * Contact prescriber
     * 
     */
    CPNA,
    CPAS,

    /**
     * Pharmacist Initiated Therapeutic Alternative with no alternative selected
     * 
     */
    PHNA,

    /**
     * Pharmacist Initiated Therapeutic Alternative with alternative selected
     * 
     */
    PHAS,

    /**
     * Patient opts-out of all HW4B alerts
     * 
     */
    POHB,

    /**
     * Patient opts-back on to HW4B alerts
     * 
     */
    PBHB,
    SPPA,
    SPPD,
    SPAA;

    public String value() {
        return name();
    }

    public static AlertFlag fromValue(String v) {
        return valueOf(v);
    }

}
