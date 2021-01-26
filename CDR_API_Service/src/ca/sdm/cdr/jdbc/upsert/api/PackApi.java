package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

/**
 * Pack API used to persist Pack entity.
 * 
 * @author LTrevino
 *
 */
public class PackApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(PackApi.class);
	   
	private final static String UPDATESQL =
	"UPDATE PACK SET " +
	"	PACKID=?,	ALTRNTVPACKSZ=?,	ALTRNTVPACKSZUOM=?,	STRNGTH=?,			GTIN=?, "			+
	"	ISACTFLG=?,	CNSMRID=?,			ISCRNTFLG=?,		PRDCRID=?,			MFCTRDISCNTDDT=" + CommonUtil.getPsToDateFunctionStr() + ", "	+
	"	PACKSZ=?,	MSTRID=?,			PACKSZUOMTYPKEY=?,	STRNGTHUOMTYPKEY=?,	DRGKEY=?  ,CDEFFENDDT=NVL(" +CommonUtil.getPsToDateFunctionStr() +",CDEFFENDDT )" + 
	" WHERE PACKKEY=?";
	
	private final static String INSERTSQL = 
	"INSERT INTO PACK (" +
		"	PACKID,		ALTRNTVPACKSZ,	ALTRNTVPACKSZUOM,	STRNGTH,			GTIN, "				+
		"	ISACTFLG,	CNSMRID,		ISCRNTFLG,			PRDCRID,			MFCTRDISCNTDDT, "	+
		"	PACKSZ,		MSTRID,			PACKSZUOMTYPKEY,	STRNGTHUOMTYPKEY,	DRGKEY, "	+
		"	CDEFFENDDT,  PACKKEY ,CDEFFSTRTDT  "	+			
	") VALUES (" +
		" ?, ?, ?, ?, ?, ?, ?, ?, ?, " + CommonUtil.getPsToDateFunctionStr() +
		",?, ?, ?, ?, ?," + CommonUtil.getPsToDateFunctionStr() + ", ? ," + CommonUtil.getPsToDateFunctionStr() +
	")";
	
	/**
	 * Attempt to persist a Pack instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Pack doesn't exist, then insert a new record.
	 * If the Pack exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param pack			Pack instance.
	 * 
	 * @return				PackKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 * @throws InvalidInputException 
	 */
	public Long upsertPack(Connection connection, String drugConsumerId, String storeNum, Pack pack,Map <String, Long> drgPK,Map <String, Timestamp> drgTime,Map <String, Long> packPK,Map <String, Timestamp> packTime) throws SQLException, KeyNotFoundFromTableCacheException, EntityNotFoundException, InvalidInputException, NamingException, IOException {
		try {
			if(pack != null)
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertPack. pack consumer id: " + pack.getConsumerId() + ", store number " + storeNum + ", drug consumerId : " + drugConsumerId);}
			Long drugKey = null;
			Drug drug = pack.getDrug();
			if (drug != null) {
				DrugApi drugApi;
				drugApi = new DrugApi();
				drugKey = drugApi.upsertDrug(connection, storeNum, drug,drgPK,drgTime);
			}
			if (drugKey == null || drugKey.intValue() < 1) {
				throw new InvalidInputException("Pack [CID: " + pack.getConsumerId() + "] does not contain a drug.");
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			// Persist main entity
			FindUtil.findPackData(connection, pack.getConsumerId(), storeNum,pack,packPK,packTime);
			Long packKey = (Long)packPK.get(pack.getConsumerId() + storeNum);
			Timestamp lastUpdatedTimeStampdb = (Timestamp)packTime.get(pack.getConsumerId() + storeNum);
	//		boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew2(lastUpdatedTimeStampdb,pack.getLastUpdateTimestamp());
			boolean isUpdateRequestNew = newRequest(lastUpdatedTimeStampdb, pack.getLastUpdateTimestamp());
			if (packKey == null) {
				packKey = insertPack(connection, storeNum, pack, drugKey);
				packPK.put((pack.getConsumerId() + storeNum), packKey );
				packTime.put((pack.getConsumerId() + storeNum),CommonUtil.toTimestamp(pack.getLastUpdateTimestamp()) );
			} else {
				if(isUpdateRequestNew==true) {
				updatePack(connection, storeNum, packKey, pack, drugKey);
				}
			}

			return packKey;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if(pack != null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertPack. pack consumer id: " + pack.getConsumerId() + ", store number " + storeNum + ", drug consumerId : " + drugConsumerId);}
		}
	}
	
	/**
	 * Attempt to update a Pack instance, which is matched by packKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param packKey		Pack Key.
	 * @param pack			Pack instance.
	 * 
	 * @return				PackKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	public Long updatePack(Connection connection, String storeNum, Long packKey, Pack pack, Long drugKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		PrescriptionApi prescriptionApi=new PrescriptionApi();
		HashMap<String,String> drugMap=(HashMap<String, String>) prescriptionApi.getCrxDrugAdherance(connection, storeNum, pack.getGTIN());
		setPsParams(storeNum, packKey, pack, drugKey,drugMap);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Pack intances updated: " + res + ". PackKey: " + packKey + ", consumerId: '" + pack.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return packKey;
	}

	/**
	 * Attempt to insert a new Pack into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param pack			Pack instance.
	 * 
	 * @return				PackKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	public Long insertPack(Connection connection, String storeNum, Pack pack, Long drugKey) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long packKey = IdGenerator.generate(connection, "PACK");
    	PrescriptionApi prescriptionApi=new PrescriptionApi();
		HashMap<String,String> drugMap=(HashMap<String, String>) prescriptionApi.getCrxDrugAdherance(connection, storeNum, pack.getGTIN());
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, packKey, pack, drugKey,drugMap);
		String startTimeStamp = CommonUtil.toTimestampStr(pack.getLastUpdateTimestamp());
		setPsStringParam(18,startTimeStamp);
		int res = ps.executeUpdate();
		if (logger.isInfoEnabled())  {logger.info("Total Pack intances updated: " + res + ". PackKey: " + packKey + ", consumerId: '" + pack.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return packKey;
	}
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param packKey		Pack Key.
	 * @param pack			Pack instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	
	private void setPsParams(String storeNum, Long packKey, Pack pack, Long drugKey,HashMap<String,String> drugMap) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		
		setPsLongParam(1, packKey);  // Pack Id
		setPsLongParam(2, String.valueOf(pack.getAlternativepacksize()));
		setPsStringParam(3, String.valueOf(pack.getAlternativepacksizeunitofmeasure()));
		 
		setPsStringParam(5, pack.getGTIN());
		setPsStringParam(6, CommonUtil.convertBooleanToYesNoFlag(pack.isIsActiveFlag()));
		setPsStringParam(7, pack.getConsumerId());
		setPsStringParam(8, CommonUtil.convertBooleanToYesNoFlag(pack.isIsCurrentFlag()));
		setPsStringParam(9, pack.getProducerId());
		String manufacturerDiscontinuedDateStr = CommonUtil.toTimestampStr(pack.getManufacturerDiscontinuedDate());
		setPsStringParam(10, manufacturerDiscontinuedDateStr);
		setPsBigDecimalParam(11, pack.getPackSize());
		setPsStringParam(12, pack.getMasterid());
		
		Long packSizeUomTypeKey = null;
		String packSizeUoMTypeCode = (pack.getPackSizeUOMCode()!=null) ? pack.getPackSizeUOMCode().getPackSizeUoMTypeCode() : null;
		if (packSizeUoMTypeCode!=null) 
			packSizeUomTypeKey = getKeyFromCode(LT_PACKSZUOMTYP, packSizeUoMTypeCode);
		
		setPsLongParam(13, packSizeUomTypeKey);
		
		
		//TE97_024
		Long strengthUomTypeKey = null;
		if(drugMap.isEmpty()){
			setPsStringParam(4, pack.getStrength()); 
				String strengthUomCode = pack.getStrengthUOMCode();
		if (strengthUomCode!=null) {
			strengthUomTypeKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUomCode);
			if(strengthUomTypeKey==null){
				
			}
		}
		setPsLongParam(14, strengthUomTypeKey);
		}
		else{
			setPsStringParam(4, drugMap.get("STRENTH"));
			String strengthUomCode = drugMap.get("STRENTHUOM");
			if (strengthUomCode!=null) 
				strengthUomTypeKey = getKeyFromCode(LT_STRNGTHUOMTYP, strengthUomCode);
			setPsLongParam(14, strengthUomTypeKey);
		}
		
		setPsLongParam(15, drugKey);
		String lastUpdateTime = CommonUtil.toTimestampStr(pack.getLastUpdateTimestamp());
		setPsStringParam(16,lastUpdateTime);
		setPsLongParam(17, packKey);
		
	}
	private boolean newRequest(Timestamp            dbUpdateTimeStamp,
			XMLGregorianCalendar payloadUpdateTimeStamp) {
		if (payloadUpdateTimeStamp == null) {
			return true;
		}
	//	payloadUpdateTimeStamp.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
		Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
		boolean isUpdateRequestNew = dbUpdateTimeStamp == null
				|| updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp) > 0;
				if(logger.isDebugEnabled()) {logger.debug("PackApi.newRequest()...isUpdateRequestNew ="+isUpdateRequestNew);
				logger.debug("dbUpdateTimeStamp ="+dbUpdateTimeStamp +"updateTimestampJaxbDate= "+updateTimestampJaxbDate);}
		return isUpdateRequestNew;
	}
	
}
