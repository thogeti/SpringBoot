package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_COMMMODETYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DISCNTDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ENGMNTTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_INTRCHGBLTYTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PHARMACYCHNLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_PKGFRMTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXNSRCECHNLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXRSMRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXSUBRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_TXTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRException;
import com.sdm.cdr.exception.api.ChannelNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.SourcePharmacyChannelSingleton;
import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.StringUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.sdm.cdr.jdbc.bean.PersonRole;
import ca.sdm.cdr.jdbc.enumeration.CDREnumerations;
import ca.sdm.cdr.jdbc.query.api.RefillDispenseGet;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import generated.RxHBBusinessEventActionEnum;


/**
 * Dispense API used to persist Dispense entity.
 * 
 *
 */
public class DispenseApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(DispenseApi.class);
	
	private final static String INSERTSQL = 
	"INSERT INTO TX (" +
		"TXID,				ADDTNLDSGINSTRCTNTXT,	ADMINPRDENDDT,		DAYSSPLY,			ADMINPRDSTRTDT, "	+
		"CRTTIMESTAMP,		PRDCRID,				CNSMRID,			TXNCANCLRSNTXT,		LSTUPDT, "	+		
		"PCKUPTIME,			QTYTXD,					TOTALAMTPAID,		TXNNUM,				TXTIME, "	+
		"DAYSINTRVL,		DRGLOTNUM,				DRGEXPRTNDT,		SIGDESCRPTNTLANG,	SEQNUM, "	+				
		"PREVTXDAYSSPLY,	QTYRMNG,				HOMEDELVRYFLAG,		STORENUM,			RTEOFADMIN_CERXTYPKEY, "	+
		"DISCNTDRSNTYPKEY,	TXREQCOMMMODETYPKEY,	TXRSMRSNTYPKEY,		" /*TXRTYPKEY,	*/ + "		TXSUBRSNTYPKEY, "	+		
		"TXTYPKEY,			EngmntTypKey,			INTRCHGBLTYTYPKEY,	PKGFRMTYPKEY,		PHARMACYCHNLTYPKEY, "	+
		"SIGCD,				TXNSRCECHNLTYPKEY,		RXKEY,				LOCKEY,				PACKKEY, "	+				
		"CMPNDKEY,			DSGFRMKEY,				PRSNRLKEY,			INITRKEY,			" + // "TXRKEY, "	+
		"SPRVSRKEY,			RCRDRKEY,				COMMMODETYPKEY,		TXKEY"	+		
	") VALUES (" +
		" ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", ?, " + CommonUtil.getPsToDateFunctionStr() + ", " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() +
		"," + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?, " + CommonUtil.getPsToDateFunctionStr() + ", ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +			
		",?, ?, ?, ?, ?, ?, ? " +
	")";
/*
	private final static String UPDATESQL =
	"UPDATE TX SET " +
			"TXID=?,						ADDTNLDSGINSTRCTNTXT=?,	ADMINPRDENDDT=" + CommonUtil.getPsToDateFunctionStr() + ",	DAYSSPLY=?,			ADMINPRDSTRTDT=" + CommonUtil.getPsToDateFunctionStr() + ", "	+
			"CRTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ",	PRDCRID=?,				CNSMRID=?,						TXNCANCLRSNTXT=?,	LSTUPDT=" + CommonUtil.getPsToDateFunctionStr() + ", "	+		
			" QTYTXD=?,				TOTALAMTPAID=?,					TXNNUM=?,			TXTIME=" + CommonUtil.getPsToDateFunctionStr() + ","	+
			"DAYSINTRVL=?,					DRGLOTNUM=?,			DRGEXPRTNDT=" + CommonUtil.getPsToDateFunctionStr() + ",	SIGDESCRPTNTLANG=?,	SEQNUM=?,"	+				
			"PREVTXDAYSSPLY=?,				QTYRMNG=?,				HOMEDELVRYFLAG=?,				STORENUM=?,			RTEOFADMIN_CERXTYPKEY=?,"	+
			"DISCNTDRSNTYPKEY=?,			TXREQCOMMMODETYPKEY=?,	TXRSMRSNTYPKEY=?,				" +  "	TXSUBRSNTYPKEY=?,"	+		
			"TXTYPKEY=?,					EngmntTypKey=?,			INTRCHGBLTYTYPKEY=?,			PKGFRMTYPKEY=?,		PHARMACYCHNLTYPKEY=?,"	+
			"SIGCD=?,						TXNSRCECHNLTYPKEY=?,	RXKEY=?,						LOCKEY=?,			PACKKEY=?,"	+				
			"CMPNDKEY=?,					DSGFRMKEY=?,			PRSNRLKEY=?,					INITRKEY=?,			" + 
			"SPRVSRKEY=?,					RCRDRKEY=?,				COMMMODETYPKEY=?"	+	
	" WHERE TXKEY=?";
	*/
	//LTPHCP-67  changes Null for missing entities (Pack @ Drug) PraveenT
	private final static String UPDATESQL =
			"UPDATE TX SET " +
					"TXID=?, "+
					"ADDTNLDSGINSTRCTNTXT=?, "+
					"ADMINPRDENDDT=" + CommonUtil.getPsToDateFunctionStr() + ", "+
					"DAYSSPLY=?, "+
					"ADMINPRDSTRTDT=" + CommonUtil.getPsToDateFunctionStr() + ", "	+
					"CRTTIMESTAMP=" + CommonUtil.getPsToDateFunctionStr() + ", "+
					"PRDCRID=?, "+
					"CNSMRID=?, "+
					"TXNCANCLRSNTXT=?, "+
					"LSTUPDT=" + CommonUtil.getPsToDateFunctionStr() + ", "	+		
					" QTYTXD=?, "+
					"TOTALAMTPAID=?, "+
					"TXNNUM=?, "+
					"TXTIME=" + CommonUtil.getPsToDateFunctionStr() + ", "	+
					"DAYSINTRVL=?, "+
					"DRGLOTNUM=?, "+
					"DRGEXPRTNDT=" + CommonUtil.getPsToDateFunctionStr() + ", "+
					"SIGDESCRPTNTLANG=?, "+
					"SEQNUM=?, "	+				
					"PREVTXDAYSSPLY=?, "+
					"QTYRMNG=?, "+
					"HOMEDELVRYFLAG=?, "+
					"STORENUM=?, "+
					"RTEOFADMIN_CERXTYPKEY=nvl(?, RTEOFADMIN_CERXTYPKEY) , "+
					"DISCNTDRSNTYPKEY=nvl(?, DISCNTDRSNTYPKEY) , "+
					"TXREQCOMMMODETYPKEY=nvl(?, TXREQCOMMMODETYPKEY) , "+
					"TXRSMRSNTYPKEY=nvl(?, TXRSMRSNTYPKEY) , "+
					"TXSUBRSNTYPKEY=nvl(?, TXSUBRSNTYPKEY) , "+		
					"TXTYPKEY=nvl(?, TXTYPKEY) , "+
					"EngmntTypKey=nvl(?, EngmntTypKey) , "+
					"INTRCHGBLTYTYPKEY=nvl(?, INTRCHGBLTYTYPKEY) , "+
					"PKGFRMTYPKEY=nvl(?, PKGFRMTYPKEY) , "+
					"PHARMACYCHNLTYPKEY=nvl(?, PHARMACYCHNLTYPKEY) , "+
					"SIGCD=?, "+
					"TXNSRCECHNLTYPKEY=nvl(?, TXNSRCECHNLTYPKEY) , "+
					"RXKEY=nvl(?, RXKEY) , "+
					"LOCKEY=nvl(?, LOCKEY) , "+
					"PACKKEY=nvl(?, PACKKEY) , "+				
					"CMPNDKEY=nvl(?, CMPNDKEY) , "+
					"DSGFRMKEY=nvl(?, DSGFRMKEY) , "+
					"PRSNRLKEY=nvl(?, PRSNRLKEY) , "+
					"INITRKEY=nvl(?, INITRKEY) , "+ 
					"SPRVSRKEY=nvl(?, SPRVSRKEY) , "+
					"RCRDRKEY=nvl(?, RCRDRKEY) , "+
					"COMMMODETYPKEY=nvl(?, COMMMODETYPKEY) "+
				" WHERE TXKEY=?";
	///FIRSTPICKUPTIME modified to PCKUPTIME on 20/08/2018 due to trigger error
		private final static String PICKUPTIMESQL="update tx  set PCKUPTIME = "
				+ " case "
				+ " when PCKUPTIME is null and LASTPICKUPTIME is null"
				+ " then " + CommonUtil.getPsToDateFunctionStr()  
				+ " else PCKUPTIME end , LASTPICKUPTIME ="
				+ " case when PCKUPTIME is not null "
				+ " then "+ CommonUtil.getPsToDateFunctionStr() + " else LASTPICKUPTIME  end"
				+ " where txkey = ? "; 
		

	
	
	
	/**
	 * 
	 * @param dispense
	 * @param store
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws NamingException
	 * @throws CDRException 
	 * @throws JAXBException 
	 */

	public RxHBBusinessEventActionEnum upsertDispense(Connection connection, Dispense dispense, Store store, Long rxKey,Integer prescriptionNumber,String eventName,Map <String, Long> drgPK,Map <String, Timestamp> drgTime,Map <String, Long> packPK,Map <String, Timestamp> packTime) throws SQLException, IOException, NamingException, CDRException, JAXBException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertDispense. , rxKey : "+ rxKey);}
			String storeNum = (store!=null && store.getStorenumber()!=null) ? CommonUtil.createStoreLeadingZeros(store.getStorenumber()) : null;
			Map<String, Long> resultMap;
			resultMap = upsertDispense(connection, storeNum, dispense, store.getConsumerId(), rxKey,prescriptionNumber,eventName,drgPK,drgTime,packPK,packTime);
			boolean updated = true;
			if( resultMap != null )
				updated = resultMap.containsKey(RxHBBusinessEventActionEnum.UPDATE.value().toUpperCase());
			return (updated) ? RxHBBusinessEventActionEnum.UPDATE : RxHBBusinessEventActionEnum.CREATE;
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
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertDispense. , rxKey : "+ rxKey);}
		}
	}
	
	
	/**
	 * Attempt to persist a Dispense instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Dispense doesn't exist, then insert a new record.
	 * If the Dispense exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param dispense			Dispense instance.
	 * @param packTime 
	 * @param packPK 
	 * 
	 * @return				DispenseKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws CDRException 
	 * @throws JAXBException 
	 */
	private Map<String, Long> upsertDispense(Connection connection, String storeNumber, Dispense dispense, String storeConsumerId, Long rxKey,Integer prescriptionNumber,String eventName,Map <String, Long> drgPK,Map <String, Timestamp> drgTime, Map<String, Long> packPK, Map<String, Timestamp> packTime) throws SQLException, IOException, NamingException, CDRException, JAXBException {
	
		if(logger.isDebugEnabled()) {logger.debug("START upsertDispense");}
		Map<String, Long> result = new HashMap<String, Long>();
		
		/*  Sharooz Shiran : Dec 9 , 2016
		 * check the last updateed timestamp against the TX table, to address a defect raised by QA. 
		 * 
		 */

		Map<String, Object> dispenseData = FindUtil.findDispenseData(connection, dispense.getConsumerId(), storeNumber);
		Long dispenseKey = (Long) dispenseData.get("TXKEY");
		
		if (dispense.getLastUpdate()==null) {
			throw new InvalidInputException("Update Timestamp not populated in Dispense object.");
		}
		Timestamp updateTimestampDb = (Timestamp) dispenseData.get("LSTUPDT");
//      2020-07-03  Started	
	//	boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew(updateTimestampDb, dispense.getLastUpdate());	
  	    boolean isUpdateRequestNew = newRequest(updateTimestampDb, dispense.getLastUpdate());
//      2020-07-03  Ended		
		
		if (isUpdateRequestNew == false ) 
		{
			if(logger.isDebugEnabled()) {logger.debug("Dispense lastUpdatedtimeStamp : " + dispense.getLastUpdate() + " is less than DB last updated : " + updateTimestampDb);}
			return null;
		}
		Long packKey = null;
		Pack pack = dispense.getPack();
		if (pack!=null) {
			PackApi packApi = new PackApi();
			String drugConsumerId = (pack.getDrug()!=null) ? pack.getDrug().getConsumerId() : null;
			packKey = packApi.upsertPack(connection, drugConsumerId, storeNumber, pack,drgPK,drgTime,packPK,packTime);
		}
		
//		Long rxKey = null;
		
		String locationConsumerId = (dispense.getServiceLocation()!=null) ? dispense.getServiceLocation().getConsumerId() : null;
		Long locKey = FindUtil.findLocationKey(connection, locationConsumerId, storeNumber);
		
		Long compoundKey = null;
		Compound compound = dispense.getCompound();
		if (compound!=null) {
			CompoundApi compoundApi = new CompoundApi();
			compoundKey = compoundApi.upsertCompound(connection, storeNumber, compound,drgPK,drgTime,packPK,packTime);
		}
		
		Long dosageFormKey = null;
		DosageForm dosageForm = dispense.getDosageForm();
		if (dosageForm != null){
			dosageFormKey =TableCacheSingleton.getInstance(connection).getKeyFromObject(connection, "DSGFRM", dosageForm);
		}
		
		PersonApi personApi = new PersonApi();
		Store store = new Store();
		store.setConsumerId(storeConsumerId);
		store.setStorenumber(storeNumber);
		
		Long recorderKey = null;
		Recorder recorder = dispense.getRecorder();
		if (recorder!=null) {
			PersonRole recorderPersonRole = new PersonRole (recorder);
			recorderKey = personApi.upsertPerson(connection, store, recorderPersonRole);
		}
		
		Long supervisorKey = null; 
		Supervisor supervisor = dispense.getSupervisor();
		if (supervisor!=null) {
			PersonRole supervisorPersonRole = new PersonRole (supervisor);
			supervisorKey = personApi.upsertPerson(connection, store, supervisorPersonRole);
		}
		
		Long initiatorKey = null; 
		Initiator initiator = dispense.getInitiator();
		if (initiator!=null) {
			PersonRole initiatorPersonRole = new PersonRole (initiator);
			initiatorKey = personApi.upsertPerson(connection, store, initiatorPersonRole);
		}			
		
		Long personRoleKey = null;
		
		// Persist main entity
		
		String operation = null;
		if (dispenseKey==null) {
			operation = RxHBBusinessEventActionEnum.CREATE.value().toUpperCase();
			if (dispense.getLastUpdate()==null) {
				dispense.setLastUpdate(dispense.getCreateTimestamp());
			}
			
			if (dispense.getCreateTimestamp()==null) {
				dispense.setCreateTimestamp(dispense.getLastUpdate());
			}
			
			dispenseKey=insertDispense(connection, storeNumber, dispense, rxKey, locKey, packKey, compoundKey, dosageFormKey, personRoleKey, initiatorKey, /*dispenserKey,*/ supervisorKey, recorderKey);
			//FFT Actual Dispense Creation..Start
			RefillDispenseGet refillDispenseGet=new RefillDispenseGet();
			RefillDispenseApi refillDispenseApi=new RefillDispenseApi();
			Long txdrxkey=refillDispenseGet.getDispenseWithIn48Frame(connection, rxKey, storeNumber, eventName);
		   if(txdrxkey !=null && txdrxkey>0L){
			   refillDispenseApi.updateLatestRefillDispense(connection, dispenseKey, txdrxkey);
		   }
		 //FFT Actual Dispense Creation.. End
			
		} else {
			operation = RxHBBusinessEventActionEnum.UPDATE.value().toUpperCase();


			NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.DISPENSE_NOTE);
			noteApi.upsertNoteList(connection, store, dispense.getNote() , dispenseKey );

			updateDispense(connection, storeNumber, dispenseKey, dispense, rxKey, locKey, packKey, compoundKey, dosageFormKey, personRoleKey, initiatorKey, /*dispenserKey,*/ supervisorKey, recorderKey);
		}
		
		NoteApi noteApi = new NoteApi(CDREnumerations.NoteTypeTable.DISPENSE_NOTE);
		noteApi.upsertNoteList(connection, store, dispense.getNote() , dispenseKey );

		Long dispenserPersonRoleKey = null; 
		List<Dispenser> dispensers = dispense.getDispenser();
		if( dispensers != null )
		{
			for( Dispenser dispenser: dispensers )
			{
				PersonRole dispenserPersonRole = new PersonRole (dispenser);
				dispenserPersonRoleKey = personApi.upsertPerson(connection, store, dispenserPersonRole);
				DispenserApi dispenserApi = new DispenserApi();
				dispenserApi.upsertDispenser(connection, dispenserPersonRoleKey, dispenseKey);
				
			}
		}
		
		TxStatApi txStatApi = new TxStatApi();		
		txStatApi.upsertTxStat(connection, dispenseKey, dispense.getFillStatus());
		
		ProfessionalServiceApi prfsvcApi = new ProfessionalServiceApi(); 
		if(dispense.getProfessionalService() !=null)
		{
		prfsvcApi.upsertProfessionalService(connection, dispense.getProfessionalService(), store, dispenseKey);
		};
		
		result.put(operation, dispenseKey);
		if(logger.isDebugEnabled()) {logger.debug("END upsertDispense");}
		return result;
	}
	
	/**
	 * Attempt to update a Dispense instance, which is matched by dispenseKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param dispenseKey	Dispense Key.
	 * @param dispense		Dispense instance.
	 * 
	 * @return				DispenseKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 * @throws JAXBException 
	 * @throws ChannelNotFoundException 
	 */
	private Long updateDispense(Connection connection, String storeNum, Long dispenseKey, Dispense dispense, Long rxKey, Long locKey, Long packKey, 
		Long compoundKey, Long dosageFormKey, Long personRoleKey, Long initrKey, /* Long dispenserKey, */Long supervisorKey, Long recorderKey
		) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException, ChannelNotFoundException, JAXBException {
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}

		ps = connection.prepareStatement(UPDATESQL);
		
	//select  query get la pick up time 	
		setUpdatePsParams(storeNum, dispenseKey, dispense, rxKey, locKey, packKey, compoundKey, dosageFormKey, personRoleKey, initrKey, /*dispenserKey, */supervisorKey, recorderKey);
		int res = ps.executeUpdate();
		
		//CR_63 FIX 
		if(dispense.getPickupTime()!=null  && dispense.getFillStatus().value().equals("Complete")){  
		XMLGregorianCalendar pickuptime = dispense.getPickupTime();
		int updateres=this.updatePCKUPTIME(connection, dispenseKey,pickuptime);
		if (logger.isInfoEnabled())  {logger.info(updateres + " Pick time records has been updated. for the  DispenseKey: " + dispenseKey );}
		}
		if (logger.isInfoEnabled())  {logger.info("Total Dispense intances updated: " + res + ". DispenseKey: " + dispenseKey + ", consumerId: '" + dispense.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return dispenseKey;
	}

	//CR_63 FIX 
		private Integer updatePCKUPTIME(Connection connection, Long dispenseKey,XMLGregorianCalendar pickuptime) throws SQLException{
			
			
			ps = connection.prepareStatement(PICKUPTIMESQL);
			String pickupTs = CommonUtil.toTimestampStr(pickuptime);
			CommonUtil.setPsStringParam(ps, 1, pickupTs);		
			CommonUtil.setPsStringParam(ps, 2, pickupTs);		
			CommonUtil.setPsLongParam(ps, 3, dispenseKey);
			int res = ps.executeUpdate();
			
			return res;
			
		}
	/**
	 * Attempt to insert a new Dispense into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param dispense		Dispense instance.
	 * 
	 * @return				DispenseKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 * @throws JAXBException 
	 * @throws ChannelNotFoundException 
	 */
	private Long insertDispense(Connection connection, String storeNum, Dispense dispense, Long rxKey, Long locKey, Long packKey, 
		Long compoundKey, Long dosageFormKey, Long personRoleKey, Long initrKey, /*Long dispenserKey,*/ Long supervisorKey, Long recorderKey) 
		throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException, ChannelNotFoundException, JAXBException {
		
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long dispenseKey = IdGenerator.generate(connection, "TX");
		
		ps = connection.prepareStatement(INSERTSQL);
		
		setPsParams(storeNum, dispenseKey, dispense, rxKey, locKey, packKey, compoundKey, dosageFormKey, personRoleKey, initrKey /*, dispenserKey */ , supervisorKey, recorderKey);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Dispense intances inserted: " + res + ". DispenseKey: " + dispenseKey + ", consumerId: '" + dispense.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return dispenseKey;
	}
	
	
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param dispenseKey		Dispense Key.
	 * @param dispense			Dispense instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws JAXBException 
	 * @throws ChannelNotFoundException 
	 */
	private void setPsParams(String storeNum, Long dispenseKey, Dispense dispense, Long rxKey, Long locKey, Long packKey, 
		Long compoundKey, Long dosageFormKey, Long personRoleKey, Long initrKey /*, Long dispenserKey */ , Long supervisorKey, Long recorderKey) 
		throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, ChannelNotFoundException, JAXBException {
		
		PharmacyChannel pharmacyChannel = dispense.getPharmacyChannel();
		/*
		 * Sharooz Shiran : Jan 31, 2017 
		 * Check if the channelType does not exist and use the default from the SourcePharmacyChannel.xml file
		 * for channelType , communicationMode and engagement 
		 *    
		 */
		String txSourceChannekllTypeCode = (dispense.getTransactionSourceCode()!=null) ? dispense.getTransactionSourceCode().value() : null; //TXNSRCECHNLTYPKEY
		if( txSourceChannekllTypeCode!=null  && ( pharmacyChannel == null || pharmacyChannel.getChannelType()== null ) )
		{
			pharmacyChannel = SourcePharmacyChannelSingleton.getInstance().getPharmacyChannel(txSourceChannekllTypeCode);
		}
		
		String dispenseId = null;
		CommonUtil.setPsStringParam(ps, 1, dispenseId); // gap		
		CommonUtil.setPsStringParam(ps, 2, dispense.getAdditionalDosageInstructionText());
		String adminPeriodEndTs = CommonUtil.toTimestampStr(dispense.getAdministrationPeriodEnd());
		CommonUtil.setPsStringParam(ps, 3, adminPeriodEndTs);
		CommonUtil.setPsLongParam(ps, 4, dispense.getDaysSupply());
		String adminPeriodStartTs = CommonUtil.toTimestampStr(dispense.getAdministrationPeriodStart());
		CommonUtil.setPsStringParam(ps, 5, adminPeriodStartTs);
		String createTs = CommonUtil.toTimestampStr(dispense.getCreateTimestamp());
		
		CommonUtil.setPsStringParam(ps, 6, createTs);
		CommonUtil.setPsStringParam(ps, 7, dispense.getProducerId());
		CommonUtil.setPsStringParam(ps, 8, dispense.getConsumerId());
		CommonUtil.setPsStringParam(ps, 9, dispense.getTransactionCancellationReasonText());
		
		String lastUpdateTs = CommonUtil.toTimestampStr(dispense.getLastUpdate());
		CommonUtil.setPsStringParam(ps, 10, lastUpdateTs);

		String pickupTs = CommonUtil.toTimestampStr(dispense.getPickupTime());
		CommonUtil.setPsStringParam(ps, 11, pickupTs);
		Double qtyDispensed = (dispense.getQuantityDispensed()!=null) ? dispense.getQuantityDispensed().doubleValue() : null;
		CommonUtil.setPsDoubleParam(ps, 12, qtyDispensed);
		Double totalAmountPaid = (dispense.getTotalAmountPaid()!=null) ? dispense.getTotalAmountPaid().doubleValue() : null;
		CommonUtil.setPsDoubleParam(ps, 13, totalAmountPaid);
		CommonUtil.setPsStringParam(ps,	14, String.valueOf(dispense.getTransactionNumber())); 
//		String dispenseTs = CommonUtil.toTimestampStr(dispense.getDispenseTime());
//		CommonUtil.setPsStringParam(ps,	15, dispenseTs);
		CommonUtil.setPsXMLGregorianCalendarParam(ps, 15, dispense.getDispenseTime());
		CommonUtil.setPsLongParam(ps,	16, dispense.getDaysInterval());
		Double drugLotNumber = null;
		try {
			drugLotNumber = (dispense.getDrugLotNumber()!=null) ? new Double(dispense.getDrugLotNumber()) : null;
		} catch (Exception ex) {
			if(logger.isErrorEnabled()) {logger.error("Could not parse drug lot number '" + dispense.getDrugLotNumber() + "'.");}
		}
		CommonUtil.setPsDoubleParam(ps, 17, drugLotNumber);
		String drugExpirationTs = CommonUtil.toTimestampStr(dispense.getDrugExpirationDate());
		CommonUtil.setPsStringParam(ps, 18, drugExpirationTs);
		CommonUtil.setPsStringParam(ps, 19, dispense.getSigDescriptionPatientLanguage());
		CommonUtil.setPsLongParam(ps, 20, dispense.getSequenceNumber());		
		CommonUtil.setPsLongParam(ps, 21, dispense.getPreviousDispenseDaysSupply());
		CommonUtil.setPsLongParam(ps, 22, dispense.getQuantityRemaining());
		CommonUtil.setPsStringParam(ps, 23, CommonUtil.convertBooleanToYesNoFlag(dispense.isIsHomeDeliveryFlag()));
		CommonUtil.setPsStringParam(ps, 24, storeNum);
		
		String adminRouteCode = (dispense.getAdministrationRouteCode()!=null) ? dispense.getAdministrationRouteCode().value() : null; //RteOfAdmin_CeRXTypKey
		Long adminRouteKey = getKeyFromCode(LT_RTEOFADMIN_CERXTYP, adminRouteCode);
		CommonUtil.setPsLongParam(ps, 25, adminRouteKey);
		
		String discontinuedReasonCode = dispense.getDiscontinuedReasonCode();
		Long discontinuedReasonKey = getKeyFromCode(LT_DISCNTDRSNTYP, discontinuedReasonCode);
		CommonUtil.setPsLongParam(ps, 26, discontinuedReasonKey);

		String txReqCommModeTypeKey = null; // Not mapped in mapping document
		CommonUtil.setPsLongParam(ps, 27, txReqCommModeTypeKey);

		Long TxRsmReasonTypeKey = null;
		if (dispense.getResumeReasonCode()!=null && dispense.getResumeReasonCode().getPrescriptionResumeReasonTypeCode() != null) 
		{
			String TxRsmReasonTypeCode = dispense.getResumeReasonCode().getPrescriptionResumeReasonTypeCode().value(); // TXRSMRSNTYPKEY
			TxRsmReasonTypeKey = getKeyFromCode(LT_TXRSMRSNTYP, TxRsmReasonTypeCode);
		}
		CommonUtil.setPsLongParam(ps, 28, TxRsmReasonTypeKey);
		
	//	Long txrTypeKey = null;
	//	String txrTypeCode = null; // TXRTYPKEY not used
	//	txrTypeKey = getKeyFromCode(LT_TXRTYP, txrTypeCode);
	//	CommonUtil.setPsLongParam(ps, 29, txrTypeKey);
		
		String subReasonCode = (dispense.getSubstitutionReason()!=null) ? dispense.getSubstitutionReason().value() : null;
		Long subReasonKey = getKeyFromCode(LT_TXSUBRSNTYP, subReasonCode);
		CommonUtil.setPsLongParam(ps, 29, subReasonKey);
		
		String dispenseTypeCode = dispense.getDispenseType();
		Long dispenseTypeKey = getKeyFromCode(LT_TXTYP, dispenseTypeCode);
		CommonUtil.setPsLongParam(ps, 30, dispenseTypeKey);
		
		String engagementTypeCode = (pharmacyChannel!=null && pharmacyChannel.getEngagment()!=null) ? pharmacyChannel.getEngagment().value() : null;
		Long engagementTypeKey = getKeyFromCode(LT_ENGMNTTYP, engagementTypeCode);
		CommonUtil.setPsLongParam(ps, 31, engagementTypeKey);
		
		String interchangeCode = (dispense.getInterchangeability()!=null) ? dispense.getInterchangeability().value() : null;
		Long interchangeKey = getKeyFromCode(LT_INTRCHGBLTYTYP, interchangeCode);
		CommonUtil.setPsLongParam(ps, 32, interchangeKey);
		
		String packageFormCode = dispense.getPackageForm();
		Long packageFormKey = getKeyFromCode(LT_PKGFRMTYP, packageFormCode);
		CommonUtil.setPsLongParam(ps, 33, packageFormKey);
		
		String pharmacyChannelTypeCode = (pharmacyChannel!=null && pharmacyChannel.getChannelType()!=null) ? pharmacyChannel.getChannelType().value() : null; //PHARMACYCHNLTYPKEY
		Long pharmacyChannelTypeKey = getKeyFromCode(LT_PHARMACYCHNLTYP, pharmacyChannelTypeCode);
		CommonUtil.setPsLongParam(ps, 34, pharmacyChannelTypeKey);
		
		String sigCode = dispense.getSigCode();
		CommonUtil.setPsStringParam(ps, 35, sigCode);
		
		Long txSourceChannekllTypeKey = getKeyFromCode(LT_TXNSRCECHNLTYP, txSourceChannekllTypeCode);
		CommonUtil.setPsLongParam(ps, 36, txSourceChannekllTypeKey);
		
		
		
		CommonUtil.setPsLongParam(ps, 37, rxKey);
		CommonUtil.setPsLongParam(ps, 38, locKey);
		CommonUtil.setPsLongParam(ps, 39, packKey);
		CommonUtil.setPsLongParam(ps, 40, compoundKey);
		CommonUtil.setPsLongParam(ps, 41, dosageFormKey);
		
		CommonUtil.setPsLongParam(ps, 42, personRoleKey);
		CommonUtil.setPsLongParam(ps, 43, initrKey);
//		CommonUtil.setPsLongParam(ps, 45, dispenserKey);
		CommonUtil.setPsLongParam(ps, 44, supervisorKey);
		CommonUtil.setPsLongParam(ps, 45, recorderKey);
		String pharmacyCommModeTypeCode = (pharmacyChannel!=null && pharmacyChannel.getCommunicationMode()!=null) ? pharmacyChannel.getCommunicationMode().value() : null; // COMMMODETYPKEY
		
		if( txSourceChannekllTypeCode != null && pharmacyCommModeTypeCode == null )
		{
			
		}
		Long pharmacyCommModeTypeKey = getKeyFromCode(LT_COMMMODETYP, pharmacyCommModeTypeCode);
		CommonUtil.setPsLongParam(ps, 46, pharmacyCommModeTypeKey);
		
		CommonUtil.setPsLongParam(ps, 47, dispenseKey);
	}
	
	
	private void setUpdatePsParams(String storeNum, Long dispenseKey, Dispense dispense, Long rxKey, Long locKey, Long packKey, 
			Long compoundKey, Long dosageFormKey, Long personRoleKey, Long initrKey /*, Long dispenserKey */ , Long supervisorKey, Long recorderKey) 
			throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, ChannelNotFoundException, JAXBException {
			
			PharmacyChannel pharmacyChannel = dispense.getPharmacyChannel();
			/*
			 * Sharooz Shiran : Jan 31, 2017 
			 * Check if the channelType does not exist and use the default from the SourcePharmacyChannel.xml file
			 * for channelType , communicationMode and engagement 
			 *    
			 */
			String txSourceChannekllTypeCode = (dispense.getTransactionSourceCode()!=null) ? dispense.getTransactionSourceCode().value() : null; //TXNSRCECHNLTYPKEY
			if( txSourceChannekllTypeCode!=null  && ( pharmacyChannel == null || pharmacyChannel.getChannelType()== null ) )
			{
				pharmacyChannel = SourcePharmacyChannelSingleton.getInstance().getPharmacyChannel(txSourceChannekllTypeCode);
			}
			
			String dispenseId = null;
			CommonUtil.setPsStringParam(ps, 1, dispenseId); // gap		
			CommonUtil.setPsStringParam(ps, 2, dispense.getAdditionalDosageInstructionText());
			String adminPeriodEndTs = CommonUtil.toTimestampStr(dispense.getAdministrationPeriodEnd());
			CommonUtil.setPsStringParam(ps, 3, adminPeriodEndTs);
			CommonUtil.setPsLongParam(ps, 4, dispense.getDaysSupply());
			String adminPeriodStartTs = CommonUtil.toTimestampStr(dispense.getAdministrationPeriodStart());
			CommonUtil.setPsStringParam(ps, 5, adminPeriodStartTs);
			String createTs = CommonUtil.toTimestampStr(dispense.getCreateTimestamp());
			
			CommonUtil.setPsStringParam(ps, 6, createTs);
			CommonUtil.setPsStringParam(ps, 7, dispense.getProducerId());
			CommonUtil.setPsStringParam(ps, 8, dispense.getConsumerId());
			CommonUtil.setPsStringParam(ps, 9, dispense.getTransactionCancellationReasonText());
			
			String lastUpdateTs = CommonUtil.toTimestampStr(dispense.getLastUpdate());
			CommonUtil.setPsStringParam(ps, 10, lastUpdateTs);

		
			Double qtyDispensed = (dispense.getQuantityDispensed()!=null) ? dispense.getQuantityDispensed().doubleValue() : null;
			CommonUtil.setPsDoubleParam(ps, 11, qtyDispensed);
			Double totalAmountPaid = (dispense.getTotalAmountPaid()!=null) ? dispense.getTotalAmountPaid().doubleValue() : null;
			CommonUtil.setPsDoubleParam(ps, 12, totalAmountPaid);
			CommonUtil.setPsStringParam(ps,	13, String.valueOf(dispense.getTransactionNumber())); 
//			String dispenseTs = CommonUtil.toTimestampStr(dispense.getDispenseTime());
//			CommonUtil.setPsStringParam(ps,	15, dispenseTs);
			CommonUtil.setPsXMLGregorianCalendarParam(ps, 14, dispense.getDispenseTime());
			CommonUtil.setPsLongParam(ps,	15, dispense.getDaysInterval());
			Double drugLotNumber = null;
			try {
				drugLotNumber = (dispense.getDrugLotNumber()!=null) ? new Double(dispense.getDrugLotNumber()) : null;
			} catch (Exception ex) {
				if(logger.isErrorEnabled()) {logger.error("Could not parse drug lot number '" + dispense.getDrugLotNumber() + "'.");}
			}
			CommonUtil.setPsDoubleParam(ps, 16, drugLotNumber);
			String drugExpirationTs = CommonUtil.toTimestampStr(dispense.getDrugExpirationDate());
			CommonUtil.setPsStringParam(ps, 17, drugExpirationTs);
			CommonUtil.setPsStringParam(ps, 18, dispense.getSigDescriptionPatientLanguage());
			CommonUtil.setPsLongParam(ps, 19, dispense.getSequenceNumber());		
			CommonUtil.setPsLongParam(ps, 20, dispense.getPreviousDispenseDaysSupply());
			CommonUtil.setPsLongParam(ps, 21, dispense.getQuantityRemaining());
			CommonUtil.setPsStringParam(ps, 22, CommonUtil.convertBooleanToYesNoFlag(dispense.isIsHomeDeliveryFlag()));
			CommonUtil.setPsStringParam(ps, 23, storeNum);
			
			String adminRouteCode = (dispense.getAdministrationRouteCode()!=null) ? dispense.getAdministrationRouteCode().value() : null; //RteOfAdmin_CeRXTypKey
			Long adminRouteKey = getKeyFromCode(LT_RTEOFADMIN_CERXTYP, adminRouteCode);
			CommonUtil.setPsLongParam(ps, 24, adminRouteKey);
			
			String discontinuedReasonCode = dispense.getDiscontinuedReasonCode();
			Long discontinuedReasonKey = getKeyFromCode(LT_DISCNTDRSNTYP, discontinuedReasonCode);
			CommonUtil.setPsLongParam(ps, 25, discontinuedReasonKey);

			String txReqCommModeTypeKey = null; // Not mapped in mapping document
			CommonUtil.setPsLongParam(ps, 26, txReqCommModeTypeKey);

			Long TxRsmReasonTypeKey = null;
			if (dispense.getResumeReasonCode()!=null && dispense.getResumeReasonCode().getPrescriptionResumeReasonTypeCode() != null) 
			{
				String TxRsmReasonTypeCode = dispense.getResumeReasonCode().getPrescriptionResumeReasonTypeCode().value(); // TXRSMRSNTYPKEY
				TxRsmReasonTypeKey = getKeyFromCode(LT_TXRSMRSNTYP, TxRsmReasonTypeCode);
			}
			CommonUtil.setPsLongParam(ps, 27, TxRsmReasonTypeKey);
			
		//	Long txrTypeKey = null;
		//	String txrTypeCode = null; // TXRTYPKEY not used
		//	txrTypeKey = getKeyFromCode(LT_TXRTYP, txrTypeCode);
		//	CommonUtil.setPsLongParam(ps, 29, txrTypeKey);
			
			String subReasonCode = (dispense.getSubstitutionReason()!=null) ? dispense.getSubstitutionReason().value() : null;
			Long subReasonKey = getKeyFromCode(LT_TXSUBRSNTYP, subReasonCode);
			CommonUtil.setPsLongParam(ps, 28, subReasonKey);
			
			String dispenseTypeCode = dispense.getDispenseType();
			Long dispenseTypeKey = getKeyFromCode(LT_TXTYP, dispenseTypeCode);
			CommonUtil.setPsLongParam(ps, 29, dispenseTypeKey);
			
			String engagementTypeCode = (pharmacyChannel!=null && pharmacyChannel.getEngagment()!=null) ? pharmacyChannel.getEngagment().value() : null;
			Long engagementTypeKey = getKeyFromCode(LT_ENGMNTTYP, engagementTypeCode);
			CommonUtil.setPsLongParam(ps, 30, engagementTypeKey);
			
			String interchangeCode = (dispense.getInterchangeability()!=null) ? dispense.getInterchangeability().value() : null;
			Long interchangeKey = getKeyFromCode(LT_INTRCHGBLTYTYP, interchangeCode);
			CommonUtil.setPsLongParam(ps, 31, interchangeKey);
			
			String packageFormCode = dispense.getPackageForm();
			Long packageFormKey = getKeyFromCode(LT_PKGFRMTYP, packageFormCode);
			CommonUtil.setPsLongParam(ps, 32, packageFormKey);
			
			String pharmacyChannelTypeCode = (pharmacyChannel!=null && pharmacyChannel.getChannelType()!=null) ? pharmacyChannel.getChannelType().value() : null; //PHARMACYCHNLTYPKEY
			Long pharmacyChannelTypeKey = getKeyFromCode(LT_PHARMACYCHNLTYP, pharmacyChannelTypeCode);
			CommonUtil.setPsLongParam(ps, 33, pharmacyChannelTypeKey);
			
			String sigCode = dispense.getSigCode();
			CommonUtil.setPsStringParam(ps, 34, sigCode);
			
			Long txSourceChannekllTypeKey = getKeyFromCode(LT_TXNSRCECHNLTYP, txSourceChannekllTypeCode);
			CommonUtil.setPsLongParam(ps, 35, txSourceChannekllTypeKey);
			
			
			
			CommonUtil.setPsLongParam(ps, 36, rxKey);
			CommonUtil.setPsLongParam(ps, 37, locKey);
			CommonUtil.setPsLongParam(ps, 38, packKey);
			CommonUtil.setPsLongParam(ps, 39, compoundKey);
			CommonUtil.setPsLongParam(ps, 40, dosageFormKey);
			
			CommonUtil.setPsLongParam(ps, 41, personRoleKey);
			CommonUtil.setPsLongParam(ps, 42, initrKey);
//			CommonUtil.setPsLongParam(ps, 45, dispenserKey);
			CommonUtil.setPsLongParam(ps, 43, supervisorKey);
			CommonUtil.setPsLongParam(ps, 44, recorderKey);
			String pharmacyCommModeTypeCode = (pharmacyChannel!=null && pharmacyChannel.getCommunicationMode()!=null) ? pharmacyChannel.getCommunicationMode().value() : null; // COMMMODETYPKEY
			
			if( txSourceChannekllTypeCode != null && pharmacyCommModeTypeCode == null )
			{
				
			}
			Long pharmacyCommModeTypeKey = getKeyFromCode(LT_COMMMODETYP, pharmacyCommModeTypeCode);
			CommonUtil.setPsLongParam(ps, 45, pharmacyCommModeTypeKey);
			
			CommonUtil.setPsLongParam(ps, 46, dispenseKey);
		}
	
//  2020-07-03  Started	
	private boolean newRequest(Timestamp dbUpdateTimeStamp,	XMLGregorianCalendar payloadUpdateTimeStamp) throws InvalidInputException {
		if (payloadUpdateTimeStamp == null) {
			throw new InvalidInputException("DispenseApi: UpdateTimeStamp is missing in payload request.");
		}		
		
//	    payloadUpdateTimeStamp.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
		Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
		boolean isUpdateRequestNew = dbUpdateTimeStamp == null
				|| updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp) >= 0;
				if(logger.isDebugEnabled()) {logger.debug("DispenseAPI. newRequest ....isUpdateRequestNew = " + isUpdateRequestNew);
				logger.debug("dbUpdateTimeStamp = " + dbUpdateTimeStamp + " updateTimestampJaxbDate= " + updateTimestampJaxbDate);	}	 
		return isUpdateRequestNew;
	}
//  2020-07-03  Ended	
}
