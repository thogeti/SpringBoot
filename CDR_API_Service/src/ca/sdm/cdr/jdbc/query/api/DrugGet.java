package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DINTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGFLVRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.shoppersdrugmart.rxhb.ehealth.Colour;
import ca.shoppersdrugmart.rxhb.ehealth.DINType;
//import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.DrugNameAlternative;
import ca.shoppersdrugmart.rxhb.ehealth.Flavour;
//import ca.shoppersdrugmart.rxhb.ehealth.Manufacturer;
import ca.shoppersdrugmart.rxhb.ehealth.ManufacturerRecall;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;
import ca.shoppersdrugmart.rxhb.ehealth.RouteOfAdmin;
import ca.shoppersdrugmart.rxhb.ehealth.Schedule;
import ca.shoppersdrugmart.rxhb.ehealth.Shape;
import ca.shoppersdrugmart.rxhb.ehealth.SpecialDrugProgram;

import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class DrugGet extends CDRGet {
	private static Logger logger = LogManager.getLogger(DrugGet.class);
 
	private final static String QUERYSQL = "SELECT "
			+ "DRG.DRGID DRG_DRGID, "
			+ " DRG.EQUVNLTTO DRG_EQUVNLTTO,"
	//		+ " DRG.ALTRNTVNM DRG_ALTRNTVNM,"
			+ " DRG.TRDNMALTRNTV DRG_TRDNMALTRNTV,"
			+ " DRG.BRANDDRG DRG_BRANDDRG, "
			+ " DRG.TRDREF DRG_TRDREF, "
			+ " DRG.DIN DRG_DIN, "
			+ " DRG.CNSMRID DRG_CNSMRID,"
			+ " DRG.MONOGRAPH DRG_MONOGRAPH, "
			+ " DRG.PRDCRID DRG_PRDCRID, "
			+ " DRG.FRMVARIANT DRG_FRMVARIANT, "
			+ " DRG.ISDEVICE DRG_ISDEVICE, "
			+ " DRG.ADLTDSGMAXQTY DRG_ADLTDSGMAXQTY, "
			+ " DRG.CHLDDSGMINQTY DRG_CHLDDSGMINQTY, "
			+ " DRG.ADLTDSGMINQTY DRG_ADLTDSGMINQTY, "
			+ " DRG.CHLDDSGMAXQTY DRG_CHLDDSGMAXQTY, "
			+ " DRG.ISIMMNZTN DRG_ISIMMNZTN, "
			+ " DRG.ISPROP DRG_ISPROP,"
			+ " DRG.ISREPORTABLE DRG_ISREPORTABLE, "
			+ " DRG.ISWRTN DRG_ISWRTN,"
			+ " DRG.MRKNG DRG_MRKNG, "
			+ " DRG.CHMCLLBLNMENG DRG_CHMCLLBLNMENG, "
			+ " DRG.CHMCLLBLNMFR DRG_CHMCLLBLNMFR,"
			+ " DRG.TRDNMENG DRG_TRDNMENG, "
			+ " DRG.TRDNMFR DRG_TRDNMFR,"
			+ " DRG.DRGDESCR DRG_DRGDESCR,"
		//	+ " DRG.STORENUM DRG_STORENUM,"
			+ " DRG.ADMINRTETYPKEY DRG_ADMINRTETYPKEY, "
			+ " DRG.DINTYPKEY DRG_DINTYPKEY, "
			+ " DRG.DRGFLVRTYPKEY DRG_DRGFLVRTYPKEY,"
			+ " DRG.DRGSCHDLTYPKEY DRG_DRGSCHDLTYPKEY,"
			+ " DRG.DRGSHPTYPKEY DRG_DRGSHPTYPKEY, "
			+ " DRG.SPCLDRGPROGTYPKEY DRG_SPCLDRGPROGTYPKEY, "
		//	+ " DRG.SUBRSNTYPKEY DRG_SUBRSNTYPKEY, "
		//	+ " DRG.MFCTRKEY DRG_MFCTRKEY,"
		//	+ " DRG.DSGFRMKEY DRG_DSGFRMKEY, DRG.DRGPRDGRPTYPKEY DRG_DRGPRDGRPTYPKEY, "
			+ " MLCL.MLCLKEY MLCL_MLCLKEY , "
		    + " DRGPRODGRP.PRODGRP DRGPRODGRP_PRODGRP "
			+ "FROM DRG "
			+ "LEFT OUTER JOIN MLCL MLCL ON DRG.MLCLKEY = MLCL.MLCLKEY "
			+ "LEFT OUTER JOIN DRGPRODGRP DRGPRODGRP ON DRG.CHMCLLBLNMENG = DRGPRODGRP.CHMCLNM "
			+ "WHERE DRG.DRGKEY = ?";

	private final static String QUERYSQL2 = "SELECT DRG.DRGKEY, "
			+ "MFCTRDRGRECALL.LOTNUM RECALL_LOTNUM, "
			+ "MFCTRDRGRECALL.RECALLDT RECALL_RECALLDT, "
			+ "MFCTRDRGRECALL.MFCTRDRGRECALLKEY RECALL_MFCTRDRGRECALLKEY, "
		//	+ "MFCTRDRGRECALL.MFCTRKEY RECALL_MFCTRKEY, "
			+ "DRGCLR.DRGCLRTYPKEY DRGCLR_DRGCLRTYPKEY, "
			+ "DRGSPCLPROG.SPCLDRGPROGTYPKEY DRGSPCLPROG_SPCLDRGPROGTYPKEY "
			+ "FROM DRG "
			+ "LEFT OUTER JOIN MFCTRDRGRECALL MFCTRDRGRECALL ON DRG.DRGKEY = MFCTRDRGRECALL.DRGKEY AND DRG.MFCTRKEY = MFCTRDRGRECALL.MFCTRKEY "
			+ "LEFT OUTER JOIN DRGCLR DRGCLR ON DRG.DRGKEY = DRGCLR.DRGKEY "
			+ "LEFT OUTER JOIN DRGSPCLPROG DRGSPCLPROG ON DRG.DRGKEY = DRGSPCLPROG.DRGKEY "
			+ "WHERE DRG.DRGKEY = ? ";
	
	
			
			
	public Drug fetch(Connection connection, Long drugKey) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			 
			   	 return populate(connection, drugKey);
			   
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
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
		}
	}

	private Drug populate(Connection connection, Long drugKey) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		Long timer = System.currentTimeMillis();
		if (logger.isInfoEnabled())  {logger.info("StartApiCall: DrugGet.populate. drugKey : " + drugKey);}
		Drug drug = null;
		PreparedStatement preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, drugKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DrugGet.querySQL drugKey : " + drugKey );}
		ResultSet resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DrugGet.querySQL drugKey : " + drugKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
		try {
		if (resultSet.next()) {
			drug = new Drug();
			Long adminRateTypeKey = CommonUtil.getLong("DRG_ADMINRTETYPKEY",resultSet);
			if (adminRateTypeKey!=null)
			{
				//added 60 prescribedDrug 
			//	String adminRteTypCdDescr = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, adminRateTypeKey);
				//1210
				String adminRteTypCdDescr = getCodeFromKey(LT_ADMINRTETYP, adminRateTypeKey);
				drug.setAdministrationRouteCode(RouteOfAdmin.fromValue(adminRteTypCdDescr));
			}
			
			
			//added 60 prescribedDrug updated QUERYSQL
			//1210
			/*Long routeOfAdminKey = getLong("DRG_RTEOFADMIN_CERXTYPKEY");
			if (routeOfAdminKey!=null)
			{
				String routeOfAdminDescr = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
				drug.setAdministrationRouteCode(RouteOfAdmin.fromValue(routeOfAdminDescr));
			}*/
			//1210
			//added 60 prescribedDrug
			
			
			if(resultSet.getString("DRG_EQUVNLTTO") != null || resultSet.getString("DRG_TRDNMALTRNTV") != null || resultSet.getString("DRG_TRDREF") != null){
				DrugNameAlternative drugNameAlternative = new DrugNameAlternative();
				drugNameAlternative.setEquivalentto(resultSet.getString("DRG_EQUVNLTTO"));
				drugNameAlternative.setTradenamealternative(resultSet.getString("DRG_TRDNMALTRNTV"));
				drugNameAlternative.setTradereference(resultSet.getString("DRG_TRDREF"));
				drug.setAlternativename(drugNameAlternative);
			
			}
			
			drug.setBranddrug(resultSet.getString("DRG_BRANDDRG"));
			drug.setDin(resultSet.getString("DRG_DIN"));
			drug.setConsumerId(resultSet.getString("DRG_CNSMRID"));
			drug.setProducerId(resultSet.getString("DRG_PRDCRID")) ;
			
			
			/*Long dosageFormKey = getLong("DRG_DSGFRMKEY");
			if (dosageFormKey!=null) {
		  		DosageForm dosageForm = (DosageForm) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,dosageFormKey, "DSGFRM");
				drug.setDosageform(dosageForm) ;
			}
			*/
			drug.setMonograph(resultSet.getString("DRG_MONOGRAPH"));			
			Long flavorKey= CommonUtil.getLong("DRG_DRGFLVRTYPKEY",resultSet);
			if(flavorKey!=null)
			{
				String flavorCode = getCodeFromKey(LT_DRGFLVRTYP, flavorKey);
				drug.setFlavour(Flavour.fromValue(flavorCode)) ;
			}
			
			drug.setFormvariant(CommonUtil.getInt("DRG_FRMVARIANT",resultSet)) ;
			drug.setIsdevice( CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("DRG_ISDEVICE")) );			
			drug.setChildDosageMaximumQuantity(CommonUtil.getBigDecimal("DRG_CHLDDSGMAXQTY",resultSet)) ;
			drug.setChildDosageMinimumQuantity(CommonUtil.getBigDecimal("DRG_CHLDDSGMINQTY",resultSet)) ;
			drug.setAdultDosageMaximumQuantity(CommonUtil.getBigDecimal("DRG_ADLTDSGMAXQTY",resultSet));
			drug.setAdultDosageMinimumQuantity(CommonUtil.getBigDecimal("DRG_ADLTDSGMINQTY",resultSet));
			drug.setIsimmunization(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("DRG_ISIMMNZTN")));
			drug.setIsproprietary( CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("DRG_ISPROP")));
			drug.setIsreportable(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("DRG_ISREPORTABLE")));
			drug.setIswritten(CommonUtil.convertYesNoFlagToBoolean(resultSet.getString("DRG_ISWRTN")));

			/*Long MfctrKey = getLong("DRG_MFCTRKEY");
			if (MfctrKey != null) {
				Manufacturer manufacturer = ( Manufacturer)TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,MfctrKey, "MFCTR");
				drug.setManufacturer(manufacturer);
			}*/
			
			drug.setMarkings(resultSet.getString("DRG_MRKNG"));
			Long moleculeKey = CommonUtil.getLong("MLCL_MLCLKEY",resultSet);
			if (moleculeKey != null) {
				MoleculeGet moleculeGet = new MoleculeGet();
				Molecule molecule = moleculeGet.fetch(connection, moleculeKey);
				drug.setMolecule(molecule);
			};
			
			drug.setChemicalLabelNameEnglish(resultSet.getString("DRG_CHMCLLBLNMENG")) ;
			drug.setChemicalLabelNameFrench(resultSet.getString("DRG_CHMCLLBLNMFR")) ;
			drug.setTradeNameEnglish(resultSet.getString("DRG_TRDNMENG")) ;
			drug.setTradeNameFrench(resultSet.getString("DRG_TRDNMFR")) ;

			Long dinTypeKey = CommonUtil.getLong("DRG_DINTYPKEY",resultSet);
			if (dinTypeKey!=null)
			{
				String DINTypCdDescr =  null;
				DINTypCdDescr = getCodeFromKey(LT_DINTYP ,dinTypeKey);
				DINType dinType = null;
				dinType = (DINTypCdDescr!=null) ? DINType.fromValue(DINTypCdDescr) : null;
				drug.setDintype(dinType) ;
			}

			Long drugSchedulKey= CommonUtil.getLong("DRG_DRGSCHDLTYPKEY",resultSet);
			if(drugSchedulKey!=null)
			{
				String drugScheduleCode =  getCodeFromKey(LT_DRGSCHDLTYP, drugSchedulKey);
				drug.setSchedule(Schedule.fromValue(drugScheduleCode)) ;
			}
			
			Long drugShapeKey = CommonUtil.getLong("DRG_DRGSHPTYPKEY",resultSet);
			if(drugShapeKey!=null)
			{
				String drugShapeCode =  getCodeFromKey(LT_DRGSHPTYP, drugShapeKey);
				drug.setShape(Shape.fromValue(drugShapeCode));
			}
			
			drug.setDrugDescription(resultSet.getString("DRG_DRGDESCR"));
			drug.setProductGroup(resultSet.getString("DRGPRODGRP_PRODGRP")) ;
			super.close();
			
			PreparedStatement preparedStatement1 = connection.prepareStatement(QUERYSQL2);
			preparedStatement1.setLong(1, drugKey);
			querytimer = System.currentTimeMillis();
			if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DrugGet.querySQL2 drugKey : " + drugKey );}
			ResultSet resultSet1 = preparedStatement1.executeQuery();
			if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DrugGet.querySQL2 drugKey : " + drugKey  + ". Total time is : " + (System.currentTimeMillis() - querytimer) + " ms") ;}
			try {
			ManufacturerRecall manufacturerRecall = null;
			String manufacturerRecallLotList = "";
			String colourList = "";
			String specialProgramList = "";
			while (resultSet1.next()) {
				if (manufacturerRecall == null) {
					Long MfctrDrgRecallKey = CommonUtil.getLong("RECALL_MFCTRDRGRECALLKEY",resultSet1);
					if (MfctrDrgRecallKey != null) {
						manufacturerRecall = new ManufacturerRecall();
						manufacturerRecall.setRecalldate(
								CommonUtil.getXMLGregorianCalendar(resultSet1.getDate("RECALL_RECALLDT"), true));
						drug.setRecall(manufacturerRecall);
					}
				}

				if (manufacturerRecall != null) {
					Integer lotNum = CommonUtil.getInt("RECALL_LOTNUM",resultSet1);
					if (lotNum != null) {
						if (manufacturerRecallLotList.indexOf(String.valueOf(lotNum)) < 0)
						{
							manufacturerRecall.getLotnumber().add(lotNum);
							manufacturerRecallLotList = manufacturerRecallLotList + "|"+lotNum;
						}
					}
				}
				
				Long colourTypeKey = CommonUtil.getLong("DRGCLR_DRGCLRTYPKEY",resultSet1);
				if(colourTypeKey !=null)
				{
					String colourTypeCode = getCodeFromKey(LT_DRGCLRTYP, colourTypeKey);
					if (colourList.indexOf(String.valueOf(colourTypeCode)) < 0)
					{
						drug.getColour().add(Colour.fromValue(colourTypeCode));
						colourList = colourList + "|"+colourTypeCode;
					}
				}
				
				Long specialDrugProgramKey = CommonUtil.getLong("DRGSPCLPROG_SPCLDRGPROGTYPKEY",resultSet1);
				if(specialDrugProgramKey !=null)
				{
					String specialProgramTypeCode = getCodeFromKey(LT_SPCLDRGPROGTYP, specialDrugProgramKey);
					if (specialProgramList.indexOf(String.valueOf(specialProgramTypeCode)) < 0)
					{
						drug.getProgram().add(SpecialDrugProgram.fromValue(specialProgramTypeCode));
						specialProgramList = specialProgramList + "|"+specialProgramTypeCode;
					}
				}
			}
			;
			}
			 finally {
			CommonUtil.closePreparedStatementResultSet(preparedStatement1, resultSet1);
			 }
		};	
		}
		finally {
		CommonUtil.closePreparedStatementResultSet(preparedStatement, resultSet);
		}
		super.close();
		
		if (logger.isInfoEnabled())  {logger.info("EndApiCall: DrugGet.populate. drugKey : " + drugKey + ". Total time is : " + (System.currentTimeMillis() - timer) + " ms");}
		
		return drug;
	}
}
