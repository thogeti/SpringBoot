
package ca.shoppersdrugmart.rxhb.drx.getpatient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.getpatient package. 
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

    private final static QName _GetPatientResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatientResponse");
    private final static QName _GetPatientByPatientId_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatientByPatientId");
    private final static QName _GetPatientByPatientIdResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatientByPatientIdResponse");
    private final static QName _GetPatient_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatient");
    private final static QName _GetPatientByQueryCriteriaResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatientByQueryCriteriaResponse");
    private final static QName _GetPatientByQueryCriteria_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", "GetPatientByQueryCriteria");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.getpatient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPatientResponse }
     * 
     */
    public GetPatientResponse createGetPatientResponse() {
        return new GetPatientResponse();
    }

    /**
     * Create an instance of {@link GetPatientByPatientId }
     * 
     */
    public GetPatientByPatientId createGetPatientByPatientId() {
        return new GetPatientByPatientId();
    }

    /**
     * Create an instance of {@link GetPatientByPatientIdResponse }
     * 
     */
    public GetPatientByPatientIdResponse createGetPatientByPatientIdResponse() {
        return new GetPatientByPatientIdResponse();
    }

    /**
     * Create an instance of {@link GetPatient }
     * 
     */
    public GetPatient createGetPatient() {
        return new GetPatient();
    }

    /**
     * Create an instance of {@link GetPatientByQueryCriteriaResponse }
     * 
     */
    public GetPatientByQueryCriteriaResponse createGetPatientByQueryCriteriaResponse() {
        return new GetPatientByQueryCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetPatientByQueryCriteria }
     * 
     */
    public GetPatientByQueryCriteria createGetPatientByQueryCriteria() {
        return new GetPatientByQueryCriteria();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatientResponse")
    public JAXBElement<GetPatientResponse> createGetPatientResponse(GetPatientResponse value) {
        return new JAXBElement<GetPatientResponse>(_GetPatientResponse_QNAME, GetPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByPatientId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatientByPatientId")
    public JAXBElement<GetPatientByPatientId> createGetPatientByPatientId(GetPatientByPatientId value) {
        return new JAXBElement<GetPatientByPatientId>(_GetPatientByPatientId_QNAME, GetPatientByPatientId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByPatientIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatientByPatientIdResponse")
    public JAXBElement<GetPatientByPatientIdResponse> createGetPatientByPatientIdResponse(GetPatientByPatientIdResponse value) {
        return new JAXBElement<GetPatientByPatientIdResponse>(_GetPatientByPatientIdResponse_QNAME, GetPatientByPatientIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatient")
    public JAXBElement<GetPatient> createGetPatient(GetPatient value) {
        return new JAXBElement<GetPatient>(_GetPatient_QNAME, GetPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByQueryCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatientByQueryCriteriaResponse")
    public JAXBElement<GetPatientByQueryCriteriaResponse> createGetPatientByQueryCriteriaResponse(GetPatientByQueryCriteriaResponse value) {
        return new JAXBElement<GetPatientByQueryCriteriaResponse>(_GetPatientByQueryCriteriaResponse_QNAME, GetPatientByQueryCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientByQueryCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/GetPatient", name = "GetPatientByQueryCriteria")
    public JAXBElement<GetPatientByQueryCriteria> createGetPatientByQueryCriteria(GetPatientByQueryCriteria value) {
        return new JAXBElement<GetPatientByQueryCriteria>(_GetPatientByQueryCriteria_QNAME, GetPatientByQueryCriteria.class, null, value);
    }

}
