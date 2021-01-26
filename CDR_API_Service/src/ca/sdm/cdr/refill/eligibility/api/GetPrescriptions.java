package ca.sdm.cdr.refill.eligibility.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;


import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.sdm.cdr.common.singleton.TableCacheSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.query.api.CDRGet;

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.PatientNotFoundException;
import com.sdm.cdr.exception.api.KeyNotFoundFromTableCacheException;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.RxFillStatus;
import ca.shoppersdrugmart.rxhb.drx.cse.EligibilityReminder;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;

public class GetPrescriptions extends CDRGet {
	   private static Logger logger = LogManager.getLogger(GetPrescriptions.class);
	   
	   private final static String rxSQL =
             " select /*+FIRST_ROWS*/ "
           + "        r.STORENUM,  "
           + "        r.prscbrkey, " 
           + "        r.rxkey      RX_RXKEY,  "
           + "        r.rxnum      RX_RXNUM,  "

           + "        r.linkedrxkey               RX_LINKEDRXKEY,    "
           + "        r.adminstartdate            RX_ADMINSTARTDATE, "
           + "        r.adminstopdate             RX_ADMINSTOPDATE, " 
           + "        r.isathrtv                  RX_ISATHRTV,    "
           + "        r.batchflg                  RX_BATCHFLG,    "
           + "        r.rxexprtndt                RX_RXEXPRTNDT,  "

           + "        case                                        "
           + "           when to_char(r.rxexprtndt, 'YYYY-MM-DD') >= to_char(sysdate, 'YYYY-MM-DD') "
           + "           then 1                                   "
           + "           else 0                                   "
           + "         end ACTIVE_RX_FLAG,                        "
           
           + "        r.rmngqty                   RX_RMNGQTY,     "
           + "        s.FILLSTATEFFDT             RXSTAT_FILLSTATEFFDT,  "
           + "        s.FILLSTATKEY               RXSTAT_FILLSTATKEY,    "
           + "        s.SUBFILLSTATKEY            RXSTAT_SUBFILLSTATKEY, "

           + "        p.cnsmrid,                                "
           + "        r.drgkey                    RX_DRGKEY,    "
           + "        rd.chmcllblnmeng            PRESCR_DRUG_ENGLISH, "
           + "        rd.chmcllblnmfr             PRESCR_DRUG_FRENCH,  "

           + "        r.cmpndkey                  RX_CMPNDKEY,    "
           + "        c.cmpndid                   RX_CMPNDID,     "
           + "        c.nmeng                     RX_CMPND_ENG,   "

           + "        r.packkey                   RX_PACKKEY,     "
           + "        p.drgkey                    RX_PACK_DRGKEY, "
           + "        p.gtin                      RX_PACK_GTIN    "

           + "   from rx     r,  "
           + "        rxstat s,  "
           + "        pack   p,  "
           + "        cmpnd  c,  "
           + "        drg    rd  "
           + "  where r.ptntkey = ?             "
           + "    and r.rxkey   = s.rxkey       "
           + "    and r.packkey = p.packkey(+)  "
           + "    and to_char(r.updttimestamp, 'YYYY-MM-DD') >= ? "
           + "    and r.cmpndkey= c.cmpndkey(+) "
           + "    and r.drgkey  = rd.drgkey(+)  ";


	   String strNum;
	   private Map<String, Object> twoResponses = new HashMap<String, Object>(); 
	   private List<Prescription> prescriptionList = new ArrayList<>();
	   private List<EligibilityReminder> notEligibleReminder = new ArrayList<>();
	   
	   
	   List<Dispense> dispenseList = new ArrayList<>();
	   Prescription thisPrescription = null;
	   PrescriptionElements rxElements;
	   DispensesElements    txElements;

	   private Map<Integer, Long[]> normalRxMap = new HashMap<>();     // rxNum, [RxKey, linkedRxKey, eligibleFlag]
	   private Map<Long, Long>      linkedRxMap = new HashMap<>();     // linkedRxKey,  RxKey
	   private Set<String> linkedRxDispenses = new HashSet<>();        // (rxNum || txNum)
	   private Map<Long, Prescription> rxResultSet = new HashMap<>();  // RxKey, Prescription
	   private Map<Long, Integer> rxKey2IndexMap = new HashMap<>();    // RxKey, "Prescription Index"
	   private Set<String> gtinSet = new HashSet<>();
	   
	   

	   public Map<String, Object> fetchPrescriptions(Connection conn, Long patientKey) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {		   
		      rxElements = new PrescriptionElements();
		      txElements = new DispensesElements();
		      
 			  DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		      LocalDate years3oldDate = LocalDate.now().minusYears(3);
		      String years3oldDateString = years3oldDate.format(format);
		      
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      
		      try {
			      ps = conn.prepareStatement(rxSQL);
		    	  CommonUtil.setPsLongParam(ps, 1, patientKey);
		    	  CommonUtil.setPsStringParam(ps, 2, years3oldDateString);
		    	  
			      Long timer = System.currentTimeMillis();
			      rs = ps.executeQuery();
			      if (logger.isDebugEnabled()) {logger.debug(String.format("Refilleligibility rxSQL ended in %s ms  for patientKey: %s", (System.currentTimeMillis() - timer),  patientKey));}
			      
			      Long timer2 = System.currentTimeMillis();
			      int prescriptionIndex = 0;
				  while (rs.next()) {
					    Long rxkey = CommonUtil.getLong("RX_RXKEY", rs);
					    thisPrescription = new Prescription();
					    rxElements.populatePrescription(thisPrescription, normalRxMap, linkedRxMap, rxResultSet, gtinSet, rs);
				        prescriptionList.add(prescriptionIndex, thisPrescription);
				        rxKey2IndexMap.put(rxkey, (Integer)prescriptionIndex);
				        prescriptionIndex++;
				  }

				  if (logger.isDebugEnabled()) {logger.debug(String.format("Refilleligibility rxResultSet ended in %s ms  for patientKey: %s", (System.currentTimeMillis() - timer2),  patientKey));}
				  CommonUtil.closePreparedStatementResultSet(ps, rs);

				  if ( ! prescriptionList.isEmpty() ) {
					  activateEligibleLinkedRx(normalRxMap, rxResultSet);
					  populateTmpTable(conn, normalRxMap, rxResultSet);
					  txElements.populateDispenses(conn, prescriptionList, rxKey2IndexMap, years3oldDateString, gtinSet, linkedRxDispenses);
					  Map<String, String[]> gtinDataMap = getCrxFlags(conn);
					  
					  List<Prescription> notEligiblePrescriptions = new ArrayList<>();
					  populateLinkedForwardRx(prescriptionList, notEligiblePrescriptions, gtinDataMap);
					  if (logger.isDebugEnabled()) {logger.debug(String.format("Total Prescriptions found : %s ",  prescriptionList.size()));}

					  
//				      REMOVE: Not eligibleRx, means Don't send to both ODM & HW
//		  		          OR  PrepopulateRx, means Send to HW as not eligible and don't send to ODM
					  prescriptionList.removeAll(notEligiblePrescriptions);
					  if (logger.isDebugEnabled()) {logger.debug(String.format("EligiblePrescriptions found : %s    notEligiblePrescriptions found : %s ",  prescriptionList.size(),  notEligiblePrescriptions.size()));}
				  }
				  
				  
			  } catch (SQLException e) {
					e.printStackTrace();
		            throw e;
		      } catch (Exception e) {
		    	    e.printStackTrace();
//		            throw e;
	          } finally {
	    	        CommonUtil.closePreparedStatementResultSet(ps, rs);
	          }
		      
		      twoResponses.put("ELIGIBLE", prescriptionList);
		      twoResponses.put("NOT_ELIGIBLE", notEligibleReminder);
			  return twoResponses;
	   }

	   
	   
	   private void activateEligibleLinkedRx(Map<Integer, Long[]> normalRxMap, 
			                                 Map<Long, Prescription> rxResultSet) throws Exception {
		   
		   normalRxMap.forEach((rxNum, threeKeys) -> {
//                     System.out.printf("%s : %d%n", rxNum, threeKeys);
                       Long linkedRxKey   = threeKeys[1];
                       Long eligibleRxKey = threeKeys[2];    
                       
                       if (eligibleRxKey == 1L && linkedRxKey != null) {
                    	   Prescription linkedRxPrescription = rxResultSet.get(linkedRxKey);
	     				   if (linkedRxPrescription != null) {
		     				   int linkedRxRxNum = linkedRxPrescription.getPrescriptionNumber();
		     				   Long[] linkedRxThreeKeys = new Long[3];
		    				   linkedRxThreeKeys = normalRxMap.get(linkedRxRxNum);
		    				   linkedRxThreeKeys[2] = 1L;     // make this linkedRx to be eligible 
		    				   normalRxMap.put(linkedRxRxNum, linkedRxThreeKeys);
		    				   if (logger.isDebugEnabled()) {logger.debug(String.format("LinkedRx with RxNum: %s has been activated", linkedRxRxNum));}
	     				   }
                       }
           });
		   
	   }

	   
	   private void populateTmpTable(Connection conn, Map<Integer, Long[]> normalRxMap,
			                                          Map<Long, Prescription> rxResultSet) throws SQLException, Exception  {
		   
		      String sql = "insert into tmp_eligibility(NOTIFICATIONKEY) values(?)";
		      PreparedStatement ps = null;
	
		      try {
				  ps = conn.prepareStatement(sql);
				  for (Map.Entry<Integer, Long[]> mapElement : normalRxMap.entrySet()) { 
					   Long[] threeKeys = mapElement.getValue();
					   Long rxKey = threeKeys[0];
					   Long eligibleRxKey = threeKeys[2];
					   
					   switch (eligibleRxKey.intValue()) {
				              case 1: // eligibleRx: Send to both ODM & HW                       
				            	    ps.setLong(1, rxKey);
							        ps.executeUpdate();
				            	    break;
				            
			                  case 2: // Prepopulate: Send to HW as not eligible and don't send to ODM
								    Prescription notEligiblePrescription = rxResultSet.get(rxKey);
								    String notEligibleRxNum = String.format("%20d", notEligiblePrescription.getPrescriptionNumber());
								    notEligibleRxNum = notEligibleRxNum.replaceAll("\\s+", "");    // remove spaces (if any)

								    EligibilityReminder elReminder = new EligibilityReminder();
								    elReminder.setPrescriptionNumber(notEligibleRxNum);
								    elReminder.setIsAutoRefillEligible("2");
								    elReminder.setIsRefillEligible("2");
								    elReminder.setPortalAutoRefillFlag("2"); 
								    notEligibleReminder.add(elReminder);
			                        break;
					   }

				  }
  		          CommonUtil.closePreparedStatementResultSet(ps, null);
  		      
		      } catch (SQLException e) {
			  	       e.printStackTrace();
			  	       throw e;
		      } catch (Exception e) {
		    	       e.printStackTrace();
		               throw e;
		      } finally {
	    	        CommonUtil.closePreparedStatementResultSet(ps, null);
	          }			      
	   }


	   
	   
	   private Map<String, String[]> getCrxFlags(Connection connection) throws SQLException, KeyNotFoundFromTableCacheException, NamingException, IOException {
		       String sql =
	                  " select GTIN, "
	                + "        REFILLREMINDERFLAG,     "
	                + "        AUTOREFILLFLAG   "
	      	        + "   from CRX_DRGADHERANCE "
	      	        + "  WHERE PROVKEY = ?      "
	      	        + "    and ltrim(GTIN,'0') in GTIN_FILLER_LIST ";

		       Long provKey = getKeyFromCode("PROV2STORE_VW", strNum);
		       StringJoiner tokens = new StringJoiner(",",   " (",   ")");
			   for (String thisGtin : gtinSet ) {
				    if (logger.isDebugEnabled()) {logger.debug(String.format("thisGtin : '%s'", thisGtin));}
				    tokens.add((String.format("'%s'", thisGtin)));
			   }
			   String gtinListInSQL = tokens.toString();
			   if(gtinSet.isEmpty()) {
				   return new HashMap<>();
			   }
			   sql = sql.replace("GTIN_FILLER_LIST", gtinListInSQL);
			   if (logger.isDebugEnabled()) {logger.debug(String.format("sql = %s", sql));}
			   
			   Map<String, String[]> gtinDataMap = new HashMap<>();
			   String[] twoFlags;
		       PreparedStatement ps = connection.prepareStatement(sql);
		       ResultSet rs = null;
		       try {
		    	   ps.setString(1, strNum);
		    	   CommonUtil.setPsLongParam(ps, 1, provKey);
		    	   rs = ps.executeQuery();
		    	   while (rs.next()) {
		    		   String gtin = rs.getString("GTIN");
		    		   gtin = gtin.replaceFirst("^0+(?!$)", "");    // remove leading zero(s) if any
		    		   
		    		   twoFlags = new String[2];
		    		   twoFlags[0] = rs.getString("REFILLREMINDERFLAG");
		    		   twoFlags[1] = rs.getString("AUTOREFILLFLAG");
		    		   
		    		   gtinDataMap.put(gtin, twoFlags);
		    	   }
				   return gtinDataMap;
		       } catch (SQLException e) {
		    	    e.printStackTrace();
					throw e;
			   } finally {
					CommonUtil.closePreparedStatementResultSet(ps, rs);
			   }
	   }
	   
	   
	   
	/*	
		LTPHCP319 Started
		            twoKeys[0]  twoKeys[1]
	----------      ---------   --------------	
	rxNum	        rxKey	    linkedRxKey
	----------      ---------   --------------
	9326438			174986552	(null)
	9351243	 x		160393626	(null)    --> has forwardRxNum = 9358970  if (linkedRxSet.contains(rxKey)... )
	9351603			160397303	(null)
	9358970	 x 		160448040	160393626 --> has linkedRxNum = 9351243   if (linkedRxKey != 0L ) {...
	9362857			160493488	(null)
	9362858			160505950	(null)
	9362859			160503933	(null)
		
	*/		   

	   
		private void populateLinkedForwardRx(List<Prescription> prescriptionList, 
				                             List<Prescription> notEligiblePrescriptions,
				                             Map<String, String[]> gtinDataMap) {
			  Long[] threeKeys = new Long[3];
			
			  String[] twoFlags;
			  String gtin = null;
			  
			  Long rxKey = 0L;
			  Long linkedRxKey = 0L;
			  Long eligibleRxKey = 0L;
			  
			  int rxNum = 0;
			  int relatedRxNum = 0;
			  int relatedTxNum = 0;
			  String txCompositeKey = null;
			  
			  Prescription relatedPrescription = null;
			  Prescription forwardRx = null;
			  RxFillStatus fillStatusCode;
			  
			  
			  if (logger.isDebugEnabled()) {logger.debug(String.format("linkedRxDispenses size = %s ",  linkedRxDispenses.size() )); }
			  for (Prescription currentPrescr : prescriptionList) {
				   rxNum = currentPrescr.getPrescriptionNumber();
				   threeKeys = normalRxMap.get(rxNum);
				   rxKey = threeKeys[0];
				   linkedRxKey = threeKeys[1];
				   eligibleRxKey = threeKeys[2];
				   
//                 if eligibleRx Then Send to both ODM & HW				   
				   if (eligibleRxKey == 1L) { 
//		                   -----------------------------------------------------
//					       CrxDrugAdherance block started
//		                   -----------------------------------------------------
						   if ( currentPrescr.getPrescribedPack() != null &&
						 	    currentPrescr.getPrescribedPack().getGTIN() != null &&
				 	    		currentPrescr.getPrescribedPack().getGTIN().length() > 1) {
						   
							    gtin = currentPrescr.getPrescribedPack().getGTIN();
							    if (logger.isDebugEnabled()) {logger.debug(String.format("rxNum = %s     gtin = %s",  rxNum, gtin )); }
							   
							    twoFlags = gtinDataMap.get(gtin);
							    if(twoFlags!=null) {
								    currentPrescr.getPrescribedPack().setRefillReminderFlag(twoFlags[0]);  // rs.getString("REFILLREMINDERFLAG");
								    currentPrescr.getPrescribedPack().setAutoRefillFlag(twoFlags[1]);      // rs.getString("AUTOREFILLFLAG");
							    }
						   }
		
						   
//	                       -----------------------------------------------------
//				           ForwardRx block started
//	                       -----------------------------------------------------			   
						   if ( linkedRxMap.containsKey(rxKey) ) { 
							   relatedPrescription = new Prescription();
							   relatedPrescription = rxResultSet.get(linkedRxMap.get(rxKey));
							   relatedRxNum = relatedPrescription.getPrescriptionNumber();
							   fillStatusCode = relatedPrescription.getFillStatusCode();
							   
							   if (rxNum < relatedRxNum) {
								   forwardRx = new Prescription(); 
								   forwardRx.setPrescriptionNumber(relatedRxNum);
								   forwardRx.setFillStatusCode(fillStatusCode);
								   if (forwardRx != null) {
									  currentPrescr.setForwardRx(forwardRx);
							       }
						        }  
						   }
		
						   
//	                       -----------------------------------------------------
//				           LinkedRx block started
//	                       -----------------------------------------------------			   
						   boolean containsLinkedRxKey = rxResultSet.containsKey(linkedRxKey);
						   if (containsLinkedRxKey && linkedRxKey != null && linkedRxKey != 0L ) {
							   relatedPrescription = new Prescription();
							   relatedPrescription = rxResultSet.get(linkedRxKey);
							   relatedRxNum = relatedPrescription.getPrescriptionNumber();
							   
							   if (rxNum > relatedRxNum) {
								   List<Dispense> linkedRxDispList = new ArrayList<Dispense>();
								   for (Dispense thisDispense : relatedPrescription.getDispense()) {
									   relatedTxNum = thisDispense.getTransactionNumber();
									   txCompositeKey = String.format("%20d %20d", relatedRxNum, relatedTxNum);
									   if (linkedRxDispenses.contains(txCompositeKey))  {
										   linkedRxDispList.add(thisDispense);
			  						       if (logger.isDebugEnabled()) {logger.debug("Used by linkedRx txCompositeKey: " + txCompositeKey); }  						       
									   }
								   }
								   Prescription linkedRx = populateLinkedPrescription(relatedPrescription);
								   linkedRx.getDispense().addAll(linkedRxDispList);
								   currentPrescr.setLinkedrx(linkedRx);
							   }
						   } 
						   
				   } else {
//                        Collect: Not eligibleRx, means Don't send to both ODM & HW
//					           or  PrepopulateRx, means Send to HW as not eligible and don't send to ODM 
					      notEligiblePrescriptions.add(currentPrescr);
				   }
				   

			  } // end "for" main loop
		} 

		
		private Prescription populateLinkedPrescription(Prescription linkedRx) {
			Prescription rx = new Prescription();
			try {
				rx.setUpdateTimestamp(linkedRx.getUpdateTimestamp());
				
				if(null != linkedRx.getAdministrationPeriodStartDate()){
					rx.setAdministrationPeriodStartDate(linkedRx.getAdministrationPeriodStartDate());
				}
				if(null !=linkedRx.getAdministrationPeriodEndDate()){
					rx.setAdministrationPeriodEndDate(linkedRx.getAdministrationPeriodEndDate());
				}
				
				rx.setConsumerId(null !=linkedRx.getConsumerId()?linkedRx.getConsumerId():null);
				rx.setProducerId(null !=linkedRx.getProducerId()?linkedRx.getProducerId():null);
				rx.setIsAuthoritative(linkedRx.isIsAuthoritative()); //Un comment for future implementation
				rx.setQuantityDispensed(null !=linkedRx.getQuantityDispensed()?linkedRx.getQuantityDispensed():null);
				rx.setRemainingQuantity(null !=linkedRx.getRemainingQuantity()?linkedRx.getRemainingQuantity():null);
				rx.setPrescriptionNumber(null !=linkedRx.getPrescriptionNumber()?linkedRx.getPrescriptionNumber() : null);
				rx.setRefillsRemainingCount(null !=linkedRx.getRefillsRemainingCount()? linkedRx.getRefillsRemainingCount():null);
				rx.setTotalQuantityAuthorized(null !=linkedRx.getTotalQuantityAuthorized()?linkedRx.getTotalQuantityAuthorized():null);
				rx.setDrugTotalDaysOfSupplyCount(null !=linkedRx.getDrugTotalDaysOfSupplyCount()?linkedRx.getDrugTotalDaysOfSupplyCount():null);
				rx.setDrugTrialDaysOfSupplyCount(null != linkedRx.getDrugTrialDaysOfSupplyCount()?linkedRx.getDrugTrialDaysOfSupplyCount() : null);
				rx.setDrugTrialFlag(null !=linkedRx.isDrugTrialFlag()?linkedRx.isDrugTrialFlag():null);
				rx.setDrugTrialQuantity(null !=linkedRx.getDrugTrialQuantity()? linkedRx.getDrugTrialQuantity() : null);
			
				if(null != linkedRx.isInferredPrescriptionFlag() ){
					rx.setInferredPrescriptionFlag(linkedRx.isInferredPrescriptionFlag());
				}
				rx.setNumberOfRefills(null !=linkedRx.getNumberOfRefills()?linkedRx.getNumberOfRefills():null);
				rx.setPrescriptionDate(linkedRx.getPrescriptionDate());
				rx.setPrescriptionExpirationDate(linkedRx.getPrescriptionExpirationDate());
								
		//		rx.setSIGAdditionalInformation(linkedRx.getSIGDescriptionPatientLanguage());
				rx.setSIGCode(null !=linkedRx.getSIGCode()?linkedRx.getSIGCode() : null);
				rx.setSIGDescriptionPatientLanguage(null != linkedRx.getSIGDescriptionPatientLanguage()?linkedRx.getSIGDescriptionPatientLanguage(): null);

				if(null !=linkedRx.isPrescriptionDispensableFlag() ){
					rx.setPrescriptionDispensableFlag(linkedRx.isPrescriptionDispensableFlag());
				}
				rx.setFillStatusEffectiveDate(linkedRx.getFillStatusEffectiveDate());
				if( null != linkedRx.isLegacyFlag()) {
					rx.setLegacyFlag(linkedRx.isLegacyFlag());
				}
				
				rx.setHoldEndDate(linkedRx.getHoldEndDate());
//				rx.setTransferableCode(null);
				rx.setCreateTimestamp(linkedRx.getCreateTimestamp());
				rx.setQuantityPrescribed(null != linkedRx.getQuantityPrescribed()? linkedRx.getQuantityPrescribed(): null);
//				rx.setNoMoreDispensesAllowedFlag(null);
				
				if(null != linkedRx.isIsBatchFlag()) {
					rx.setIsBatchFlag(linkedRx.isIsBatchFlag());
				}
				rx.setNoSubstitutionReason(null !=linkedRx.getNoSubstitutionReason()?linkedRx.getNoSubstitutionReason() : null);
				
				if(null != linkedRx.getFillStatusCode()){
					rx.setFillStatusCode(linkedRx.getFillStatusCode());
				}
				
				if(null != linkedRx.getFillStatusSubCode()){
					rx.setFillStatusSubCode(linkedRx.getFillStatusSubCode());
				}
				rx.setRenewalReasonCode(null != linkedRx.getRenewalReasonCode()? linkedRx.getRenewalReasonCode() : null );
		        
				if(null != linkedRx.getSubstitutionInitiator()){
		        rx.setSubstitutionInitiator(linkedRx.getSubstitutionInitiator());
			    }
				if(null != linkedRx.getTreatmentType()){
					rx.setTreatmentType(linkedRx.getTreatmentType());
				}
				
				rx.setResumeReasonCode((null !=linkedRx.getResumeReasonCode())? linkedRx.getResumeReasonCode(): null);
				rx.setSource(linkedRx.getSource());
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
			return rx;
		}


		
	   public Map<String, Object> fetchPatient(Connection conn, String consumerId, String storeNum) throws SQLException, PatientNotFoundException, ParseException, DatatypeConfigurationException  {
				PreparedStatement ps = null ;
				ResultSet rs = null ;
				strNum = storeNum;
				
				try {
					Map<String, Object> dataMap = new HashMap<>();
					String sql = 
							"select PTNTKEY,                "
						 +  "       dtofbirth  DTOFBIRTH,   " 
					     +  "       dcsddt     DECEASEDDATE " 
						 +  "  from ptnt " 
						 +	" where cnsmrid = ? and storenum = ? ";
					ps = conn.prepareStatement(sql);
					CommonUtil.setPsStringParam(ps, 1, consumerId);
					CommonUtil.setPsStringParam(ps, 2, storeNum);
					rs = ps.executeQuery();
					if (rs.next()) {
						dataMap.put("PTNTKEY", ResultSetWrapper.getLong(rs, "PTNTKEY"));
						dataMap.put("DTOFBIRTH", CommonUtil.getXMLGregorianCalendarByDate(rs.getDate("DTOFBIRTH")));
						dataMap.put("DECEASEDDATE",CommonUtil.getXMLGregorianCalendarByDate(rs.getDate("DECEASEDDATE")));
					} else {
					    throw new PatientNotFoundException(String.valueOf(storeNum), consumerId);
					}
					return dataMap;
				}
				catch (SQLException e) {
					e.printStackTrace();
					throw new SQLException(e.getMessage());
				} finally {
					CommonUtil.closePreparedStatementResultSet(ps,rs);
				}
	   }	


}
