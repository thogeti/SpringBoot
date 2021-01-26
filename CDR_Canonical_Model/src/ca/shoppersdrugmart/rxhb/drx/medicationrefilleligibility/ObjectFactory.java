
package ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibility;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibility package. 
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

    private final static QName _GetPatientAdherenceEligibility_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibility", "GetPatientAdherenceEligibility");
    private final static QName _GetPatientAdherenceEligibilityResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibility", "GetPatientAdherenceEligibilityResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibility
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPatientAdherenceEligibility }
     * 
     */
    public GetPatientAdherenceEligibility createGetPatientAdherenceEligibility() {
        return new GetPatientAdherenceEligibility();
    }

    /**
     * Create an instance of {@link GetPatientAdherenceEligibilityResponse }
     * 
     */
    public GetPatientAdherenceEligibilityResponse createGetPatientAdherenceEligibilityResponse() {
        return new GetPatientAdherenceEligibilityResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientAdherenceEligibility }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibility", name = "GetPatientAdherenceEligibility")
    public JAXBElement<GetPatientAdherenceEligibility> createGetPatientAdherenceEligibility(GetPatientAdherenceEligibility value) {
        return new JAXBElement<GetPatientAdherenceEligibility>(_GetPatientAdherenceEligibility_QNAME, GetPatientAdherenceEligibility.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientAdherenceEligibilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibility", name = "GetPatientAdherenceEligibilityResponse")
    public JAXBElement<GetPatientAdherenceEligibilityResponse> createGetPatientAdherenceEligibilityResponse(GetPatientAdherenceEligibilityResponse value) {
        return new JAXBElement<GetPatientAdherenceEligibilityResponse>(_GetPatientAdherenceEligibilityResponse_QNAME, GetPatientAdherenceEligibilityResponse.class, null, value);
    }

}
