
package ca.shoppersdrugmart.rxhb.drx.medicationprofile;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.medicationprofile package. 
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

    private final static QName _GetPrescription_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPrescription");
    private final static QName _GetPrescriptionResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPrescriptionResponse");
    private final static QName _GetDispense_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetDispense");
    private final static QName _GetDispenseResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetDispenseResponse");
    private final static QName _GetPrescriptionAdherenceCalendar_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPrescriptionAdherenceCalendar");
    private final static QName _GetPrescriptionAdherenceCalendarResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPrescriptionAdherenceCalendarResponse");
    private final static QName _GetDispenseAdherenceCalendar_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetDispenseAdherenceCalendar");
    private final static QName _GetDispenseAdherenceCalendarResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetDispenseAdherenceCalendarResponse");
    private final static QName _GetPatientAdherenceCalendar_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPatientAdherenceCalendar");
    private final static QName _GetPatientAdherenceCalendarResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", "GetPatientAdherenceCalendarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.medicationprofile
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPrescription }
     * 
     */
    public GetPrescription createGetPrescription() {
        return new GetPrescription();
    }

    /**
     * Create an instance of {@link GetPrescriptionResponse }
     * 
     */
    public GetPrescriptionResponse createGetPrescriptionResponse() {
        return new GetPrescriptionResponse();
    }

    /**
     * Create an instance of {@link GetDispense }
     * 
     */
    public GetDispense createGetDispense() {
        return new GetDispense();
    }

    /**
     * Create an instance of {@link GetDispenseResponse }
     * 
     */
    public GetDispenseResponse createGetDispenseResponse() {
        return new GetDispenseResponse();
    }

    /**
     * Create an instance of {@link GetPrescriptionAdherenceCalendar }
     * 
     */
    public GetPrescriptionAdherenceCalendar createGetPrescriptionAdherenceCalendar() {
        return new GetPrescriptionAdherenceCalendar();
    }

    /**
     * Create an instance of {@link GetPrescriptionAdherenceCalendarResponse }
     * 
     */
    public GetPrescriptionAdherenceCalendarResponse createGetPrescriptionAdherenceCalendarResponse() {
        return new GetPrescriptionAdherenceCalendarResponse();
    }

    /**
     * Create an instance of {@link GetDispenseAdherenceCalendar }
     * 
     */
    public GetDispenseAdherenceCalendar createGetDispenseAdherenceCalendar() {
        return new GetDispenseAdherenceCalendar();
    }

    /**
     * Create an instance of {@link GetDispenseAdherenceCalendarResponse }
     * 
     */
    public GetDispenseAdherenceCalendarResponse createGetDispenseAdherenceCalendarResponse() {
        return new GetDispenseAdherenceCalendarResponse();
    }

    /**
     * Create an instance of {@link GetPatientAdherenceCalendar }
     * 
     */
    public GetPatientAdherenceCalendar createGetPatientAdherenceCalendar() {
        return new GetPatientAdherenceCalendar();
    }

    /**
     * Create an instance of {@link GetPatientAdherenceCalendarResponse }
     * 
     */
    public GetPatientAdherenceCalendarResponse createGetPatientAdherenceCalendarResponse() {
        return new GetPatientAdherenceCalendarResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPrescription")
    public JAXBElement<GetPrescription> createGetPrescription(GetPrescription value) {
        return new JAXBElement<GetPrescription>(_GetPrescription_QNAME, GetPrescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrescriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPrescriptionResponse")
    public JAXBElement<GetPrescriptionResponse> createGetPrescriptionResponse(GetPrescriptionResponse value) {
        return new JAXBElement<GetPrescriptionResponse>(_GetPrescriptionResponse_QNAME, GetPrescriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDispense }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetDispense")
    public JAXBElement<GetDispense> createGetDispense(GetDispense value) {
        return new JAXBElement<GetDispense>(_GetDispense_QNAME, GetDispense.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDispenseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetDispenseResponse")
    public JAXBElement<GetDispenseResponse> createGetDispenseResponse(GetDispenseResponse value) {
        return new JAXBElement<GetDispenseResponse>(_GetDispenseResponse_QNAME, GetDispenseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrescriptionAdherenceCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPrescriptionAdherenceCalendar")
    public JAXBElement<GetPrescriptionAdherenceCalendar> createGetPrescriptionAdherenceCalendar(GetPrescriptionAdherenceCalendar value) {
        return new JAXBElement<GetPrescriptionAdherenceCalendar>(_GetPrescriptionAdherenceCalendar_QNAME, GetPrescriptionAdherenceCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrescriptionAdherenceCalendarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPrescriptionAdherenceCalendarResponse")
    public JAXBElement<GetPrescriptionAdherenceCalendarResponse> createGetPrescriptionAdherenceCalendarResponse(GetPrescriptionAdherenceCalendarResponse value) {
        return new JAXBElement<GetPrescriptionAdherenceCalendarResponse>(_GetPrescriptionAdherenceCalendarResponse_QNAME, GetPrescriptionAdherenceCalendarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDispenseAdherenceCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetDispenseAdherenceCalendar")
    public JAXBElement<GetDispenseAdherenceCalendar> createGetDispenseAdherenceCalendar(GetDispenseAdherenceCalendar value) {
        return new JAXBElement<GetDispenseAdherenceCalendar>(_GetDispenseAdherenceCalendar_QNAME, GetDispenseAdherenceCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDispenseAdherenceCalendarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetDispenseAdherenceCalendarResponse")
    public JAXBElement<GetDispenseAdherenceCalendarResponse> createGetDispenseAdherenceCalendarResponse(GetDispenseAdherenceCalendarResponse value) {
        return new JAXBElement<GetDispenseAdherenceCalendarResponse>(_GetDispenseAdherenceCalendarResponse_QNAME, GetDispenseAdherenceCalendarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientAdherenceCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPatientAdherenceCalendar")
    public JAXBElement<GetPatientAdherenceCalendar> createGetPatientAdherenceCalendar(GetPatientAdherenceCalendar value) {
        return new JAXBElement<GetPatientAdherenceCalendar>(_GetPatientAdherenceCalendar_QNAME, GetPatientAdherenceCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientAdherenceCalendarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfile", name = "GetPatientAdherenceCalendarResponse")
    public JAXBElement<GetPatientAdherenceCalendarResponse> createGetPatientAdherenceCalendarResponse(GetPatientAdherenceCalendarResponse value) {
        return new JAXBElement<GetPatientAdherenceCalendarResponse>(_GetPatientAdherenceCalendarResponse_QNAME, GetPatientAdherenceCalendarResponse.class, null, value);
    }

}
