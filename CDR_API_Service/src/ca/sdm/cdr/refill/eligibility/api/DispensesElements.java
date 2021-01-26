package ca.sdm.cdr.refill.eligibility.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import com.sdm.cdr.exception.CDRInternalException;

import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.jdbc.query.api.CDRGet;
import ca.shoppersdrugmart.rxhb.ehealth.Prescription;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.PharmacyChannel;
import ca.shoppersdrugmart.rxhb.ehealth.ChannelType;
import ca.shoppersdrugmart.rxhb.ehealth.EngagementType;
import ca.shoppersdrugmart.rxhb.ehealth.CommunicationModeType;
import ca.shoppersdrugmart.rxhb.ehealth.TxFillStatus;



public class DispensesElements extends CDRGet { 
	   private static Logger logger = LogManager.getLogger(DispensesElements.class);
	   private Set <String> txStatusSet = new HashSet<>();
	   
	   private final static String txSQL =			   
	             " select /*+FIRST_ROWS*/ "
	           + "        t.RXKEY,  "
	           + "        t.TXKEY,  "
	           + "        t.txnnum                TX_TXNNUM,     "
	           + "        t.txtime                TX_TXTIME,     "
	           + "        t.daysintrvl            TX_DAYSINTRVL, "
		       + "        t.dayssply              TX_DAYSSPLY,   "
	           + "        t.pckuptime             TX_PCKUPTIME,  "
	           + "        t.lastpickuptime        TX_LASTPICKUPTIME, "
	           + "        t.qtytxd                TX_QTYTXD,   "
	           + "        t.sigcd                 TX_SIGCD,    "
	           + "        t.sigdescrptntlang      TX_SIGDESCRPTNTLANG,   "
	           + "        t.pkgfrmtypkey          TX_PKGFRMTYPKEY,       "
	           + "        t.pharmacychnltypkey    TX_PHARMACYCHNLTYPKEY, "
	           + "        t.engmnttypkey          TX_ENGMNTTYPKEY,   "
	           + "        t.commmodetypkey        TX_COMMMODETYPKEY,       "
	           + "        s.txfillstattypkey      TXSTAT_TXFILLSTATTYPKEY, "

	           + "        t.packkey               TX_PACKKEY,  "
	           + "        p.gtin                  TX_PACK_GTIN "

	           + "   from tx     t, "
	           + "        txstat s, "
	           + "        cmpnd  c, "
	           + "        pack   p  "
	           + "  where t.rxkey in (select NOTIFICATIONKEY rxkey from tmp_eligibility)  "
	           + "    and t.txkey    = s.txkey       "
	           + "    and t.cmpndkey = c.cmpndkey(+) "
	           + "    and to_char(t.lstupdt, 'YYYY-MM-DD') >= ? "
	           + "    and t.packkey  = p.packkey(+) "
		       + "  order by t.RXKEY, t.txnnum desc";
	
	
	   public void populateDispenses(Connection conn,
			                         List<Prescription> prescriptionList,
			                         Map<Long, Integer> rxKey2IndexMap,
		                             String years3oldDateString,
                                     Set<String> gtinSet,
                                     Set<String> linkedRxDispenses) throws SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException, CDRInternalException {
		   
		           Long rxkey = null;
		           int prescriptionIndex = 0;
		        		   
		           Set<Long> rxkeySet = new HashSet<>();
		           PreparedStatement ps = null;
		           ResultSet rs = null;
		           
		           try {
		        	   Long timer = System.currentTimeMillis();
			           ps = conn.prepareStatement(txSQL);
			           CommonUtil.setPsStringParam(ps, 1, years3oldDateString);
			           rs = ps.executeQuery();
			           if (logger.isDebugEnabled()) {logger.debug(String.format("Refilleligibility txSQL ended in %s ms", (System.currentTimeMillis() - timer)));}
			           
			           Long timer2 = System.currentTimeMillis();
			           while (rs.next()) {
			        	      rxkey = CommonUtil.getLong("RXKEY", rs);
			        	      if (! rxkeySet.contains(rxkey)) {
			        	    	    rxkeySet.add(rxkey);
			        	    	    txStatusSet.clear();
			        	    	    prescriptionIndex = rxKey2IndexMap.get(rxkey);
			        	      }
			        	      getThisDispense(prescriptionList.get(prescriptionIndex), rs, gtinSet, linkedRxDispenses);
			           }
			           if (logger.isDebugEnabled()) {logger.debug(String.format("Refilleligibility txResultSet ended in %s ms", (System.currentTimeMillis() - timer2)));}
			           
		           } catch (SQLException e) {
						e.printStackTrace();
			            throw e;
			       } catch (Exception e) {
			    	    e.printStackTrace();
			            throw e;
		           } finally {
		    	        CommonUtil.closePreparedStatementResultSet(ps, rs);
		           }			  
	   }
	   
	   
	   private void getThisDispense(Prescription thisPrescription,
			                        ResultSet rs, 
			                        Set<String> gtinSet, 
			                        Set<String> linkedRxDispenses) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, NamingException, IOException {

		     Dispense thisDispense = new Dispense();
		     int rxNum = thisPrescription.getPrescriptionNumber();
	    	 int txNum = CommonUtil.getInt("TX_TXNNUM",rs);
	    	 
	    	 thisDispense.setTransactionNumber(txNum);
			 if (logger.isDebugEnabled()) {logger.debug(String.format("getThisDispense with rxNum: %s  txNum: %s", rxNum, txNum));}
			    
	 		 Long packageFormTypeKey = CommonUtil.getLong("TX_PKGFRMTYPKEY",rs);
	 		 if (packageFormTypeKey != null) {
	 			 String packageFormCode = getCodeFromKey("PKGFRMTYP", packageFormTypeKey);
	 			 thisDispense.setPackageForm(packageFormCode);
	 		 }
	    	 
	    	 thisDispense.setDispenseTime(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_TXTIME"), true));
	    	 thisDispense.setDaysInterval(CommonUtil.getInt("TX_DAYSINTRVL",rs));
	    	 thisDispense.setDaysSupply(CommonUtil.getInt("TX_DAYSSPLY",rs));
	    	 thisDispense.setPickupTime(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_PCKUPTIME"), true));
	    	 thisDispense.setPickupTime2(CommonUtil.getXMLGregorianCalendar(rs.getDate("TX_LASTPICKUPTIME"), true));
	    	 thisDispense.setQuantityDispensed(CommonUtil.getBigDecimal("TX_QTYTXD",rs));
	    	 thisDispense.setSigCode(rs.getString("TX_SIGCD"));
	    	 thisDispense.setSigDescriptionPatientLanguage(rs.getString("TX_SIGDESCRPTNTLANG"));

	    	 
	    	 
	    	 // ***********  pack GTIN started: ********************
		   	 String gtin = rs.getString("TX_PACK_GTIN");
		  	 if (gtin != null) {

		  		 // if Pack or GTIN is missing in thisPrescription : 
				 if  (! (thisPrescription.getPrescribedPack() != null &&
				         thisPrescription.getPrescribedPack().getGTIN() != null &&
					     thisPrescription.getPrescribedPack().getGTIN().length() > 1)) {

			  		     gtin = gtin.replaceFirst("^0+(?!$)", "");    // remove leading zero(s) if any
			  	         Pack pack = new Pack();
			  		     pack.setGTIN(gtin);
			  		     thisPrescription.setPrescribedPack(pack);
			  		     
			  		     if (! gtinSet.contains(gtin)) {
			  		    	   gtinSet.add(gtin);
			  		     }
				 }	   
		  	 }
	    	 
	    	 
	    	 // ***********  pharmacyChannel: ********************
	 		 Long pharmacyChannelTypeKey =CommonUtil.getLong("TX_PHARMACYCHNLTYPKEY",rs);
			 Long engagementTypeKey = CommonUtil.getLong("TX_ENGMNTTYPKEY",rs);
			 Long communicationModeKey = CommonUtil.getLong("TX_COMMMODETYPKEY",rs);
	 		 if (pharmacyChannelTypeKey != null && engagementTypeKey != null && communicationModeKey !=null) {
				 PharmacyChannel pharmacyChannel = new PharmacyChannel();
				 String pharmacyChannelTypeCode = getCodeFromKey("PHARMACYCHNLTYP", pharmacyChannelTypeKey);
				 pharmacyChannel.setChannelType(ChannelType.fromValue(pharmacyChannelTypeCode));

				 String engagementTypeCode = getCodeFromKey("ENGMNTTYP", engagementTypeKey);
				 pharmacyChannel.setEngagment(EngagementType.fromValue(engagementTypeCode));
				
				 String communicationModeCode = getCodeFromKey("COMMMODETYP", communicationModeKey);
				 pharmacyChannel.setCommunicationMode(CommunicationModeType.fromValue(communicationModeCode));
				
				 thisDispense.setPharmacyChannel(pharmacyChannel);
			 }

	    	 
	    	 
	         // ***********  dispenseFillStatus Started: ********************
	 		 Long dispenseFillStatusKey = CommonUtil.getLong("TXSTAT_TXFILLSTATTYPKEY",rs);
			 if(dispenseFillStatusKey != null) {
		         String fillStatusCode = getCodeFromKey("TXFILLSTATTYP", dispenseFillStatusKey);
		         if (fillStatusCode != null) {
		        	 thisDispense.setFillStatus(TxFillStatus.fromValue(fillStatusCode));
		        	
		        	 if (rxNum > 0 && linkedRxDispenses != null) {
		        		String txCompositeKey = String.format("%20d %20d", rxNum, txNum);
		        		if (linkedRxDispenses.isEmpty() ) {
		        			linkedRxDispenses.add(txCompositeKey);
			        		txStatusSet.add(thisDispense.getFillStatus().value());
		        		
		        		} else {
		        			if ((! txStatusSet.contains(TxFillStatus.COMPLETE.value()))  &&
	        					(! linkedRxDispenses.contains(txCompositeKey))  &&
	        					(  TxFillStatus.COMPLETE.equals(thisDispense.getFillStatus()) )) {
		        				
			        			linkedRxDispenses.add(txCompositeKey);
			        			txStatusSet.add(thisDispense.getFillStatus().value());
	        				}
		        		}
		        	 }
		         }
			 }
	         // ***********  dispenseFillStatus Ended ********************
		   
			 thisPrescription.getDispense().add(thisDispense);
	   }
}
