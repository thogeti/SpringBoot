
package ca.shoppersdrugmart.message.errorxml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.message.errorxml package. 
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

    private final static QName _ErrorDetail_QNAME = new QName("http://www.shoppersdrugmart.ca/message/ErrorXML", "ErrorDetail");
    private final static QName _ErrorHeader_QNAME = new QName("http://www.shoppersdrugmart.ca/message/ErrorXML", "ErrorHeader");
    private final static QName _ErrorMessage_QNAME = new QName("http://www.shoppersdrugmart.ca/message/ErrorXML", "ErrorMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.message.errorxml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link ErrorHeader }
     * 
     */
    public ErrorHeader createErrorHeader() {
        return new ErrorHeader();
    }

    /**
     * Create an instance of {@link ErrorDetail }
     * 
     */
    public ErrorDetail createErrorDetail() {
        return new ErrorDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.shoppersdrugmart.ca/message/ErrorXML", name = "ErrorDetail")
    public JAXBElement<ErrorDetail> createErrorDetail(ErrorDetail value) {
        return new JAXBElement<ErrorDetail>(_ErrorDetail_QNAME, ErrorDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.shoppersdrugmart.ca/message/ErrorXML", name = "ErrorHeader")
    public JAXBElement<ErrorHeader> createErrorHeader(ErrorHeader value) {
        return new JAXBElement<ErrorHeader>(_ErrorHeader_QNAME, ErrorHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.shoppersdrugmart.ca/message/ErrorXML", name = "ErrorMessage")
    public JAXBElement<ErrorMessage> createErrorMessage(ErrorMessage value) {
        return new JAXBElement<ErrorMessage>(_ErrorMessage_QNAME, ErrorMessage.class, null, value);
    }

}
