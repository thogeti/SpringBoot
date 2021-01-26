package ca.sdm.cdr.jdbc.query.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Dispenser;

public class TxDispenserGet  extends CDRGet{

	private static Logger logger = LogManager.getLogger(TxDispenserGet.class);
	private final static String querySQL = "SELECT PRSNRLKEY FROM TXR WHERE TXKEY = ?";
	
	public List<Dispenser> fetch(Connection connection, Long dispenseKey) throws SQLException, CDRInternalException, NamingException, IOException
	{
		try {
			return populate(connection, dispenseKey);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (CDRInternalException e) {
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
		}
	}
	
	private List<Dispenser> populate(Connection connection, Long dispenseKey) throws SQLException, CDRInternalException, NamingException, IOException
	{
		Long timer = System.currentTimeMillis();
//		logger.info("StartApiCall: TxDispenserGet.populate. dispenseKey : " + dispenseKey );
		List<Dispenser> dispenserList = new ArrayList<Dispenser>();		
		PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
		preparedStatement.setLong(1, dispenseKey);
		Long querytimer = System.currentTimeMillis();
//		logger.debug("StartExecuteQuery: TxDispenserGet. dispenseKey : " + dispenseKey );
		ResultSet resultSet = preparedStatement.executeQuery();
//		logger.debug("EndExecuteQuery: TxDispenserGet. dispenseKey : " + dispenseKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;
		while (resultSet.next()) {
			Long personRoleKey = CommonUtil.getLong("PRSNRLKEY",resultSet);
			if(personRoleKey!=null)
			{
				PersonRoleViewGet personRoleViewGet = new PersonRoleViewGet();
				Dispenser dispenser = personRoleViewGet.fetchDispenser(connection, personRoleKey);
				if(dispenser!=null)
					dispenserList.add(dispenser);
			}
		}
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
//		logger.info("EndApiCall: TxDispenserGet.populate. dispenseKey : " + dispenseKey  + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");
		return dispenserList;
	}
}
