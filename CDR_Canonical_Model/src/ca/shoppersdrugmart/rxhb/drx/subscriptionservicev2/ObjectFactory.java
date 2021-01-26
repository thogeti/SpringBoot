
package ca.shoppersdrugmart.rxhb.drx.subscriptionservicev2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.subscriptionservicev2 package. 
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

    private final static QName _SubscribeToEntityBySourceChannel_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", "subscribeToEntityBySourceChannel");
    private final static QName _UnSubscribeToEntityBySourceChannel_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", "unSubscribeToEntityBySourceChannel");
    private final static QName _SubscribeToEntityBySourceChannelResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", "subscribeToEntityBySourceChannelResponse");
    private final static QName _UnSubscribeToEntityBySourceChannelResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", "unSubscribeToEntityBySourceChannelResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.subscriptionservicev2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubscribeToEntityBySourceChannel }
     * 
     */
    public SubscribeToEntityBySourceChannel createSubscribeToEntityBySourceChannel() {
        return new SubscribeToEntityBySourceChannel();
    }

    /**
     * Create an instance of {@link UnSubscribeToEntityBySourceChannel }
     * 
     */
    public UnSubscribeToEntityBySourceChannel createUnSubscribeToEntityBySourceChannel() {
        return new UnSubscribeToEntityBySourceChannel();
    }

    /**
     * Create an instance of {@link SubscribeToEntityBySourceChannelResponse }
     * 
     */
    public SubscribeToEntityBySourceChannelResponse createSubscribeToEntityBySourceChannelResponse() {
        return new SubscribeToEntityBySourceChannelResponse();
    }

    /**
     * Create an instance of {@link UnSubscribeToEntityBySourceChannelResponse }
     * 
     */
    public UnSubscribeToEntityBySourceChannelResponse createUnSubscribeToEntityBySourceChannelResponse() {
        return new UnSubscribeToEntityBySourceChannelResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToEntityBySourceChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", name = "subscribeToEntityBySourceChannel")
    public JAXBElement<SubscribeToEntityBySourceChannel> createSubscribeToEntityBySourceChannel(SubscribeToEntityBySourceChannel value) {
        return new JAXBElement<SubscribeToEntityBySourceChannel>(_SubscribeToEntityBySourceChannel_QNAME, SubscribeToEntityBySourceChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnSubscribeToEntityBySourceChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", name = "unSubscribeToEntityBySourceChannel")
    public JAXBElement<UnSubscribeToEntityBySourceChannel> createUnSubscribeToEntityBySourceChannel(UnSubscribeToEntityBySourceChannel value) {
        return new JAXBElement<UnSubscribeToEntityBySourceChannel>(_UnSubscribeToEntityBySourceChannel_QNAME, UnSubscribeToEntityBySourceChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeToEntityBySourceChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", name = "subscribeToEntityBySourceChannelResponse")
    public JAXBElement<SubscribeToEntityBySourceChannelResponse> createSubscribeToEntityBySourceChannelResponse(SubscribeToEntityBySourceChannelResponse value) {
        return new JAXBElement<SubscribeToEntityBySourceChannelResponse>(_SubscribeToEntityBySourceChannelResponse_QNAME, SubscribeToEntityBySourceChannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnSubscribeToEntityBySourceChannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/SubscriptionServiceV2", name = "unSubscribeToEntityBySourceChannelResponse")
    public JAXBElement<UnSubscribeToEntityBySourceChannelResponse> createUnSubscribeToEntityBySourceChannelResponse(UnSubscribeToEntityBySourceChannelResponse value) {
        return new JAXBElement<UnSubscribeToEntityBySourceChannelResponse>(_UnSubscribeToEntityBySourceChannelResponse_QNAME, UnSubscribeToEntityBySourceChannelResponse.class, null, value);
    }

}
