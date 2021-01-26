
package ca.shoppersdrugmart.message.errorxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This is the header information for the error which is applicable to all error messages thrown.
 * 
 * <p>Java class for ErrorHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DestinationApp" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}DestinationApp" minOccurs="0"/>
 *         &lt;element name="DirectionType" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}DirectionType" minOccurs="0"/>
 *         &lt;element name="Encoding" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}Encoding" minOccurs="0"/>
 *         &lt;element name="Identifier" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}Identifier"/>
 *         &lt;element name="InputFileName" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}InputFileName" minOccurs="0"/>
 *         &lt;element name="MediaType" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}MediaType" minOccurs="0"/>
 *         &lt;element name="MessageProcessor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageType" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}MessageType"/>
 *         &lt;element name="ProcessingComponent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RelatedErrorID" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}RelatedErrorID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ServiceName" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}ServiceName" minOccurs="0"/>
 *         &lt;element name="Severity" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}Severity" minOccurs="0"/>
 *         &lt;element name="SourceApp" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}SourceApp" minOccurs="0"/>
 *         &lt;element name="State" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}State" minOccurs="0"/>
 *         &lt;element name="TimeStamp" type="{http://www.shoppersdrugmart.ca/message/EnterpriseGlossary}ErrorHeaderTimeStamp"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorHeader", propOrder = {
    "destinationApp",
    "directionType",
    "encoding",
    "identifier",
    "inputFileName",
    "mediaType",
    "messageProcessor",
    "messageType",
    "processingComponent",
    "relatedErrorID",
    "serviceName",
    "severity",
    "sourceApp",
    "state",
    "timeStamp"
})
public class ErrorHeader {

    @XmlElement(name = "DestinationApp")
    protected String destinationApp;
    @XmlElement(name = "DirectionType")
    protected String directionType;
    @XmlElement(name = "Encoding")
    protected String encoding;
    @XmlElement(name = "Identifier", required = true)
    protected String identifier;
    @XmlElement(name = "InputFileName")
    protected String inputFileName;
    @XmlElement(name = "MediaType")
    protected String mediaType;
    @XmlElement(name = "MessageProcessor")
    protected String messageProcessor;
    @XmlElement(name = "MessageType", required = true)
    protected String messageType;
    @XmlElement(name = "ProcessingComponent")
    protected String processingComponent;
    @XmlElement(name = "RelatedErrorID")
    protected List<String> relatedErrorID;
    @XmlElement(name = "ServiceName")
    protected String serviceName;
    @XmlElement(name = "Severity")
    protected String severity;
    @XmlElement(name = "SourceApp")
    protected String sourceApp;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "TimeStamp", required = true)
    protected XMLGregorianCalendar timeStamp;

    /**
     * Gets the value of the destinationApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationApp() {
        return destinationApp;
    }

    /**
     * Sets the value of the destinationApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationApp(String value) {
        this.destinationApp = value;
    }

    /**
     * Gets the value of the directionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectionType() {
        return directionType;
    }

    /**
     * Sets the value of the directionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectionType(String value) {
        this.directionType = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the inputFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputFileName() {
        return inputFileName;
    }

    /**
     * Sets the value of the inputFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputFileName(String value) {
        this.inputFileName = value;
    }

    /**
     * Gets the value of the mediaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Sets the value of the mediaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaType(String value) {
        this.mediaType = value;
    }

    /**
     * Gets the value of the messageProcessor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageProcessor() {
        return messageProcessor;
    }

    /**
     * Sets the value of the messageProcessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageProcessor(String value) {
        this.messageProcessor = value;
    }

    /**
     * Gets the value of the messageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the value of the messageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageType(String value) {
        this.messageType = value;
    }

    /**
     * Gets the value of the processingComponent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingComponent() {
        return processingComponent;
    }

    /**
     * Sets the value of the processingComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingComponent(String value) {
        this.processingComponent = value;
    }

    /**
     * Gets the value of the relatedErrorID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedErrorID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedErrorID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRelatedErrorID() {
        if (relatedErrorID == null) {
            relatedErrorID = new ArrayList<String>();
        }
        return this.relatedErrorID;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * Gets the value of the sourceApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceApp() {
        return sourceApp;
    }

    /**
     * Sets the value of the sourceApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceApp(String value) {
        this.sourceApp = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

}
