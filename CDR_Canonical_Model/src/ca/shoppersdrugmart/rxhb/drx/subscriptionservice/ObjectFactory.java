
package ca.shoppersdrugmart.rxhb.drx.subscriptionservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.subscriptionservice package. 
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

    private final static QName _UnsubscribeToPatient_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "UnsubscribeToPatient");
    private final static QName _UnsubscribeToRx_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "UnsubscribeToRx");
    private final static QName _UnsubscribeToRxResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "UnsubscribeToRxResponse");
    private final static QName _SubscribeToPatient_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "SubscribeToPatient");
    private final static QName _SubscribeToPatientResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "SubscribeToPatientResponse");
    private final static QName _UnsubscribeToPatientResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "UnsubscribeToPatientResponse");
    private final static QName _SubscribeToRxResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "SubscribeToRxResponse");
    private final static QName _SubscribeToRx_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", "SubscribeToRx");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.subscriptionservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnsubscribeToPatient }
     * 
     */
    public UnsubscribeToPatient createUnsubscribeToPatient() {
        return new UnsubscribeToPatient();
    }

    /**
     * Create an instance of {@link UnsubscribeToRxResponse }
     * 
     */
    public UnsubscribeToRxResponse createUnsubscribeToRxResponse() {
        return new UnsubscribeToRxResponse();
    }

    /**
     * Create an instance of {@link UnsubscribeToRx }
     * 
     */
    public UnsubscribeToRx createUnsubscribeToRx() {
        return new UnsubscribeToRx();
    }

    /**
     * Create an instance of {@link SubscribeToRx }
     * 
     */
    public SubscribeToRx createSubscribeToRx() {
        return new SubscribeToRx();
    }

    /**
     * Create an instance of {@link SubscribeToPatientResponse }
     * 
     */
    public SubscribeToPatientResponse createSubscribeToPatientResponse() {
        return new SubscribeToPatientResponse();
    }

    /**
     * Create an instance of {@link SubscribeToPatient }
     * 
     */
    public SubscribeToPatient createSubscribeToPatient() {
        return new SubscribeToPatient();
    }

    /**
     * Create an instance of {@link UnsubscribeToPatientResponse }
     * 
     */
    public UnsubscribeToPatientResponse createUnsubscribeToPatientResponse() {
        return new UnsubscribeToPatientResponse();
    }

    /**
     * Create an instance of {@link SubscribeToRxResponse }
     * 
     */
    public SubscribeToRxResponse createSubscribeToRxResponse() {
        return new SubscribeToRxResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeToPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "UnsubscribeToPatient")
    public JAXBElement<UnsubscribeToPatient> createUnsubscribeToPatient(UnsubscribeToPatient value) {
        return new JAXBElement<UnsubscribeToPatient>(_UnsubscribeToPatient_QNAME, UnsubscribeToPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeToRx }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "UnsubscribeToRx")
    public JAXBElement<UnsubscribeToRx> createUnsubscribeToRx(UnsubscribeToRx value) {
        return new JAXBElement<UnsubscribeToRx>(_UnsubscribeToRx_QNAME, UnsubscribeToRx.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeToRxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "UnsubscribeToRxResponse")
    public JAXBElement<UnsubscribeToRxResponse> createUnsubscribeToRxResponse(UnsubscribeToRxResponse value) {
        return new JAXBElement<UnsubscribeToRxResponse>(_UnsubscribeToRxResponse_QNAME, UnsubscribeToRxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "SubscribeToPatient")
    public JAXBElement<SubscribeToPatient> createSubscribeToPatient(SubscribeToPatient value) {
        return new JAXBElement<SubscribeToPatient>(_SubscribeToPatient_QNAME, SubscribeToPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "SubscribeToPatientResponse")
    public JAXBElement<SubscribeToPatientResponse> createSubscribeToPatientResponse(SubscribeToPatientResponse value) {
        return new JAXBElement<SubscribeToPatientResponse>(_SubscribeToPatientResponse_QNAME, SubscribeToPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeToPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "UnsubscribeToPatientResponse")
    public JAXBElement<UnsubscribeToPatientResponse> createUnsubscribeToPatientResponse(UnsubscribeToPatientResponse value) {
        return new JAXBElement<UnsubscribeToPatientResponse>(_UnsubscribeToPatientResponse_QNAME, UnsubscribeToPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToRxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "SubscribeToRxResponse")
    public JAXBElement<SubscribeToRxResponse> createSubscribeToRxResponse(SubscribeToRxResponse value) {
        return new JAXBElement<SubscribeToRxResponse>(_SubscribeToRxResponse_QNAME, SubscribeToRxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToRx }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionService", name = "SubscribeToRx")
    public JAXBElement<SubscribeToRx> createSubscribeToRx(SubscribeToRx value) {
        return new JAXBElement<SubscribeToRx>(_SubscribeToRx_QNAME, SubscribeToRx.class, null, value);
    }

}
