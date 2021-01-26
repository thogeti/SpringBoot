package ca.sdm.cdr.jdbc.api.dispense.query;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;










//import com.sdm.cdr.exception.CDRDataException;
import com.sdm.cdr.exception.CDRInternalException;
import com.sdm.cdr.exception.api.CodeNotFoundFromTableCacheException;
import com.sdm.cdr.exception.api.DispenseNotFoundException;

import javax.naming.NamingException;

import ca.sdm.cdr.jdbc.api.address.query.AddressApi;
import ca.sdm.cdr.jdbc.api.populate.PopulateJAXB;
import ca.sdm.cdr.common.singleton.TableColumnSingleton;
import ca.sdm.cdr.common.util.CommonUtil;
//    import ca.sdm.cdr.jdbc.patient.query.api.QueryPatient;
import ca.sdm.cdr.jdbc.JDBCConnection;
import ca.shoppersdrugmart.rxhb.ehealth.Address;
import ca.shoppersdrugmart.rxhb.ehealth.Compound;
import ca.shoppersdrugmart.rxhb.ehealth.CompoundIngredient;
import ca.shoppersdrugmart.rxhb.ehealth.Dispense;
import ca.shoppersdrugmart.rxhb.ehealth.Drug;
import ca.shoppersdrugmart.rxhb.ehealth.Initiator;
import ca.shoppersdrugmart.rxhb.ehealth.Location;
import ca.shoppersdrugmart.rxhb.ehealth.MedicalPractitioner;
import ca.shoppersdrugmart.rxhb.ehealth.Note;
import ca.shoppersdrugmart.rxhb.ehealth.Pack;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalRegistration;
import ca.shoppersdrugmart.rxhb.ehealth.ProfessionalService;
import ca.shoppersdrugmart.rxhb.ehealth.Recorder;
import ca.shoppersdrugmart.rxhb.ehealth.Supervisor;
import ca.shoppersdrugmart.rxhb.ehealth.TxFillStatus;


public class QueryDispense extends JDBCConnection {


/*
 
Dispense    0005
---------
    |
    |       0010
    |-----> Compound(cmpnd) 0012                            0014       0016       0017            0018
    |            |------->> List(CompoundIngredients) ----> Pack ----> Drug ---> Drug_Mfctr  ---> Drug_MfctrDrgRecall 
    |                                                                     | ---> Molecule
    |            
    |                                                  
    |       0020
    |-----> Initiator(Initr)
    |           |
    |           |----> 0022 {Addr, Email, Telecom}
    |           |
    |           |----> 0024 ProfessionalRegistration
    |
    |
    |       0030
    |-----> Location(txLoc)
    |             |----> 0032 {Addr, Email, Telecom}
    |             |
    |             |----> 0034 Store ???
    |
    |       0040
    |-----> Dispenser ---> ???
    |
    |
    |
    |       0050              0052      0053             0054
    |-----> Pack(txPack) ---> Drug ---> Drug_Mfctr  ---> Drug_MfctrDrgRecall 
    |                            | ---> Molecule
    |
    |
    |        0060
    |------> DosageForm  ??
    |
    |        0070
    |----->> List(Notes) TxNt
    |        ____________
    |            |   |   |--------> 0072 recorder    ---> {Addr, Email, Telecom}
    |            |   |
    |            |   -------------> 0074 Supervisor  ---> {Addr, Email, Telecom}
    |            |
    |            |----------------> 0076 Location    ---> {Addr, Email, Telecom}
    |
    |
    |        0080
    |------> Supervisor(txSuper) ---> {Addr, Email, Telecom}
    |
    |
    |        0090  
    |------> Recorder(txRec) ---> {Addr, Email, Telecom}
    |
    |
    |
    |
    |        0100
    |------> ProfessionalService(PrfsnlSvc)
    |        ______________________________       
    |          |       |        |          |--> 0110 Location -------------> 0115 {Addr, Email, Telecom}
    |          |       |        |                  
    |          |       |        |-------------> 0120 MedicalPractitioner --> 0125 {Addr, Email, Telecom}
    |          |       |
    |          |       |----------------------> 0130 Supervisor  ----------> 0135 {Addr, Email, Telecom}
    |          |      
    |          |----------------------------->> 0140 List(Notes) PrfsnlSvcNt
    |                                           ____________
    |                                           |   |   |--------> 0143 recorder    ---> {Addr, Email, Telecom}
    |                                           |   |
    |                                           |   -------------> 0146 Supervisor  ---> {Addr, Email, Telecom}
    |                                           |
    |                                           |----------------> 0148 Location    ---> {Addr, Email, Telecom}
    |
    |
    |------> PrescriptionHoldReasonTypeDisplay   ???
    |------> PrescriptionResumeReasonTypeDisplay ???  
 
 
 */

	
/*
	   @revision 
	   TAG  Date	     Vendor       Name 	        Change
	   ---- -----------  -----------  -----------   -------------------
	   VL99 2018-01-15   NTT Data     Vlad Eidinov  QHR Accuro Project
	                                    
*/
	
	private static Logger logger = LogManager.getLogger(QueryDispense.class);
	
		private String dispenseSQL = null;
	
	  /******************************************************************************
	   * query alias
	   ******************************************************************************/
	   private final String txAlias = "TX";

	   private final static String TxStatAlias = "TxStat";
	   
  	   private final String  cmpndAlias = "CMPND";
   	   private final String  CmpndIngrdntAlias = "CMPNDINGRDNT";
       private final String  CmpIngrPackAlias = "CMPINGRPACK";
       private final String  CmpIngrPackDrgAlias = "T10";
       private final String  drDsgFrm = "drDsgFrm";


       private final String  cipgMfctrAlias = "CIPGMFCTR";
       private final String  cipgMfctrRecallAlias = "T15";
       private final String  cmpndDrgMlclAlias = "CMPNDDRGMLCL";

       //private final String  InitrAlias = "INITR";
       private final String  prsnInitrAlias = "PRSNINITR";
       private final String  txInitrCntctMthdAlias = "T20";
       private final String  txInitrEmailAlias = "TXINITREMAIL";
       private final String  txInitrAddrAlias = "TXINITRADDR";
       private final String  txInitrTelecomAlias = "TXINITRTELECOM";

       private final String  txLocAlias = "TXLOC";
       private final String  txLocCntctMthdAlias = "T25";
       private final String  txLocEmailAlias = "TXLOCEMAIL";
       private final String  txLocAddrAlias = "TXLOCADDR";
       private final String  txLocTelecomAlias = "TXLOCTELECOM";
       
       
       private final String  txPackAlias = "TXPACK";
       private final String  txPackDrgAlias = "TXPACKDRG";
       private final String  txPacDsgFrm = "txPacDsgFrm";
       private final String  txDrugMfctrAlias = "TXDRUGMFCTR";
       private final String  txDrugMfctrRecallAlias = "T40";
       private final String  txPackDrgMlclAlias = "T45";
       //private final String  txSUOMTAlias = "TXSUOMT";

       private final String  TxNtAlias = "TXNT";
       private final String  TxNtRLSAlias = "TXNTRLS";
       private final String  TxNtSuperAlias = "TXNTSUPER";
       private final String  TxNtSuperCntctMthdAlias = "T50";
       private final String  txNtSuperAddrAlias = "TXNTSUPERADDR";
       private final String  txNtSuperEmailAlias = "TXNTSUPEREMAIL";
       private final String  txNtSuperTelecomAlias = "TXNTSUPERTELECOM";

       private final String  TxNtRLRAlias = "TXNTRLR";
       private final String  txNtRecAlias = "TXNTREC";
       private final String  txNtRecCntctMthdAlias = "T60";
       private final String  txNtRecAddrAlias = "TXNTRECADDR";
       private final String  txNtRecEmailAlias = "TXNTRECEMAIL";
       private final String  txNtRecTelecomAlias = "TXNTRECTELECOM";

       
       // 0076
       private final String txNtLocAlias          = "TXNTLOC";
       private final String txNtLocCntctMthdAlias = "T65";
       private final String txLocNtAddrAlias      = "TXLOCNTADDR";
       private final String txLocNtEmailAlias     = "TXLOCNTEMAIL";
       private final String txLocNtTelecomAlias   = "TXLOCNTTELECOM";		          
       
       
       private final String  txPrsnRLSAlias = "TXPRSNRLS";
       private final String  txSuperAlias = "TXSUPER";
       private final String  txSuperCntctMthdAlias = "T70";
       private final String  txSuperEmailAlias = "TXSUPEREMAIL";
       private final String  txSuperAddrAlias = "TXSUPERADDR";
       private final String  txSuperTelecomAlias = "TXSUPERTELECOM";

       private final String  txPrsnRLRAlias = "TXPRSNRLR";
       private final String  txRecAlias = "TXREC";
       private final String  txRecCntctMthdAlias = "T75";
       private final String  txRecEmailAlias = "TXRECEMAIL";
       private final String  txRecAddrAlias = "TXRECADDR";
       private final String  txRecTelecomAlias = "TXRECTELECOM";


    // 0100 ProfessionalService Location:
       private final String  PrfSvcAlias = "PRFSVC";
       private final String  PrfSvcLocAlias = "LC";
       private final String  PrfSvcLocCtcMthAlias = "LcCtMth";
       private final String  PrfSvcLocAddrAlias = "LcAddr";
       private final String  PrfSvcLocEmailAlias = "LcEmail";
       private final String  PrfSvcLocTelAlias = "LcTel";

    // 0120 Professional Service Medical Practitioner:
       private final String  PrfSvcMcPcRlAlias = "MdPcRl";
       private final String  PrfSvcMcPcAlias = "MdPc";
       private final String  PrfSvcMcPcCtcMthAlias = "MdPcCtMth";
       private final String  PrfSvcMcPcAddrAlias = "MdPcAddr";
       private final String  PrfSvcMcPcEmailAlias = "MdPcEmail";
       private final String  PrfSvcMcPcTelAlias = "MdPcTel";
       private final String  PrfSvcMcPcRegAlias = "MdPcRg";
       

   //  0130 Professional Service Supervisor:
       private final String PrfSvcSpRlAlias = "SpRl";
       private final String PrfSvcSpAlias = "Sp";
       private final String PrfSvcSpCtcMthAlias = "SpCtMth";
       private final String PrfSvcSpAddrAlias = "SpAddr";
       private final String PrfSvcSpEmailAlias = "SpEmail";
       private final String PrfSvcSpTelAlias = "SpTel";

   //  0140 ProfessionalService Note
       private final String  PrfSvcNtAlias = "Nt";
       
   //  0146 ProfessionalService Note Supervisor:       
       private final String  PrfSvcNtRLSAlias = "NtSpRl";
       private final String  PrfSvcNtSpAlias = "NtSp";
       private final String  PrfSvcNtSpPrfRegAlias = "NtSpRg";
       private final String  PrfSvcNtCMSAlias = "NtSpCtMth";
       private final String  PrfSvcNtSAddrAlias = "NtSpCtAddr";
       private final String  PrfSvcNtSEmailAlias = "NtSpCtEmail";
       private final String  PrfSvcNtSTelecomAlias = "NtSpCtTel";

   //  0143 ProfessionalService Note Recorder:
       private final String  PrfSvcNtRcRlAlias = "RcRl";
       private final String  PrfSvcNtRcAlias = "Rc";
       private final String  PrfSvcNtRcPrfRegAlias = "NtRcRg";
       private final String  PrfSvcNtRcCtcMthAlias = "RcCtMth";
       private final String  PrfSvcNtRcAddrAlias = "RcAddr";
       private final String  PrfSvcNtRcEmailAlias = "RcEmail";
       private final String  PrfSvcNtRcTelAlias = "RcTel";

   //  0148 ProfessionalService Note Location:
       private final String  PrfSvcNtLocAlias = "NtLc";
       private final String  PrfSvcNtLocCMAlias = "NtLcCtMth";
       private final String  PrfSvcNtLocAddrAlias = "NtLcAddr";
       private final String  PrfSvcNtLocEmailAlias = "NtLcEmail";
       private final String  PrfSvcNtLocTelecomAlias = "NtLcTel";
       
       private Map<Long, Dispense> dispenseMap = new HashMap<Long, Dispense>();
 
       
		Address currPrfSvcNoteRecAddressObj = null;
		ProfessionalRegistration currPrfSvcNoteRecPrfRegObj = null;
		String currPrfSvcNoteRecEmail = null;
		String currPrfSvcNoteRecPhone = null;
		String currPrfSvcNoteRecFax = null;
		
		Address currPrfSvcNoteSupervisorAddressObj = null;
		Address currPrfSvcNoteSupervisorPrfRegObj = null;
		String currPrfSvcNoteSupervisorEmail = null;
		String currPrfSvcNoteSupervisorPhone = null;
		String currPrfSvcNoteSupervisorFax = null;
		
 	   private final static String WHERE_CLAUSE = "WHERE 1=1";

      /******************************************************************************
   	   * JaxB objects
   	   ******************************************************************************/
       Dispense    curr_Disp_Obj = null;
       Compound	   curr_Cmpnd_Obj = null;
       TxFillStatus	curr_TxStat_Obj = null;
       CompoundIngredient   curr_CmpndIngr_Obj = null;
       
       Initiator   curr_Initr_Obj = null;
       Location    curr_DispLoc_Obj = null;
       
       Pack        curr_DispPack_Obj = null;
       Drug        curr_DispPackDrg_Obj = null;
       
       Note        curr_TxNt_Obj = null;
       Supervisor  curr_TxNtSuper_Obj = null;
       Recorder    curr_TxNtRec_Obj = null;
       Location    curr_TxNtLoc_Obj = null;
       
       
       Supervisor  curr_txSuper_Obj = null;
       Recorder    curr_txRec_Obj = null;
       
       ProfessionalService curr_PrfSvc_Obj = null;	// 0100 ProfessionalService
       Location    curr_PrfSvcLoc_Obj = null;     	// 0110 ProfessionalService Location
       MedicalPractitioner curr_PrfSvcMcPc_Obj = null; 	// 0120 ProfessionalService MedicalPractitioner
       ProfessionalRegistration curr_PrfSvcMcPcPrfReg_Obj = null; 	// 0120 MedicalPractitioner's Professional Registration
       
       Supervisor  curr_PrfSvcSp_Obj = null;         // 0130 ProfessionalService Supervisor
       ProfessionalService curr_PrfSvcSpPrfReg_Obj = null;         // 0130 ProfessionalService Supervisor
       Note        curr_PrfSvcNt_Obj = null;      // 0140 ProfessionalService Notes
       Supervisor  curr_PrfSvcNtS_Obj = null;     // 0146 ProfessionalService Note Supervisor
       Recorder    curr_PrfSvcNtR_Obj = null;     // 0143 ProfessionalService Note Recorder
       Location    curr_PrfSvcNtLocCM_Obj = null; // 0148 ProfessionalService Note Location
       
 
       String currEmail = null;
       String currPhone = null;
       String currFax   = null;

       long curr_TxStat_key = -0;
       
       long curr_Disp_key = -0;
       long	curr_Cmpnd_key = -0;
       long	curr_CmpndIngr_key = -0;
       
       long curr_Initr_key = -0;
       long curr_DispLoc_key = -0;
       
       long curr_DispPack_key = -0;
       long curr_DispPackDrg_key = -0;
       
       
       long curr_TxNt_key = -0;
       long curr_TxNtSuper_key = -0;
       long curr_TxNtRec_key = -0;
       long curr_TxNtLoc_key = -0;
       
       long curr_txSuper_key = -0;
       long curr_txRec_key = -0;
       
       long curr_PrfSvc_key = -0;        // ProfessionalService
       long curr_PrfSvcLoc_key = -0;     // ProfessionalService Location
       long curr_PrfSvcMcPc_key = -0;         // ProfessionalService MedicalPractitioner
       long curr_PrfSvcMcPcPrfReg_key = -0;         // MedicalPractitioner's Professional Registration
       long curr_PrfSvcSp_key = -0;         // ProfessionalService Supervisor
       long curr_PrfSvcSpPrfReg_key = -0;         // ProfessionalService Supervisor's Professional Registration
       
       
       long curr_PrfSvcNt_key = -0;       // ProfessionalService Notes
       long curr_PrfSvcNtSp_key = -0;      // ProfessionalService Note Supervisor
       long curr_PrfSvcNtSpPrfReg_key = -0;      // ProfessionalService Note Supervisor's Professional Registration
       long curr_PrfSvcNtRc_key = -0;      // ProfessionalService Note Recorder
       long curr_PrfSvcNtRcPrfReg_key = -0;      // ProfessionalService Note Recorder's Professional Registration
       long curr_PrfSvcNtLocCM_key = -0;  // ProfessionalService Note Location
       
       
       static Dispense currentDispense;
       static Set <Long> Disp_Hashkey = new HashSet<Long>();
       static Set <Long> TxStat_Hashkey = new HashSet<Long>();
       static Set <Long> Cmpnd_Hashkey = new HashSet<Long>();
       static Set <Long> CmpndIngr_Hashkey = new HashSet<Long>();
       static Set <Long> Initr_Hashkey = new HashSet<Long>();
       static Set <Long> DispLoc_Hashkey = new HashSet<Long>(); 
       
       static Set <Long> DispPack_Hashkey = new HashSet<Long>();
       static Set <Long> DispPackDrg_Hashkey = new HashSet<Long>();
       
       static Set <Long> TxNt_Hashkey = new HashSet<Long>();
       static Set <Long> TxNtSuper_Hashkey = new HashSet<Long>();
       static Set <Long> TxNtRec_Hashkey = new HashSet<Long>();
       static Set <Long> TxNtLoc_Hashkey = new HashSet<Long>();
       
       static Set <Long> txSuper_Hashkey = new HashSet<Long>();
       static Set <Long> txRec_Hashkey = new HashSet<Long>();
       
       static Set <Long> PrfSvc_Hashkey = new HashSet<Long>();     // ProfessionalService
       static Set <Long> PrfSvcLoc_Hashkey = new HashSet<Long>();  // ProfessionalService Location
       static Set <Long> PrfSvcMcPc_Hashkey = new HashSet<Long>();      // ProfessionalService MedicalPractitioner
       static Set <Long> PrfSvcMcPcPrfReg_Hashkey = new HashSet<Long>();      // ProfessionalService MedicalPractitioner's Prof Reg
       static Set <Long> PrfSvcSp_Hashkey = new HashSet<Long>();      // ProfessionalService Supervisor
       static Set <Long> PrfSvcSpPrfReg_Hashkey = new HashSet<Long>();      // ProfessionalService Supervisor
       
       static Set <Long> PrfSvcNt_Hashkey = new HashSet<Long>();     // ProfessionalService Notes
       static Set <Long> PrfSvcNtSp_Hashkey = new HashSet<Long>();    // ProfessionalService Note Supervisor
       static Set <Long> PrfSvcNtSpPrfReg_Hashkey = new HashSet<Long>();    // ProfessionalService Note Supervisor
       
       static Set <Long> PrfSvcNtRc_Hashkey = new HashSet<Long>();    // ProfessionalService Note Recorder
       static Set <Long> PrfSvcNtRcPrfReg_Hashkey = new HashSet<Long>();    // ProfessionalService Note Recorder
       
       static Set <Long> PrfSvcNtLoc_Hashkey = new HashSet<Long>();// ProfessionalService Note Location

        
	   
	   public QueryDispense (Connection conn) {   
		      super(conn);
	   }

	   private String getDispenseSQL() throws CodeNotFoundFromTableCacheException, SQLException , IOException{
		   
		       String tableAliasList[][] = {
	    		  {"rx",             "RX"},		    		   
	    		  {"tx",             "TX"},
	    		  
	    		  {"TxStat", 		"TxStat"},

	    		  {"cmpnd",          "CMPND"},
	    		  {"CmpndIngrdnt",   "CMPNDINGRDNT"},
	    		  {"Pack",           "CMPINGRPACK"},
	    		  {"drg",             CmpIngrPackDrgAlias},
	    		  {"DsgFrm",          drDsgFrm},
	    		  {"Mlcl",            cmpndDrgMlclAlias },
	    		  
	    		  
	    		  {"Mfctr",          "CIPGMFCTR"},
	    		  {"MfctrDrgRecall", cipgMfctrRecallAlias},

 			      {"PrsnRl",         "INITR"},
				  {"prsn",           "PRSNINITR"},
				  {"CntctMthd",      txInitrCntctMthdAlias},
		          {"Email",          "TXINITREMAIL"},
		          {"Addr",           "TXINITRADDR"},
		          {"Telecom",        "TXINITRTELECOM"},

		          {"Loc",            "TXLOC"},
		          {"CntctMthd",      txLocCntctMthdAlias},
		          {"Email",          "TXLOCEMAIL"},
		          {"Addr",           "TXLOCADDR"},
		          {"Telecom",        "TXLOCTELECOM"},
		          
		          {"Pack",           "TXPACK"},
		          {"drg",            "TXPACKDRG"},
		          {"DsgFrm",          txPacDsgFrm},
		          {"Mfctr",          "TXDRUGMFCTR"},
		          {"MfctrDrgRecall", txDrugMfctrRecallAlias},
		          {"Mlcl",           txPackDrgMlclAlias},
		          {"StrngthUOMTyp",  "TXSUOMT"},

		          {"TxNt",           "TXNT"},
		          {"PrsnRl",         "TXNTRLS"},
		          {"prsn",           "TXNTSUPER"},
		          {"CntctMthd",      TxNtSuperCntctMthdAlias},
		          {"Email",          "TXNTSUPEREMAIL"},
		          {"Addr",           "TXNTSUPERADDR"},
		          {"Telecom",        "TXNTSUPERTELECOM"},

		          {"PrsnRl",         "TXNTRLR"},
		          {"prsn",           "TXNTREC"},
		          {"CntctMthd",      txNtRecCntctMthdAlias}, // T60
		          {"Email",          "TXNTRECEMAIL"},
		          {"Addr",           "TXNTRECADDR"},
		          {"Telecom",        "TXNTRECTELECOM"},

                  {"loc",            "TXNTLOC"},             // 0076
		          {"CntctMthd",      txNtLocCntctMthdAlias}, // T65
		          {"Email",          "TXLOCNTEMAIL"},
		          {"Addr",           "TXLOCNTADDR"},
		          {"Telecom",        "TXLOCNTTELECOM"},		          
		          
		          {"PrsnRl",         "TXPRSNRLS"},
		          {"prsn",           "TXSUPER"},
		          {"CntctMthd",      txSuperCntctMthdAlias}, // T70
		          {"Email",          "TXSUPEREMAIL"},
		          {"Addr",           "TXSUPERADDR"},
		          {"Telecom",        "TXSUPERTELECOM"},

		          {"PrsnRl",         "TXPRSNRLR"},
		          {"prsn",           "TXREC"}, 
		          {"CntctMthd",      txRecCntctMthdAlias},
		          {"Email",          "TXRECEMAIL"},
		          {"Addr",           "TXRECADDR"},
		          {"Telecom",        "TXRECTELECOM"},


		          
		       };

		      String selectString = TableColumnSingleton.getInstance().createSQLSelectFromColumns(connection, tableAliasList);		       
		  	  String sqlQuery =  
			 " select distinct " +  selectString +
			 "   from rx rx              " +             
			 "   left outer join tx tx on tx.rxkey=rx.rxkey \n" +
					 
			 // -------------- 0005 Fill Status block 
			 " 			left outer join TxStat TxStat on tx.TxKey = TxStat.TxKey \n" +
        
             //  ------------ 0010 tx Compound BLOCK ----------------    
             "           left outer join cmpnd  cmpnd \n" +
             "             on tx.cmpndkey = cmpnd.cmpndkey \n" +

             "           left outer join CmpndIngrdnt CmpndIngrdnt \n" +
             "             on cmpnd.CmpndKey = CmpndIngrdnt.CmpndKey \n" +

             "           left outer join Pack CmpIngrPack \n" +
             "             on CmpndIngrdnt.packKey = CmpIngrPack.packKey \n" +

             "           left outer join drg T10 \n" +
             "             on CmpIngrPack.DrgKey = T10.DrgKey \n" +
             
             "           left outer join DsgFrm drDsgFrm \n" +
             "             on drDsgFrm.DsgFrmKey = T10.DsgFrmKey \n" +

             
             "           left outer join Mfctr cipgMfctr                         \n" +
             "             on T10.MfctrKey = cipgMfctr.MfctrKey \n" +

             "           left outer join MfctrDrgRecall T15          \n" +
             "             on cipgMfctr.MfctrKey = T15.MfctrKey  and \n" +
             "                T10.DrgKey = T15.DrgKey     \n" +
             
             "           left outer join Mlcl cmpndDrgMlcl \n" +
             "             on T10.MlclKey = cmpndDrgMlcl.MlclKey \n" +

             //     ------------ 0020 tx Initiator BLOCK ----------------
             "           left outer join PrsnRl Initr \n" +
             "             on tx.InitrKey = Initr.PrsnRlKey \n" +

             "           left outer join prsn prsnInitr \n" +
             "             on Initr.PrsnRlKey = prsnInitr.PrsnKey \n" +
			       
             "           left outer join CntctMthd T20  \n" +
             "             on prsnInitr.PrsnKey = T20.PrsnKey \n" +  
				   
             "           left outer join Email txInitrEmail \n" +
	         "             on T20.CntctMthdKey = txInitrEmail.EmailKey \n" +  
				   
             "           left outer join Addr txInitrAddr \n" +
             "             on T20.CntctMthdKey = txInitrAddr.AddrKey \n" +  
				   
             "           left outer join Telecom txInitrTelecom \n" +
             "             on T20.CntctMthdKey = txInitrTelecom.TelecomKey \n" +  



             //------------ 0030 tx Location BLOCK ------------------
             "           left outer join Loc txLoc  \n" +
             "             on tx.LocKey = txLoc.LocKey \n" +

             "           left outer join CntctMthd T25  \n" +  
             "             on txLoc.LocKey = T25.LocKey  \n" +

             "           left outer join Email txLocEmail \n" +
	         "             on T25.CntctMthdKey = txLocEmail.EmailKey \n" +  
				   
             "           left outer join Addr txLocAddr \n" +
	         "             on T25.CntctMthdKey = txLocAddr.AddrKey \n" +  
				   
             "           left outer join Telecom txLocTelecom \n" +
	         "             on T25.CntctMthdKey = txLocTelecom.TelecomKey \n" +  


             //------------ 0050 tx Pack BLOCK  ---------------------------
             "           left outer join Pack txPack \n" +
             "             on tx.packKey = txPack.packKey \n" +

             "           left outer join drg txPackDrg \n" +
             "             on txPack.DrgKey = txPackDrg.DrgKey \n" +

             "           left outer join DsgFrm txPacDsgFrm \n" +
             "             on txPacDsgFrm.DsgFrmKey = txPackDrg.DsgFrmKey \n" +

             "           left outer join Mfctr txDrugMfctr                   \n" +      
             "             on txPackDrg.MfctrKey = txDrugMfctr.MfctrKey \n" +

             "           left outer join MfctrDrgRecall T40  \n" +
             "             on txDrugMfctr.MfctrKey = T40.MfctrKey  and \n" + 
             "                txPackDrg.DrgKey = T40.DrgKey  \n" + 
             
             "           left outer join Mlcl T45 \n" +
             "             on txPackDrg.MlclKey = T45.MlclKey \n" +

             "           left outer join StrngthUOMTyp txSUOMT \n" +
             "             on T45.StrngthUOMTypKey = txSUOMT.StrngthUOMTypKey \n" +

             // =====================================================
             //             0070 tx Note BLOCK                       
             // =====================================================
             "           left outer join TxNt TxNt \n" +
             "             on tx.TxKey = TxNt.TxKey \n" +

             "           left outer join PrsnRl  TxNtRLS  \n" +
             "             on TxNt.SprvsrKey = TxNtRLS.PrsnRlKey  \n" +

             "           left outer join prsn TxNtSuper              \n" +      // 0074 Supervisor
             "             on TxNtRLS.PrsnRlKey = TxNtSuper.PrsnKey \n" +
			       
	         "           left outer join CntctMthd T50  \n" +
  		     "             on TxNtSuper.PrsnKey = T50.PrsnKey \n" +  
				   
             "           left outer join Email txNtSuperEmail  \n" +
		     "             on T50.CntctMthdKey = txNtSuperEmail.EmailKey \n" +  
				   
             "           left outer join Addr txNtSuperAddr  \n" +
             "             on T50.CntctMthdKey = txNtSuperAddr.AddrKey \n" +  
				   
             "           left outer join Telecom txNtSuperTelecom  \n" +
	         "             on T50.CntctMthdKey = txNtSuperTelecom.TelecomKey \n" +  
             //    -----------------------------------------------------

             "          left outer join PrsnRl  TxNtRLR \n" +
             "            on txNt.RcrdrKey = txNtRLR.PrsnRlKey \n" +

             "          left outer join prsn txNtRec                \n" +  // 0072 Recorder
             "            on txNtRLR.PrsnRlKey = txNtRec.PrsnKey \n" +
			       
	         "          left outer join CntctMthd T60  \n" +
  		     "            on txNtRec.PrsnKey = T60.PrsnKey \n" +  
				   
             "          left outer join Email txNtRecEmail  \n" +
		     "            on T60.CntctMthdKey = txNtRecEmail.EmailKey \n" +  
				   
             "          left outer join Addr txNtRecAddr  \n" +
             "            on T60.CntctMthdKey = txNtRecAddr.AddrKey \n" +  
				   
             "          left outer join Telecom txNtRecTelecom  \n" +
	         "            on T60.CntctMthdKey = txNtRecTelecom.TelecomKey \n" +  
	         //          --------------------------------------------------------------
             
             
             "          left outer join loc txNtLoc                   \n" +  // 0076
             "                  on TxNt.LocKey = txNtLoc.LocKey       \n" +

             "          left outer join CntctMthd T65    \n" +
             "                  on txNtLoc.LocKey = T65.LocKey \n" + 

             "          left outer join Email txLocNtEmail            \n" +
             "                  on T65.CntctMthdKey = txLocNtEmail.EmailKey \n" +  
	   
             "          left outer join Addr txLocNtAddr              \n" +
             "                  on T65.CntctMthdKey = txLocNtAddr.AddrKey \n" +  
	   
             "          left outer join Telecom txLocNtTelecom \n" +
             "                  on T65.CntctMthdKey = txLocNtTelecom.TelecomKey \n" +  
             

             // ------------ 0080 tx Supervisor BLOCK  ----------------------
             "         left outer join PrsnRl txPrsnRLS \n" +
             "           on tx.SprvsrKey = txPrsnRLS.PrsnRlKey \n" +

             "         left outer join prsn txSuper \n" +
             "           on txPrsnRLS.PrsnRlKey = txSuper.PrsnKey \n" +
			       
             "         left outer join CntctMthd T70  \n" +
             "           on txSuper.PrsnKey = T70.PrsnKey \n" +  
				   
             "         left outer join Email txSuperEmail \n" +
	         "           on T70.CntctMthdKey = txSuperEmail.EmailKey \n" +  
				   
             "         left outer join Addr txSuperAddr \n" +
             "           on T70.CntctMthdKey = txSuperAddr.AddrKey \n" +  
				   
             "         left outer join Telecom txSuperTelecom \n" +
             "           on T70.CntctMthdKey = txSuperTelecom.TelecomKey \n" +  


             //   ------------ 0090 tx Recorder BLOCK  ---------------------- 
             "         left outer join PrsnRl  txPrsnRLR  \n" +
             "           on tx.RcrdrKey = txPrsnRLS.PrsnRlKey \n" +

             "         left outer join prsn txRec \n" +
             "           on txPrsnRLR.PrsnRlKey = txRec.PrsnKey \n" +
			       
	         "         left outer join CntctMthd T75 \n" +
	         "           on txRec.PrsnKey = T75.PrsnKey \n" + 
				   
             "         left outer join Email txRecEmail \n" +
             "           on T75.CntctMthdKey = txRecEmail.EmailKey \n" +  
				   
             "         left outer join Addr  txRecAddr \n" +
             "           on T75.CntctMthdKey = txRecAddr.AddrKey \n" +  
				   
             "         left outer join Telecom txRecTelecom  \n" +
	         "           on T75.CntctMthdKey = txRecTelecom.TelecomKey " +  


             "  " + WHERE_CLAUSE + "  \n" +  
             "  order by tx.TxKey,    \n" +
             
             "           tx.cmpndkey, \n" +
             "  CmpndIngrdnt.CmpndKey,\n" +
             
             "           tx.InitrKey, \n" +
             "           tx.LocKey,   \n" +
             "           tx.packKey,  \n" +
             
             "           TxNt.TxKey,         \n" +
             "           TxNtSuper.PrsnKey,  \n" +
             "           TxNtRec.PrsnKey,    \n" +
             "           txNtLoc.LocKey,     \n" + // 0076

             "           txSuper.PrsnKey,    \n" +
             "           txRec.PrsnKey       \n";             
		   
		   return sqlQuery;
       }

	   private String prepareSQL(String whereClause) throws CodeNotFoundFromTableCacheException, SQLException, IOException {
		 if (dispenseSQL == null) {
			 dispenseSQL = getDispenseSQL(); 
		 } 
		 String resSQL = dispenseSQL.replaceFirst(WHERE_CLAUSE, whereClause);
		 return resSQL;
	   }
	   
/*	   
	   private String prepareFindDispenseByTxnnum()
	   {
		   String query = "select tx.txKey as TXKEY from tx where tx.StoreNum = ? and tx.TxnNum = ? ";
		   return query ;
	   }
*/	   

	   public Dispense getDispenseByTxnNum(int txnNumber, String storeNumber) throws SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, DispenseNotFoundException, CodeNotFoundFromTableCacheException, CDRInternalException {
		   if (logger.isInfoEnabled())  {logger.info( "Start getDispense. TxnNum: " + txnNumber + " : storeNumber " + storeNumber  + ".");}
		   String query  =  prepareSQL("where tx.TxnNum = ? and tx.StoreNum = ?");
		   if(logger.isDebugEnabled()) {logger.debug("query : \n" + query);}
		   String storeNumberStr = CommonUtil.createStoreLeadingZeros(storeNumber);  
		   PreparedStatement preparedStatement =null;
		   ResultSet rs = null;
		   try {
		   preparedStatement= connection.prepareStatement(query);
		   preparedStatement.setInt(1 , txnNumber);
		   preparedStatement.setString(2 , storeNumberStr);
		   rs = preparedStatement.executeQuery();
			  
		      Disp_Hashkey.clear();
		      dispenseMap.clear();
		 	  while(rs.next()) {
		 		  tr_0005Dispense(rs); // this sets curr_Disp_key, curr_Disp_Obj
	    	  }
		 	   
		 	  QueryProfessionalService qPrfSvc = new QueryProfessionalService(connection);

		 	  Long txnNum = new Long(txnNumber);
		 	  ResultSet rsPrfSvc = qPrfSvc.getResultSetByTxnNum(txnNum, storeNumberStr);
		 	  while (rsPrfSvc.next() ) {
		 		  curr_Disp_key = rsPrfSvc.getLong(txAlias + "_TXKEY"); 
		 		 tr_0100ProfService(rsPrfSvc, curr_Disp_key);	// this function sets curr_Disp_key, curr_Disp_Obj
		 	  }	
		   }finally {
			   CommonUtil.closePreparedStatementResultSet(preparedStatement, rs);
		   }

		      return curr_Disp_Obj;		   
	   }

	   
	   /*
	   public Dispense getDispenseByKey(long txKey ) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
			  String query = prepareSQL("where TX.TxKey = ?");
			  
			  logger.debug("query : \n" + query);
			  PreparedStatement preparedStatement = connection.prepareStatement(query);
			  
			  preparedStatement.setLong(1, txKey);
			  ResultSet rs = preparedStatement.executeQuery();
			  
		      Disp_Hashkey.clear();
		      dispenseMap.clear();
		 	  while(rs.next()) {
		 		  tr_0005Dispense(rs); // this sets curr_Disp_key, curr_Disp_Obj
	    	  }
		 	   
		 	  QueryProfessionalService qPrfSrv = new QueryProfessionalService(connection);
		 	  ResultSet rsPrfSvc = qPrfSrv.getResultSetByDispenseKey(txKey);
		 	  while (rsPrfSvc.next()) {
			     curr_Disp_key = rsPrfSvc.getLong(txAlias + "_TXKEY"); 
		 		 tr_0100ProfService(rsPrfSvc, curr_Disp_key);	// this function sets curr_Disp_key, curr_Disp_Obj
		 	  }	 	  

		      return curr_Disp_Obj;
       }
       */




	   public List<Dispense> getDispenseList (long RxKey) throws SQLException, CDRInternalException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		      if (logger.isInfoEnabled())  {logger.info( "Start getDispenseList : RxKey = " + RxKey);}
		      
			  String query = prepareSQL("where Rx.RxKey = ?");
			  query = query.replaceFirst("FILLER", "RxKey");
			  PreparedStatement preparedStatement=null;
			  ResultSet rs = null ;
			  try {
			    preparedStatement = connection.prepareStatement(query);
			  preparedStatement.setLong(1, RxKey);
			   rs = preparedStatement.executeQuery();

		      Disp_Hashkey.clear();
		      dispenseMap.clear();
		 	  while(rs.next()) {
		 		   tr_0005Dispense(rs); // this function sets curr_Disp_key, curr_Disp_Obj
	    	  }
		 	  
		 	  QueryProfessionalService qPrfSrv = new QueryProfessionalService(connection);
		 	  ResultSet rsPrfSvc = qPrfSrv.getResultSetByRxKey(new Long(RxKey));
		 	  while (rsPrfSvc.next()) {
		 		 curr_Disp_key = rsPrfSvc.getLong(txAlias + "_TXKEY"); 
		 		 tr_0100ProfService(rsPrfSvc, curr_Disp_key);	// this function sets curr_Disp_key, curr_Disp_Obj
		 	  }
			  }
			  finally {
				  CommonUtil.closePreparedStatementResultSet(preparedStatement, rs);
			  }
		 	CleanAllObjects();
		 	  
		 	List<Dispense> dispenses = getDispenseList(dispenseMap);
            return dispenses;
	   }

	   
	   
	   private List<Dispense> getDispenseList(Map<Long, Dispense> txMap) {
		   List<Dispense> txList = new ArrayList<Dispense>();
		   if (txMap==null)
			   return null;
		   for (Dispense dispense : txMap.values()) {
			   txList.add(dispense);
		   }
		   return txList;
	   }
	   
	   
	   private void tr_0005Dispense(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		       // 0005
		       curr_Disp_key = rs.getLong(txAlias + "_TXKEY");
		       
		       // 0010
		       curr_Cmpnd_key = rs.getLong(cmpndAlias + "_CMPNDKEY");
    	       curr_CmpndIngr_key = rs.getLong(CmpndIngrdntAlias + "_CMPNDINGRDNTKEY");
    	       
    	       curr_TxStat_key = rs.getLong(TxStatAlias + "_TXSTATKEY");
    	       
    	       // 0020
    	       curr_Initr_key = rs.getLong(prsnInitrAlias + "_PRSNKEY");
    	       curr_DispLoc_key = rs.getLong(txLocAlias + "_LOCKEY");
    	       
    	       
    	       // 0050 
    	       curr_DispPack_key = rs.getLong(txPackAlias + "_PACKKEY");
    	       curr_DispPackDrg_key = rs.getLong(txPackDrgAlias + "_DRGKEY");
    	       
    	       
    	       // 0070 - 0076   	       
    	       curr_TxNt_key = rs.getLong(TxNtAlias + "_TXKEY");
    	       curr_TxNtSuper_key = rs.getLong(TxNtSuperAlias + "_PRSNKEY"); 
    	       curr_TxNtRec_key = rs.getLong(txNtRecAlias + "_PRSNKEY");
    	       curr_TxNtLoc_key = rs.getLong(txNtLocAlias + "_LOCKEY");
    	       
    	       
    	       curr_txSuper_key = rs.getLong(txSuperAlias + "_PRSNKEY");  // 0080
    	       curr_txRec_key = rs.getLong(txRecAlias + "_PRSNKEY");      // 0090

  	   		   // 0100 ProfessionalService
//    	       curr_PrfsnlSvc_key    = rs.getLong(PrfsnlSvcAlias + "_PRFSNLSVCKEY");
//    	       curr_PrfsnlSvcLoc_key = rs.getLong(PrfsnlSvcLocAlias + "_LOCKEY");
//    	       
//  	    	   // 0120 ProfessionalService MedicalPractitioner    	    		   
//    	       curr_MedPrctp_key = rs.getLong(MedPrctpAlias + "_PRSNKEY");
//    	    		   
//  	    	   // 0130 ProfessionalService Supervisor   
//    	       curr_PssSuper_key = rs.getLong(PssSuperAlias + "_PRSNKEY");      
//
//    	       
//    	       
//    	    		   
//       		   // 0140 ProfessionalService Notes    	    		   
//    	       curr_PrfsnlSvcNt_key = rs.getLong(PrfsnlSvcNtAlias + "_PRFSNLSVCKEY");      
//    	    		   
//    	    		   
//       		   // 0146 ProfessionalService Note Supervisor    	    		   
//    	       curr_PrfsnlSvcNtS_key = rs.getLong(PrfsnlSvcNtSAlias + "_PRSNKEY");      
//
//    	    		   
//  	   		   // 0143 ProfessionalService Note Recorder    	    		   
//    	       curr_PrfsnlSvcNtR_key = rs.getLong(PrfsnlSvcNtRAlias + "_PRSNKEY");   
//
//    	    		   
// 	   		   // 0148 ProfessionalService Note Location    	    		   
//    	       curr_PrfsnlSvcNtLocCM_key = rs.getLong(PrfsnlSvcNtLocAlias + "_LOCKEY");  
		       
    	       if (curr_Disp_key <= 0)
    	    	   return;
		       
		       if (!Disp_Hashkey.contains(curr_Disp_key) ) {
		    	   Disp_Hashkey.add(curr_Disp_key);
		    	   
		    	   // New parent object detected. Clear his child objects:
		    	   TxStat_Hashkey.clear();     curr_TxStat_Obj = null;
				   Cmpnd_Hashkey.clear();      curr_Cmpnd_Obj = null;
				   Initr_Hashkey.clear();      curr_Initr_Obj = null;
  			       CmpndIngr_Hashkey.clear();  curr_CmpndIngr_Obj = null;
				   
				   DispLoc_Hashkey.clear();    curr_DispLoc_Obj = null;
				   DispPack_Hashkey.clear();   curr_DispPack_Obj = null;
				   DispPackDrg_Hashkey.clear();  curr_DispPackDrg_Obj = null;
				   
				   TxNt_Hashkey.clear();       curr_TxNt_Obj = null;
				   TxNtSuper_Hashkey.clear();  curr_TxNtSuper_Obj = null;
				   TxNtRec_Hashkey.clear();    curr_TxNtRec_Obj = null;
				   TxNtLoc_Hashkey.clear();    curr_TxNtLoc_Obj = null;
				   
				   txSuper_Hashkey.clear();    curr_txSuper_Obj = null;
				   txRec_Hashkey.clear();      curr_txRec_Obj = null;

				   PrfSvc_Hashkey.clear();     curr_PrfSvc_Obj= null;
				   PrfSvcLoc_Hashkey.clear();  curr_PrfSvcLoc_Obj = null;
				   PrfSvcMcPc_Hashkey.clear();      curr_PrfSvcMcPc_Obj = null;
				   PrfSvcMcPcPrfReg_Hashkey.clear();      curr_PrfSvcMcPcPrfReg_Obj = null;
				   PrfSvcSp_Hashkey.clear();      curr_PrfSvcSp_Obj = null;
				   PrfSvcSpPrfReg_Hashkey.clear();      curr_PrfSvcSpPrfReg_Obj = null;

				   PrfSvcNt_Hashkey.clear();   curr_PrfSvcNt_Obj = null;
				   PrfSvcNtSp_Hashkey.clear();  curr_PrfSvcNtS_Obj = null;
				   PrfSvcNtRc_Hashkey.clear();  curr_PrfSvcNtR_Obj = null;
				   PrfSvcNtLoc_Hashkey.clear();  curr_PrfSvcNtLocCM_Obj = null;
				   
				   curr_Disp_Obj  = PopulateJAXB.populateDispense(rs, txAlias);
				   
				   if (curr_Disp_Obj!=null) {
					   dispenseMap.put(curr_Disp_key, curr_Disp_Obj);
					   
				       tr_FillStatus(rs);
				       tr_0010Compound(rs);
				       tr_0020Initiator(rs);
				       tr_0030Location(rs);
				       tr_0050Pack(rs);
				       tr_0070Notes(rs);
				       tr_0080Supervisor(rs);
				       tr_0090Recorder(rs);
				   }
				   
		       } else {
		    	   
		    	   tr_FillStatus(rs);
			       tr_0010Compound(rs);
			       tr_0020Initiator(rs);
			       tr_0030Location(rs);
			       tr_0050Pack(rs);
			       tr_0070Notes(rs);
			       tr_0080Supervisor(rs);
			       tr_0090Recorder(rs);
		    	   
		       }
		       
	   }
	   
	   

	   private void tr_FillStatus(ResultSet rs) throws CodeNotFoundFromTableCacheException, NamingException, SQLException, IOException {
		   if (curr_TxStat_key <= 0)
			   return;
		   
		   if (!TxStat_Hashkey.contains(curr_TxStat_key)) {
			   TxStat_Hashkey.add(curr_TxStat_key);
			   
			   TxStat_Hashkey.clear(); curr_TxStat_Obj = null;
			   TxFillStatus txFillStatus = PopulateJAXB.populateDispenseFillStatus(rs, TxStatAlias);
			   if (txFillStatus!=null) {
				   curr_TxStat_Obj = txFillStatus;
				   curr_Disp_Obj.setFillStatus(curr_TxStat_Obj);
			   }
		   }
	   }
	   

	   private void tr_0010Compound(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		   if (curr_Cmpnd_key <= 0)
		   		return;
		   	
	       if (Cmpnd_Hashkey.contains(curr_Cmpnd_key) == false ) {
	    	   Cmpnd_Hashkey.add(curr_Cmpnd_key);
	    	   
	    	   CmpndIngr_Hashkey.clear();  curr_CmpndIngr_Obj = null;
	    	   Compound compound = PopulateJAXB.populateCompound(rs, cmpndAlias);
	    	   if( compound != null ) {
	    		   curr_Cmpnd_Obj = compound; 
	    		   curr_Disp_Obj.setCompound(curr_Cmpnd_Obj);
	    		   tr_0012CompIngr(rs);
	    	   }
	       } else {
	    	   tr_0012CompIngr(rs);
	       }
	   }

	   
	   private void tr_0012CompIngr(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		   	if (curr_CmpndIngr_key <=0)
		   		return;
		   	
		       if (CmpndIngr_Hashkey.contains(curr_CmpndIngr_key) == false ) {
		    	   CmpndIngr_Hashkey.add(curr_CmpndIngr_key);

		    	   CompoundIngredient compIngr = PopulateJAXB.populateCompIngr(rs, CmpndIngrdntAlias);
		    	   if( compIngr != null ) {
		    		   curr_CmpndIngr_Obj = compIngr;
		    		   //PopulateJAXB.populatePack() populates drug
		    		   Pack pack = PopulateJAXB.populatePack(rs, CmpIngrPackAlias, CmpIngrPackDrgAlias, cipgMfctrAlias, cmpndDrgMlclAlias,drDsgFrm);
		    		   curr_CmpndIngr_Obj.setPack(pack);
		    		   
		    		   curr_Cmpnd_Obj.getCompoundIngredients().add(curr_CmpndIngr_Obj);
		    		   
		    	   }
		       }
	   }

	   
   
	   private void tr_0020Initiator(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		       Address tmpAddressObj = null;
		   
		       if (curr_Initr_key <= 0)
		    	   return;
		       
		       if (Initr_Hashkey.contains(curr_Initr_key) == false ) {
		    	   Initr_Hashkey.add(curr_Initr_key);
		    	
		    	   Initiator initator = PopulateJAXB.populateInitiator(rs, prsnInitrAlias);
		    	   if( initator != null ) {
		    		   curr_Initr_Obj = initator;
		    		                                                                                        
		    		   tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txInitrCntctMthdAlias, txInitrAddrAlias, txInitrEmailAlias, txInitrTelecomAlias, txInitrTelecomAlias); 
			    	   if ( tmpAddressObj != null ) {
//			    		   initator.getPerson().setAddress(tmpAddressObj);
			    		   initator.getPerson().getAddress().add(tmpAddressObj);  //VL99
			    	   }

			    	   curr_Disp_Obj.setInitiator(initator);
		    	   }
		       }
		       tmpAddressObj = null;
	   }

	   
	   
	   
	   private void tr_0030Location(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		       Address tmpAddressObj = null;
		       
		       if (curr_DispLoc_key <= 0)
		    	   return;

		       if (DispLoc_Hashkey.contains(curr_DispLoc_key) == false ) {
		    	   DispLoc_Hashkey.add(curr_DispLoc_key);
		    	
		    	   Location loc = PopulateJAXB.populateLocation(rs, txLocAlias);
		    	   if( loc != null ) {
		    		   curr_DispLoc_Obj = loc;

		    		   tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txLocCntctMthdAlias, txLocAddrAlias, txLocEmailAlias, txLocTelecomAlias, txLocTelecomAlias);
			    	   if ( tmpAddressObj != null ) {
			    		   loc.setAddress(tmpAddressObj);
			    	   }
			    	   curr_Disp_Obj.setServiceLocation(loc);
		    	   }
		       }
		       tmpAddressObj = null;
	   }
	   

	   private void tr_0050Pack(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		   if (curr_DispPack_key <= 0)
			   return;
           if (DispPack_Hashkey.contains(curr_DispPack_key) == false ) {
    	       DispPack_Hashkey.add(curr_DispPack_key);
    	       
    	       //PopulateJAXB.populatePack() populates drug
    	       Pack pack = PopulateJAXB.populatePack(rs, txPackAlias, txPackDrgAlias, txDrugMfctrAlias, txPackDrgMlclAlias,txPacDsgFrm); 
	    	   if( pack != null ) {
	    		   curr_Disp_Obj.setPack(pack);
	    	   }
           }
	   }
	   

	   
	   private void tr_0080Supervisor(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		       Address tmpAddressObj = null;
		       
		       if (curr_txSuper_key <= 0)
		    	   return;
		       
		       if (txSuper_Hashkey.contains(curr_txSuper_key) == false) {
		    	   txSuper_Hashkey.add(curr_txSuper_key);
		    	     
		    	     Supervisor spr = PopulateJAXB.populateSupervisor(rs, txSuperAlias, txPrsnRLSAlias);
		    	     if( spr != null ) {
		    	    	 curr_txSuper_Obj = spr;
                                                                                                              
		    	    	 tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txSuperCntctMthdAlias, txSuperAddrAlias, txSuperEmailAlias, txSuperTelecomAlias, txSuperTelecomAlias);
		    	    	 if ( tmpAddressObj != null ) {
//		    	    		  spr.getPerson().setAddress(tmpAddressObj);
		    	    		  spr.getPerson().getAddress().add(tmpAddressObj);  //VL99
				    	 }
		    	    	 curr_Disp_Obj.setSupervisor(spr);
		    	     }
		    	     
		       }
		       tmpAddressObj = null;
	   }

	   
	   private void tr_0090Recorder(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {	
	           Address tmpAddressObj = null;
	           
	           if (curr_txRec_key <= 0)
	        	   return;
	           
	           if (txRec_Hashkey.contains(curr_txRec_key) == false) {
	        	   txRec_Hashkey.add(curr_txRec_key);
	        	     
	    	         Recorder rec = PopulateJAXB.populateRecorder(rs, txRecAlias, txPrsnRLRAlias);
	    	         if( rec != null ) {
	    	        	 curr_txRec_Obj = rec;

	    	    	     tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txRecCntctMthdAlias, txRecAddrAlias, txRecEmailAlias, txRecTelecomAlias,txRecTelecomAlias );
	    	    	     if ( tmpAddressObj != null ) {
//	    	    		      rec.getPerson().setAddress(tmpAddressObj);
	    	    		      rec.getPerson().getAddress().add(tmpAddressObj);  //VL99
			    	     }
	    	    	     curr_Disp_Obj.setRecorder(rec);
	    	         }
	    	     
	           }
	           tmpAddressObj = null;
	   }
	   
	   
	   

	   private void tr_0070Notes(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		   if (curr_TxNt_key <= 0)
			   return;
		   
	       if (TxNt_Hashkey.contains(curr_TxNt_key) == false ) {
	    	   TxNt_Hashkey.add(curr_TxNt_key);
	    	   
			   TxNtSuper_Hashkey.clear();  curr_TxNtSuper_Obj = null;
			   TxNtRec_Hashkey.clear();    curr_TxNtRec_Obj = null;
			   TxNtLoc_Hashkey.clear();    curr_TxNtLoc_Obj = null;

			   Note note = PopulateJAXB.populateNote(rs, TxNtAlias, "TxNtKey" );
			   if( note != null ) {
				    curr_TxNt_Obj = note ;
				    
			        tr_0072txNtRec(rs);
					tr_0074txNtSuper(rs);
			    	tr_0076txNtLoc(rs);
		    	    curr_Disp_Obj.getNote().add(curr_TxNt_Obj);
		       }
	       }
	   }
	   
	   
	   private void tr_0072txNtRec(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
           Address tmpAddressObj = null;
           if (curr_TxNtRec_key <= 0)
        	   return;
           
           if (TxNtRec_Hashkey.contains(curr_TxNtRec_key) == false) {
        	   TxNtRec_Hashkey.add(curr_TxNtRec_key);
    	     
	             Recorder rec = PopulateJAXB.populateRecorder(rs, txNtRecAlias, TxNtRLRAlias);
	             if( rec != null ) {
	            	 curr_TxNtRec_Obj = rec;
                                                                                                          
	    	         tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txNtRecCntctMthdAlias, txNtRecAddrAlias, txNtRecEmailAlias, txNtRecTelecomAlias,txNtRecTelecomAlias );
	    	         if ( tmpAddressObj != null ) {
//	    		          rec.getPerson().setAddress(tmpAddressObj);
	    	        	  rec.getPerson().getAddress().add(tmpAddressObj);  //VL99
	    	         }
	    	         curr_TxNt_Obj.setRecorder(rec);
	             }
	     
           }
           tmpAddressObj = null;
	   }


	   
	   private void tr_0074txNtSuper(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
 	           Address tmpAddressObj = null;
 	           if (curr_TxNtSuper_key <= 0)
 	        	   return;
 	           
	           if (TxNtSuper_Hashkey.contains(curr_TxNtSuper_key) == false) {
	        	   TxNtSuper_Hashkey.add(curr_TxNtSuper_key);

	    	         Supervisor spr = PopulateJAXB.populateSupervisor(rs, TxNtSuperAlias, TxNtRLSAlias);
	    	         if( spr != null ) {
	    	        	 curr_TxNtSuper_Obj = spr;

	    	    	     tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, TxNtSuperCntctMthdAlias, txNtSuperAddrAlias, txNtSuperEmailAlias, txNtSuperTelecomAlias,txNtSuperTelecomAlias );
	    	    	     if ( tmpAddressObj != null ) {
//	    	    		      spr.getPerson().setAddress(tmpAddressObj);
	    	    		      spr.getPerson().getAddress().add(tmpAddressObj);  //VL99
			    	     }
	    	    	     curr_TxNt_Obj.setSupervisor(spr);
	    	         }
	           }
	           tmpAddressObj = null;
	   }

	   

	   private void tr_0076txNtLoc(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
 	           Address tmpAddressObj = null;
 	           
 	           if (curr_TxNtLoc_key <= 0)
 	        	   return;

	           if (TxNtLoc_Hashkey.contains(curr_TxNtLoc_key) == false ) {
	        	   TxNtLoc_Hashkey.add(curr_TxNtLoc_key);
	    	
	    	       Location loc = PopulateJAXB.populateLocation(rs, txNtLocAlias);
	    	       if( loc != null ) {
	    	    	   curr_TxNtLoc_Obj = loc;

	    		       tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, txNtLocCntctMthdAlias, txLocNtAddrAlias, txLocNtEmailAlias, txLocNtTelecomAlias,txLocNtTelecomAlias);
		    	       if ( tmpAddressObj != null ) {
		    		       loc.setAddress(tmpAddressObj);
		    	       }
		    	       curr_TxNt_Obj.setLocation(loc);
	    	       }
	           }
	           tmpAddressObj = null;
	   }

	   
	   
	   private void tr_0100ProfService(ResultSet rs, Long dispenseKey) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {	
	       // 0005
	       curr_Disp_key = rs.getLong(txAlias + "_TXKEY");
	       
	   		   // 0100 ProfessionalService
	       curr_PrfSvc_key    = rs.getLong(PrfSvcAlias + "_PRFSNLSVCKEY");
	       curr_PrfSvcLoc_key = rs.getLong(PrfSvcLocAlias + "_LOCKEY");

	    	   // 0120 ProfessionalService MedicalPractitioner    	    		   
	       curr_PrfSvcMcPc_key = rs.getLong(PrfSvcMcPcAlias + "_PRSNKEY");
	    		   
	    	   // 0130 ProfessionalService Supervisor   
	       curr_PrfSvcSp_key = rs.getLong(PrfSvcSpAlias + "_PRSNKEY");      
	    		   
   		   // 0140 ProfessionalService Notes    	    		   
	       curr_PrfSvcNt_key = rs.getLong(PrfSvcNtAlias + "_PRFSNLSVCKEY");      
	    		   
   		   // 0146 ProfessionalService Note Supervisor    	    		   
	       curr_PrfSvcNtSp_key = rs.getLong(PrfSvcNtSpAlias + "_PRSNKEY");      
	    		   
	   		   // 0143 ProfessionalService Note Recorder    	    		   
	       curr_PrfSvcNtRc_key = rs.getLong(PrfSvcNtRcAlias + "_PRSNKEY");   
	    		   
	   		   // 0148 ProfessionalService Note Location    	    		   
	       curr_PrfSvcNtLocCM_key = rs.getLong(PrfSvcNtLocAlias + "_LOCKEY");  
	       
	       if (curr_PrfSvc_key <= 0) 
	    	   return;
	       
           if (!PrfSvc_Hashkey.contains(curr_PrfSvc_key) ) {
        	   PrfSvc_Hashkey.add(curr_PrfSvc_key);
    	   
   
 		       PrfSvcLoc_Hashkey.clear();  curr_PrfSvcLoc_Obj = null;
 		       
		       PrfSvcMcPc_Hashkey.clear();      curr_PrfSvcMcPc_Obj = null;
		       PrfSvcSp_Hashkey.clear();      curr_PrfSvcSp_Obj = null;

		       PrfSvcNt_Hashkey.clear();   curr_PrfSvcNt_Obj = null;
		       PrfSvcNtRc_Hashkey.clear();  curr_PrfSvcNtR_Obj = null;
		       PrfSvcNtSp_Hashkey.clear();  curr_PrfSvcNtS_Obj = null;
		       PrfSvcNtLoc_Hashkey.clear();  curr_PrfSvcNtLocCM_Obj = null;

		       curr_PrfSvc_Obj = PopulateJAXB.populateProfService(rs, PrfSvcAlias);
    	       if( curr_PrfSvc_Obj != null) {
    	    	   curr_Disp_Obj.setProfessionalService(curr_PrfSvc_Obj);
    	    	   
		           tr_0110PrfsLocation(rs);
		           tr_0130PrfsSupervisor(rs);
		           tr_0120MedPractitioner(rs);
		           tr_0120MedicalPractPrfReg(rs);
		           traversePrfSvcNote(rs);
    	       }
    	       
           } else {
        	   
		           tr_0110PrfsLocation(rs);
		           tr_0130PrfsSupervisor(rs);
		           tr_0120MedPractitioner(rs);
		           tr_0120MedicalPractPrfReg(rs);
		           traversePrfSvcNote(rs);	        	   
           }
	   }

	   
	   
	   private void tr_0110PrfsLocation(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
 	           Address tmpAddressObj = null;
 	           
 	           if (curr_PrfSvcLoc_key <= 0)
 	        	   return;

	           if (PrfSvcLoc_Hashkey.contains(curr_PrfSvcLoc_key) == false ) {
	        	   PrfSvcLoc_Hashkey.add(curr_PrfSvcLoc_key);
	    	
	        	   curr_PrfSvcLoc_Obj = PopulateJAXB.populateLocation(rs, PrfSvcLocAlias);

	    	       if( curr_PrfSvcLoc_Obj != null ) {
	    		       tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax,  PrfSvcLocCtcMthAlias,  PrfSvcLocAddrAlias, PrfSvcLocEmailAlias, PrfSvcLocTelAlias, PrfSvcLocTelAlias );
		    	       if ( tmpAddressObj != null ) {
		    		       curr_PrfSvcLoc_Obj.setAddress(tmpAddressObj);
		    	       }
		    	       curr_PrfSvc_Obj.setServiceLocation(curr_PrfSvcLoc_Obj);
	    	       }

	           }
	           tmpAddressObj = null;
	   }


	   private void tr_0120MedPractitioner(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
           Address tmpAddressObj = null;
           
           if (curr_PrfSvcMcPc_key <= 0)
        	   return;
           
           if (PrfSvcMcPc_Hashkey.contains(curr_PrfSvcMcPc_key) == false ) {
        	   PrfSvcMcPc_Hashkey.add(curr_PrfSvcMcPc_key);
        	   curr_PrfSvcMcPc_Obj = PopulateJAXB.populateMedicalPractitioner(rs, PrfSvcMcPcAlias, PrfSvcMcPcRlAlias);
	           if( curr_PrfSvcMcPc_Obj != null ) {
		           tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax,  PrfSvcMcPcCtcMthAlias, PrfSvcMcPcAddrAlias, PrfSvcMcPcEmailAlias, PrfSvcMcPcTelAlias, PrfSvcMcPcTelAlias);
    	           if ( tmpAddressObj != null ) {
//    	        	   curr_PrfSvcMcPc_Obj.getPerson().setAddress(tmpAddressObj);
    	        	   curr_PrfSvcMcPc_Obj.getPerson().getAddress().add(tmpAddressObj);  //VL99
    	           }
	           }
           }
           tmpAddressObj = null;
   }
	   

	   private void tr_0120MedicalPractPrfReg(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
			if(curr_PrfSvcMcPcPrfReg_key <=0 )
				return;
			
			if ( PrfSvcMcPcPrfReg_Hashkey.contains(curr_PrfSvcMcPcPrfReg_key) == false ) {
				curr_PrfSvcMcPcPrfReg_Obj = null;
		
				curr_PrfSvcMcPcPrfReg_Obj = PopulateJAXB.populateProfessionalRegistration(rs, PrfSvcMcPcRegAlias);
				
				if( curr_PrfSvcMcPcPrfReg_Obj != null )
				{
					curr_PrfSvcMcPc_Obj.setProfessionalRegistration(curr_PrfSvcMcPcPrfReg_Obj);
					PrfSvcMcPcPrfReg_Hashkey.add(curr_PrfSvcMcPcPrfReg_key);
				}
			}
		
		}
	   

	   private void tr_0130PrfsSupervisor(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
 	           Address tmpAddressObj = null;
 	           
 	           if (curr_PrfSvcSp_key <= 0)
 	        	   return;
 	           
	           if (PrfSvcSp_Hashkey.contains(curr_PrfSvcSp_key) == false) {
	        	   PrfSvcSp_Hashkey.add(curr_PrfSvcSp_key);

	        	   curr_PrfSvcSp_Obj = PopulateJAXB.populateSupervisor(rs, PrfSvcSpAlias, PrfSvcSpRlAlias);
	    	        if( curr_PrfSvcSp_Obj != null ) {
	    	    	    tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, PrfSvcSpCtcMthAlias, PrfSvcSpAddrAlias, PrfSvcSpEmailAlias, PrfSvcSpTelAlias, PrfSvcSpTelAlias);
	    	    	    if ( tmpAddressObj != null ) {
//	    	    	    	curr_PrfSvcSp_Obj.getPerson().setAddress(tmpAddressObj);
	    	    	    	curr_PrfSvcSp_Obj.getPerson().getAddress().add(tmpAddressObj);  //VL99
			    	    }
	    	    	    curr_PrfSvc_Obj.setSupervisor(curr_PrfSvcSp_Obj);
	    	        }
	    	     
	           }
	           tmpAddressObj = null;
	   }

	   
/* 	   
	   private void tr_0140PrfsNotes(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
	           if (curr_PrfsnlSvcNt_key > 0 && PrfsnlSvcNt_Hashkey.contains(curr_PrfsnlSvcNt_key) == false ) {
	        	   PrfsnlSvcNt_Hashkey.add(curr_PrfsnlSvcNt_key);
	    	   
			       PrfsnlSvcNtR_Hashkey.clear();  curr_PrfsnlSvcNtR_Obj = null;
			       PrfsnlSvcNtS_Hashkey.clear();  curr_PrfsnlSvcNtS_Obj = null;
			       PrfsnlSvcNtLocCM_Hashkey.clear();  curr_PrfsnlSvcNtLocCM_Obj = null;
			       
			       
			       Note note = PopulateJAXB.populateNote(rs, PrfsnlSvcNtAlias, "PrfsnlSvcNtKey" );
			       if( note != null ) {
			    	   curr_PrfsnlSvcNt_Obj = note ;
				    
			           tr_0143PrfsNtRec(rs);
					   tr_0146PrfsNtSuper(rs);
			    	   tr_0148PrfsNtLoc(rs);
			    	   curr_PrfsnlSvc_Obj.getNote().add(curr_PrfsnlSvcNt_Obj);
			       }
	           }
	   }
*/
	   
		private void traversePrfSvcNote(ResultSet rs) throws CDRInternalException, 
			CodeNotFoundFromTableCacheException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException
		{

			if ( curr_PrfSvcNt_key <= 0)
				return;
			
			if ( !PrfSvcNt_Hashkey.contains(curr_PrfSvcNt_key) ) {
				PrfSvcNt_Hashkey.add(curr_PrfSvcNt_key);
				
				curr_PrfSvcNt_Obj = null;
			
				curr_PrfSvcNt_Obj = PopulateJAXB.populateNote(rs, PrfSvcNtAlias, "PrfsnlSvcNtKey" );
				
				if(curr_PrfSvcNt_Obj != null ) 	{
					curr_PrfSvc_Obj.getNote().add(curr_PrfSvcNt_Obj);
					PrfSvcNt_Hashkey.add(curr_PrfSvcNt_key);
					
					tr_0148PrfsNtLoc(rs);
					traversePrfSvcNoteRecorder(rs);
					traversePrfSvcNoteSupervisor(rs);
				}
				//prev_PrfSvcNt_key = curr_PrfSvcNt_key;
			}
			else
			{
				// populate Note and other PatientNote objects
				traversePrfSvcNoteRecorder(rs);
				traversePrfSvcNoteSupervisor(rs);
			}

		}
		
	   
		private void traversePrfSvcNoteRecorder(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
		{
			if( curr_PrfSvcNtRc_key <= 0)
				return;
			
            if (!PrfSvcNtRc_Hashkey.contains(curr_PrfSvcNtRc_key)) {
            	curr_PrfSvcNtR_Obj = null;
		
            	curr_PrfSvcNtR_Obj = PopulateJAXB.populateRecorder(rs, PrfSvcNtRcAlias, PrfSvcNtRcRlAlias);
				
				if(curr_PrfSvcNtR_Obj != null )
				{
					curr_PrfSvcNt_Obj.setRecorder(curr_PrfSvcNtR_Obj);
					PrfSvcNtRc_Hashkey.add(curr_PrfSvcNtRc_key);
					
					traversePrfSvcNoteRecorderAddress(rs);
					traversePrfSvcNoteRecorderPrfReg(rs);
				}
			} else {
				// populate Note and other PatientNote objects
				traversePrfSvcNoteRecorderAddress(rs);
				traversePrfSvcNoteRecorderPrfReg(rs);
		
			}
			
		}
		
		
		private void traversePrfSvcNoteRecorderAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
		{
			currPrfSvcNoteRecAddressObj = AddressApi.populateAddress(rs , currPrfSvcNoteRecAddressObj, currPrfSvcNoteRecEmail , currPrfSvcNoteRecPhone , currPrfSvcNoteRecFax, PrfSvcNtRcCtcMthAlias,
					PrfSvcNtRcAddrAlias , PrfSvcNtRcEmailAlias ,PrfSvcNtRcTelAlias , PrfSvcNtRcTelAlias);
			
			if ( currPrfSvcNoteRecAddressObj!= null )
//			    curr_PrfSvcNtR_Obj.getPerson().setAddress(currPrfSvcNoteRecAddressObj);
		        curr_PrfSvcNtR_Obj.getPerson().getAddress().add(currPrfSvcNoteRecAddressObj);  //VL99
		}		
		
		
		private void traversePrfSvcNoteRecorderPrfReg(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		
			if( curr_PrfSvcNtRcPrfReg_key <=0)
				return;
			
			if ( !PrfSvcNtRcPrfReg_Hashkey.contains(curr_PrfSvcNtRcPrfReg_key) ) {
				currPrfSvcNoteRecPrfRegObj = PopulateJAXB.populateProfessionalRegistration(rs, PrfSvcNtRcPrfRegAlias);
				
				if( currPrfSvcNoteRecPrfRegObj != null ) {
					curr_PrfSvcNtR_Obj.getProfessionalRegistration().add(currPrfSvcNoteRecPrfRegObj);
					PrfSvcNtRcPrfReg_Hashkey.add(curr_PrfSvcNtRcPrfReg_key);
				}
			}
		}
		
/*	   
	    private void tr_0143PrfsNtRec(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
	            Address tmpAddressObj = null;
	            if (curr_PrfsnlSvcNtR_key > 0 && PrfsnlSvcNtR_Hashkey.contains(curr_PrfsnlSvcNtR_key) == false) {
	            	PrfsnlSvcNtR_Hashkey.add(curr_PrfsnlSvcNtR_key);

	            	
	    	         Recorder rec = PopulateJAXB.populateRecorder(rs, PrfsnlSvcNtRAlias, PrfsnlSvcNtRLRAlias);
	    	         if( rec != null ) {
	    	        	 curr_PrfsnlSvcNtR_Obj = rec;
	    	        	 
	    	    	     tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, PrfsnlSvcNtCMRAlias, PrfsnlSvcNtRAddrAlias, PrfsnlSvcNtREmailAlias, PrfsnlSvcNtRTelecomAlias, PrfsnlSvcNtRTelecomAlias); 
	    	    	     if ( tmpAddressObj != null ) {
	    	    		      rec.getPerson().setAddress(tmpAddressObj);
			    	     }
	    	    	     curr_PrfsnlSvcNt_Obj.setRecorder(rec);
	    	         }
	    	     
	            }
	            tmpAddressObj = null;
	    }
*/	    


		private void traversePrfSvcNoteSupervisor(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException
		{
			if( curr_PrfSvcNtSp_key <= 0)
				return;
			
			if ( !PrfSvcNtSp_Hashkey.contains(curr_PrfSvcNtSp_key) ) {
				curr_PrfSvcNtS_Obj = null;
		
				curr_PrfSvcNtS_Obj = PopulateJAXB.populateSupervisor(rs, PrfSvcNtSpAlias, PrfSvcNtRLSAlias);
				
				if(curr_PrfSvcNtS_Obj != null ) {
					curr_PrfSvcNt_Obj.setSupervisor(curr_PrfSvcNtS_Obj);
					PrfSvcNtSp_Hashkey.add(curr_PrfSvcNtSp_key);
					traversePrfSvcNoteSupervisorAddress(rs);
					traversePrfSvcNoteSupervisorPrfReg(rs);
				}
			} else {
				traversePrfSvcNoteSupervisorAddress(rs);
				traversePrfSvcNoteSupervisorPrfReg(rs);
			}
			
		}		
		
		
		private void traversePrfSvcNoteSupervisorAddress(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException  
		{
			currPrfSvcNoteSupervisorAddressObj = AddressApi.populateAddress(rs , currPrfSvcNoteSupervisorAddressObj ,currPrfSvcNoteSupervisorEmail , currPrfSvcNoteSupervisorPhone , currPrfSvcNoteSupervisorFax, PrfSvcNtCMSAlias,
					PrfSvcNtSAddrAlias ,PrfSvcNtSEmailAlias ,PrfSvcNtSTelecomAlias , PrfSvcNtSTelecomAlias);
			
			if ( currPrfSvcNoteSupervisorAddressObj!= null )
//	 		    curr_PrfSvcNtS_Obj.getPerson().setAddress(currPrfSvcNoteSupervisorAddressObj);
			    curr_PrfSvcNtS_Obj.getPerson().getAddress().add(currPrfSvcNoteSupervisorAddressObj);  //VL99
		}
		

		private void traversePrfSvcNoteSupervisorPrfReg(ResultSet rs) throws CDRInternalException, SQLException, IOException, NamingException, CodeNotFoundFromTableCacheException {
			
			if( curr_PrfSvcNtSpPrfReg_key <=0)
				return;
			
			if ( !PrfSvcNtSpPrfReg_Hashkey.contains(curr_PrfSvcNtSpPrfReg_key) ) {
				currPrfSvcNoteRecPrfRegObj = PopulateJAXB.populateProfessionalRegistration(rs, PrfSvcNtSpPrfRegAlias);
				if( curr_PrfSvcNtS_Obj != null ) {
					curr_PrfSvcNtR_Obj.getProfessionalRegistration().add(currPrfSvcNoteRecPrfRegObj);
					PrfSvcNtSpPrfReg_Hashkey.add(curr_PrfSvcNtSpPrfReg_key);
				}
			}
		}
		

		
/*		
	    private void tr_0146PrfsNtSuper(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		        Address tmpAddressObj = null;
		        if (curr_PrfsnlSvcNtS_key > 0 && PrfsnlSvcNtS_Hashkey.contains(curr_PrfsnlSvcNtS_key) == false) {
		        	PrfsnlSvcNtS_Hashkey.add(curr_PrfsnlSvcNtS_key);
		    	     
		    	     Supervisor spr = PopulateJAXB.populateSupervisor(rs, PrfsnlSvcNtSAlias, PrfsnlSvcNtRLSAlias);
		    	     if( spr != null ) {
		    	    	 curr_PrfsnlSvcNtS_Obj = spr;

		    	    	 tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, PrfsnlSvcNtCMSAlias, PrfsnlSvcNtSAddrAlias, PrfsnlSvcNtSEmailAlias, PrfsnlSvcNtSTelecomAlias,PrfsnlSvcNtSTelecomAlias); 
		    	    	 if ( tmpAddressObj != null ) {
		    	    		  spr.getPerson().setAddress(tmpAddressObj);
				    	 }
		    	    	 curr_PrfsnlSvcNt_Obj.setSupervisor(spr);
		    	     }
		    	     
		        }
		        tmpAddressObj = null;
	    }
*/
	    
	    // + CURR
	    private void tr_0148PrfsNtLoc(ResultSet rs) throws CDRInternalException, SQLException, ParseException, DatatypeConfigurationException, IOException, NamingException, CodeNotFoundFromTableCacheException {
		        Address tmpAddressObj = null;
		        
		        if (curr_PrfSvcNtLocCM_key <= 0)
		        	return;

		        if (PrfSvcNtLoc_Hashkey.contains(curr_PrfSvcNtLocCM_key) == false ) {
		        	PrfSvcNtLoc_Hashkey.add(curr_PrfSvcNtLocCM_key);
		    	
		        	curr_PrfSvcNtLocCM_Obj = PopulateJAXB.populateLocation(rs, PrfSvcNtLocAlias);

		    	    if( curr_PrfSvcNtLocCM_Obj != null ) {
		    		    tmpAddressObj = AddressApi.populateAddress(rs , tmpAddressObj, currEmail , currPhone , currFax, PrfSvcNtLocCMAlias, PrfSvcNtLocAddrAlias, PrfSvcNtLocEmailAlias, PrfSvcNtLocTelecomAlias, PrfSvcNtLocTelecomAlias); 
			    	    if ( tmpAddressObj != null ) {
			    	    	curr_PrfSvcNtLocCM_Obj.setAddress(tmpAddressObj);
			    	    }
			    	    curr_PrfSvcNt_Obj.setLocation(curr_PrfSvcNtLocCM_Obj);
		    	    }

		        }
		        tmpAddressObj = null;
	    }
	    

	    
	    private void CleanAllObjects() {
		      Disp_Hashkey.clear();       
		      // curr_Disp_Obj = null;
		      Cmpnd_Hashkey.clear();      
//		      curr_Cmpnd_Obj = null;
	
		      CmpndIngr_Hashkey.clear();  
//		      curr_CmpndIngr_Obj = null;
		      
		      Initr_Hashkey.clear();      
//		      curr_Initr_Obj = null;
		      DispLoc_Hashkey.clear();    
		      //		      curr_DispLoc_Obj = null;
		      
		      DispPack_Hashkey.clear();   
		      //		      curr_DispPack_Obj = null;
		      DispPackDrg_Hashkey.clear();  
		      //		      curr_DispPackDrg_Obj = null;
		      
		      TxNt_Hashkey.clear();       
		      //		      curr_TxNt_Obj = null;
		      TxNtSuper_Hashkey.clear();  
		      //		      curr_TxNtSuper_Obj = null;
		      TxNtRec_Hashkey.clear();   
		      //		      curr_TxNtRec_Obj = null;
		      TxNtLoc_Hashkey.clear();    
		      //		      curr_TxNtLoc_Obj = null;
		      
		      txSuper_Hashkey.clear();    
		      //		      curr_txSuper_Obj = null;
		      txRec_Hashkey.clear();      
		      //		      curr_txRec_Obj = null;

		      PrfSvc_Hashkey.clear();     
		      //		      curr_PrfSvc_Obj= null;
		      PrfSvcLoc_Hashkey.clear();  
		      //		      curr_PrfSvcLoc_Obj = null;
		      PrfSvcMcPc_Hashkey.clear();      
		      //		      curr_MedPrctp_Obj = null;
		      PrfSvcSp_Hashkey.clear();      
		      //		      curr_PrfSvcSp_Obj = null;

		      PrfSvcNt_Hashkey.clear();   
		      //		      curr_PrfSvcNt_Obj = null;
		      PrfSvcNtSp_Hashkey.clear();  
		      //		      curr_PrfSvcNtS_Obj = null;
		      PrfSvcNtRc_Hashkey.clear();  
		      //		      curr_PrfSvcNtR_Obj = null;
		      PrfSvcNtLoc_Hashkey.clear();  
		      //		      curr_PrfSvcNtLocCM_Obj = null;

	    }

	   
	   
}
