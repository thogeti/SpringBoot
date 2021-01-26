
package ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.ehealth.CoverageCard;


/**
 * <p>Java class for GetMasterCardListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMasterCardListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CoverageCard" type="{http://shoppersdrugmart.ca/RxHB/eHealth}CoverageCard" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMasterCardListResponse", propOrder = {
    "coverageCard"
})
public class GetMasterCardListResponse {

    @XmlElement(name = "CoverageCard")
    protected List<CoverageCard> coverageCard;

    /**
     * Gets the value of the coverageCard property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coverageCard property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoverageCard().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoverageCard }
     * 
     * 
     */
    public List<CoverageCard> getCoverageCard() {
        if (coverageCard == null) {
            coverageCard = new ArrayList<CoverageCard>();
        }
        return this.coverageCard;
    }

}
