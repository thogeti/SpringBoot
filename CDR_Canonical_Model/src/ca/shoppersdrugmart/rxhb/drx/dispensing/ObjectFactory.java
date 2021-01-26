
package ca.shoppersdrugmart.rxhb.drx.dispensing;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.dispensing package. 
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

    private final static QName _Refill_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", "Refill");
    private final static QName _RefillResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", "RefillResponse");
    private final static QName _Renew_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", "Renew");
    private final static QName _RenewResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", "RenewResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.dispensing
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Refill }
     * 
     */
    public Refill createRefill() {
        return new Refill();
    }

    /**
     * Create an instance of {@link RefillResponse }
     * 
     */
    public RefillResponse createRefillResponse() {
        return new RefillResponse();
    }

    /**
     * Create an instance of {@link Renew }
     * 
     */
    public Renew createRenew() {
        return new Renew();
    }

    /**
     * Create an instance of {@link RenewResponse }
     * 
     */
    public RenewResponse createRenewResponse() {
        return new RenewResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refill }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", name = "Refill")
    public JAXBElement<Refill> createRefill(Refill value) {
        return new JAXBElement<Refill>(_Refill_QNAME, Refill.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefillResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", name = "RefillResponse")
    public JAXBElement<RefillResponse> createRefillResponse(RefillResponse value) {
        return new JAXBElement<RefillResponse>(_RefillResponse_QNAME, RefillResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Renew }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", name = "Renew")
    public JAXBElement<Renew> createRenew(Renew value) {
        return new JAXBElement<Renew>(_Renew_QNAME, Renew.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RenewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/Dispensing", name = "RenewResponse")
    public JAXBElement<RenewResponse> createRenewResponse(RenewResponse value) {
        return new JAXBElement<RenewResponse>(_RenewResponse_QNAME, RenewResponse.class, null, value);
    }

}
