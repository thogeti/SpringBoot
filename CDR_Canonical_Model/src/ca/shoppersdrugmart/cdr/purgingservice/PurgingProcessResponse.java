
package ca.shoppersdrugmart.cdr.purgingservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PurgingProcessResponseDetail" type="{http://shoppersdrugmart.ca/cdr/PurgingService}PurgingProcessResponseDetail" maxOccurs="unbounded"/>
 *         &lt;element name="TotalDeletedPrescription" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TotalDeletedDispense" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "purgingProcessResponseDetail",
    "totalDeletedPrescription",
    "totalDeletedDispense"
})
@XmlRootElement(name = "PurgingProcessResponse")
public class PurgingProcessResponse {

    @XmlElement(name = "PurgingProcessResponseDetail", required = true)
    protected List<PurgingProcessResponseDetail> purgingProcessResponseDetail;
    @XmlElement(name = "TotalDeletedPrescription")
    protected int totalDeletedPrescription;
    @XmlElement(name = "TotalDeletedDispense")
    protected int totalDeletedDispense;

    /**
     * Gets the value of the purgingProcessResponseDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purgingProcessResponseDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurgingProcessResponseDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PurgingProcessResponseDetail }
     * 
     * 
     */
    public List<PurgingProcessResponseDetail> getPurgingProcessResponseDetail() {
        if (purgingProcessResponseDetail == null) {
            purgingProcessResponseDetail = new ArrayList<PurgingProcessResponseDetail>();
        }
        return this.purgingProcessResponseDetail;
    }

    /**
     * Gets the value of the totalDeletedPrescription property.
     * 
     */
    public int getTotalDeletedPrescription() {
        return totalDeletedPrescription;
    }

    /**
     * Sets the value of the totalDeletedPrescription property.
     * 
     */
    public void setTotalDeletedPrescription(int value) {
        this.totalDeletedPrescription = value;
    }

    /**
     * Gets the value of the totalDeletedDispense property.
     * 
     */
    public int getTotalDeletedDispense() {
        return totalDeletedDispense;
    }

    /**
     * Sets the value of the totalDeletedDispense property.
     * 
     */
    public void setTotalDeletedDispense(int value) {
        this.totalDeletedDispense = value;
    }

}
