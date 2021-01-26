package ca.sdm.cdr.jdbc.api.dispense.query;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;

import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
import ca.sdm.cdr.common.util.ResultSetWrapper;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.jdbc.api.util.CdrQueryMgr;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalService;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;



public class QueryProfessionalService extends JDBCConnection {
	
	private static Logger logger = LogManager.getLogger(QueryProfessionalService.class);
	
	private File   queryOutputFolder = null;
	private String queryOutputFileName = null;
	

	public QueryProfessionalService(Connection conn) {
		super(conn);
	} 
	
	public QueryProfessionalService(Connection conn,File queryOutputFolder, String queryOutputFileName) {
		this(conn);
		this.queryOutputFolder = queryOutputFolder;
		this.queryOutputFileName = queryOutputFileName;
	}	
	
	
	public String prepareSQL(String whereClause) throws CodeNotFoundFromTableCacheException, SQLException, IOException {
		StringBuffer sql = new StringBuffer("");
		CdrQueryMgr cdrQueryMgr = new CdrQueryMgr(QueryProfessionalService.class.getSimpleName(), PRF_SRV_SQL);
		String[][] tableAliasList = cdrQueryMgr.getTableAliasArray();
		TableColumnSingleton columnCache = TableColumnSingleton.getInstance();
		String selectString = columnCache.createSQLSelectFromColumns(connection, tableAliasList);
		sql.append("\n\n-------------------- \n");
		sql.append("\n\nSELECT DISTINCT \n" + selectString);
		sql.append("\n\n" + cdrQueryMgr.getFromClause());
		sql.append("\n\n" + whereClause + "\n\n");
		sql.append("\n" + cdrQueryMgr.getOrderByClause());
		if (getQueryFile()!=null) {
			CdrQueryMgr.appendToFile(getQueryFile(), sql.toString());
		}
		return sql.toString();
	}	

	
	
	

	/**
	 * 
	 * @param rxKey
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 * @throws CDRInternalException
	 */
	public Dispense[] getDispensesByRxKey(Long rxKey) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		if (rxKey==null)
			return null;
		String selectSql = prepareSQL("WHERE TX.RXKEY = ?");
		PreparedStatement ps = null;
		Dispense[] txArray;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, rxKey);
		rs = ps.executeQuery();
		txArray = populateTxArray(rs, "TX");
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return txArray;
	}	
	
	
	public ResultSet getResultSetByRxKey(Long rxKey) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		if (rxKey==null)
			return null;
		String selectSql = prepareSQL("WHERE TX.RXKEY = ?");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, rxKey);
		rs = ps.executeQuery();
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return rs;
	}	
	

	/**
	 * 
	 * @param rxKey
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 * @throws CDRInternalException
	 */
	public Dispense[] getDispensesByRxNum(String rxNum, String storeNum) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		if (rxNum==null)
			return null;
		String selectSql = prepareSQL("WHERE RX.RXNUM = ? AND RX.STORENUM = ?");
		PreparedStatement ps =null;
		Dispense[] txArray ;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setString(1, rxNum);
		ps.setString(2, storeNum);
		rs = ps.executeQuery();
		  txArray = populateTxArray(rs, "TX");
		 
		}	finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return txArray;
	}	
	
	
	
	/**
	 * 
	 * @param txnNum
	 * @param storeNum
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 * @throws CDRInternalException
	 */
	public Dispense getDispenseByTxnNum(String txnNum, String storeNum) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Dispense professionalService = null;
		String selectSql = prepareSQL("WHERE UPPER(Tx.TxnNum)=UPPER(?) AND Tx.StoreNum=?");
		//if(logger.isDebugEnabled()) {logger.debug("Prf Svc SQL: \n" + selectSql);}
		PreparedStatement ps = connection.prepareStatement(selectSql);
		ps.setString(1, txnNum);
		ps.setString(2, storeNum);
		ResultSet rs = ps.executeQuery();
		Dispense[] txArray = populateTxArray(rs, "TX");
		professionalService = (txArray!=null && txArray.length>0) ? txArray[0] : null;
		if (professionalService==null)
			return null;
		try {
			rs.close();
			ps.close();
		} catch (Exception ex) {
		}		
		return professionalService;
	}
	
	
	
	/**
	 * 
	 * @param txnNum
	 * @param storeNum
	 * @return
	 * @throws CDRInternalException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 */
	public ProfessionalService getProfessionalServiceByTxnNum(String txnNum, String storeNum) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException {
		Dispense dispense = getDispenseByTxnNum(txnNum, storeNum);
		ProfessionalService professionalService = (dispense!=null) ? dispense.getProfessionalService() : null;
		return professionalService;
	}
	
	

	/**
	 * 
	 * @param txKey
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 * @throws CDRInternalException
	 */
	public Dispense getDispenseByTxKey(Long txKey) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		Dispense professionalService = null;
		if (txKey==null)
			return null;
		String selectSql = prepareSQL("WHERE Tx.TXKEY = ?");
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, txKey);
		rs = ps.executeQuery();
		Dispense[] txArray = populateTxArray(rs, "TX");
		professionalService = (txArray!=null && txArray.length>0) ? txArray[0] : null;
		
		}finally{
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return professionalService;
	}	

	
	/*
	public ResultSet getResultSetByDispenseKey(Long txKey) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		if (txKey==null)
			return null;
		String selectSql = prepareSQL("WHERE Tx.TXKEY = ?");
		PreparedStatement ps = connection.prepareStatement(selectSql);
		ps.setLong(1, txKey);
		ResultSet rs = ps.executeQuery();
		return rs;
	}	
	*/
	
	public ResultSet getResultSetByTxnNum(Long txnNum, String storeNumber) throws SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException, CDRInternalException {
		if (txnNum==null || storeNumber==null || txnNum < 1 || storeNumber.equals("0"))
			return null;
		String selectSql = prepareSQL("WHERE Tx.TxnNum = ? and Tx.StoreNum = ?");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		ps=connection.prepareStatement(selectSql);
		ps.setLong(1, txnNum);
		ps.setString(2, storeNumber);
		  rs = ps.executeQuery();
		}finally {
			CommonUtil.closePreparedStatementResultSet(ps, rs);
		}
		return rs;
	}		
	
	
	
	/**
	 * 
	 * @param txKey
	 * @return
	 * @throws CDRInternalException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 */
	public ProfessionalService getProfessionalServiceByTxKey(Long txKey) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException {
		Dispense dispense = getDispenseByTxKey(txKey);
		ProfessionalService professionalService = (dispense!=null) ? dispense.getProfessionalService() : null;
		return professionalService;  
	}
	
	

	/**
	 * 
	 * @param rs
	 * @param txAlias
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws CDRInternalException
	 */
	private Dispense[] populateTxArray(ResultSet rs, String txAlias) throws SQLException, NamingException, IOException, ParseException, DatatypeConfigurationException, CDRInternalException  {
		List<Dispense> txList = new ArrayList<Dispense>();
		Dispense iTx = null, prevTx = null;
		long iTxKey = -1, prevTxKey = -1;
		ProfessionalService iPrfSvc = null;
		long jNoteKey = -1, prevNoteKey = -1;
		int rowNo=0, maxRows = 1000;

		while (rs.next() && rowNo < maxRows) {
			rowNo++;
			
			iTxKey = ResultSetWrapper.getKey(rs, txAlias, "TXKEY");
			if (iTxKey != prevTxKey) {
				// new Tx --> pack previous Tx
				iTx = new Dispense();
				String txnNum   = ResultSetWrapper.getString(rs, txAlias, "TXNNUM");
				String storeNum = ResultSetWrapper.getString(rs, txAlias, "STORENUM");
				populateDispenseTxnAndStoreNum(iTx, txnNum, storeNum);
				txList.add(iTx);

				// 1 to 1 relationship between Dispense and Professional Service
				Long prfSvcKey = ResultSetWrapper.getLong(rs, "PrfSvc", "PRFSNLSVCKEY");
				if (prfSvcKey==null || prfSvcKey < 1)
					continue;
				iPrfSvc = PopulateJAXB.populateProfService(rs, "PrfSvc");
				if(logger.isDebugEnabled()) {logger.debug(" TxKey [TxKey: : " + iTxKey + "], Prf Svc [Key: " + prfSvcKey + "], [TxnNum: " + iPrfSvc.getConsumerId() + "]");}
				populateProfessionalService(rs, iPrfSvc);
				iTx.setProfessionalService(iPrfSvc);
				iPrfSvc.getNote().clear();
				
				jNoteKey = ResultSetWrapper.getLong(rs, "Nt", "PRFSNLSVCNTKEY");
				if (jNoteKey < 1)
					continue;
				if(logger.isDebugEnabled()) {logger.debug("Getting NoteKey: " + jNoteKey + " for Tx [TxnNum: " + iTx.getTransactionNumber() + "], " +
						", ProfessionalService [CID: " + iPrfSvc.getConsumerId( )+ "]");}
				Note noteData = PopulateJAXB.populateNote(rs, "Nt", "PRFSNLSVCNTKEY");
				if (noteData != null) {
					iPrfSvc.getNote().add(noteData);
				}				
				
				prevTxKey = iTxKey;
				prevTx = iTx;
				prevNoteKey = jNoteKey; 
			} else {
				// same iTx and PrfSvc
				jNoteKey = ResultSetWrapper.getLong(rs, "Nt", "PRFSNLSVCNTKEY");
				if (jNoteKey < 1)
					continue;
				if(logger.isDebugEnabled()) {logger.debug("Getting NoteKey: " + jNoteKey + " for Tx [TxnNum: " + iTx.getTransactionNumber() + "], " +
						", ProfessionalService [CID: " + iPrfSvc.getConsumerId( )+ "]");}
				if (jNoteKey != prevNoteKey) {
					// New note --> add to note list
					Note noteData = PopulateJAXB.populateNote(rs, "Nt", "PRFSNLSVCNTKEY");
					if (noteData != null) {
						iPrfSvc.getNote().add(noteData);
					}						
				} else {
					// same note
				}
				
			}
			
		}
		Dispense[] txArray = new Dispense[txList.size()];
		txArray = txList.toArray(txArray);
		return txArray;
	}	
	
	

	private void populateProfessionalService(ResultSet rs, ProfessionalService professionalService) throws SQLException, CDRInternalException, IOException, ParseException, DatatypeConfigurationException {
		String observationTypeCode = ResultSetWrapper.getString(rs, "PrfSvc", "OBSRVTNTYPCD");
//		ServiceType serviceType = (observationTypeCode!=null) ? ServiceType.fromValue(observationTypeCode) : null;
//		professionalService.setObservationTypeCode(serviceType);
		professionalService.setObservationTypeCode(observationTypeCode);
		
		//Long serviceLocationKey = ResultSetWrapper.getLongVal(rs, prfSvcAlias, "LOCKEY");
		Location svcLocation = PopulateJAXB.populateLocation(rs, "Lc");
		professionalService.setServiceLocation(svcLocation);
		
		//Long medicalPractitionerKey = ResultSetWrapper.getLongVal(rs, prfSvcAlias, "SVCPRVDRKEY");
		MedicalPractitioner medicalPractitioner = PopulateJAXB.populateMedicalPractitioner(rs, "MdPc", "MdPcRl");
		professionalService.setServiceProvider(medicalPractitioner);
		
		//Long supervisorKey = ResultSetWrapper.getLongVal(rs, prfSvcAlias, "SPRVSRKEY");
		Supervisor supervisor = PopulateJAXB.populateSupervisor(rs, "Sp", "SpRl");
		professionalService.setSupervisor(supervisor);
		
		//keepRelatedInfo(professionalService, rs, txAlias, relatedResultsMap);		
	}
	


	/**
	 * Populate Dispense's TxnNum and StoreNum
	 * 
	 *  
	 * @param dispense	Dispense object to populate
	 * @param txnNum	Txn Number
	 * @param storeNum	Store Number
	 */
	private void populateDispenseTxnAndStoreNum(Dispense dispense, String txnNum, String storeNum) {
	   Integer txnNumber = null;
	   Integer storeNumber = null;
	   try {
		   txnNumber = new Integer(txnNum);
	   } catch (NumberFormatException nfe) {
	   }
	   try {
		   storeNumber = new Integer(storeNum);
	   } catch (NumberFormatException nfe) {
	   }
	   dispense.setTransactionNumber(txnNumber);
	   dispense.setSequenceNumber(storeNumber);
	}
	

	/**
	 * Populate Professional Services ByRxKey
	 * 
	 * @param rxKey						RxKey
	 * @param targetDispenses			Target Dispense objects to populate
	 * 
	 * @throws CDRInternalException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 */
	public void populateProfessionalServicesByRxKey(Long rxKey, List<Dispense> targetDispenses) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException {
		Dispense[] txsWithPrfSrvcs = getDispensesByRxKey(rxKey);
		populateProfessionalServices(targetDispenses, txsWithPrfSrvcs);
	}
	

	/**
	 * Populate Professional Services ByRxNum
	 * 
	 * @param rxKey						RxNum
	 * @param targetDispenses			Target Dispense objects to populate
	 * 
	 * @throws CDRInternalException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 * @throws NamingException
	 */
	public void populateProfessionalServicesByRxNum(String rxNum, String storeNum, List<Dispense> targetDispenses) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException {
		Dispense[] txsWithPrfSrvcs = getDispensesByRxNum(rxNum, storeNum);
		populateProfessionalServices(targetDispenses, txsWithPrfSrvcs);
	}	
	

	
	/**
	 * Private method to populate targetDispenses list 
	 * using Professional Services info from txsWithPrfSrvcs list
	 *     
	 * @param targetDispenses	Target dispenses to populate
	 * @param txsWithPrfSrvcs	Dispenses with Professional Services info
	 */
	private void populateProfessionalServices(List<Dispense> targetDispenses, Dispense[] txsWithPrfSrvcs) throws CDRInternalException, SQLException, IOException, ParseException, DatatypeConfigurationException, NamingException {
 	  if (targetDispenses!=null && targetDispenses.size() > 0) {
 		  if (txsWithPrfSrvcs==null || txsWithPrfSrvcs.length < 1) {
 			  return;
 		  }	  
 		  Map<Integer, Dispense> txnPrfSrvMap = new HashMap<Integer, Dispense>();
 		  for (Dispense iTxPrfSrv : txsWithPrfSrvcs) {
 			  txnPrfSrvMap.put(iTxPrfSrv.getTransactionNumber(), iTxPrfSrv);
 		  }
 		  
 		  for (Dispense dispense : targetDispenses) {
 			  Integer targetTxn = dispense.getTransactionNumber();
 			  Dispense iTxPrfSrv = txnPrfSrvMap.get(targetTxn);
 			  ProfessionalService iPrfSvc = (iTxPrfSrv.getProfessionalService()!=null) ? iTxPrfSrv.getProfessionalService() : null;
 			  if (iPrfSvc!=null) {
 				  dispense.setProfessionalService(iPrfSvc);
 			  }
 		  } 
 	  } 		   
	}	
	   

	
	
	private File getQueryFile() {
		if (queryOutputFolder==null || queryOutputFileName==null || queryOutputFileName.trim().length()<1)
			return null;
		File queryOutFile = null;
		try {
			queryOutputFolder.mkdirs();
			queryOutFile = new File(this.queryOutputFolder, this.queryOutputFileName); 
		} catch (Exception exc) {
			return null;
		}
		return queryOutFile;
	}
	

	private final String PRF_SRV_SQL = 
	" Select * \n" +  
	" FROM Rx Rx \n" +
	
	" left outer join Tx Tx on Tx.RxKey = Rx.RxKey \n" +
	
	" left outer join PrfsnlSvc PrfSvc		on tx.TxKey = PrfSvc.TxKey \n" +          
	
	" left outer join Loc Lc				on PrfSvc.LocKey = Lc.LocKey \n" +           
	" left outer join CntctMthd LcCtMth		on (LcCtMth.LocKey = Lc.LocKey and LcCtMth.PtntKey is null and LcCtMth.PrsnKey is null) \n" +          
	" left outer join Email LcEmail			on LcCtMth.CntctMthdKey = LcEmail.EmailKey \n" +           
	" left outer join Addr LcAddr			on LcCtMth.CntctMthdKey = LcAddr.AddrKey \n" +          
	" left outer join Telecom LcTel			on LcCtMth.CntctMthdKey = LcTel.TelecomKey \n" +           
	
	" left outer join PrsnRl MdPcRl			on PrfSvc.SvcPrvdrKey = MdPcRl.PrsnRlKey \n" +           
	" left outer join prsn MdPc				on MdPcRl.PrsnRlKey = MdPc.PrsnKey \n" +           
	" left outer join CntctMthd MdPcCtMth	on MdPc.PrsnKey = MdPcCtMth.PrsnKey \n" +          
	" left outer join Email MdPcEmail		on MdPcCtMth.CntctMthdKey = MdPcEmail.EmailKey \n" +           
	" left outer join Addr  MdPcAddr		on MdPcCtMth.CntctMthdKey = MdPcAddr.AddrKey \n" +           
	" left outer join Telecom MdPcTel		on MdPcCtMth.CntctMthdKey = MdPcTel.TelecomKey \n" +          
	" left outer join PrfsnlReg MdPcRg 		on MdPcRg.PRSNRLKEY=MdPcRl.PrsnRlKey \n" +
	
	" left outer join PrsnRl SpRl			on PrfSvc.SprvsrKey = SpRl.PrsnRlKey \n" +           
	" left outer join prsn Sp				on SpRl.PrsnRlKey = Sp.PrsnKey \n" +           
	" left outer join CntctMthd SpCtMth		on Sp.PrsnKey = SpCtMth.PrsnKey \n" +          
	" left outer join Email SpEmail			on SpCtMth.CntctMthdKey = SpEmail.EmailKey \n" +           
	" left outer join Addr SpAddr			on SpCtMth.CntctMthdKey = SpAddr.AddrKey \n" +           
	" left outer join Telecom SpTel			on SpCtMth.CntctMthdKey = SpTel.TelecomKey \n" +
	" left outer join PrfsnlReg SpRg 		on SpRg.PRSNRLKEY=SpRl.PrsnRlKey \n" +	
	
	" --< Note(s) \n" + 
	" left outer join PrfsnlSvcNt Nt		on PrfSvc.PrfsnlSvcKey = Nt.PrfsnlSvcKey \n" +           
	" left outer join PrsnRl  NtSpRl		on Nt.SprvsrKey = NtSpRl.PrsnRlKey \n" +           
	" left outer join prsn NtSp				on NtSpRl.PrsnRlKey = NtSp.PrsnKey \n" +            
	" left outer join CntctMthd NtSpCtMth	on NtSp.PrsnKey = NtSpCtMth.PrsnKey \n" +             
	" left outer join Email NtSpEmail		on NtSpCtMth.CntctMthdKey = NtSpEmail.EmailKey \n" +             
	" left outer join Addr NtSpAddr			on NtSpCtMth.CntctMthdKey = NtSpAddr.AddrKey \n" +            
	" left outer join Telecom NtSpTel		on NtSpCtMth.CntctMthdKey = NtSpTel.TelecomKey \n " +
	" left outer join PrfsnlReg NtSpRg 		on NtSpRg.PRSNRLKEY=NtSpRl.PrsnRlKey \n" +	
	
	" left outer join PrsnRl  RcRl			on Nt.RcrdrKey = RcRl.PrsnRlKey \n" +             
	" left outer join prsn Rc				on RcRl.PrsnRlKey = Rc.PrsnKey \n" +              
	" left outer join CntctMthd NtRcCtMth	on Rc.PrsnKey = NtRcCtMth.PrsnKey \n" +               
	" left outer join Email RcEmail			on NtRcCtMth.CntctMthdKey = RcEmail.EmailKey \n" +               
	" left outer join Addr RcAddr			on NtRcCtMth.CntctMthdKey = RcAddr.AddrKey \n" +              
	" left outer join Telecom RcTel			on NtRcCtMth.CntctMthdKey = RcTel.TelecomKey \n" +
	" left outer join PrfsnlReg NtRcRg 		on NtRcRg.PRSNRLKEY=RcRl.PrsnRlKey \n" +	
	
	" left outer join Loc  NtLc				on Nt.LocKey = NtLc.LocKey \n" +               
	" left outer join CntctMthd NtLcCtMth	on (NtLcCtMth.LocKey = NtLc.LocKey and NtLcCtMth.PtntKey is null and NtLcCtMth.PrsnKey is null)  \n" +               
	" left outer join Email NtLcEmail		on NtLcCtMth.CntctMthdKey = NtLcEmail.EmailKey \n" +               
	" left outer join Addr NtLcAddr			on NtLcCtMth.CntctMthdKey = NtLcAddr.AddrKey \n" +               
	" left outer join Telecom NtLcTel		on NtLcCtMth.CntctMthdKey = NtLcTel.TelecomKey \n" +
	
	" where 1=1 \n" +      
	
	" order by \n" +
	" tx.TxKey \n" +
	" , LcCtMth.CntctMthdKey  \n" +
	" , Nt.PrfsnlSvcKey \n" +
	" , NtLcCtMth.CntctMthdKey ";



	
	
}
