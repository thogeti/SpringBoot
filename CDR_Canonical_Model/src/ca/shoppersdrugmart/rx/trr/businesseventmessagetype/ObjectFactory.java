
package ca.shoppersdrugmart.rx.trr.businesseventmessagetype;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rx.trr.businesseventmessagetype package. 
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

    private final static QName _BusinessEvent_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "BusinessEvent");
    private final static QName _ParameterKeyValue_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "ParameterKeyValue");
    private final static QName _StringEntityReference_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "StringEntityReference");
    private final static QName _EntityReference_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "EntityReference");
    private final static QName _BusinessEventMessage_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "BusinessEventMessage");
    private final static QName _QueueNames_QNAME = new QName("http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", "QueueNames");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rx.trr.businesseventmessagetype
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
     * Create an instance of {@link QueueNames }
     * 
     */
    public QueueNames createQueueNames() {
        return new QueueNames();
    }

    /**
     * Create an instance of {@link BusinessEventMessage }
     * 
     */
    public BusinessEventMessage createBusinessEventMessage() {
        return new BusinessEventMessage();
    }

    /**
     * Create an instance of {@link ParameterKeyValue }
     * 
     */
    public ParameterKeyValue createParameterKeyValue() {
        return new ParameterKeyValue();
    }

    /**
     * Create an instance of {@link EntityReference }
     * 
     */
    public EntityReference createEntityReference() {
        return new EntityReference();
    }

    /**
     * Create an instance of {@link StringEntityReference }
     * 
     */
    public StringEntityReference createStringEntityReference() {
        return new StringEntityReference();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "BusinessEvent")
    public JAXBElement<BusinessEvent> createBusinessEvent(BusinessEvent value) {
        return new JAXBElement<BusinessEvent>(_BusinessEvent_QNAME, BusinessEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterKeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "ParameterKeyValue")
    public JAXBElement<ParameterKeyValue> createParameterKeyValue(ParameterKeyValue value) {
        return new JAXBElement<ParameterKeyValue>(_ParameterKeyValue_QNAME, ParameterKeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringEntityReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "StringEntityReference")
    public JAXBElement<StringEntityReference> createStringEntityReference(StringEntityReference value) {
        return new JAXBElement<StringEntityReference>(_StringEntityReference_QNAME, StringEntityReference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "EntityReference")
    public JAXBElement<EntityReference> createEntityReference(EntityReference value) {
        return new JAXBElement<EntityReference>(_EntityReference_QNAME, EntityReference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessEventMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "BusinessEventMessage")
    public JAXBElement<BusinessEventMessage> createBusinessEventMessage(BusinessEventMessage value) {
        return new JAXBElement<BusinessEventMessage>(_BusinessEventMessage_QNAME, BusinessEventMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RX/TRR/BusinessEventMessageType", name = "QueueNames")
    public JAXBElement<QueueNames> createQueueNames(QueueNames value) {
        return new JAXBElement<QueueNames>(_QueueNames_QNAME, QueueNames.class, null, value);
    }

}
