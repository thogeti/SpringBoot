
------------------------------------------
-- Prescription (Rx) SELECT statement
-- version: 2016-08-17
------------------------------------------

select * 
from Rx Rx


----- Rx Fill Status block -----
left outer join RxStat RxStat on Rx.RxKey = RxStat.RxKey


----- Rx Location block -----
left outer join LOC RxLoc                           on Rx.SVCLOCKEY = RxLoc.LOCKEY
   ----- Rx Location Address
   left outer join CntctMthd RxLocCtMthd on (RxLocCtMthd.LocKey = RxLoc.LocKey and RxLocCtMthd.PtntKey is null and RxLocCtMthd.PrsnKey is null)
   left outer join Email     RxLocEmail     on RxLocEmail.EmailKey = RxLocCtMthd.CntctMthdKey
   left outer join Addr      RxLocAddr      on RxLocAddr.AddrKey = RxLocCtMthd.CntctMthdKey
   left outer join Telecom   RxLocTelecom   on RxLocTelecom.TelecomKey = RxLocCtMthd.CntctMthdKey
   ----< Rx Location Store
   left outer join Store RxLcStr on RxLcStr.LOCKEY = RxLoc.LOCKEY
      ----- Rx Location Store Address
      left outer join CntctMthd RxLcStrCtMthd on RxLcStrCtMthd.LocKey = RxLoc.LocKey
      left outer join Email     RxLcStrEmail     on RxLcStrEmail.EmailKey = RxLcStrCtMthd.CntctMthdKey
      left outer join Addr      RxLcStrAddr      on RxLcStrAddr.AddrKey = RxLcStrCtMthd.CntctMthdKey
      left outer join Telecom   RxLcStrTelecom   on RxLcStrTelecom.TelecomKey = RxLcStrCtMthd.CntctMthdKey
   
   
----- Rx Pack block -----
left outer join PACK RxPck on Rx.PACKKEY = RxPck.PACKKEY
   ----- Rx Pack Drug
   left outer join Drg RxPckDrg		on RxPck.DrgKey = RxPckDrg.DrgKey
   left outer join DsgFrm pckDsgFrm	on RxPckDrg.DsgFrmKey=pckDsgFrm.DsgFrmKey      
      --- Rx Pack Drug Manufacturer
      left outer join Mfctr RxPckDrgMfctr                on RxPckDrg.MfctrKey = RxPckDrgMfctr.MfctrKey
      --- Rx Pack Drug Manufacturer Recall
      left outer join MfctrDrgRecall RxPkDgMfRcl on RxPckDrgMfctr.MfctrKey = RxPkDgMfRcl.MfctrKey 
      --- Rx Pack Drug Molecule
      left outer join Mlcl RxPckDrgMlcl                  on RxPckDrg.DrgKey = RxPckDrgMlcl.DrgKey


----- Rx Patient block
left outer join PTNT Ptnt                         on Rx.PTNTKEY = Ptnt.PTNTKEY
   ----- Rx Patient Contact Info block				       
   left outer join CntctMthd rxPtntCtMthd on Ptnt.PTNTKEY = rxPtntCtMthd.PrsnKey
   left outer join Email rxPtntEmail         on rxPtntCtMthd.CntctMthdKey = rxPtntEmail.EmailKey  
   left outer join Addr rxPtntAddr           on rxPtntCtMthd.CntctMthdKey = rxPtntAddr.AddrKey  
   left outer join Telecom rxPtntTelecom     on rxPtntCtMthd.CntctMthdKey = rxPtntTelecom.TelecomKey  
   ---- Rx Patient Person block
   left outer join PRSN PtntPrsn on Ptnt.PTNTKEY=PtntPrsn.PrsnKey
      -- Rx Patient Person Cust Pref block
      left outer join CUSTPREF PtntPref on PtntPref.PRSNKEY=PtntPrsn.PRSNKEY
      
----- Rx Prescriber Block
left outer join PRSNRL Prscbr                     on Rx.PRSCBRKEY = Prscbr.PRSNRLKEY
   ----- Rx Prescriber Contact Info block
   left outer join CntctMthd rxrCtMthd on Prscbr.PRSNKEY = rxrCtMthd.PrsnKey
   left outer join Email rxPtntEmail         on rxrCtMthd.CntctMthdKey = rxPtntEmail.EmailKey  
   left outer join Addr rxPtntAddr           on rxrCtMthd.CntctMthdKey = rxPtntAddr.AddrKey  
   left outer join Telecom rxPtntTelecom     on rxrCtMthd.CntctMthdKey = rxPtntTelecom.TelecomKey  
   ---- Rx Prescriber Person block
   left outer join PRSN PrscbrPrsn on Prscbr.PRSNKEY=PrscbrPrsn.PrsnKey

----< Rx Dispense block
left outer join Tx RxDisp                           on RxDisp.RxKey = Rx.RXKEY

----< Rx Note block
left outer join RxNt RxNt on rx.RxKey = RxNt.RxKey
   -- Rx Note Supervisor Role
   left outer join PrsnRl  RxNtSupr on RxNt.SprvsrKey = RxNtSupr.PrsnRlKey
   -- Rx Note Supervisor Person
   left outer join prsn RxNtSprP  on RxNtSupr.PrsnKey = RxNtSprP.PrsnKey
   -- Rx Note Supervisor Person Contact Method
 	 left outer join CntctMthd RxNtSprCtMthd on RxNtSprP.PrsnKey = RxNtSprCtMthd.PrsnKey  
   -- Rx Note Supervisor Person Contact Method: Email
   left outer join Email RxNtSprEmail on RxNtSprCtMthd.CntctMthdKey = RxNtSprEmail.EmailKey  
   -- Rx Note Supervisor Person Contact Method: Address
   left outer join Addr RxNtSprAddr on RxNtSprCtMthd.CntctMthdKey = RxNtSprAddr.AddrKey  
   -- Rx Note Supervisor Person Contact Method: Telecom
   left outer join Telecom RxNtSprTelecom on RxNtSprCtMthd.CntctMthdKey = RxNtSprTelecom.TelecomKey  


----- Rx Drug
left outer join Drg RxDrg 			on Rx.DRGKEY = RxDrg.DRGKEY
left outer join DsgFrm rxDsgFrm	on RxDrg.DsgFrmKey=rxDsgFrm.DsgFrmKey
   --- Rx Pack Drug Manufacturer
   left outer join Mfctr RxDrgMfctr                on RxDrg.MfctrKey = RxDrgMfctr.MfctrKey
   --- Rx Pack Drug Manufacturer Recall
   left outer join MfctrDrgRecall RxDrgMfRcl on RxDrgMfctr.MfctrKey = RxDrgMfRcl.MfctrKey 
   --- Rx Pack Drug Molecule
   left outer join Mlcl RxDrgMlcl                  on RxDrg.DrgKey = RxDrgMlcl.DrgKey


----- Rx Compound
left outer join cmpnd  RxCmpnd on Rx.cmpndkey = RxCmpnd.cmpndkey
   -- Rx Compound Ingredient  
   left outer join CmpndIngrdnt RxCmpndIngrdnt on RxCmpnd.CmpndKey = RxCmpndIngrdnt.CmpndKey
   -- Rx Compund Ingredient Pack
   left outer join Pack RxCpIgPk on RxCmpndIngrdnt.packKey = RxCpIgPk.packKey
   -- Rx Compound Ingredient Pack Drug
   left outer join drg RxCpIgPkDrg  on RxCpIgPk.DrgKey = RxCpIgPkDrg.DrgKey
   left outer join DsgFrm cpDsgFrm on RxDrg.DsgFrmKey=cpDsgFrm.DsgFrmKey
   -- Rx Compound Ingredient Pack Drug Manufacturer
   left outer join Mfctr cipdMfctr      on RxCpIgPkDrg.MfctrKey = cipdMfctr.MfctrKey
   -- Rx Compound Ingredient Pack Drug Recall
   left outer join MfctrDrgRecall cipdrMfRcl  on cipdMfctr.MfctrKey = cipdrMfRcl.MfctrKey 
   -- Rx Compound Ingredient Pack Drug Moleacule
   left outer join Mlcl RxcipdMlcl on RxCpIgPkDrg.DrgKey = RxcipdMlcl.DrgKey


---- Rx Recorder
left outer join PRSNRL Rcrdr                     on Rx.RCRDRKEY = Rcrdr.PRSNRLKEY
   ----- Rx Recorder Contact Info block				       
   left outer join CntctMthd rxrRcrdrCtMthd on Rcrdr.PrsnKey = rxrRcrdrCtMthd.PrsnKey
   left outer join Email rxPtntEmail         on rxrRcrdrCtMthd.CntctMthdKey = rxPtntEmail.EmailKey  
   left outer join Addr rxPtntAddr           on rxrRcrdrCtMthd.CntctMthdKey = rxPtntAddr.AddrKey  
   left outer join Telecom rxPtntTelecom     on rxrRcrdrCtMthd.CntctMthdKey = rxPtntTelecom.TelecomKey 
   ---- Rx Recorder Person block
   left outer join PRSN RcrdrPrsn on Rcrdr.PRSNKEY=RcrdrPrsn.PrsnKey


---- Rx Hold Reason
left outer join RXHLDRSNTYP RxHldRsnTyp           on Rx.RXHLDRSNTYPKEY= RxHldRsnTyp.RXHLDRSNTYPKEY

---- Rx Responsible
left outer join PRSN Rspnsbl                     on Rx.PrsnKey = Rspnsbl.PRSNKEY
   ----- Rx Patient Contact Info block				       
   left outer join CntctMthd rxRsblCtMthd on Rspnsbl.PRSNKEY = rxRsblCtMthd.PrsnKey
   left outer join Email rxRsblEmail         on rxRsblCtMthd.CntctMthdKey = rxRsblEmail.EmailKey  
   left outer join Addr rxRsblAddr           on rxRsblCtMthd.CntctMthdKey = rxRsblAddr.AddrKey  
   left outer join Telecom rxRsblTelecom     on rxRsblCtMthd.CntctMthdKey = rxRsblTelecom.TelecomKey 
   ---- Rx Responsible Person block
   left outer join PRSN RspnsblPrsn on Rspnsbl.PRSNKEY=RspnsblPrsn.PrsnKey


---- Rx Supervisor
left outer join PRSNRL Sprvsr                    on Rx.SPRVSRKEY = Sprvsr.PRSNRLKEY
   ----- Rx Supervisor Contact Info block				       
   left outer join CntctMthd rxSprvsrCtMthd on Sprvsr.PRSNKEY = rxSprvsrCtMthd.PrsnKey
   left outer join Email rxSprvsrEmail         on rxSprvsrCtMthd.CntctMthdKey = rxSprvsrEmail.EmailKey  
   left outer join Addr rxSprvsrAddr           on rxSprvsrCtMthd.CntctMthdKey = rxSprvsrAddr.AddrKey  
   left outer join Telecom rxSprvsrTelecom     on rxSprvsrCtMthd.CntctMthdKey = rxSprvsrTelecom.TelecomKey  
   
   ---- Rx Sprvsr Person block
   left outer join PRSN SprvsrPrsn on Sprvsr.PRSNKEY=SprvsrPrsn.PrsnKey   


   

WHERE 1=1


ORDER BY
Rx.RxKey
, RxLcStr.LocKey
, RxDisp.TxKey DESC
, RxNt.RxNtKey DESC
, RxLocCtMthd.CntctMthdKey
