package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_NOSUBRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PRSCBRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RNWLRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXHLDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXRFSLRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXRSMRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RXSRCETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STRNGTHUOMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_SUBINITRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TRTMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXSUBDCLNDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_GETPROVKEY;//TE97_024

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.subscription.SubscriptionAPI;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.bean.UpsertResponse;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations.NoteTypeTable;
import ca.shoppersdrugmart.cdr.cdrcommoneventpersist.SourceSystem;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.Prescriber;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.PrescriptionHoldReasonTypeDisplay;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.pharmacycentralevent.PharmacyBusinessEventEnum;
import generated.RxHBBusinessEventActionEnum;

/**
 * Prescription API used to persist Prescription entity.
 * 
 * @author LTrevino
 *
 */
public class PrescriptionApi extends CDRUpsert {

	private static Logger logger = LogManager.getLogger(PrescriptionApi.class);
	  Map <String, Object> drgAherance = new HashMap<String, Object>();
	// update 20200330 Started
	   Map <String, Long> packPK = new HashMap<String, Long>();
	   Map <String, Timestamp> packTime = new HashMap<String, Timestamp>();

	   Map <String, Long> drgPK = new HashMap<String, Long>();
	   Map <String, Timestamp> drgTime = new HashMap<String, Timestamp>();
	   // update 20200330 Ended
	 
//TE97_024   TE97_Drop53 start
	private final static String INSERTSQL = 
			"INSERT INTO RX (" +
					"UPDTTIMESTAMP	,	ISATHRTV			,	CNSMRID			,	PRDCRID					,	QTYTXD				, "	+
					"RMNGQTY		,	RFLSRMNGCNT			,	TOTALQTYATHRZD	,	DRGTOTALDAYSOFSPLYCNT	,	DRGTRLDAYSOFSPLYCNT ,"	+
					"DRGTRLFLG		,	DRGTRLQTY			,	INFRDRXFLG		,	NUMOFRFLS				, 	RXDT 				, " +
					"RXEXPRTNDT		,	SIGADDTNLINFRMATION	,	SIGDESCRPTNTLANG,	RXDSPNSBLFLG			, 	LEGACYFLG			, "	+
					"HLDENDDT		,	CRTTIMESTAMP		,	QTYRXD			,	SVCLOCKEY				, 	PRSCBRKEY			,"	+

			"LINKEDRXKEY	,	RCRDRKEY			,	SPRVSRKEY		,	BATCHFLG	,	RXID			,"	+
			"RNWLRSNTYPKEY	,	TXSUBDCLNDRSNTYPKEY	,	NOSUBRSNTYPKEY	, 	PRSCBRTYPKEY,	RXHLDRSNTYPKEY	,"	+
			"RXRFSLRSNTYPKEY,	RXRSMRSNTYPKEY		,	RXSRCETYPKEY	, 	SIGCD		,	STRNGTHUOMTYPKEY,"	+
			"SUBINITRTYPKEY	,	TRTMNTTYPKEY		,	CMPNDKEY		, 	DRGKEY		,	PACKKEY			,"	+
			"PTNTKEY		,	" + /* PRSNKEY 			, "	+ */ 		
			"RXNUM 			,	STORENUM 			," +
			"RXKEY ,ADMINSTARTDATE  ,  ADMINSTOPDATE ,PRESCRIBERADDRKEY" + 
			") VALUES (" +
			CommonUtil.getPsToDateFunctionStr() + "	,	?	,	?	,	?	,	?	,	" +
			"	?			,	?	,	?	,	?	,	? 	, 	" +
			"	?			,	?	,	?	,	?	,	" + CommonUtil.getPsToDateFunctionStr() + " , " + 	
			CommonUtil.getPsToDateFunctionStr() + "	, 	?	, 	?	, 	?	,	?	, "  +
			CommonUtil.getPsToDateFunctionStr() + "	, 	" + 	CommonUtil.getPsToDateFunctionStr() + ",	?	,	?,	?	,	" +
			"	?			,	?	, 	?	,	?	,	?	,	" +
			"	?			,	?	, 	?	,	?	,	?	,	" +
			"	?			,	?	, 	?	,	?	,	?	,	" +
			"	?			,	?	, 	?	,	?	,	?	,	" +
			"	?			,	" + /* ?	, " + */ 
			"	?			,	?	, " +
			"	?,"+CommonUtil.getPsToDateFunctionStr()+","+CommonUtil.getPsToDateFunctionStr() +
			",?)";


/*	private final static String UPDATESQL = 
			"UPDATE RX SET " +
					"UPDTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ",	ISATHRTV=?,						CNSMRID=?,						PRDCRID=?,					QTYTXD=?, "	+
					//		"RMNGQTY=?,							RFLSRMNGCNT=?,					TOTALQTYATHRZD=?,				DRGTOTALDAYSOFSPLYCNT=?,	DRGTRLDAYSOFSPLYCNT=? ,"	+
					"RMNGQTY= NVL(?,RMNGQTY),			RFLSRMNGCNT= NVL(?,RFLSRMNGCNT),	TOTALQTYATHRZD=?,				DRGTOTALDAYSOFSPLYCNT=?,	DRGTRLDAYSOFSPLYCNT=? ,"	+
					"DRGTRLFLG=?,						DRGTRLQTY=?,					INFRDRXFLG=?,					NUMOFRFLS=?, 				RXDT=NVL(" + CommonUtil.getPsToDateFunctionStr() 	+ ",RXDT) , " +
					"RXEXPRTNDT=" + CommonUtil.getPsToDateFunctionStr() + ",		SIGADDTNLINFRMATION=?,			SIGDESCRPTNTLANG=?,				RXDSPNSBLFLG=?, 			LEGACYFLG=?,"	+
					"HLDENDDT=" + CommonUtil.getPsToDateFunctionStr() + ",			CRTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ",	QTYRXD=?,						SVCLOCKEY=?, 				PRSCBRKEY=?,"	+

		"LINKEDRXKEY=?,			RCRDRKEY=?,						SPRVSRKEY=?,			BATCHFLG=?,			RXID=?,"	+
		"RNWLRSNTYPKEY=?,		TXSUBDCLNDRSNTYPKEY=?,			NOSUBRSNTYPKEY=?, 		PRSCBRTYPKEY=?,		RXHLDRSNTYPKEY=?,"	+
		"RXRFSLRSNTYPKEY=?,		RXRSMRSNTYPKEY=?,				RXSRCETYPKEY=?, 		SIGCD=?,			STRNGTHUOMTYPKEY=?,"	+
		"SUBINITRTYPKEY=?,		TRTMNTTYPKEY=?,			CMPNDKEY= NVL(?,CMPNDKEY), 		DRGKEY= NVL(?,DRGKEY),			PACKKEY=decode(packkey, null, NVL(?,PACKKEY), packkey) ,"	+			
		"PTNTKEY= ? ,ADMINSTARTDATE=" + CommonUtil.getPsToDateFunctionStr() + "  ,  ADMINSTOPDATE= "+ CommonUtil.getPsToDateFunctionStr() +"	" +  PRSNKEY=? "	+  		
		",PRESCRIBERADDRKEY=?  WHERE RXKEY=?";
	*/
	//LTPHCP-67  changes Null for missing entities (Pack @ Drug) PraveenT
	private final static String UPDATESQL = 
			"UPDATE RX SET " +
			"UPDTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ",	"+
			"ISATHRTV=?, "+
			"CNSMRID=?, "+
			"PRDCRID=?, "+
			"QTYTXD=?, "+
			"RMNGQTY= NVL(?,RMNGQTY), "+
			"RFLSRMNGCNT= NVL(?,RFLSRMNGCNT), "+
			"TOTALQTYATHRZD=?, "+
			"DRGTOTALDAYSOFSPLYCNT=?, "+
			"DRGTRLDAYSOFSPLYCNT=? , "+
			"DRGTRLFLG=?, "+
			"DRGTRLQTY=?, "+
			"INFRDRXFLG=?, "+
			"NUMOFRFLS=?, "+
			"RXDT=NVL(" + CommonUtil.getPsToDateFunctionStr() 	+ ",RXDT) , "+
			"RXEXPRTNDT=" + CommonUtil.getPsToDateFunctionStr() + ", "+
			"SIGADDTNLINFRMATION=?, "+
			"SIGDESCRPTNTLANG=?, "+
			"RXDSPNSBLFLG=?, "+
			"LEGACYFLG=?, "+
			"HLDENDDT=" + CommonUtil.getPsToDateFunctionStr() + ", "+
			"CRTTIMESTAMP=NVL(" + CommonUtil.getPsToDateFunctionStr() + ",CRTTIMESTAMP ) ,"+
			"QTYRXD=?, "+
			
			" SVCLOCKEY=nvl(?, SVCLOCKEY) , "+			
			"PRSCBRKEY=nvl(?, PRSCBRKEY) , "+		
		"LINKEDRXKEY=nvl(?, LINKEDRXKEY) , "+		
		"RCRDRKEY=nvl(?, RCRDRKEY) , "+
		"SPRVSRKEY=nvl(?, SPRVSRKEY) , "+
		"BATCHFLG=?, "+
		"RXID=?, "+
		"RNWLRSNTYPKEY=nvl(?, RNWLRSNTYPKEY) , "+
		"TXSUBDCLNDRSNTYPKEY=nvl(?, TXSUBDCLNDRSNTYPKEY) , "+
		"NOSUBRSNTYPKEY=nvl(?, NOSUBRSNTYPKEY) , "+
		"PRSCBRTYPKEY=nvl(?, PRSCBRTYPKEY) , "+
		"RXHLDRSNTYPKEY=nvl(?, RXHLDRSNTYPKEY) , "+
		"RXRFSLRSNTYPKEY=nvl(?, RXRFSLRSNTYPKEY) , "+
		"RXRSMRSNTYPKEY=nvl(?, RXRSMRSNTYPKEY) , "+
		"RXSRCETYPKEY=nvl(?, RXSRCETYPKEY) , "+
		"SIGCD=?, "+
		"STRNGTHUOMTYPKEY=nvl(?, STRNGTHUOMTYPKEY) , "+
		"SUBINITRTYPKEY=nvl(?, SUBINITRTYPKEY) , "+
		"TRTMNTTYPKEY=nvl(?, TRTMNTTYPKEY) , "+
		"CMPNDKEY= NVL(?,CMPNDKEY), DRGKEY= NVL(?,DRGKEY), "+
		"PACKKEY=nvl(?, PACKKEY) , "+		
		"PTNTKEY=nvl(?, PTNTKEY) , "+		
		"ADMINSTARTDATE=" + CommonUtil.getPsToDateFunctionStr() + "  , "+
		"ADMINSTOPDATE= "+ CommonUtil.getPsToDateFunctionStr() +"  , "+ 
		"PRESCRIBERADDRKEY=nvl(?, PRESCRIBERADDRKEY)"+
		"  WHERE RXKEY=?";


	
//	TE97_Drop53 end
	
	
	//TE97_FIX
	private final static String GETSQL_V1="select s.SRCESYSCD from RXSUBSCRPTNSRCESYS r,SRCESYS s "
			+" where r.SRCESYSKEY=s.SRCESYSKEY and rxkey=? union select s.SRCESYSCD from "
			+ "	PTNTSUBSCRPTNSRCESYS p, SRCESYS s where p.SRCESYSKEY=s.SRCESYSKEY and "
			+ " p.ptntkey=(select ptntkey from rx where rxkey = ?)";
//TE97_FIX

private final static String GETLINKEDRX_SQL_V1="select r2.rxnum from rx r1,rx r2 where   r1.rxkey = ?   "
+ " and r1.rxkey = r2.linkedrxkey  and r2.rxnum is not null  order by r2.rxnum desc fetch first 20 rows only";
	
	private final static String GETCRXDRUGSQL  ="select STRENTH,STRENTHUOM,ADHERANCECATEGORY,REFILLREMINDERFLAG,PICKUPREMINDERFLAG,AUTOREFILLFLAG,TRRFLAG "
			+ " from CRX_DRGADHERANCE WHERE ltrim(GTIN,'0') = ltrim(?,'0') AND PROVKEY = ? ";

	/**
	 * 
	 * @param prescription
	 * @param store
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws NamingException
	 * @throws CDRException 
	 * @throws JAXBException 
	 */

	public UpsertResponse upsert(Connection connection, Prescription prescription, String storeNumber,String eventName) throws SQLException, IOException, NamingException, CDRException, JAXBException {
		UpsertResponse upsertResponse;
		try {
			if(prescription!=null)
				if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsert. store number: " + storeNumber + ", prescription number: " + prescription.getPrescriptionNumber());}
			upsertResponse = upsertPrescription(connection, storeNumber, prescription,eventName);
			return upsertResponse;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRException e) {
			e.printStackTrace();
			throw e;
		} finally
		{
			super.close();
			if(prescription!=null)
				if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsert. store number: " + storeNumber + ", prescription number: " + prescription.getPrescriptionNumber());}
		}
	}

	/**
	 * Attempt to persist a Prescription instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Prescription doesn't exist, then insert a new record.
	 * If the Prescription exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param prescription	Prescription instance.
	 * 
	 * @return				PrescriptionKey value.
	 * 
	 * @throws SQLException
	 * @throws  
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 * @throws JAXBException 
	 */
	//TE97 Added for LinkedRx Subscription start
	private UpsertResponse upsertPrescription(Connection connection, String storeNumber, Prescription prescription,String eventName) throws SQLException, IOException, NamingException, CDRException, JAXBException {
		if (prescription==null) {
			throw new InvalidInputException("Precription object cannot be null.");
		}

		if (storeNumber== null ||  StringUtil.isEmpty(storeNumber)) {
			throw new InvalidInputException("Store Number not provided in Rx.");
		}

		Integer prescriptionNumber = prescription.getPrescriptionNumber() ;
		if ( prescriptionNumber == null || StringUtil.isEmpty(String.valueOf(prescriptionNumber))) {
			throw new InvalidInputException("Precription Number not provided for Prescription[CID: " + prescription.getConsumerId() + ", storeNumber: " + storeNumber + "]");
		}

		String patientConsumerId = (prescription.getPatient()!=null) ? prescription.getPatient().getConsumerId() : null;
		if (patientConsumerId==null) {
			throw new InvalidInputException("Patient's ConsumerId not provided.");
		}

		Long patientKey = (patientConsumerId!=null && !"0".equals(patientConsumerId)) ? FindUtil.findPatientKey(connection, patientConsumerId, storeNumber) : null;
		if( patientKey == null  )
		{
			throw new PatientNotFoundException(storeNumber, patientConsumerId);
		}

		Map<String, Object> prescriptionData = FindUtil.findPrescriptionDataByRxNum(connection, prescription.getPrescriptionNumber() , storeNumber );
		Long prescriptionKey = (Long) prescriptionData.get("RXKEY");
		Timestamp updateTimestampDb = (Timestamp) prescriptionData.get("UPDTTIMESTAMP");
//      2020-07-03  Started				
//		boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, prescription.getUpdateTimestamp());
		boolean isUpdateRequestNew = newRequest(updateTimestampDb, prescription.getUpdateTimestamp());
//      2020-07-03  Ended		

		// Recursive call to persist Linked Prescription
		Integer linkedRxPrescriptionNumber = (prescription.getLinkedrx()!=null) ? prescription.getLinkedrx().getPrescriptionNumber() : null;
		Long linkedRxKey = null;

		if (linkedRxPrescriptionNumber!= null && linkedRxPrescriptionNumber >0 ) 
		{
			linkedRxKey = FindUtil.findPrescriptionKeyByRxNum(connection, linkedRxPrescriptionNumber , storeNumber) ;
		}	
		
		
		if (isUpdateRequestNew == false) 
		{
			if(logger.isDebugEnabled()) {logger.debug("Prescription lastUpdatedtimeStamp : " +  prescription.getUpdateTimestamp() + " is less than DB last updated : " + updateTimestampDb );}
			UpsertResponse upsertResponse = new UpsertResponse();
			upsertResponse.setPrescriptionKey(prescriptionKey);
			upsertResponse.setPatientKey(patientKey);
			upsertResponse.setLinkedPrescriptionKey(linkedRxKey);
			upsertResponse.setRxHBBusinessEventActionEnum(RxHBBusinessEventActionEnum.UPDATE);

			return upsertResponse;
		}

		Store store = new Store();
		store.setStorenumber(storeNumber);

		// Recursive call to persist Linked Prescription
	/*	Integer linkedRxPrescriptionNumber = (prescription.getLinkedrx()!=null) ? prescription.getLinkedrx().getPrescriptionNumber() : null;
		Long linkedRxKey = null;

		if (linkedRxPrescriptionNumber!= null && linkedRxPrescriptionNumber >0 ) 
		{
			linkedRxKey = FindUtil.findPrescriptionKeyByRxNum(connection, linkedRxPrescriptionNumber , storeNumber) ;
		}		

		if(linkedRxKey != null){
			SubscriptionAPI subscriptionAPI=new SubscriptionAPI();
			subscriptionAPI.SubscribeLinkedRx(connection, prescriptionKey, linkedRxKey);
			subscriptionAPI.SubscribeLinkedRx(connection, linkedRxKey,prescriptionKey);
			}*/
		// As per team conversation on 2016-08-18, Patient data will not be saved by PrescriptionApi module.

		String locationConsumerId = (prescription.getServiceLocation()!=null) ? prescription.getServiceLocation().getConsumerId() : null;
		Long locationKey = (locationConsumerId!=null && !"0".equals(locationConsumerId)) ? FindUtil.findLocationKey(connection, locationConsumerId, storeNumber) : null;

		Long drugKey = null;
		Drug drug = prescription.getPrescribedDrug();
		if (drug!=null) {
			DrugApi drugApi = new DrugApi();
			drugKey = drugApi.upsertDrug(connection, storeNumber, drug,drgPK ,drgTime);
		}

		Long packKey = null;
		Pack pack = prescription.getPrescribedPack();
		if (pack!=null && drug!=null && drug.getConsumerId()!=null) {
		//	HashMap<String,String> adheranceFlags = (HashMap<String, String>)getCrxDrugAdherance(connection, storeNumber, pack.getGTIN());
			PackApi packApi = new PackApi();
			packKey = packApi.upsertPack(connection, drug.getConsumerId(), storeNumber, pack,drgPK,drgTime,packPK,packTime);
		}

		Compound compound = prescription.getPrescribedCompound();
		String compoundConsumerId = (compound!=null) ? compound.getConsumerId() : null;
		Long compoundKey = null; 	
		if (compoundConsumerId!=null) {
			CompoundApi compoundApi = new CompoundApi();
		/*	// 2020-07-28 Started
			if (compound.getCompoundIngredients() != null ) {
				for (CompoundIngredient compoundIngredient : compound.getCompoundIngredients()) {
					if(compoundIngredient.getPack()!=null) {
					 Map<String,String> adheranceFlags = getCrxDrugAdherance(connection, storeNumber, compoundIngredient.getPack().getGTIN());
					 if (adheranceFlags.get("STRENTH") != null) {
						 compoundIngredient.getPack().setStrength(adheranceFlags.get("STRENTH"));
					 }
					 if (adheranceFlags.get("STRENTHUOM") != null) {
						 compoundIngredient.getPack().setStrengthUOMCode(adheranceFlags.get("STRENTHUOM"));
					 }
				}
				}
			}*/
			// 2020-07-28 Ended
			compoundKey = compoundApi.upsertCompound(connection, storeNumber, compound,drgPK,drgTime,packPK,packTime); 
			
		}

		PersonApi personApi = new PersonApi();

		Recorder recorder = prescription.getRecorder();
		Long recorderKey = null;
		if (recorder!=null) {
			PersonRole recorderPersonRole = new PersonRole (recorder);
			recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
		}
		Long prescriberKey = null;
	
		Prescriber prescriber = prescription.getPrescriber();
		if (prescriber!=null) {
			PersonRole prescriberPersonRole = new PersonRole (prescriber);
			personApi.verifyPersonExists(connection, store, prescriberPersonRole ); 
			if (newPrescriber(prescriberPersonRole.getDbUpdateTimeStamp(), prescriberPersonRole.getPayloadUpdateTimeStamp())) {
			prescriberKey = personApi.upsertPerson(connection, store, prescriberPersonRole);
			}else {
				prescriberKey = prescriberPersonRole.getPersonRoleKey();     
			}				
		}
		Long supervisorKey = null; 
		Supervisor supervisor = prescription.getSupervisor();
		if (supervisor!=null) {
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}		

		// Persist main entity		

		RxHBBusinessEventActionEnum rxHBBusinessEventActionEnum = null;
	
		if (prescriptionKey==null) {
			rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.CREATE;
			if (prescription.getCreateTimestamp()==null && prescription.getUpdateTimestamp() == null) {
				throw new InvalidInputException("Create Timestamp and Update Timestamp not populated in Prescription object.");
			}			

			if (prescription.getCreateTimestamp()==null) {
				prescription.setCreateTimestamp(prescription.getUpdateTimestamp());
			}

			if (prescription.getUpdateTimestamp()==null) {
				prescription.setUpdateTimestamp(prescription.getCreateTimestamp());
			}
			//TE97_Drop53
			prescriptionKey=insertPrescription(connection, storeNumber, prescription, patientKey, locationKey, drugKey, packKey, compoundKey, prescriberKey, recorderKey, supervisorKey, linkedRxKey,personApi.prescriberAddressKey);
		} else {
			rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.UPDATE;
			if (prescription.getUpdateTimestamp()==null) {
				throw new InvalidInputException("Update Timestamp not populated in Prescription object.");
			}
			//TE97_Drop53
			updatePrescription(connection, storeNumber, prescriptionKey, prescription, patientKey, locationKey, drugKey, packKey, compoundKey, prescriberKey, recorderKey, supervisorKey, linkedRxKey,personApi.prescriberAddressKey);
		}

		if(linkedRxKey != null){
			SubscriptionAPI subscriptionAPI=new SubscriptionAPI();
			subscriptionAPI.SubscribeLinkedRx(connection, prescriptionKey, linkedRxKey);
			subscriptionAPI.SubscribeLinkedRx(connection, linkedRxKey,prescriptionKey);
			}

		List<Note> notes = prescription.getNote();
		if (notes!=null && notes.size() > 0) {
			NoteApi noteApi = new NoteApi(NoteTypeTable.PRESCRIPTION_NOTE);
			noteApi.upsertNoteList(connection, store, notes,prescriptionKey);
		}

		//ResponsiblePerson responsiblePerson = prescription.getResponsiblePerson();
		// As per conversation with Sharooz, unlike the other roles, there's currently no responsibleKey field in Rx table. 

	
		List<Dispense> dispenseList = prescription.getDispense();
		if (dispenseList!=null && dispenseList.size() > 0) {
			DispenseApi dispenseApi = new DispenseApi();
			for (Dispense dispense : dispenseList) {
				if(dispense.getTransactionNumber()!=null && dispense.getTransactionNumber().intValue() != 0) {
					/*HashMap<String,String> adheranceFlags = new HashMap<String ,String>();
					if(dispense.getPack()!= null) {
					 Pack dispPack = dispense.getPack();
					 adheranceFlags = (HashMap<String, String>)getCrxDrugAdherance(connection, storeNumber, dispPack.getGTIN());
					}
					if(dispense.getCompound()!=null && !dispense.getCompound().getCompoundIngredients().isEmpty()) {
						for(CompoundIngredient cmpndIngredient :dispense.getCompound().getCompoundIngredients() ) {
							if(cmpndIngredient.getPack()!=null) {
								 Map<String,String> adheranceFlags1 = getCrxDrugAdherance(connection, storeNumber, cmpndIngredient.getPack().getGTIN());
								 if (adheranceFlags1.get("STRENTH") != null) {
									 cmpndIngredient.getPack().setStrength(adheranceFlags.get("STRENTH"));
								 }
								 if (adheranceFlags1.get("STRENTHUOM") != null) {
									 cmpndIngredient.getPack().setStrengthUOMCode(adheranceFlags.get("STRENTHUOM"));
								 }
							}
						}
					}*/
					dispenseApi.upsertDispense(connection, dispense, store, prescriptionKey,prescriptionNumber,eventName,drgPK,drgTime,packPK,packTime);
				}
			}	
		}

		UpsertResponse upsertResponse = new UpsertResponse();
		upsertResponse.setPrescriptionKey(prescriptionKey);
		upsertResponse.setPatientKey(patientKey);
		upsertResponse.setLinkedPrescriptionKey(linkedRxKey);
		upsertResponse.setRxHBBusinessEventActionEnum(rxHBBusinessEventActionEnum);
		
		 NotificationApi notificationApi= new NotificationApi();
		 notificationApi.upsertNotification(connection, prescriptionKey);
		return upsertResponse;
	}
	//TE97 Added for LinkedRx Subscription start

	/**
	 * Attempt to update a Prescription instance, which is matched by prescriptionKey.
	 * 
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param prescriptionKey	Prescription Key.
	 * @param prescription		Prescription instance.
	 * @param prescriberAddressKey 
	 * 
	 * @return					PrescriptionKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 *///TE97_024 for notification service
	private Long updatePrescription(Connection connection, String storeNum, Long prescriptionKey, Prescription prescription, Long patientKey, Long locationKey, 
			Long drugKey, Long packKey, Long compoundKey, Long prescriberKey, Long recorderKey, Long supervisorKey, Long linkedRxKey, Long prescriberAddressKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {

		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}

		if (logger.isTraceEnabled())  {logger.trace("updatePrescription :sqlQuey \n" + UPDATESQL );}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, prescriptionKey, prescription, patientKey, locationKey, drugKey, packKey, compoundKey, prescriberKey, recorderKey, supervisorKey, linkedRxKey , false,prescriberAddressKey);
		int res = ps.executeUpdate();
		RxStatApi rxStatApi = new RxStatApi();		
		rxStatApi.upsertRxStat(connection, prescriptionKey, prescription.getFillStatusCode(), prescription.getFillStatusEffectiveDate(),prescription.getFillStatusSubCode());
		if (logger.isInfoEnabled())  {logger.info("Total Prescription intances updated: " + res + ". PrescriptionKey: " + prescriptionKey + ", consumerId: '" + prescription.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
	//	NotificationApi notificationApi=new NotificationApi();   //fix for prod defect
	//	notificationApi.upsertNotification(connection,prescriptionKey); //fix for prod defect
		return prescriptionKey;
	}



	/**
	 * Attempt to insert a new Prescription into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param prescription			Prescription instance.
	 * @param prescriberAddressKey 
	 * 
	 * @return				PrescriptionKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long insertPrescription(Connection connection, String storeNum, Prescription prescription, Long patientKey, Long locationKey, 
			Long drugKey, Long packKey, Long compoundKey, Long prescriberKey, Long recorderKey, Long supervisorKey, Long linkedRxKey, Long prescriberAddressKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {

		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long prescriptionKey = IdGenerator.generate(connection, "RX");
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, prescriptionKey, prescription, patientKey, locationKey, drugKey, packKey, compoundKey, prescriberKey, recorderKey, supervisorKey, linkedRxKey , true ,prescriberAddressKey);
		int res = ps.executeUpdate();
		RxStatApi rxStatApi = new RxStatApi();		
		rxStatApi.upsertRxStat(connection, prescriptionKey, prescription.getFillStatusCode(), prescription.getFillStatusEffectiveDate(),prescription.getFillStatusSubCode());
		if (logger.isInfoEnabled())  {logger.info("Total Prescription intances updated: " + res + ". PrescriptionKey: " + prescriptionKey + ", consumerId: '" + prescription.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
	//	NotificationApi notificationApi=new NotificationApi();
    // notificationApi.upsertNotification(connection,prescriptionKey);
		return prescriptionKey;
	}



	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps				PreparedStatement object.
	 * @param consumerId		Consumer ID.
	 * @param storeNum			Store Number.
	 * @param prescriptionKey	Prescription Key.
	 * @param prescription		Prescription instance.
	 * @param prescriberAddressKey 
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 *///TE97_Drop53
	private void setPsParams(String storeNum, Long prescriptionKey, Prescription prescription, Long patientKey, Long locationKey, 
			Long drugKey, Long packKey, Long compoundKey, Long prescriberKey, Long recorderKey, Long supervisorKey, Long linkedRxKey , boolean isInsert, Long prescriberAddressKey ) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException {

		String updateTs = CommonUtil.toTimestampStr(prescription.getUpdateTimestamp());
		setPsStringParam(1, updateTs);
		setPsStringParam(2, CommonUtil.convertBooleanToYesNoFlag(prescription.isIsAuthoritative()));
		setPsStringParam(3, prescription.getConsumerId());
		setPsStringParam(4, prescription.getProducerId());
		Double qtyDispensed = (prescription.getQuantityDispensed()!=null) ? prescription.getQuantityDispensed().doubleValue() : null;
		setPsDoubleParam(5, qtyDispensed);
		Double remainingQty = (prescription.getRemainingQuantity()!=null) ? prescription.getRemainingQuantity().doubleValue() : null;
		setPsDoubleParam(6, remainingQty);
		Long refillsRemainingCount = (prescription.getRefillsRemainingCount()!=null) ? prescription.getRefillsRemainingCount().longValue() : null; 
		setPsLongParam(7, refillsRemainingCount);
		Double totalQuantityAuthorized = (prescription.getTotalQuantityAuthorized()!=null) ? prescription.getTotalQuantityAuthorized().doubleValue() : null;
		setPsDoubleParam(8, totalQuantityAuthorized);
		String drugTotalDaysOfSupplyCount = (prescription.getDrugTotalDaysOfSupplyCount()!=null) ? prescription.getDrugTotalDaysOfSupplyCount().toString() : null;
		setPsStringParam(9, drugTotalDaysOfSupplyCount); // varchar in db
		Long drugTrialDaysOfSupplyCount = (prescription.getDrugTrialDaysOfSupplyCount()!=null) ? prescription.getDrugTrialDaysOfSupplyCount().longValue() : null;
		setPsLongParam(10, drugTrialDaysOfSupplyCount);
		setPsStringParam(11, CommonUtil.convertBooleanToYesNoFlag(prescription.isDrugTrialFlag()));
		Long drugTrialQuantity = (prescription.getDrugTrialQuantity()!=null) ? prescription.getDrugTrialQuantity().longValue() : null;
		setPsLongParam(12, drugTrialQuantity);
		setPsStringParam(13, CommonUtil.convertBooleanToYesNoFlag(prescription.isInferredPrescriptionFlag()));
		Long numberOfRefills = (prescription.getNumberOfRefills()!=null) ? prescription.getNumberOfRefills().longValue() : null;
		setPsLongParam(14, numberOfRefills); 
		String rxDate = CommonUtil.toTimestampStr(prescription.getPrescriptionDate());
		setPsStringParam(15, rxDate);
		String rxExpirationDate = CommonUtil.toTimestampStr(prescription.getPrescriptionExpirationDate());
		setPsStringParam(16, rxExpirationDate);
		setPsStringParam(17, prescription.getSIGAdditionalInformation());
		setPsStringParam(18, prescription.getSIGDescriptionPatientLanguage());
		setPsStringParam(19, CommonUtil.convertBooleanToYesNoFlag(prescription.isPrescriptionDispensableFlag())); 
		setPsStringParam(20, CommonUtil.convertBooleanToYesNoFlag(prescription.isLegacyFlag()));
		String holdEndDate = CommonUtil.toTimestampStr(prescription.getHoldEndDate());
		setPsStringParam(21, holdEndDate);
		String createTs = CommonUtil.toTimestampStr(prescription.getCreateTimestamp());
		setPsStringParam(22, createTs);
		Double qtyPrescribed = (prescription.getQuantityPrescribed()!=null) ? prescription.getQuantityPrescribed().doubleValue() : null;
		setPsDoubleParam(23, qtyPrescribed); 
		setPsLongParam(24, locationKey);
		setPsLongParam(25, prescriberKey);		
		setPsLongParam(26, linkedRxKey);
		setPsLongParam(27, recorderKey);
		setPsLongParam(28, supervisorKey);
		setPsStringParam(29, CommonUtil.convertBooleanToYesNoFlag(prescription.isIsBatchFlag()));
		String prescriptionId = null; // not mapped
		setPsStringParam(30, prescriptionId);

		String renewalReasonCode = prescription.getRenewalReasonCode();
		Long renewalReasonKey = getKeyFromCode(LT_RNWLRSNTYP, renewalReasonCode);
		setPsLongParam(31, renewalReasonKey);

		String TxSubDclndrsnTypCode = ""; 
		Long TxSubDclndrsnTypKey = getKeyFromCode(LT_TXSUBDCLNDRSNTYP, TxSubDclndrsnTypCode);
		setPsLongParam(32, TxSubDclndrsnTypKey);

		String noSubstitutionReasonCode = prescription.getNoSubstitutionReason();
		Long noSubstitutionReasontypeKey = getKeyFromCode(LT_NOSUBRSNTYP, noSubstitutionReasonCode);
		setPsLongParam(33, noSubstitutionReasontypeKey);

		Prescriber prescriber = prescription.getPrescriber();
		String prescriberTypeCode = (prescriber!=null && prescriber.getPrescriberTypeCode()!=null) ? prescriber.getPrescriberTypeCode().value() : null;
		Long prescriberTypeKey = getKeyFromCode(LT_PRSCBRTYP, prescriberTypeCode);
		setPsLongParam(34, prescriberTypeKey);

		PrescriptionHoldReasonTypeDisplay holdReasonTypeDisplay = prescription.getHoldReasonCode();
		String holdReasonTypeCode = (holdReasonTypeDisplay!=null && holdReasonTypeDisplay.getPrescriptionHoldReasonTypeCode()!=null) ? holdReasonTypeDisplay.getPrescriptionHoldReasonTypeCode().value() : null;
		Long holdReasonTypeKey = getKeyFromCode(LT_RXHLDRSNTYP, holdReasonTypeCode);
		setPsLongParam(35, holdReasonTypeKey);

		String rxRefusalReasonTypeCode = null;  
		Long rxRefusalReasonTypeKey = getKeyFromCode(LT_RXRFSLRSNTYP, rxRefusalReasonTypeCode);
		setPsLongParam(36, rxRefusalReasonTypeKey);

		String rxResumeReasonTypeCode = (prescription.getResumeReasonCode()!=null) ? prescription.getResumeReasonCode().value() : null;
		Long rxResumeReasonTypeKey = getKeyFromCode(LT_RXRSMRSNTYP, rxResumeReasonTypeCode);
		setPsLongParam(37, rxResumeReasonTypeKey);

		String sourceTypeCode = (prescription.getSource()!=null) ? prescription.getSource().value() : null;
		Long sourceTypeKey = getKeyFromCode(LT_RXSRCETYP, sourceTypeCode);
		setPsLongParam(38, sourceTypeKey);

		String sigCode = prescription.getSIGCode();
		setPsStringParam(39, sigCode);

		String strengthUomTypCode = null; //mapping document pending
		Long strengthUomTypKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUomTypCode);
		setPsLongParam(40, strengthUomTypKey);

		String subInitTypeCode = (prescription.getSubstitutionInitiator()!=null) ? prescription.getSubstitutionInitiator().value() : null;
		Long subInitTypeKey = getKeyFromCode(LT_SUBINITRTYP, subInitTypeCode);
		setPsLongParam(41, subInitTypeKey);

		String treatmentTypeCode = (prescription.getTreatmentType()!=null) ? prescription.getTreatmentType().value() : null;
		Long treatmentTypeKey = getKeyFromCode(LT_TRTMNTTYP, treatmentTypeCode);
		setPsLongParam(42, treatmentTypeKey);

		setPsLongParam(43, compoundKey);
		setPsLongParam(44, drugKey);
		setPsLongParam(45, packKey);
		setPsLongParam(46, patientKey);
		//		Long personKey = patientKey;
		//		setPsLongParam(47, personKey); 
		String adminStartDate = CommonUtil.toDateStrWithOutTime(prescription.getAdministrationPeriodStartDate());
		String adminStopDate = CommonUtil.toDateStrWithOutTime(prescription.getAdministrationPeriodEndDate());
		

		if( isInsert == true )
		{
			String rxNumber = (prescription.getPrescriptionNumber()!=null) ? prescription.getPrescriptionNumber().toString() : null;
			setPsStringParam(47, rxNumber);
			setPsStringParam(48, storeNum);
			setPsLongParam(49, prescriptionKey);
			setPsStringParam(50, adminStartDate);
			setPsStringParam(51, adminStopDate);
			setPsLongParam(52, prescriberAddressKey);//TE97_Drop53
		}
		else
		{
			
			setPsStringParam(47, adminStartDate);
			setPsStringParam(48, adminStopDate);
			setPsLongParam(49, prescriberAddressKey);//TE97_Drop53
			setPsLongParam(50, prescriptionKey);
			
		}
		
		
	}
	
	
	//TE97 added for Subscriptions
	
	
		public List<SourceSystem> getSubscriptions(Connection connection, Long prescriptionKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {
			if (logger.isTraceEnabled())  {logger.trace("getSubscripton all :sqlQuey \n" + GETSQL_V1 );}
			List<SourceSystem> sourceChannels=null;
			if (prescriptionKey!=null) {
				sourceChannels=new ArrayList<SourceSystem>();
				ps = connection.prepareStatement(GETSQL_V1);
				setPsLongParam(1, prescriptionKey);
				setPsLongParam(2, prescriptionKey);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				sourceChannels.add(SourceSystem.fromValue(rs.getString("SRCESYSCD")));		
			}
			}	
			if (logger.isInfoEnabled())  {logger.info("Total getSubscriptions intances fetched: " + sourceChannels.size() + ". PrescriptionKey: " + prescriptionKey);}
			return sourceChannels;
		}
			//TE97_FIX  End
		//TE97_FIX  Start
				public List<String> getLinkedRxNum(Connection connection,Long rxKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {
				if (logger.isTraceEnabled())  {logger.trace("getSubscripton all :sqlQuey \n" + GETLINKEDRX_SQL_V1 );}
				List<String> linkedRxNumbers=null;
				if (rxKey!=null) {
					linkedRxNumbers=new ArrayList<String>();
					ps = connection.prepareStatement(GETLINKEDRX_SQL_V1);
					setPsLongParam(1, rxKey);
					
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						linkedRxNumbers.add(rs.getString("rxnum"));		
				}
			}	
				if (logger.isInfoEnabled())  {logger.info("Total getLinkedRx intances fetched: " + linkedRxNumbers.size() + ". rxKey: " + rxKey);}
				return linkedRxNumbers;
			}
		
			//TE97_FIX  End
		//TE97_024
		@SuppressWarnings("unchecked")
		public Map<String,String> getCrxDrugAdherance(Connection connection, String storeNumber,String gtin) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException  {
			if (logger.isTraceEnabled())  {logger.trace("getCrxDrugAdherance all :sqlQuey \n" + GETCRXDRUGSQL );}
			Long provKey=null;
			Map<String,String> drugMap=new HashMap<String, String>();
			if (storeNumber != null && gtin != null) {
    		    if ((drgAherance.containsKey((storeNumber + gtin)))) {
    		    	 if(logger.isDebugEnabled()) {logger.debug("getCrxDrugAdherance storeNumber  : " + storeNumber  +"  gtin = "+gtin +" drgAherance.size = " + drgAherance.size());}
    		    	    drugMap = ((HashMap<String, String>) drgAherance.get(storeNumber + gtin));
    		    } else {
    		   provKey = getKeyFromCode(LT_GETPROVKEY, storeNumber);
			
				ps = connection.prepareStatement(GETCRXDRUGSQL);
				setPsStringParam(1, gtin);
				setPsLongParam(2, provKey);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
				drugMap.put("STRENTH", rs.getString("STRENTH"));
				String strengthUOMCode = (rs.getString("STRENTHUOM")!=null ) ? rs.getString("STRENTHUOM").trim() : null;
				drugMap.put("STRENTHUOM", strengthUOMCode);
				if(strengthUOMCode != null && !"".equalsIgnoreCase(rs.getString("STRENTHUOM").trim())) {
				Long strengthUomTypeKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUOMCode);
				}
				drugMap.put("ADHERANCECATEGORY", rs.getString("ADHERANCECATEGORY"));
				drugMap.put("REFILLREMINDERFLAG", rs.getString("REFILLREMINDERFLAG"));
				drugMap.put("PICKUPREMINDERFLAG", rs.getString("PICKUPREMINDERFLAG"));
				drugMap.put("AUTOREFILLFLAG", rs.getString("AUTOREFILLFLAG"));
				drugMap.put("TRRFLAG", rs.getString("TRRFLAG"));
				
			}
				drgAherance.put((storeNumber + gtin), drugMap);
				super.close(); // VLAD FIXED: ORA-01000: maximum open cursors exceeded	
				
			}
			}
//			logger.info("Total getCrxDrugAdherance intances fetched: " + drugMap.size() );
			return drugMap;
		}
		
		private boolean newPrescriber(Timestamp            dbUpdateTimeStamp,
				XMLGregorianCalendar payloadUpdateTimeStamp) {
			
			if (payloadUpdateTimeStamp == null) {
				return true;
			}
		//	 payloadUpdateTimeStamp.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
			Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
			boolean isUpdateRequestNew = dbUpdateTimeStamp == null
					|| updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp) > 0;
					if(logger.isDebugEnabled()) {logger.debug("PrescriptionApi. newPrescriber....isUpdateRequestNew ="+isUpdateRequestNew);
					logger.debug("dbUpdateTimeStamp ="+dbUpdateTimeStamp +"updateTimestampJaxbDate= "+updateTimestampJaxbDate);	}	 
			return isUpdateRequestNew;
		}
		
//      2020-07-03  Started		
		private boolean newRequest(Timestamp dbUpdateTimeStamp,	XMLGregorianCalendar payloadUpdateTimeStamp) throws InvalidInputException {
			if (payloadUpdateTimeStamp == null) {
				throw new InvalidInputException("PrescriptionApi: UpdateTimeStamp is missing in payload request.");
			}			
			
//		    payloadUpdateTimeStamp.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
			Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
			boolean isUpdateRequestNew = dbUpdateTimeStamp == null
					|| updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp) >= 0;
					if(logger.isDebugEnabled()) {logger.debug("PrescriptionApi. newRequest....isUpdateRequestNew = " + isUpdateRequestNew);
					logger.debug("dbUpdateTimeStamp = " + dbUpdateTimeStamp + " updateTimestampJaxbDate= " + updateTimestampJaxbDate);}		 
			return isUpdateRequestNew;
		}
//      2020-07-03  Ended	
		

}
