
package ca.shoppersdrugmart.rxhb.ehealth.businessevent;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.ehealth.businessevent package. 
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

    private final static QName _BusinessEvent_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/eHealth/BusinessEvent", "BusinessEvent");
    private final static QName _BusinessEventPayload_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/eHealth/BusinessEvent", "BusinessEventPayload");
    private final static QName _BusinessEventRequestedStartDate_QNAME = new QName("", "requestedStartDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.ehealth.businessevent
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessEvent }
     * 
     */
    public BusinessEvent createBusinessEvent() {
        return new BusinessEvent();
    }

    /**
     * Create an instance of {@link BusinessEventPayload }
     * 
     */
    public BusinessEventPayload createBusinessEventPayload() {
        return new BusinessEventPayload();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/eHealth/BusinessEvent", name = "BusinessEvent")
    public JAXBElement<BusinessEvent> createBusinessEvent(BusinessEvent value) {
        return new JAXBElement<BusinessEvent>(_BusinessEvent_QNAME, BusinessEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessEventPayload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/eHealth/BusinessEvent", name = "BusinessEventPayload")
    public JAXBElement<BusinessEventPayload> createBusinessEventPayload(BusinessEventPayload value) {
        return new JAXBElement<BusinessEventPayload>(_BusinessEventPayload_QNAME, BusinessEventPayload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "requestedStartDate", scope = BusinessEvent.class)
    public JAXBElement<XMLGregorianCalendar> createBusinessEventRequestedStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BusinessEventRequestedStartDate_QNAME, XMLGregorianCalendar.class, BusinessEvent.class, value);
    }

}
