
package ca.shoppersdrugmart.rxhb.drx.rxtransfer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.rxtransfer package. 
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

    private final static QName _RxTransfer_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/RxTransfer", "RxTransfer");
    private final static QName _RxTransferResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/RxTransfer", "RxTransferResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.rxtransfer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RxTransfer }
     * 
     */
    public RxTransfer createRxTransfer() {
        return new RxTransfer();
    }

    /**
     * Create an instance of {@link RxTransferResponse }
     * 
     */
    public RxTransferResponse createRxTransferResponse() {
        return new RxTransferResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RxTransfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/RxTransfer", name = "RxTransfer")
    public JAXBElement<RxTransfer> createRxTransfer(RxTransfer value) {
        return new JAXBElement<RxTransfer>(_RxTransfer_QNAME, RxTransfer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RxTransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/RxTransfer", name = "RxTransferResponse")
    public JAXBElement<RxTransferResponse> createRxTransferResponse(RxTransferResponse value) {
        return new JAXBElement<RxTransferResponse>(_RxTransferResponse_QNAME, RxTransferResponse.class, null, value);
    }

}
