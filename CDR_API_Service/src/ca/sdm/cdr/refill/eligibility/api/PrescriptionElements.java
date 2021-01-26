package ca.sdm.cdr.refill.eligibility.api;

import java.io.IOException;import java.sql.ResultSet;import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.CDRInternalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.query.api.CDRGet;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;


public class PrescriptionElements extends CDRGet {
	   private static Logger logger = LogManager.getLogger(PrescriptionElements.class);
	   
	   public PrescriptionElements () {

	   }


	   public void populatePrescription(Prescription thisPrescription, Map<Integer, Long[]> normalRxMap, Map<Long, Long> linkedRxMap,  Map<Long, Prescription> rxResultSet, Set<String> gtinSet, ResultSet rs)  throws SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException, CDRInternalException {
		      RxFillStatus rxFillStatus = null;
              int activeRxFlag = 0;		      
		      
		      // ***********  Prescription basic info: ****************
		      thisPrescription.setPrescriptionNumber(CommonUtil.getInt("RX_RXNUM", rs));
		      thisPrescription.setAdministrationPeriodStartDate(CommonUtil.getCalendar("RX_ADMINSTARTDATE",rs));
		      thisPrescription.setAdministrationPeriodEndDate(CommonUtil.getCalendar("RX_ADMINSTOPDATE",rs));
		      thisPrescription.setIsAuthoritative(CommonUtil.getBoolean("RX_ISATHRTV",rs));

			  String isBatchFlag = rs.getString("RX_BATCHFLG");
			  if (isBatchFlag != null) {
				  thisPrescription.setIsBatchFlag(CommonUtil.convertYesNoFlagToBoolean(isBatchFlag));
			  }
				

			  Long fillStatusKey = new Long(rs.getLong("RXSTAT_FILLSTATKEY"));
			  if (fillStatusKey != null && fillStatusKey > 0L) {
				  String fillStatusCode = getCodeFromKey("RXFILLSTATTYP", fillStatusKey);
				  rxFillStatus = RxFillStatus.fromValue(fillStatusCode);
				  thisPrescription.setFillStatusCode(rxFillStatus);
			  }

			  Long subFillStatusKey =  new Long(rs.getLong("RXSTAT_SUBFILLSTATKEY"));
			  if (fillStatusKey != null &&  subFillStatusKey > 0L) {
				  String subFillStatusCode = getCodeFromKey("RXFILLSTATTYP", subFillStatusKey);
				  if (subFillStatusCode != null) {
					  RxFillStatus rxSubFillStatus = RxFillStatus.fromValue(subFillStatusCode);
					  thisPrescription.setFillStatusSubCode(rxSubFillStatus);
				  }
			  }
			  
			  thisPrescription.setPrescriptionExpirationDate(CommonUtil.getCalendar("RX_RXEXPRTNDT",rs));
			  activeRxFlag = CommonUtil.getInt("ACTIVE_RX_FLAG",rs);
			  thisPrescription.setRemainingQuantity(CommonUtil.getBigDecimal("RX_RMNGQTY",rs));
			  
			  
		      // ***********  prescribedDrug: ****************
	          Long prescribedDrugKey = CommonUtil.getLong("RX_DRGKEY", rs);
		      if (prescribedDrugKey != null ) {
		    	  Drug drug = new Drug();
				  drug.setChemicalLabelNameEnglish(rs.getString("PRESCR_DRUG_ENGLISH")) ;
				  drug.setChemicalLabelNameFrench(rs.getString("PRESCR_DRUG_FRENCH")) ;
				  thisPrescription.setPrescribedDrug(drug);
		      }

		      
		      // ***********  prescribedCompound: ****************
		      Long prescribedCompoundKey = CommonUtil.getLong("RX_CMPNDKEY", rs);
		  	  if (prescribedCompoundKey != null) {
		  		  Compound compound = new Compound();
		  		  compound.setConsumerId(rs.getString("RX_CMPNDID"));
				  compound.setNameEnglish(rs.getString("RX_CMPND_ENG"));
				  thisPrescription.setPrescribedCompound(compound);
			  }
		      
		      
		      // ***********  prescribedPack: ********************
		  	  String gtin = rs.getString("RX_PACK_GTIN");
		  	  if (gtin != null) {
		  		  gtin = gtin.replaceFirst("^0+(?!$)", "");    // remove leading zero(s) if any
		  		  Pack pack = new Pack();
		  		  pack.setGTIN(gtin);
		  		  thisPrescription.setPrescribedPack(pack);
		  		  gtinSet.add(gtin);
		  	  }
		  	  
		      // ***********  linkedRx Prescription: ********************
			  Long rxKey = CommonUtil.getLong("RX_RXKEY", rs);
			  Long linkedRxKey = CommonUtil.getLong("RX_LINKEDRXKEY",rs);
			  if (linkedRxKey != null) {
				  linkedRxMap.put(linkedRxKey, rxKey);
			  } 
			  
			  Long[] threeKeys = new Long[3];
			  threeKeys[0] = rxKey;
			  threeKeys[1] = linkedRxKey;
			  threeKeys[2] = 3L;             // Not eligibleRx : Don't send to both ODM & HW

			  
			  if (activeRxFlag == 1 && rxFillStatus != null  &&
				 (rxFillStatus == RxFillStatus.HELD || rxFillStatus == RxFillStatus.NOT_DISPENSED || rxFillStatus == RxFillStatus.REFILLS_REMAIN ))  {
					  
				  threeKeys[2] = 1L;        // eligibleRx: Send to both ODM & HW
		      } 


			  if (activeRxFlag == 0 && rxFillStatus != null  &&
				 (rxFillStatus == RxFillStatus.HELD || rxFillStatus == RxFillStatus.NOT_DISPENSED || rxFillStatus == RxFillStatus.REFILLS_REMAIN ))  {
					  
				  threeKeys[2] = 2L;        // Prepopulate: Send to HW as not eligible and don't send to ODM
		      } 
			  
			  
			  if (rxFillStatus != null  &&
				 (rxFillStatus == RxFillStatus.CANCELLED || rxFillStatus == RxFillStatus.NO_REFILLS_REMAIN || rxFillStatus == RxFillStatus.EXPIRED ))  {
						  
				  threeKeys[2] = 2L;        // Prepopulate: Send to HW as not eligible and don't send to ODM
		      } 
			  
				  
			  normalRxMap.put(CommonUtil.getInt("RX_RXNUM", rs), threeKeys);
			  rxResultSet.put(rxKey, thisPrescription);
       }
	   
	   
/*
 * 
   	   Held			             40	  eligibleRx: Send to both ODM & HW
	   Not Dispensed		     60	  eligibleRx: Send to both ODM & HW
	   Refills Remain		     80	  eligibleRx: Send to both ODM & HW

	   Cancelled		         10	  Prepopulate: Send to HW as not eligible and don't send to ODM
	   No Refills Remain	     50	  Prepopulate: Send to HW as not eligible and don't send to ODM
	   Expired			         30	  Prepopulate: Send to HW as not eligible and don't send to ODM

	   Discontinued		         20	  Not eligibleRx : Don't send to both ODM & HW
	   Transferred		        120   Not eligibleRx : Don't send to both ODM & HW
	   Revoked			         90   Not eligibleRx : Don't send to both ODM & HW
	   Unknown			        100   Not eligibleRx : Don't send to both ODM & HW
	   Transfer		            110   Not eligibleRx : Don't send to both ODM & HW
	   Log Pending		        130   Not eligibleRx : Don't send to both ODM & HW
	   Waiting for data entry	330   Not eligibleRx : Don't send to both ODM & HW
	   Refused to Fill		    340   Not eligibleRx : Don't send to both ODM & HW
	   Adapt			        350   Not eligibleRx : Don't send to both ODM & HW
	   ePrescription		    360   Not eligibleRx : Don't send to both ODM & HW
	   Other Medication	         70   Not eligibleRx : Don't send to both ODM & HW 
	   
		          rxFillStatus != RxFillStatus.DISCONTINUED           &&
				  rxFillStatus != RxFillStatus.TRANSFERRED            &&
				  rxFillStatus != RxFillStatus.REVOKED                &&
				  rxFillStatus != RxFillStatus.UNKNOWN                &&
				  rxFillStatus != RxFillStatus.TRANSFER               &&
				  rxFillStatus != RxFillStatus.LOG_PENDING            &&
				  rxFillStatus != RxFillStatus.WAITING_FOR_DATA_ENTRY &&
				  rxFillStatus != RxFillStatus.REFUSED_TO_FILL        &&
				  rxFillStatus != RxFillStatus.ADAPT                  &&
				  rxFillStatus != RxFillStatus.E_PRESCRIPTION         &&
				  rxFillStatus != RxFillStatus.OTHER_MEDICATION)  {	   
	   
*/
	   
	   
}
