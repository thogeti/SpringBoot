package ca.sdm.cdr.jdbc.upsert.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.SpecialDrugProgram;
import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class SpecialDrugProgramApi extends CDRUpsert {

	private static Logger logger = LogManager.getLogger(SpecialDrugProgramApi.class);

	private final static String INSERTSQL = "INSERT INTO DRGSPCLPROG (SPCLDRGPROGTYPKEY, DRGKEY, DRGSPCLPROGKEY) VALUES(?,?,?)";

	public void upsertSpecialDrugProgram(Connection connection, SpecialDrugProgram program, Long drugKey) throws KeyNotFoundFromTableCacheException, NamingException, SQLException, IOException 
	{
		try {
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertSpecialDrugProgram, drug key : " + drugKey);			}

			Long programTypeKey;
			
			programTypeKey = getKeyFromCode(LT_SPCLDRGPROGTYP, program.value());
			Long programKey = FindUtil.findSpecialDrugProgram(connection, drugKey, programTypeKey);
			if (programKey==null) {
				insertSpecialDrugProgram(connection, programTypeKey, drugKey);
			} else {
				//nothing to update
			}
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally
		{
			super.close();
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertSpecialDrugProgram, drug key : " + drugKey);	}		
		}
	}
	
	private void insertSpecialDrugProgram(Connection connection, Long programTypeKey, Long drugKey) throws SQLException
	{
		Long specialDrugProgramKey = IdGenerator.generate(connection, "DRGSPCLPROG");
		ps = connection.prepareStatement(INSERTSQL);
		setPsLongParam(1, programTypeKey);
		setPsLongParam(2, drugKey);
		setPsLongParam(3, specialDrugProgramKey);
		ps.executeUpdate();
	}
}
