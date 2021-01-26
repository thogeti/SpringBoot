
package ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;


/**
 * <p>Java class for GetCoverageCardResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCoverageCardResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InsuranceCoverage" type="{http://shoppersdrugmart.ca/RxHB/eHealth}InsuranceCoverage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCoverageCardResponse", propOrder = {
    "insuranceCoverage"
})
public class GetCoverageCardResponse {

    @XmlElement(name = "InsuranceCoverage")
    protected List<InsuranceCoverage> insuranceCoverage;

    /**
     * Gets the value of the insuranceCoverage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insuranceCoverage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsuranceCoverage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsuranceCoverage }
     * 
     * 
     */
    public List<InsuranceCoverage> getInsuranceCoverage() {
        if (insuranceCoverage == null) {
            insuranceCoverage = new ArrayList<InsuranceCoverage>();
        }
        return this.insuranceCoverage;
    }

}
