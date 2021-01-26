package ca.sdm.cdr.jdbc.upsert.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.InvalidInputException;
import ca.shoppersdrugmart.rxhb.ehealth.ManufacturerRecall;

/**
 * ManufacturerRecall API used to persist ManufacturerRecall entity.
 * 
 * @author LTrevino
 *
 */

public class ManufacturerRecallApi extends CDRUpsert {
	private static Logger logger = LogManager.getLogger(ManufacturerRecallApi.class);
	private final static String MERGESQL = 
			" MERGE INTO MfctrDrgRecall target   " + 
					"  USING    " + 
					"  ( select MfctrDrgRecallKey  from    " + 
					"       (select MfctrDrgRecallKey from MfctrDrgRecall where DRGKEY = ? and LOTNUM = ? AND MFCTRKEY = ? AND RECALLDT= TO_TIMESTAMP_TZ(?,  'YYYY-MM-DD')" + 
					"            union all   " + 
					"            select NULL MfctrDrgRecallKey from dual    " + 
					"       )where rownum=1   " + 
					"       order by MfctrDrgRecallKey desc   " + 
					"  )  src    " + 
					"  ON  ( src.MfctrDrgRecallKey = target.MfctrDrgRecallKey )   " + 
					"    WHEN NOT MATCHED THEN   " + 
					"      INSERT (MfctrDrgRecallKEY , DRGKEY , LOTNUM , MFCTRKEY , RECALLDT)   " + 
					"      VALUES (MfctrDrgRecall_seq.nextval, ? , ? , ? , TO_TIMESTAMP_TZ(?, 'YYYY-MM-DD') )  " ;

	public void upsertManufacturerRecall(Connection connection, Long drugKey, Long manufacturerKey, ManufacturerRecall  manufacturerRecall) throws InvalidInputException, SQLException {
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: mergeManufacturerRecall. drugKey : " + drugKey + ", manufacturerKey : " + manufacturerKey);		}
			List<Integer> lotNumbers = manufacturerRecall.getLotnumber();
			if (lotNumbers == null) {
				throw new InvalidInputException("Recall lot number required.");
			}

			XMLGregorianCalendar recallDate = manufacturerRecall.getRecalldate();
			for (Integer lotNumber : lotNumbers) {
				mergeManufacturerRecall(connection, drugKey, manufacturerKey, lotNumber, recallDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: mergeManufacturerRecall. drugKey : " + drugKey + ", manufacturerKey : " + manufacturerKey);	}	
		}
	}

	private void mergeManufacturerRecall(Connection connection, Long drugKey, Long manufacturerKey, int lotNumber , XMLGregorianCalendar recallDate ) throws SQLException {
		ps = connection.prepareStatement(MERGESQL);
		
    	setPsLongParam(1, drugKey); 
    	setPsIntParam(2, lotNumber); 
    	setPsLongParam(3, manufacturerKey); 
    	setPsXMLGregorianCalendarToDateParam(4, recallDate); 

    	setPsLongParam(5, drugKey); 
    	setPsIntParam(6, lotNumber); 
    	setPsLongParam(7, manufacturerKey); 
    	setPsXMLGregorianCalendarToDateParam(8, recallDate); 
    	
		ps.executeUpdate();
	}
}

