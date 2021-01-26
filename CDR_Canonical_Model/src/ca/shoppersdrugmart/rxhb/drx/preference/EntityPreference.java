
package ca.shoppersdrugmart.rxhb.drx.preference;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ca.shoppersdrugmart.rxhb.drx.cse.UpdatePreferenceEntityBySourceChannel;


/**
 * <p>Java class for EntityPreference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityPreference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EntityPreference" type="{http://shoppersdrugmart.ca/RxHB/DRx/CSE}UpdatePreferenceEntityBySourceChannel" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityPreference", propOrder = {
    "entityPreference"
})
public class EntityPreference {

    @XmlElement(name = "EntityPreference", required = true)
    protected List<UpdatePreferenceEntityBySourceChannel> entityPreference;

    /**
     * Gets the value of the entityPreference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityPreference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityPreference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdatePreferenceEntityBySourceChannel }
     * 
     * 
     */
    public List<UpdatePreferenceEntityBySourceChannel> getEntityPreference() {
        if (entityPreference == null) {
            entityPreference = new ArrayList<UpdatePreferenceEntityBySourceChannel>();
        }
        return this.entityPreference;
    }

}
