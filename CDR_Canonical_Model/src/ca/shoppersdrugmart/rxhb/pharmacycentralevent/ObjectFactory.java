
package ca.shoppersdrugmart.rxhb.pharmacycentralevent;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.pharmacycentralevent package. 
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

    private final static QName _PharmacyCentralBusinessEvent_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/PharmacyCentralEvent", "PharmacyCentralBusinessEvent");
    private final static QName _PharmacyCentralBusinessEventRequestedStartDate_QNAME = new QName("", "requestedStartDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.pharmacycentralevent
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PharmacyCentralBusinessEvent }
     * 
     */
    public PharmacyCentralBusinessEvent createPharmacyCentralBusinessEvent() {
        return new PharmacyCentralBusinessEvent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PharmacyCentralBusinessEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/PharmacyCentralEvent", name = "PharmacyCentralBusinessEvent")
    public JAXBElement<PharmacyCentralBusinessEvent> createPharmacyCentralBusinessEvent(PharmacyCentralBusinessEvent value) {
        return new JAXBElement<PharmacyCentralBusinessEvent>(_PharmacyCentralBusinessEvent_QNAME, PharmacyCentralBusinessEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "requestedStartDate", scope = PharmacyCentralBusinessEvent.class)
    public JAXBElement<XMLGregorianCalendar> createPharmacyCentralBusinessEventRequestedStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_PharmacyCentralBusinessEventRequestedStartDate_QNAME, XMLGregorianCalendar.class, PharmacyCentralBusinessEvent.class, value);
    }

}
