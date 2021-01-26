
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Subscription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Subscription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SourceSystem" type="{http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist}SourceSystem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subscription", propOrder = {
    "sourceSystem",
    "linkedRxNumber" //TE97 CR_Prod
})
public class Subscription {

    @XmlElement(name = "SourceSystem")
    protected List<SourceSystem> sourceSystem;
    
    //TE97_FIX 
    @XmlElement(name = "linkedRxNumber")
    protected List<String> linkedRxNumber; 

    /**
     * Gets the value of the sourceSystem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceSystem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceSystem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SourceSystem }
     * 
     * 
     */
    public List<SourceSystem> getSourceSystem() {
        if (sourceSystem == null) {
            sourceSystem = new ArrayList<SourceSystem>();
        }
        return this.sourceSystem;
    }

    ///TE97_FIX Start
	public List<String> getLinkedRxNumber() {
		if (linkedRxNumber == null) {
			linkedRxNumber = new ArrayList<String>();
        }
		return linkedRxNumber;
	}

	public void setLinkedRxNumber(List<String> linkedRxNumber) {
		this.linkedRxNumber = linkedRxNumber;
	}
	
	//TE97_FIX End

}
