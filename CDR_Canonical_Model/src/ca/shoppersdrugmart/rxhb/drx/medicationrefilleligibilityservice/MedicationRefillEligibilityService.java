
package ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibilityservice;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150616.1732
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MedicationRefillEligibilityService", targetNamespace = "http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibilityService", wsdlLocation = "/wsdls/MedicationRefillEligibility.wsdl")
public class MedicationRefillEligibilityService
    extends Service
{

    private final static URL MEDICATIONREFILLELIGIBILITYSERVICE_WSDL_LOCATION;
    private final static WebServiceException MEDICATIONREFILLELIGIBILITYSERVICE_EXCEPTION;
    private final static QName MEDICATIONREFILLELIGIBILITYSERVICE_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibilityService", "MedicationRefillEligibilityService");

    static {
        MEDICATIONREFILLELIGIBILITYSERVICE_WSDL_LOCATION = ca.shoppersdrugmart.rxhb.drx.medicationrefilleligibilityservice.MedicationRefillEligibilityService.class.getResource("/wsdls/MedicationRefillEligibility.wsdl");
        WebServiceException e = null;
        if (MEDICATIONREFILLELIGIBILITYSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdls/MedicationRefillEligibility.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        MEDICATIONREFILLELIGIBILITYSERVICE_EXCEPTION = e;
    }

    public MedicationRefillEligibilityService() {
        super(__getWsdlLocation(), MEDICATIONREFILLELIGIBILITYSERVICE_QNAME);
    }

    public MedicationRefillEligibilityService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MEDICATIONREFILLELIGIBILITYSERVICE_QNAME, features);
    }

    public MedicationRefillEligibilityService(URL wsdlLocation) {
        super(wsdlLocation, MEDICATIONREFILLELIGIBILITYSERVICE_QNAME);
    }

    public MedicationRefillEligibilityService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MEDICATIONREFILLELIGIBILITYSERVICE_QNAME, features);
    }

    public MedicationRefillEligibilityService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MedicationRefillEligibilityService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MedicationRefillEligibility
     */
    @WebEndpoint(name = "MedicationRefillEligibility")
    public MedicationRefillEligibility getMedicationRefillEligibility() {
        return super.getPort(new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibilityService", "MedicationRefillEligibility"), MedicationRefillEligibility.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MedicationRefillEligibility
     */
    @WebEndpoint(name = "MedicationRefillEligibility")
    public MedicationRefillEligibility getMedicationRefillEligibility(WebServiceFeature... features) {
        return super.getPort(new QName("http://shoppersdrugmart.ca/RxHB/DRx/MedicationRefillEligibilityService", "MedicationRefillEligibility"), MedicationRefillEligibility.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MEDICATIONREFILLELIGIBILITYSERVICE_EXCEPTION!= null) {
            throw MEDICATIONREFILLELIGIBILITYSERVICE_EXCEPTION;
        }
        return MEDICATIONREFILLELIGIBILITYSERVICE_WSDL_LOCATION;
    }

}
