
package ca.shoppersdrugmart.rxhb.drx.medicationprofileservice;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8-b13937
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MedicationProfileService", targetNamespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfileService", wsdlLocation = "/wsdls/MedicationProfile.wsdl")
public class MedicationProfileService
    extends Service
{

    private final static URL MEDICATIONPROFILESERVICE_WSDL_LOCATION;
    private final static WebServiceException MEDICATIONPROFILESERVICE_EXCEPTION;
    private final static QName MEDICATIONPROFILESERVICE_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfileService", "MedicationProfileService");

    static {
        MEDICATIONPROFILESERVICE_WSDL_LOCATION = ca.shoppersdrugmart.rxhb.drx.medicationprofileservice.MedicationProfileService.class.getResource("/wsdls/MedicationProfile.wsdl");
        WebServiceException e = null;
        if (MEDICATIONPROFILESERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdls/MedicationProfile.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        MEDICATIONPROFILESERVICE_EXCEPTION = e;
    }

    public MedicationProfileService() {
        super(__getWsdlLocation(), MEDICATIONPROFILESERVICE_QNAME);
    }

    public MedicationProfileService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MEDICATIONPROFILESERVICE_QNAME, features);
    }

    public MedicationProfileService(URL wsdlLocation) {
        super(wsdlLocation, MEDICATIONPROFILESERVICE_QNAME);
    }

    public MedicationProfileService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MEDICATIONPROFILESERVICE_QNAME, features);
    }

    public MedicationProfileService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MedicationProfileService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MedicationProfile
     */
    @WebEndpoint(name = "MedicationProfile")
    public MedicationProfile getMedicationProfile() {
        return super.getPort(new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfileService", "MedicationProfile"), MedicationProfile.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MedicationProfile
     */
    @WebEndpoint(name = "MedicationProfile")
    public MedicationProfile getMedicationProfile(WebServiceFeature... features) {
        return super.getPort(new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationProfileService", "MedicationProfile"), MedicationProfile.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MEDICATIONPROFILESERVICE_EXCEPTION!= null) {
            throw MEDICATIONPROFILESERVICE_EXCEPTION;
        }
        return MEDICATIONPROFILESERVICE_WSDL_LOCATION;
    }

}
