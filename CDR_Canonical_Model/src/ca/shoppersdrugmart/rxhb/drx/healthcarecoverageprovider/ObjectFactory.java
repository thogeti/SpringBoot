
package ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider package. 
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

    private final static QName _GetMasterCardList_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", "GetMasterCardList");
    private final static QName _GetCoverageCard_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", "GetCoverageCard");
    private final static QName _GetMasterCardListResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", "GetMasterCardListResponse");
    private final static QName _GetCoverageCardResponse_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", "GetCoverageCardResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCoverageCardResponse }
     * 
     */
    public GetCoverageCardResponse createGetCoverageCardResponse() {
        return new GetCoverageCardResponse();
    }

    /**
     * Create an instance of {@link GetMasterCardList }
     * 
     */
    public GetMasterCardList createGetMasterCardList() {
        return new GetMasterCardList();
    }

    /**
     * Create an instance of {@link GetMasterCardListResponse }
     * 
     */
    public GetMasterCardListResponse createGetMasterCardListResponse() {
        return new GetMasterCardListResponse();
    }

    /**
     * Create an instance of {@link GetCoverageCard }
     * 
     */
    public GetCoverageCard createGetCoverageCard() {
        return new GetCoverageCard();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMasterCardList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", name = "GetMasterCardList")
    public JAXBElement<GetMasterCardList> createGetMasterCardList(GetMasterCardList value) {
        return new JAXBElement<GetMasterCardList>(_GetMasterCardList_QNAME, GetMasterCardList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCoverageCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", name = "GetCoverageCard")
    public JAXBElement<GetCoverageCard> createGetCoverageCard(GetCoverageCard value) {
        return new JAXBElement<GetCoverageCard>(_GetCoverageCard_QNAME, GetCoverageCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMasterCardListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", name = "GetMasterCardListResponse")
    public JAXBElement<GetMasterCardListResponse> createGetMasterCardListResponse(GetMasterCardListResponse value) {
        return new JAXBElement<GetMasterCardListResponse>(_GetMasterCardListResponse_QNAME, GetMasterCardListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCoverageCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/HealthCareCoverageProvider", name = "GetCoverageCardResponse")
    public JAXBElement<GetCoverageCardResponse> createGetCoverageCardResponse(GetCoverageCardResponse value) {
        return new JAXBElement<GetCoverageCardResponse>(_GetCoverageCardResponse_QNAME, GetCoverageCardResponse.class, null, value);
    }

}
