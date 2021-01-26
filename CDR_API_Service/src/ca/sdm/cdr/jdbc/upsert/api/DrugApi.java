package ca.sdm.cdr.jdbc.upsert.api;

import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DINTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGFLVRTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSCHDLTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_DRGSHPTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_RTEOFADMIN_CERXTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_SPCLDRGPROGTYP;
import static ca.sdm.cdr.common.constant.LookupTableConstants.LT_ADMINRTETYP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sdm.cdr.exception.api.EntityNotFoundException;
import com.sdm.cdr.exception.api.InvalidInputException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.FindUtil;
import ca.sdm.cdr.jdbc.api.util.idgen.IdGenerator;
import ca.shoppersdrugmart.rxhb.ehealth.Colour;
import ca.shoppersdrugmart.rxhb.ehealth.DosageForm;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.DrugNameAlternative;
import ca.shoppersdrugmart.rxhb.ehealth.GPI;
import ca.shoppersdrugmart.rxhb.ehealth.Manufacturer;
import ca.shoppersdrugmart.rxhb.ehealth.ManufacturerRecall;
import ca.shoppersdrugmart.rxhb.ehealth.Molecule;
import ca.shoppersdrugmart.rxhb.ehealth.SpecialDrugProgram;

/**
 * Drug API used to persist Drug entity.
 * 
 * @author LTrevino
 *
 */
public class DrugApi extends CDRUpsert {
	
	private static Logger logger = LogManager.getLogger(DrugApi.class);

	/*private final static String UPDATESQL =
	"UPDATE DRG SET " +
	"   DRGID=?, 			EQUVNLTTO=?, 		ALTRNTVNM=?, 			TRDNMALTRNTV=?, 	BRANDDRG=?,"		+
	"   TRDREF=?,			DIN=?,				CNSMRID=?,				MONOGRAPH=?, 		PRDCRID=?," 		+
	"   FRMVARIANT=?,		ISDEVICE=?,			ADLTDSGMAXQTY=?,		CHLDDSGMINQTY=?, 	ADLTDSGMINQTY=?,"	+
	"   CHLDDSGMAXQTY=?,	ISIMMNZTN=?,		ISPROP=?,				ISREPORTABLE=?,		ISWRTN=?,"			+
	"   MRKNG=?,			CHMCLLBLNMENG=?,	CHMCLLBLNMFR=?,			TRDNMENG=?, 		TRDNMFR=?,"			+
	"  	DRGDESCR=?,			STORENUM=?,			ADMINRTETYPKEY=?,		DINTYPKEY=?, 		DRGFLVRTYPKEY=?,"	+
	"   DRGSCHDLTYPKEY=?,	DRGSHPTYPKEY=?,		SPCLDRGPROGTYPKEY=?,	SUBRSNTYPKEY=?, 	MFCTRKEY=?,"		+
	"   DSGFRMKEY=?,		DRGPRDGRPTYPKEY=? ,	MLCLKEY = ? "	+
	" WHERE DRGKEY=?";
	
	private final static String INSERTSQL = 
	"INSERT INTO DRG (" +
		"DRGID,				EQUVNLTTO,			ALTRNTVNM,			TRDNMALTRNTV, 	BRANDDRG," 		+
		"TRDREF,			DIN, 				CNSMRID, 			MONOGRAPH, 		PRDCRID,"		+
		"FRMVARIANT,		ISDEVICE,			ADLTDSGMAXQTY,		CHLDDSGMINQTY, 	ADLTDSGMINQTY,"	+
		"CHLDDSGMAXQTY,		ISIMMNZTN,			ISPROP,				ISREPORTABLE, 	ISWRTN," 		+
		"MRKNG,				CHMCLLBLNMENG,		CHMCLLBLNMFR,		TRDNMENG, 		TRDNMFR,"		+
		"DRGDESCR,			STORENUM,			ADMINRTETYPKEY,		DINTYPKEY,		DRGFLVRTYPKEY,"	+
		"DRGSCHDLTYPKEY,	DRGSHPTYPKEY,		SPCLDRGPROGTYPKEY,	SUBRSNTYPKEY, 	MFCTRKEY,"		+
		"DSGFRMKEY,			DRGPRDGRPTYPKEY,	MLCLKEY , " +
		"DRGKEY "												+
		"" + 
	") VALUES (" +
		" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ? ," +
		"? " + 
	")";*/
	
	//" +"
	//added 60 prescribedDrug
	private final static String UPDATESQL =
			"UPDATE DRG SET " +
			"   DRGID=?, 			EQUVNLTTO=?, 		ALTRNTVNM=?, 			TRDNMALTRNTV=?, 	BRANDDRG=?,"		+
			"   TRDREF=?,			DIN=?,				CNSMRID=?,				MONOGRAPH=?, 		PRDCRID=?," 		+
			"   FRMVARIANT=?,		ISDEVICE=?,			ADLTDSGMAXQTY=?,		CHLDDSGMINQTY=?, 	ADLTDSGMINQTY=?,"	+
			"   CHLDDSGMAXQTY=?,	ISIMMNZTN=?,		ISPROP=?,				ISREPORTABLE=?,		ISWRTN=?,"			+
			"   MRKNG=?,			CHMCLLBLNMENG=?,	CHMCLLBLNMFR=?,			TRDNMENG=?, 		TRDNMFR=?,"			+
			"  	DRGDESCR=?,			STORENUM=?,	" +
			"		ADMINRTETYPKEY=nvl(?, ADMINRTETYPKEY),	" +
			"  GPITYPKEY = nvl(? ,GPITYPKEY) , " +
			"	DINTYPKEY=nvl(?, DINTYPKEY), " +
			"		DRGFLVRTYPKEY=nvl(?, DRGFLVRTYPKEY), "	+
			"   DRGSCHDLTYPKEY=nvl(?, DRGSCHDLTYPKEY),	" +
			"DRGSHPTYPKEY=nvl(?, DRGSHPTYPKEY),	" +
			"	SPCLDRGPROGTYPKEY=nvl(?, SPCLDRGPROGTYPKEY),	" +
			"SUBRSNTYPKEY=nvl(?, SUBRSNTYPKEY), " +
			"	MFCTRKEY=nvl(?, MFCTRKEY), "		+
			"   DSGFRMKEY=nvl(?, DSGFRMKEY), " +
			"		DRGPRDGRPTYPKEY=nvl(?, DRGPRDGRPTYPKEY) ,	" +
			"MLCLKEY =nvl(?, MLCLKEY), "	+
			"RTEOFADMIN_CERXTYPKEY=nvl(?, RTEOFADMIN_CERXTYPKEY) , "	+ 
			"CDEFFENDDT = NVL(" + CommonUtil.getPsToDateFunctionStr() + ",CDEFFENDDT )" +
			"  WHERE DRGKEY=?";
	private final static String INSERTSQL = 
	"INSERT INTO DRG (" +
		"DRGID,				EQUVNLTTO,			ALTRNTVNM,			TRDNMALTRNTV, 	BRANDDRG," 		+
		"TRDREF,			DIN, 				CNSMRID, 			MONOGRAPH, 		PRDCRID,"		+
		"FRMVARIANT,		ISDEVICE,			ADLTDSGMAXQTY,		CHLDDSGMINQTY, 	ADLTDSGMINQTY,"	+
		"CHLDDSGMAXQTY,		ISIMMNZTN,			ISPROP,				ISREPORTABLE, 	ISWRTN," 		+
		"MRKNG,				CHMCLLBLNMENG,		CHMCLLBLNMFR,		TRDNMENG, 		TRDNMFR,"		+
		"DRGDESCR,			STORENUM,			ADMINRTETYPKEY,		GPITYPKEY,  DINTYPKEY,		DRGFLVRTYPKEY,"	+
		"DRGSCHDLTYPKEY,	DRGSHPTYPKEY,		SPCLDRGPROGTYPKEY,	SUBRSNTYPKEY, 	MFCTRKEY,"		+
		"DSGFRMKEY,			DRGPRDGRPTYPKEY,	MLCLKEY , " +
		"RTEOFADMIN_CERXTYPKEY,  CDEFFENDDT ,	DRGKEY , CDEFFSTRTDT  "												+
		"" + 
	") VALUES (" +
		" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
		",?, ?, ?, ?, ?, ?, ?, ? ," +
		"? ,?   ," + CommonUtil.getPsToDateFunctionStr() + ", ? ," + CommonUtil.getPsToDateFunctionStr() +   
	")";
	//added 60 prescribedDrug
	/**
	 * Attempt to persist a Drug instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Drug doesn't exist, then insert a new record.
	 * If the Drug exists, then update its data.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param drug			Drug instance.
	 * 
	 * @return				DrugKey value.
	 * @throws SQLException 
	 * @throws EntityNotFoundException 
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 * @throws  
	 * @throws Exception 
	 */
	
	public Long upsertDrug(Connection connection, String storeNum, Drug drug ,Map <String, Long> drgPK ,Map <String, Timestamp> drgTime) throws EntityNotFoundException, SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException, InvalidInputException {
		try {
			if(drug != null)			
			if (logger.isInfoEnabled())  {logger.info("StartApiCall: upsertDrug. store number " +storeNum + ", drug consumerId : " + drug.getConsumerId());		}
			
			Long manufKey = null;
			Manufacturer manufacturer = drug.getManufacturer();
			if (manufacturer != null) {
				manufKey=TableCacheSingleton.getInstance(connection).getKeyFromObject(connection, "MFCTR", manufacturer);
			}
		 
			Long moleculeKey = null;
			// Molecute must be upserted before drug
			Molecule molecule = drug.getMolecule();
			if (molecule != null) {
				MoleculeApi moleculeApi = new MoleculeApi();
				moleculeKey = moleculeApi.upsertMolecule(connection, drug.getConsumerId(), storeNum, molecule);
			}

			Long dosageFormKey = null;
			DosageForm dosageForm = drug.getDosageform();
			if (dosageForm != null){
				dosageFormKey =TableCacheSingleton.getInstance(connection).getKeyFromObject(connection, "DSGFRM", dosageForm);
			}
					
		// Persist main entity
		//	Map<String, Object> dataMap = new HashMap<String, Object>();
			FindUtil.findDrugData2(connection, drug , storeNum,drgPK,drgTime);
			Long drugKey = (Long)drgPK.get(drug.getConsumerId() + storeNum);
			Timestamp lastUpdatedTimeStampdb = (Timestamp)drgTime.get(drug.getConsumerId() + storeNum);
	//		  boolean isUpdateRequestNew = CommonUtil.isUpdateRequestNew2(lastUpdatedTimeStampdb, drug.getLastUpdateTimestamp());
			 boolean isUpdateRequestNew = newRequest(lastUpdatedTimeStampdb,drug.getLastUpdateTimestamp());
			if (drugKey == null) {
				//drugKey = insertDrug(connection, storeNum, drug, manufKey, moleculeKey, dosageFormKey);
				Long gpiTypKey = null;
				GPI gpi = drug.getGpi();
				if(gpi != null) {
					GpiApi gpiApi = new GpiApi();
					gpiTypKey = gpiApi.upsertGPI(connection, gpi);
				}
				drugKey = insertDrug(connection, storeNum, drug, manufKey, moleculeKey, dosageFormKey,gpiTypKey);
				drgPK.put((drug.getConsumerId() + storeNum),drugKey );  
				drgTime.put((drug.getConsumerId() + storeNum), CommonUtil.toTimestamp(drug.getLastUpdateTimestamp()) );
			} else {
			    if(isUpdateRequestNew==true) {
					if(logger.isDebugEnabled()) {logger.debug("Start: Updating drug table");}
					Long gpiTypKey = null;
					GPI gpi = drug.getGpi();
					if(gpi != null) {
						GpiApi gpiApi = new GpiApi();
						gpiTypKey = gpiApi.upsertGPI(connection, gpi);
					}
					updateDrug(connection, storeNum, drugKey, drug, manufKey, moleculeKey, dosageFormKey,gpiTypKey,lastUpdatedTimeStampdb);
					if(logger.isDebugEnabled()) {logger.debug("End: Updating drug table");}
				}
				
			}
			
			List<Colour> colours = drug.getColour();
			if (colours != null && colours.size() > 0) {
				DrugColourApi drugColourApi = new DrugColourApi();
				for (Colour colour : colours) {
					drugColourApi.upsertColour(connection, drug.getConsumerId(), storeNum, colour);
				}
			}

			ManufacturerRecall manufacturerRecall = drug.getRecall();
			if (manufacturerRecall != null) {
				ManufacturerRecallApi manufRecallApi = new ManufacturerRecallApi();
				Long drugManufId = (drug.getManufacturer() != null
						&& drug.getManufacturer().getManufacturerId() != null)
								? new Long(drug.getManufacturer().getManufacturerId()) : null;
				if (drugManufId != null) {
					manufRecallApi.upsertManufacturerRecall(connection, drugKey, manufKey, manufacturerRecall);
				}
			}

			List<SpecialDrugProgram> programs = drug.getProgram();
			if (programs != null && programs.size() > 0) {
				SpecialDrugProgramApi programApi = new SpecialDrugProgramApi();
				for (SpecialDrugProgram program : programs) {
					programApi.upsertSpecialDrugProgram(connection, program, drugKey);
				}
			}

			return drugKey;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyNotFoundFromTableCacheException e) {
			e.printStackTrace();
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidInputException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.close();
			if(drug != null)
			if (logger.isInfoEnabled())  {logger.info("EndApiCall: upsertDrug. store number " +storeNum + ", drug consumerId : " + drug.getConsumerId());	}		
		}
	}
	
	
	
	/**
	 * Attempt to update a Drug instance, which is matched by drugKey.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param drugKey		Drug Key.
	 * @param drug			Drug instance.
	 * 
	 * @return				DrugKey value.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long updateDrug(Connection connection, String storeNum, Long drugKey, Drug drug, Long manufKey, Long moleculeKey, Long dosageFormKey ,Long gpiTypKey,Timestamp lastUpdateTimeStamp) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {
		if (logger.isInfoEnabled())  {logger.info("DrugApi : starting updateDrug");}
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		ps = connection.prepareStatement(UPDATESQL);
		setPsParams(storeNum, drugKey, drug, manufKey, moleculeKey, dosageFormKey,gpiTypKey);
		int res = ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug("Total Drug intances updated: " + res + ". DrugKey: " + drugKey + ", consumerId: '" + drug.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return drugKey;
	}

	
	/**
	 * Attempt to update a Drug instance, which is searched by consumerId and storeNum.
	 * 
	 * If the Drug exists, then update its data.
	 * If if doesn't exist, then do nothing (no new record will be created).
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param drug			Drug instance.
	 * 
	 * @return				DrugKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws EntityNotFoundException 
	 */
//	private Long findAndUpdateDrug2(String storeNum, Drug drug, Long manufKey , Long moleculeKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, EntityNotFoundException {
//		Long drugKey = FindUtil.findDrugKey(connection, drug.getConsumerId(), storeNum);
// 		return updateDrug(storeNum, drugKey, drug, manufKey , moleculeKey);
//	}
	
	
	
	/**
	 * Attempt to insert a new Drug into corresponding database table.
	 * 
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param drug			Drug instance.
	 * 
	 * @return				DrugKey.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 * @throws InvalidInputException 
	 */
	private Long insertDrug(Connection connection, String storeNum, Drug drug, Long manufKey, Long moleculeKey, Long dosageFormKey , Long gpiTypKey ) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException, InvalidInputException {
		if (storeNum==null || storeNum.trim().length() < 1) {
			throw new InvalidInputException("StoreNum is required");
		}
		Long drugKey = IdGenerator.generate(connection, "DRG");
		
		ps = connection.prepareStatement(INSERTSQL);
		setPsParams(storeNum, drugKey, drug, manufKey , moleculeKey, dosageFormKey,gpiTypKey );
		//setPsXMLGregorianCalendarParam(43, drug.getLastUpdateTimestamp());
		setPsStringParam(43,CommonUtil.toTimestampStr( drug.getLastUpdateTimestamp()));
		int res = ps.executeUpdate();
		if(logger.isDebugEnabled()) {logger.debug("Total Drug intances updated: " + res + ". DrugKey: " + drugKey + ", consumerId: '" + drug.getConsumerId() + "', storeNum: '" + storeNum + "'.");}
		return drugKey;
	}
	
	
	
	/**
	 * Private method to set Prepared Statement Parameters.
	 * 
	 * @param ps			PreparedStatement object.
	 * @param consumerId	Consumer ID.
	 * @param storeNum		Store Number.
	 * @param drugKey		Drug Key.
	 * @param drug			Drug instance.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws NamingException 
	 * @throws KeyNotFoundFromTableCacheException 
	 */
	private void setPsParams(String storeNum, Long drugKey, Drug drug, Long manufKey, Long moleculeKey, Long dosageFormKey,Long gpiTypKey) throws SQLException, NamingException, IOException, KeyNotFoundFromTableCacheException {
		setPsStringParam(1, String.valueOf(drugKey));
		DrugNameAlternative drugAltName = drug.getAlternativename();
		String equivTo = (drugAltName!=null) ? drugAltName.getEquivalentto() : null;
		setPsStringParam(2, equivTo);
		String tradeNameAlt = (drugAltName!=null) ? drugAltName.getTradenamealternative() : null;
		setPsStringParam(3, tradeNameAlt);
		setPsStringParam(4, tradeNameAlt);
		setPsLongParam(5, drug.getBranddrug());

		String tradeRef = (drugAltName!=null) ? drugAltName.getTradereference() : null;
		setPsStringParam(6, tradeRef);
		setPsStringParam(7, drug.getDin());
		setPsStringParam(8, drug.getConsumerId());
		setPsStringParam(9, drug.getMonograph());
		setPsStringParam(10, drug.getProducerId());

		Long formVar = (drug.getFormvariant()!=null) ? new Long(drug.getFormvariant()) : null;
		setPsLongParam(11, formVar);
		setPsStringParam(12, CommonUtil.convertBooleanToYesNoFlag(drug.isIsdevice()));
		setPsBigDecimalParam(13, drug.getAdultDosageMaximumQuantity());
		setPsBigDecimalParam(14, drug.getChildDosageMinimumQuantity());
		setPsBigDecimalParam(15, drug.getAdultDosageMinimumQuantity());

		setPsBigDecimalParam(16, drug.getChildDosageMaximumQuantity());
		setPsStringParam(17, CommonUtil.convertBooleanToYesNoFlag(drug.isIsimmunization()));
		setPsStringParam(18, CommonUtil.convertBooleanToYesNoFlag(drug.isIsproprietary()));
		setPsStringParam(19, CommonUtil.convertBooleanToYesNoFlag(drug.isIsreportable()));
		setPsStringParam(20, CommonUtil.convertBooleanToYesNoFlag(drug.isIswritten()));

		setPsStringParam(21, drug.getMarkings());
		setPsStringParam(22, drug.getChemicalLabelNameEnglish());
		setPsStringParam(23, drug.getChemicalLabelNameFrench());
		setPsStringParam(24, drug.getTradeNameEnglish());
		setPsStringParam(25, drug.getTradeNameFrench());

		setPsStringParam(26, drug.getDrugDescription());
		setPsStringParam(27, storeNum);
		
		String adminRouteCode = (drug.getAdministrationRouteCode()!=null) ? drug.getAdministrationRouteCode().value() : null;
		//select Adm.ADMINRTETYPKEY, Adm.* from ADMINRTETYP Adm Where Adm.CDDESCR = '[adminRouteCodeVal]'
		//LT_ADMINRTETYP added 60 prescribedDrug
		//Long adminRouteTypeKey = getKeyFromCode(LT_RTEOFADMIN_CERXTYP, adminRouteCode);		
		Long adminRouteTypeKey = getKeyFromCode(LT_ADMINRTETYP, adminRouteCode);
		setPsLongParam(28, adminRouteTypeKey);	
		setPsLongParam(29 ,gpiTypKey);
		String dinTypeCode = (drug.getDintype()!=null) ? drug.getDintype().value() : null;
		//select dinTypKey from dinTyp where dinTyp.CdDescr = '[dinType]'
		Long dynTypeKey = getKeyFromCode(LT_DINTYP, dinTypeCode);
		setPsLongParam(30, dynTypeKey);
		
		String drugFlavCode = (drug.getFlavour()!=null) ? drug.getFlavour().value() : null;
		//select dft.DRGFLVRTYPKEY from DrgFlvrTyp dft where dft.CdDescr = '[drugFlavCode]'
		Long drugFlavKey = getKeyFromCode(LT_DRGFLVRTYP, drugFlavCode);
		setPsLongParam(31, drugFlavKey);
		
		String drugSchTypeCode = (drug.getSchedule()!=null) ? drug.getSchedule().value() : null;
		//select dst.DRGSCHDLTYPKEY from DrgSchdlTyp dst WHERE dst.CdDescr = '[drugSchTypeCode]'
		Long drugSchTypeKey = getKeyFromCode(LT_DRGSCHDLTYP, drugSchTypeCode);
		setPsLongParam(32, drugSchTypeKey);
		
		String drugShapeCode = (drug.getShape()!=null) ? drug.getShape().value() : null;
		//select dst.DRGSHPTYPKEY from DrgShpTyp dst WHERE dst.CdDescr like '%%'
		Long drugShapeKey = getKeyFromCode(LT_DRGSHPTYP, drugShapeCode);
		setPsLongParam(33, drugShapeKey);
		
		List<SpecialDrugProgram> spcDrugPrograms = drug.getProgram();
		String spcDrugProgramCode = null;
		for (int ii=0; spcDrugPrograms!=null && ii < spcDrugPrograms.size(); ii++) {
			SpecialDrugProgram drugProgram = spcDrugPrograms.get(ii);
			if (drugProgram.value()!=null) {
				spcDrugProgramCode = drugProgram.value();
				break;
			}
		}
		//select sdp.SpclDrgProgTypKey from SpclDrgProgTyp sdp WHERE sdp.CdDescr like '%%'
		Long spcDrugProgramTypeKey = getKeyFromCode(LT_SPCLDRGPROGTYP, spcDrugProgramCode);
		setPsLongParam(34, spcDrugProgramTypeKey);
		
		// SubRsnTypKey is NOT required, as per mapping document
		Long SubRsnTypKey = null;
		setPsLongParam(35, SubRsnTypKey);
		
		//Manufacturer
		setPsLongParam(36, manufKey);
		
		//String dosageFullName = (drug.getDosageform()!=null) ? drug.getDosageform().getFullName() : null;
		//select df.DSGFRMKEY from DsgFrm df where df.DSGFRMFULLNM = '[]'
		setPsLongParam(37, dosageFormKey);
		
		//String productGroupCode = drug.getProductGroup();
		// PrdGrpTypKey: as per mapping doc, "the xsd does not have the value and this is a new ChangeRequest and will be mapped after eHealth.xsd released"
		ps.setNull(38, Types.NUMERIC);
		
		setPsLongParam(39, moleculeKey);
		//setPsLongParam(39, drugKey);
		//added 60 prescribedDrug
				String adminRouteCode1 = (drug.getAdministrationRouteCode()!=null) ? drug.getAdministrationRouteCode().value() : null; //RteOfAdmin_CeRXTypKey
				Long adminRouteKey1 = getKeyFromCode(LT_RTEOFADMIN_CERXTYP, adminRouteCode1);
				setPsLongParam(40, adminRouteKey1);
				 //Drug last updateTime
				setPsStringParam(41,CommonUtil.toTimestampStr( drug.getLastUpdateTimestamp()));
				setPsLongParam(42, drugKey);
				//added 60 prescribedDrug
		
		
	}
	private boolean newRequest(Timestamp            dbUpdateTimeStamp,
			XMLGregorianCalendar payloadUpdateTimeStamp) {
		
		if (payloadUpdateTimeStamp == null) {
			return true;
		}
	//	 payloadUpdateTimeStamp.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
		Date updateTimestampJaxbDate = CommonUtil.toTimestamp(payloadUpdateTimeStamp);
		boolean isUpdateRequestNew = dbUpdateTimeStamp == null
				|| updateTimestampJaxbDate.compareTo(dbUpdateTimeStamp) > 0;
				if(logger.isDebugEnabled()) {logger.debug("DrugAPI. newRequest11....isUpdateRequestNew ="+isUpdateRequestNew);
				logger.debug("dbUpdateTimeStamp ="+dbUpdateTimeStamp +"updateTimestampJaxbDate= "+updateTimestampJaxbDate);	}	 
		return isUpdateRequestNew;
	}
	
}
