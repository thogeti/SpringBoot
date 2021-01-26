package ca.sdm.cdr.jdbc.query.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DINTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGFLVRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.CrxReferenceBean;
import ca.sdm.cdr.common.util.FindUtil;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Colour;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DINType;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DosageForm;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Drug;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DrugNameAlternative;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Flavour;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.GPI;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Manufacturer;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.ManufacturerRecall;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Molecule;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.RouteOfAdmin;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Shape;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.SpecialDrugProgram;

import static ca.sdm.cdr.common.constant.LookupTableConstants.*;

public class DrugMedReviewGet extends CDRMedReviewGet {
	private static Logger logger = LogManager.getLogger(DrugMedReviewGet.class);
	//customize
	private final static String QUERYSQL = "SELECT   DRG.DIN DRG_DIN," + 
	//		"         DRG.ISIMMNZTN DRG_ISIMMNZTN," + 
			"         DRG.CHMCLLBLNMENG DRG_CHMCLLBLNMENG," + 
			"         DRG.CHMCLLBLNMFR DRG_CHMCLLBLNMFR," + 
			"         DRG.TRDNMENG DRG_TRDNMENG," + 
			"         DRG.TRDNMFR DRG_TRDNMFR," + 
			"         DRG.DSGFRMKEY DRG_DSGFRMKEY," +
			"         DRG.DINTYPKEY DRG_DINTYPKEY ," +
			"         DRG.ISDEVICE DRG_ISDEVICE ," +
			"         DRG.DRGSCHDLTYPKEY DRG_DRGSCHDLTYPKEY  , " +
			"         DRG.RTEOFADMIN_CERXTYPKEY ," +
			"         DRG.GPITYPKEY  DRG_GPITYPKEY ," +
			"         DRG.MFCTRKEY DRG_MFCTRKEY , " +
			"         GPITYP.GPITYPCD GPITYP_GPITYPCD , " +
			"         GPITYP.CDDESCR  GPITYP_CDDESCR , " +
			"         GPITYP.CDDESCRFR GPITYP_CDDESCRFR " +
			"         FROM DRG" + 
			"         LEFT OUTER JOIN GPITYP GPITYP ON DRG.GPITYPKEY = GPITYP.GPITYPKEY "+
			"         WHERE DRG.DRGKEY = ? " ;
			 
	public Drug fetch(Connection connection, Long drugKey,String storenum,CrxReferenceBean bean,Map <Long, Drug> drgPK) throws SQLException, CDRInternalException, NamingException, IOException, ParseException, DatatypeConfigurationException {
		try {
			// VLAD CRX_REFERENCE_BEAN
			if ((drgPK!=null)&&drgPK.containsKey(drugKey)) {
				if(logger.isDebugEnabled()) { logger.debug("DrugMedReivew fetch drugKey  : " + drugKey  + " drgPK.size = " + drgPK.size());}
				 Drug drug = drgPK.get(drugKey);
				 return drug;
			}else {
			return populate(connection, drugKey,storenum,bean,drgPK);
			}
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

	// VLAD CRX_REFERENCE_BEAN
	private Drug populate(Connection connection, Long drugKey,String storenum,CrxReferenceBean bean,Map <Long, Drug> drgPK) throws SQLException, NamingException, IOException, CDRInternalException, ParseException, DatatypeConfigurationException
	{
		Long timer = System.currentTimeMillis();
		if(logger.isInfoEnabled()) {logger.info("StartApiCall: DrugMedReviewGet.populate. drugKey : " + drugKey);}
		Drug drug = null;
			preparedStatement = connection.prepareStatement(QUERYSQL);
		preparedStatement.setLong(1, drugKey);
		Long querytimer = System.currentTimeMillis();
		if(logger.isDebugEnabled()) {logger.debug("StartExecuteQuery: DrugMedReviewGet.querySQL drugKey : " + drugKey);}
		resultSet = preparedStatement.executeQuery();
		if(logger.isDebugEnabled()) {logger.debug("EndExecuteQuery: DrugMedReviewGet.querySQL drugKey : " + drugKey + ". Total time is : "
				+ (System.currentTimeMillis() - querytimer) + " ms");}
		if (resultSet.next()) {
			drug = new Drug();
            String din = getString("DRG_DIN");
			drug.setDin(din);//A
			drug.setChemicalLabelNameEnglish(getString("DRG_CHMCLLBLNMENG")) ;//A
			drug.setChemicalLabelNameFrench(getString("DRG_CHMCLLBLNMFR")) ; //A
			drug.setTradeNameEnglish(getString("DRG_TRDNMENG")) ;//A
			drug.setTradeNameFrench(getString("DRG_TRDNMFR")) ;//A
			drug.setIsdevice( CommonUtil.convertYesNoFlagToBoolean(getString("DRG_ISDEVICE")) );//A
			
			Long dinTypeKey = getLong("DRG_DINTYPKEY");
			if (dinTypeKey!=null)
			{
				String DINTypCdDescr =  null;
				DINTypCdDescr = getCodeFromKey(LT_DINTYP ,dinTypeKey);
				drug.setDintype(DINTypCdDescr) ; //A
			}
			
			
			
			Long dosageFormKey = getLong("DRG_DSGFRMKEY");
			if (dosageFormKey != null) {
				ca.shoppersdrugmart.rxhb.ehealth.DosageForm dosageFormEhealth = (ca.shoppersdrugmart.rxhb.ehealth.DosageForm) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,dosageFormKey, "DSGFRM");
					
				if(dosageFormEhealth!=null) {
				DosageForm dosageForm = new DosageForm();
				dosageForm.setFullName(dosageFormEhealth.getFullName());
				dosageForm.setFullNameFrench(dosageFormEhealth.getFullNameFrench() ); 
				drug.setDosageform(dosageForm);
				}
			}else {
				// VLAD CRX_REFERENCE_BEAN
				if(din!=null) {
				DosageForm dosageForm = new DosageForm();
				bean.getCrxReference(din, dosageForm);
				if (dosageForm.getFullName() != null) {
					drug.setDosageform(dosageForm);
				}
				}
				
			}
			
			Long routeOfAdminKey = getLong("RTEOFADMIN_CERXTYPKEY"); 
			String routeOfAdminCode =null;
			if (routeOfAdminKey != null) {
				routeOfAdminCode = getCodeFromKey(LT_RTEOFADMIN_CERXTYP, routeOfAdminKey);
			} else {
				if(din!=null) {
				routeOfAdminCode=bean.getAdminRouteCode(din, routeOfAdminCode);
				}
			}
			drug.setAdministrationRouteCode(routeOfAdminCode);//A
			 
			
			String  drugScheduleCode = null; 
		//	drugScheduleCode = FindUtil.getDrugScheduleFromCRX_REF(connection,storenum, din);
		//	 bean.getCrxReference(din, drugScheduleCode);
			if(din!=null) {
			drugScheduleCode = bean.getSchedule(din, drugScheduleCode);
			}
			if(drugScheduleCode==null) {
			Long drugSchedulKey= getLong("DRG_DRGSCHDLTYPKEY");
			if(drugSchedulKey!=null)
			{
				 drugScheduleCode =  getCodeFromKey(LT_DRGSCHDLTYP, drugSchedulKey);
				
			}
			}
			drug.setSchedule(drugScheduleCode) ; //A
			
			Long MfctrKey = getLong("DRG_MFCTRKEY");
			if (MfctrKey != null) {
				ca.shoppersdrugmart.rxhb.ehealth.Manufacturer manufacturerEhealth = (ca.shoppersdrugmart.rxhb.ehealth.Manufacturer) TableCacheSingleton.getInstance(connection).getObjectFromKey(connection,MfctrKey, "MFCTR");
				
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setManufacturerNameEnglish(manufacturerEhealth.getManufacturerNameEnglish());//Get it from cache..
				manufacturer.setManufacturerNameFrench(manufacturerEhealth.getManufacturerNameFrench());
			 	manufacturer.setVendorCode(manufacturerEhealth.getVendorCode());
			 	drug.setManufacturer(manufacturer);
			}else {
				// VLAD CRX_REFERENCE_BEAN
				if(din!=null) {
				Manufacturer manufacturer = new Manufacturer();
  			    bean.getCrxReference(din, manufacturer);
  			    if (manufacturer.getVendorCode() != null) {
  				    drug.setManufacturer(manufacturer);
  				}
				}
			}
			
			
			Long gpiTypeCdKey = getLong("DRG_GPITYPKEY");
			if(gpiTypeCdKey!= null) {
				GPI gpi = new GPI();
				gpi.setGPINumber(getString("GPITYP_GPITYPCD"));
				gpi.setDescriptionEnglish(getString("GPITYP_CDDESCR"));
				gpi.setDescriptionFrench(getString("GPITYP_CDDESCRFR"));
				drug.setGpi(gpi);
			}else {
				if(din!=null) {
				GPI gpi = new GPI();
				bean.getCrxReference(din, gpi);
				if(gpi.getGPINumber()!=null) {
					drug.setGpi(gpi);
				}
				}
				}
			
			super.close();

			if(logger.isInfoEnabled()) {	logger.info("EndApiCall: DrugMedReviewGet.populate. drugKey : " + drugKey + ". Total time is : "
					+ (System.currentTimeMillis() - timer) + " ms");}
			if(drgPK!=null) {
				drgPK.put(drugKey, drug);
				}

		}
		return drug;
	}
}