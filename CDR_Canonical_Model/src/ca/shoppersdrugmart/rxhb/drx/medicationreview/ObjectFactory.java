
package ca.shoppersdrugmart.rxhb.drx.medicationreview;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.medicationreview package. 
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

    private final static QName _GetMedicationReviewRequest_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationReview", "GetMedicationReviewRequest");
    private final static QName _GetMedicationReviewResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationReview", "GetMedicationReviewResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.medicationreview
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMedicationReviewRequest }
     * 
     */
    public GetMedicationReviewRequest createGetMedicationReviewRequest() {
        return new GetMedicationReviewRequest();
    }

    /**
     * Create an instance of {@link GetMedicationReviewResponse }
     * 
     */
    public GetMedicationReviewResponse createGetMedicationReviewResponse() {
        return new GetMedicationReviewResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMedicationReviewRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationReview", name = "GetMedicationReviewRequest")
    public JAXBElement<GetMedicationReviewRequest> createGetMedicationReviewRequest(GetMedicationReviewRequest value) {
        return new JAXBElement<GetMedicationReviewRequest>(_GetMedicationReviewRequest_QNAME, GetMedicationReviewRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMedicationReviewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationReview", name = "GetMedicationReviewResponse")
    public JAXBElement<GetMedicationReviewResponse> createGetMedicationReviewResponse(GetMedicationReviewResponse value) {
        return new JAXBElement<GetMedicationReviewResponse>(_GetMedicationReviewResponse_QNAME, GetMedicationReviewResponse.class, null, value);
    }

}
