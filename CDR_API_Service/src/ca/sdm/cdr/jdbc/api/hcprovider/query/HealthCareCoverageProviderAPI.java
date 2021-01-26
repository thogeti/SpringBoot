package ca.sdm.cdr.jdbc.api.hcprovider.query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.PatientNotFoundException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.query.api.CDRGet;
import ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider.GetCoverageCard;
import ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider.GetCoverageCardResponse;
import ca.shoppersdrugmart.rxhb.drx.healthcarecoverageprovider.GetMasterCardListResponse;
import ca.shoppersdrugmart.rxhb.ehealth.CoverageCard;
import ca.shoppersdrugmart.rxhb.ehealth.InsuranceCoverage;

public class HealthCareCoverageProviderAPI extends CDRGet {
	
	
	private static Logger logger = LogManager.getLogger(HealthCareCoverageProviderAPI.class);
	
	private String prepareGetCoverageCardSQL(  )
	{
		
		String sqlQuery = 
				" SELECT Ptnt.PtntKey as Ptnt_PtntKey , PC.CRRID as PC_CRRID, PC.GRPID as PC_GRPID, PC.CLNTID as PC_CLNTID, PC.PLANNUM as PC_PLANNUM, PC.ACTFLG as PC_ACTFLG, " +
				" PC.PRIORITY as PC_PRIORITY,  PC.CARDID as PC_CARDID, PC.PTNTCVRGID as PC_PTNTCVRGID, " + 
				" PC.INSCVRGID as PC_INSCVRGID, PC.TPID as PC_TPID, PC.PTNTCVRGKEY as PC_PTNTCVRGKEY, PC.BNFTCARDHLDRRLTNSHPTYPKEY as PC_BNFTCARDHLDRRLTNSHPTYPKEY  " +
				" FROM " +
				" 	ptnt ptnt left outer join ptntCvrg PC   on ptnt.PtntKey = PC.PtntKey " + 
				" WHERE    " + 
			    " 		ptnt.CNSMRID = ? " +
			    " and 	ptnt.storeNum = ? ";

		
		if (logger.isTraceEnabled())  {logger.trace("GetCoverageCard Query : \n" + sqlQuery) ;}
		return sqlQuery ;
		
	}
	
	private String prepareGetMasterCardListSQL(  )
	{
		
		String sqlQuery = 
				" select hdm_tp_card.CardId,hdm_tp_card.TPIN_ID,hdm_tp_card.CardNm,hdm_tp_card.CDLA,hdm_tp_card.PprFlg,hdm_tp_card.GenFlg,hdm_tp_card.CardFrntImg,hdm_tp_card.FrntImgNm, " + 
				" 		hdm_tp_card.CardImgBk,hdm_tp_card.BkImgNm,hdm_tp_card.Width,hdm_tp_card.Height,hdm_tp_card.CNStatFlg,hdm_tp_card.CNCrtUsrID,hdm_tp_card.CNCrtDttime," +
				"		hdm_tp_card.CNUpDtUsrID,hdm_tp_card.CNUpDtDttime, hdm_tp_card.CNTrnsmtDt,hdm_tp_card.hDM_TP_CARDKey , hdm_tp.TPNmEng " +
				" from hdm_tp_card , hdm_tp " +
				" where hdm_tp_card.TPIN_ID = hdm_tp.TPId ";
	
		if (logger.isTraceEnabled())  {logger.trace("GetMasterCardList Query : \n" + sqlQuery) ;}
		return sqlQuery ;
		
	}
	
	public GetCoverageCardResponse getCoverageCardDetails(Connection connection, GetCoverageCard getCoverageCard) throws Exception 
	{
		if (logger.isInfoEnabled())  {logger.info( "Start getCoverageCardDetails : storeNumber " + getCoverageCard.getStoreNumber()  + " , PatientId : " + getCoverageCard.getPatientId() );}
		
		try
		{
			String storeNumber = getCoverageCard.getStoreNumber();
			storeNumber = CommonUtil.createStoreLeadingZeros(storeNumber);  
			boolean isData = false;
			
			
			GetCoverageCardResponse getCoverageCardResponse = new GetCoverageCardResponse();
			String query = prepareGetCoverageCardSQL();
			
			preparedStatement = connection.prepareStatement(query);
			CommonUtil.setPsStringParam(preparedStatement, 1, getCoverageCard.getPatientId() );
			CommonUtil.setPsStringParam(preparedStatement, 2, storeNumber);
	
			resultSet= preparedStatement.executeQuery();
			int counter = 1;
	    	while( resultSet.next() ) 
			
	    	{
	    		if(logger.isDebugEnabled()) {logger.debug("Processing record : " + counter);}
	    		isData = true;
	    		InsuranceCoverage insuranceCoverage = PopulateJAXB.populateCoverage(resultSet,"PC");
	    		if( insuranceCoverage  != null )
	    		{
	    			getCoverageCardResponse.getInsuranceCoverage().add(insuranceCoverage);
	    		}
			
			}
			
	
	    	if( isData == false )
	    	{
	    		throw new PatientNotFoundException(storeNumber,getCoverageCard.getPatientId());
	    	}
	    	
			if (logger.isInfoEnabled())  {logger.info( "End getCoverageCardDetails");}
			
			return getCoverageCardResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			super.close();
		}
	}
	
	
	public GetMasterCardListResponse getMasterCardListDetails(Connection connection ) throws Exception{
		
		if (logger.isInfoEnabled())  {logger.info( "Start getMasterCardListDetails " );}

		GetMasterCardListResponse getMasterCardListResponse= new GetMasterCardListResponse();
		try
		{
			String query = prepareGetMasterCardListSQL();
			
			preparedStatement = connection.prepareStatement(query);
	    	resultSet = preparedStatement.executeQuery();
	    	while( resultSet.next() ) 
			{
	    		CoverageCard coverageCard=new CoverageCard();
	    		coverageCard.setCardBackImage(resultSet.getBytes("CARDIMGBK".toUpperCase()));
	    		coverageCard.setCardFrontImage(resultSet.getBytes("CARDFRNTIMG".toUpperCase()));
	    		coverageCard.setCardName(resultSet.getString("CARDNM".toUpperCase()));
	    		coverageCard.setConsumerId(resultSet.getString("CARDID".toUpperCase()));
	    		coverageCard.setIsActive( CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("CNStatFlg")) );
	    		coverageCard.setThirdPartyName(resultSet.getString("TPNMENG"));
	    		
	    		getMasterCardListResponse.getCoverageCard().add(coverageCard);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		finally
		{
	    	super.close();
			if (logger.isInfoEnabled())  {logger.info( "End getMasterCardListDetails");}
		}
		
		if( getMasterCardListResponse.getCoverageCard().size()<=0)
			return null;
		return getMasterCardListResponse;
	}
}
