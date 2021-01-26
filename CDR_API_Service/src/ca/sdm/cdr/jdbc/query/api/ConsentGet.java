package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNSNTOVRDRSNTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_CNSNTRSNTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.shoppersdrugmart.rxhb.ehealth.Consent;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentOverrideReason;
import ca.shoppersdrugmart.rxhb.ehealth.ConsentReasonCode;

public class ConsentGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(ConsentGet.class);
	private final static String QUERYSQL = "SELECT "
			+ "CNSMRID, CNSNTEFFTIMESTAMP, CNSNTENDTIMESTAMP, PRDCRID, TXR, "
			+ " PTNTCNSNTKEY, CNSNTOVRDRSNTYPKEY, CNSNTRSNTYPKEY , CNSNTCRTTIMESTAMP, CNSNTUPTTIMESTAMP, CONSENTTYPE ,  " //removed PTNTID
			+" AGENTFRSTNM, AGENTLSTNM , AGENTRELATIONSHIP, CONSENTPROVIDERTYPE, CONSENTPROVIDERTYPE , USERID , NOTIFMETHOD "
			+ "FROM PTNTCNSNT "
			+ "WHERE PTNTKEY = ?";
	
	public List<Consent> fetch(Connection connection, Long patientKey) throws CDRInternalException, SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		try {
			return populate(connection, patientKey);
		} catch (CDRInternalException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
		}
	};
	
	private List<Consent> populate(Connection connection, Long patientKey) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException
	{
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: ConsentGet.populate. patientKey : " + patientKey);}
		
		List<Consent> consentList = new ArrayList<Consent>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		try {
		preparedStatement=connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, patientKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: ConsentGet. patientKey : " + patientKey );}
		 resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: ConsentGet. patientKey : " + patientKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		while(resultSet.next())
		{
			Consent consent = new Consent();
			consent.setConsentEffectiveTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CNSNTEFFTIMESTAMP"), true));
			consent.setConsentEndTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CNSNTENDTIMESTAMP"), true));
			Long consentOverrideReasonKey = CommonUtil.getLong("CNSNTOVRDRSNTYPKEY",resultSet);
			if(consentOverrideReasonKey != null)
			{	
			String consentOverrideReasonCode = getCodeFromKey(LT_CNSNTOVRDRSNTYP, consentOverrideReasonKey);
			consent.setConsentOverrideReasonCode(ConsentOverrideReason.fromValue(consentOverrideReasonCode));
			};
			consent.setConsumerId(resultSet.getString("CNSMRID"));
			consent.setProducerId(resultSet.getString("PRDCRID"));
			//Added as part of SmartNotification
			Long consentRsnTypeKey = CommonUtil.getLong("CNSNTRSNTYPKEY",resultSet);
			
			if(consentRsnTypeKey != null){
				String cnsntRsnTypCode = getCodeFromKey(LT_CNSNTRSNTYP,  consentRsnTypeKey);
				consent.setConsentReasonCode(ConsentReasonCode.fromValue(cnsntRsnTypCode));
			};
			consent.setConsentType(resultSet.getString("CONSENTTYPE"));
			consent.setConsentCreateTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CNSNTCRTTIMESTAMP"), true));
			consent.setConsentUpdateTimestamp(CommonUtil.getXMLGregorianCalendar(resultSet.getDate("CNSNTUPTTIMESTAMP"), true));
		//	consent.setAgentName(getString("CAREGIVERNAME")); // ams
			consent.setAgentFirstName(resultSet.getString("AGENTFRSTNM"));
			consent.setAgentLastName(resultSet.getString("AGENTLSTNM"));
			consent.setAgentRelationship(resultSet.getString("AGENTRELATIONSHIP"));
			consent.setConsentProvider(resultSet.getString("CONSENTPROVIDERTYPE"));
			consent.setConsentUserId(resultSet.getString("USERID"));
	    	consent.setNotificationMethod(resultSet.getString("NOTIFMETHOD"));
			consentList.add(consent);
		};
		}finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: ConsentGet.populate. patientKey : " + patientKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		return consentList;
	}
	
}
