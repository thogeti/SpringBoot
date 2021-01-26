
package ca.shoppersdrugmart.cdr.cdrcommoneventpersist;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.cdr.cdrcommoneventpersist package. 
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

    private final static QName _PatientUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PatientUpsertResponse");
    private final static QName _PrescriptionUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PrescriptionUpsertResponse");
    private final static QName _PatientUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PatientUpsert");
    private final static QName _DispenseUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "DispenseUpsertResponse");
    private final static QName _StoreUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "StoreUpsert");
    private final static QName _PrescriberUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PrescriberUpsert");
    private final static QName _StoreUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "StoreUpsertResponse");
    private final static QName _PrescriberUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PrescriberUpsertResponse");
    private final static QName _PrescriptionUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "PrescriptionUpsert");
    private final static QName _DispenseUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "DispenseUpsert");
    private final static QName _HW4BEventUpsert_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "HW4BEventUpsert");
    private final static QName _HW4BEventUpsertResponse_QNAME = new QName("http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", "HW4BEventUpsertResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.cdr.cdrcommoneventpersist
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HW4BEventUpsert }
     * 
     */
    public HW4BEventUpsert createHW4BEventUpsert() {
        return new HW4BEventUpsert();
    }

    /**
     * Create an instance of {@link HW4BEventUpsertResponse }
     * 
     */
    public HW4BEventUpsertResponse createHW4BEventUpsertResponse() {
        return new HW4BEventUpsertResponse();
    }



/**
     * Create an instance of {@link DispenseUpsertResponse }
     * 
     */
    public DispenseUpsertResponse createDispenseUpsertResponse() {
        return new DispenseUpsertResponse();
    }

    /**
     * Create an instance of {@link DispenseUpsert }
     * 
     */
    public DispenseUpsert createDispenseUpsert() {
        return new DispenseUpsert();
    }

    /**
     * Create an instance of {@link PrescriberUpsertResponse }
     * 
     */
    public PrescriberUpsertResponse createPrescriberUpsertResponse() {
        return new PrescriberUpsertResponse();
    }

    /**
     * Create an instance of {@link PrescriptionUpsert }
     * 
     */
    public PrescriptionUpsert createPrescriptionUpsert() {
        return new PrescriptionUpsert();
    }

    /**
     * Create an instance of {@link StoreUpsert }
     * 
     */
    public StoreUpsert createStoreUpsert() {
        return new StoreUpsert();
    }

    /**
     * Create an instance of {@link PrescriberUpsert }
     * 
     */
    public PrescriberUpsert createPrescriberUpsert() {
        return new PrescriberUpsert();
    }

    /**
     * Create an instance of {@link PrescriptionUpsertResponse }
     * 
     */
    public PrescriptionUpsertResponse createPrescriptionUpsertResponse() {
        return new PrescriptionUpsertResponse();
    }

    /**
     * Create an instance of {@link StoreUpsertResponse }
     * 
     */
    public StoreUpsertResponse createStoreUpsertResponse() {
        return new StoreUpsertResponse();
    }

    /**
     * Create an instance of {@link PatientUpsertResponse }
     * 
     */
    public PatientUpsertResponse createPatientUpsertResponse() {
        return new PatientUpsertResponse();
    }

    /**
     * Create an instance of {@link PatientUpsert }
     * 
     */
    public PatientUpsert createPatientUpsert() {
        return new PatientUpsert();
    }

    /**
     * Create an instance of {@link CDRPersistResponse }
     * 
     */
    public CDRPersistResponse createCDRPersistResponse() {
        return new CDRPersistResponse();
    }

    /**
     * Create an instance of {@link Subscription }
     * 
     */
    public Subscription createSubscription() {
        return new Subscription();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientUpsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PatientUpsertResponse")
    public JAXBElement<PatientUpsertResponse> createPatientUpsertResponse(PatientUpsertResponse value) {
        return new JAXBElement<PatientUpsertResponse>(_PatientUpsertResponse_QNAME, PatientUpsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrescriptionUpsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PrescriptionUpsertResponse")
    public JAXBElement<PrescriptionUpsertResponse> createPrescriptionUpsertResponse(PrescriptionUpsertResponse value) {
        return new JAXBElement<PrescriptionUpsertResponse>(_PrescriptionUpsertResponse_QNAME, PrescriptionUpsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientUpsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PatientUpsert")
    public JAXBElement<PatientUpsert> createPatientUpsert(PatientUpsert value) {
        return new JAXBElement<PatientUpsert>(_PatientUpsert_QNAME, PatientUpsert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispenseUpsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "DispenseUpsertResponse")
    public JAXBElement<DispenseUpsertResponse> createDispenseUpsertResponse(DispenseUpsertResponse value) {
        return new JAXBElement<DispenseUpsertResponse>(_DispenseUpsertResponse_QNAME, DispenseUpsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreUpsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "StoreUpsert")
    public JAXBElement<StoreUpsert> createStoreUpsert(StoreUpsert value) {
        return new JAXBElement<StoreUpsert>(_StoreUpsert_QNAME, StoreUpsert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrescriberUpsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PrescriberUpsert")
    public JAXBElement<PrescriberUpsert> createPrescriberUpsert(PrescriberUpsert value) {
        return new JAXBElement<PrescriberUpsert>(_PrescriberUpsert_QNAME, PrescriberUpsert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreUpsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "StoreUpsertResponse")
    public JAXBElement<StoreUpsertResponse> createStoreUpsertResponse(StoreUpsertResponse value) {
        return new JAXBElement<StoreUpsertResponse>(_StoreUpsertResponse_QNAME, StoreUpsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrescriberUpsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PrescriberUpsertResponse")
    public JAXBElement<PrescriberUpsertResponse> createPrescriberUpsertResponse(PrescriberUpsertResponse value) {
        return new JAXBElement<PrescriberUpsertResponse>(_PrescriberUpsertResponse_QNAME, PrescriberUpsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrescriptionUpsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "PrescriptionUpsert")
    public JAXBElement<PrescriptionUpsert> createPrescriptionUpsert(PrescriptionUpsert value) {
        return new JAXBElement<PrescriptionUpsert>(_PrescriptionUpsert_QNAME, PrescriptionUpsert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispenseUpsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/cdr/CDRCommonEventPersist", name = "DispenseUpsert")
    public JAXBElement<DispenseUpsert> createDispenseUpsert(DispenseUpsert value) {
        return new JAXBElement<DispenseUpsert>(_DispenseUpsert_QNAME, DispenseUpsert.class, null, value);
    }

}
