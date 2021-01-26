
package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
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

    private final static QName _RxHBBusinessEventEntity_QNAME = new QName("", "RxHBBusinessEventEntity");
    private final static QName _RxHBBusinessEvent_QNAME = new QName("", "RxHBBusinessEvent");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RxHBBusinessEvent }
     * 
     */
    public RxHBBusinessEvent createRxHBBusinessEvent() {
        return new RxHBBusinessEvent();
    }

    /**
     * Create an instance of {@link RxHBBusinessEventEntity }
     * 
     */
    public RxHBBusinessEventEntity createRxHBBusinessEventEntity() {
        return new RxHBBusinessEventEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RxHBBusinessEventEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RxHBBusinessEventEntity")
    public JAXBElement<RxHBBusinessEventEntity> createRxHBBusinessEventEntity(RxHBBusinessEventEntity value) {
        return new JAXBElement<RxHBBusinessEventEntity>(_RxHBBusinessEventEntity_QNAME, RxHBBusinessEventEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RxHBBusinessEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RxHBBusinessEvent")
    public JAXBElement<RxHBBusinessEvent> createRxHBBusinessEvent(RxHBBusinessEvent value) {
        return new JAXBElement<RxHBBusinessEvent>(_RxHBBusinessEvent_QNAME, RxHBBusinessEvent.class, null, value);
    }

}
