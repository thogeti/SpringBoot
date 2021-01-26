
package ca.shoppersdrugmart.rxhb.drx.cse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.shoppersdrugmart.rxhb.drx.cse package. 
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

    private final static QName _Preference_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "Preference");
    private final static QName _QueryCriteria_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "QueryCriteria");
    private final static QName _CoverageCard_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "CoverageCard");
    private final static QName _CalendarReminder_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "CalendarReminder");
    private final static QName _RxRefillWindow_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "RxRefillWindow");
    private final static QName _Customer_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "Customer");
    private final static QName _AdherenceCalendar_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "adherenceCalendar");
    private final static QName _ReminderDate_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "ReminderDate");
    private final static QName _RefillRequest_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "RefillRequest");
    private final static QName _UpdatePreferenceEntityBySourceChannel_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "UpdatePreferenceEntityBySourceChannel");
    private final static QName _Fault_QNAME = new QName("http://shoppersdrugmart.ca/RxHB/DRx/CSE", "Fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.shoppersdrugmart.rxhb.drx.cse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Preference }
     * 
     */
    public Preference createPreference() {
        return new Preference();
    }

    /**
     * Create an instance of {@link QueryCriteria }
     * 
     */
    public QueryCriteria createQueryCriteria() {
        return new QueryCriteria();
    }

    /**
     * Create an instance of {@link CoverageCard }
     * 
     */
    public CoverageCard createCoverageCard() {
        return new CoverageCard();
    }

    /**
     * Create an instance of {@link CalendarReminder }
     * 
     */
    public CalendarReminder createCalendarReminder() {
        return new CalendarReminder();
    }

    /**
     * Create an instance of {@link RxRefillWindow }
     * 
     */
    public RxRefillWindow createRxRefillWindow() {
        return new RxRefillWindow();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link AdherenceCalendar }
     * 
     */
    public AdherenceCalendar createAdherenceCalendar() {
        return new AdherenceCalendar();
    }

    /**
     * Create an instance of {@link ReminderDate }
     * 
     */
    public ReminderDate createReminderDate() {
        return new ReminderDate();
    }

    /**
     * Create an instance of {@link RefillRequest }
     * 
     */
    public RefillRequest createRefillRequest() {
        return new RefillRequest();
    }

    /**
     * Create an instance of {@link UpdatePreferenceEntityBySourceChannel }
     * 
     */
    public UpdatePreferenceEntityBySourceChannel createUpdatePreferenceEntityBySourceChannel() {
        return new UpdatePreferenceEntityBySourceChannel();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Preference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "Preference")
    public JAXBElement<Preference> createPreference(Preference value) {
        return new JAXBElement<Preference>(_Preference_QNAME, Preference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "QueryCriteria")
    public JAXBElement<QueryCriteria> createQueryCriteria(QueryCriteria value) {
        return new JAXBElement<QueryCriteria>(_QueryCriteria_QNAME, QueryCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoverageCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "CoverageCard")
    public JAXBElement<CoverageCard> createCoverageCard(CoverageCard value) {
        return new JAXBElement<CoverageCard>(_CoverageCard_QNAME, CoverageCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarReminder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "CalendarReminder")
    public JAXBElement<CalendarReminder> createCalendarReminder(CalendarReminder value) {
        return new JAXBElement<CalendarReminder>(_CalendarReminder_QNAME, CalendarReminder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RxRefillWindow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "RxRefillWindow")
    public JAXBElement<RxRefillWindow> createRxRefillWindow(RxRefillWindow value) {
        return new JAXBElement<RxRefillWindow>(_RxRefillWindow_QNAME, RxRefillWindow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "Customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdherenceCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "adherenceCalendar")
    public JAXBElement<AdherenceCalendar> createAdherenceCalendar(AdherenceCalendar value) {
        return new JAXBElement<AdherenceCalendar>(_AdherenceCalendar_QNAME, AdherenceCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReminderDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "ReminderDate")
    public JAXBElement<ReminderDate> createReminderDate(ReminderDate value) {
        return new JAXBElement<ReminderDate>(_ReminderDate_QNAME, ReminderDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefillRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "RefillRequest")
    public JAXBElement<RefillRequest> createRefillRequest(RefillRequest value) {
        return new JAXBElement<RefillRequest>(_RefillRequest_QNAME, RefillRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePreferenceEntityBySourceChannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "UpdatePreferenceEntityBySourceChannel")
    public JAXBElement<UpdatePreferenceEntityBySourceChannel> createUpdatePreferenceEntityBySourceChannel(UpdatePreferenceEntityBySourceChannel value) {
        return new JAXBElement<UpdatePreferenceEntityBySourceChannel>(_UpdatePreferenceEntityBySourceChannel_QNAME, UpdatePreferenceEntityBySourceChannel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shoppersdrugmart.ca/RxHB/DRx/CSE", name = "Fault")
    public JAXBElement<Fault> createFault(Fault value) {
        return new JAXBElement<Fault>(_Fault_QNAME, Fault.class, null, value);
    }

}
