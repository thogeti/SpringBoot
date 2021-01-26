
package ca.shoppersdrugmart.rxhb.drx.preference;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.preference package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EntityPreference_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Preference", "EntityPreference");
    private final static QName _EntityPreferenceResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Preference", "EntityPreferenceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.preference
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntityPreference }
     * 
     */
    public EntityPreference createEntityPreference() {
        return new EntityPreference();
    }

    /**
     * Create an instance of {@link EntityPreferenceResponse }
     * 
     */
    public EntityPreferenceResponse createEntityPreferenceResponse() {
        return new EntityPreferenceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityPreference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Preference", name = "EntityPreference")
    public JAXBElement<EntityPreference> createEntityPreference(EntityPreference value) {
        return new JAXBElement<EntityPreference>(_EntityPreference_QNAME, EntityPreference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityPreferenceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Preference", name = "EntityPreferenceResponse")
    public JAXBElement<EntityPreferenceResponse> createEntityPreferenceResponse(EntityPreferenceResponse value) {
        return new JAXBElement<EntityPreferenceResponse>(_EntityPreferenceResponse_QNAME, EntityPreferenceResponse.class, null, value);
    }

}
