package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_STORE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.DivisionTypeNotFoundException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.constant.LookupTableConstants;
import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindEnum;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.bean.UpsertResponse;
import ca.shoppersdrugmart.rxhb.ehealth.Division;
import ca.shoppersdrugmart.rxhb.ehealth.Store;
import generated.RxHBBusinessEventActionEnum;

public class StoreApi extends CDRUpsert {

	private static Logger logger = LogManager.getLogger(StoreApi.class);

	private final static String INSERT_SQL = " Insert into STORE (STOREID,STORENUM,STORENM,CNSMRID,PRDCRID,STOREKEY,CNTCTMTHDKEY,LOCKEY,DIVISION,CDEFFSTRTDT,CDEFFENDDT) "
			+ "values ('Y',?,? ,?,null,STORE_SEQ.nextval, ? , ? , ? ,"+ CommonUtil.TO_TIMESTAMP_TZ + " ,"+ CommonUtil.TO_TIMESTAMP_TZ + " ) ";
	private final static String UPDATE_SQL = " update STORE set  STORENM= ? , CNSMRID = ? ,DIVISION = ? , CDEFFENDDT = "+CommonUtil.TO_TIMESTAMP_TZ+" WHERE storeNum = ? ";
	String jndiVersion=null;
	public StoreApi() {
		jndiVersion = TableCacheSingleton.JNDI_VERSION; 
	}

	public UpsertResponse upsertStore(Connection connection, Store store) throws Exception {

		Long storeKey = null;
		Long locKey = null;
		
		UpsertResponse upsertResponse = new UpsertResponse();
		String storeNumber = CommonUtil.createStoreLeadingZeros(store.getStorenumber());

		if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertStore. store number " + storeNumber);}

		RxHBBusinessEventActionEnum rxHBBusinessEventActionEnum = null;
		try {
			 Map<String, Object> storeData = FindUtil.findStoreData(connection, storeNumber);
			storeKey = (Long)storeData.get("STOREKEY");
			locKey = (Long)storeData.get("LOCKEY");
			Timestamp updateTimestampDb = (Timestamp) storeData.get("CDEFFENDDT");
			boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew2(updateTimestampDb, store.getLastUpdateTimestamp());
			if(isUpdateRequestNew==false) {
				rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.UPDATE;
				upsertResponse.setStoreKey(storeKey);
				upsertResponse.setRxHBBusinessEventActionEnum(rxHBBusinessEventActionEnum);
				return upsertResponse;
				
			}
           
			if (storeKey == null) {
				storeKey = insertStore(connection, store, storeNumber );
				rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.CREATE;
			} else {
				update(connection, store, storeNumber , storeKey , locKey);
				rxHBBusinessEventActionEnum = RxHBBusinessEventActionEnum.UPDATE;
			}
			
			/* 
			 * the table cache update must be here, to address multi treading
			 * upsert, to make sure the store would be added to the cache.
			 */
		//	TableCacheSingleton.getInstance(jndiVersion).refreshTable(LookupTableConstants.LT_STORE, storeKey, storeNumber);
			storeKey = FindUtil.findStoreKey(connection, storeNumber);
			upsertResponse.setStoreKey(storeKey);
			upsertResponse.setRxHBBusinessEventActionEnum(rxHBBusinessEventActionEnum);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertStore. store number " + storeNumber);}
		}
		return upsertResponse;
	}

	private Long insertStore(Connection connection, Store store, String storeNumber ) throws Exception {
		Long storeKey = null;
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: insert store. store number " + storeNumber);}
			LocationApi locationApi = new LocationApi();

			Long locationKey = locationApi.insert(connection);
			ContactMethodApi contactMethodApi = new ContactMethodApi();
			Long contactMethodKey = contactMethodApi.upsertContactMethodByLocationKey(connection, storeNumber,
					locationKey, store.getAddress());
			//Insert Division
			String divisionEnum = store.getDivision() != null ? store.getDivision().name() : null;
			if (!FindEnum.contains(Division.class, divisionEnum)) {
				divisionEnum = "SDM";
				//logger.error("Division  " + divisionEnum + " not found");
				//throw new DivisionTypeNotFoundException(storeNumber ,store.getConsumerId() , null ,null);
			}

			ps = connection.prepareStatement(INSERT_SQL, new String[] { "STOREKEY" });
			ps.setString(1, storeNumber);
			ps.setString(2, store.getStoreName());
			ps.setString(3, storeNumber);
			ps.setLong(4, contactMethodKey);
			ps.setLong(5, locationKey);
			ps.setString(6,divisionEnum); //Divison
			ps. setString(7, CommonUtil.toTimestampStr(store.getLastUpdateTimestamp()));
			ps.setString(8,CommonUtil.toTimestampStr(store.getLastUpdateTimestamp()));

			int res = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				storeKey = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: insert store. store number " + storeNumber);}
		}

		return storeKey;
	}

	private void update(Connection connection, Store store, String storeNumber, long storeKey , long locationKey ) throws Exception {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: update store. store number " + storeNumber);}
			LocationApi locationApi = new LocationApi();

//			Long locationKey = locationApi.insert(connection);
			ContactMethodApi contactMethodApi = new ContactMethodApi();
			Long contactMethodKey = contactMethodApi.upsertContactMethodByLocationKey(connection, storeNumber,
					locationKey, store.getAddress());
			//Insert Division
			
			String divisionEnum = store.getDivision() != null ? store.getDivision().name() : null;
			
			if (!FindEnum.contains(Division.class, divisionEnum)) {
				divisionEnum = "SDM";
				//logger.error("Division  " + divisionEnum + " not found");
				//throw new DivisionTypeNotFoundException(storeNumber ,store.getConsumerId() , null ,null);
			}

			ps = connection.prepareStatement(UPDATE_SQL);
			ps.setString(1, store.getStoreName());
			ps.setString(2, store.getConsumerId());
			
			ps.setString(3, divisionEnum);
			ps.setString(4,CommonUtil.toTimestampStr(store.getLastUpdateTimestamp()));
			ps.setString(5, storeNumber);
			int res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: insert store. store number " + storeNumber);}
		}

	}

	/*
	 * this method, first looks up the store in the DB, if not found then it
	 * inserts. this is required for cache update.
	 */
	private Long insertIfNotFound(Connection connection, Store store) throws Exception {

		Long storeKey = null;
		String storeNumber = store.getStorenumber();
		storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);

		if (logger.isInfoEnabled())  {logger.info("StartApiCall: insertIfNotFound. store number " + storeNumber);}

		try {
			storeKey = FindUtil.findStoreKey(connection, storeNumber);
			if (storeKey == null) {
				storeKey = insertStore(connection, store, storeNumber);
			}
	//		TableCacheSingleton.getInstance(jndiVersion).refreshTable(LookupTableConstants.LT_STORE, storeKey, storeNumber);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: insertIfNotFound. store number " + storeNumber);}
		}
		return storeKey;
	}

	public void verifyStore(Connection connection, Store store) throws Exception {
		String storeNumber = CommonUtil.createStoreLeadingZeros(store.getStorenumber());

		Long storeKey = null;
		try {
			//storeKey = TableCacheSingleton.getInstance(jndiVersion).getKeyFromCode(LT_STORE, storeNumber);
			storeKey = FindUtil.findStoreKey(connection, storeNumber);
	//	} catch (KeyNotFoundFromTableCacheException e1) {
		} catch (Exception e1) {
			// the store does not exist in the cache. insert the store into the
			// Store table.

			if (logger.isInfoEnabled())  {logger.info("Store : " + storeNumber
					+ " does not exist in the Store cache and table. start inserting the store data into the CDR database");}
			StoreApi storeApi = new StoreApi();
			storeKey = storeApi.insertIfNotFound(connection, store);
		}
	}
}
