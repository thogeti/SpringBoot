SELECT  
        TX.SIGCD TX_SIGCD,
        TX.ADDTNLDSGINSTRCTNTXT TX_ADDTNLDSGINSTRCTNTXT,
        TX.ADMINPRDENDDT TX_ADMINPRDENDDT,
        TX.DAYSSPLY TX_DAYSSPLY,
        TX.ADMINPRDSTRTDT TX_ADMINPRDSTRTDT, 
        TX.CRTTIMESTAMP TX_CRTTIMESTAMP,--
        TX.PRDCRID TX_PRDCRID, 
        TX.CNSMRID TX_CNSMRID, 
        TX.TXNCANCLRSNTXT TX_TXNCANCLRSNTXT,
        TX.LSTUPDT TX_LSTUPDT, --
        TX.PCKUPTIME TX_PCKUPTIME,--
        TX.QTYTXD TX_QTYTXD, 
        TX.TOTALAMTPAID TX_TOTALAMTPAID,
        TX.TXTIME TX_TXTIME,
        TX.DAYSINTRVL TX_DAYSINTRVL, --
        TX.DRGLOTNUM TX_DRGLOTNUM,
        TX.DRGEXPRTNDT TX_DRGEXPRTNDT,
        TX.SIGDESCRPTNTLANG TX_SIGDESCRPTNTLANG,
        TX.SEQNUM TX_SEQNUM, 
        TX.PREVTXDAYSSPLY TX_PREVTXDAYSSPLY, 
        TX.QTYRMNG TX_QTYRMNG,
        TX.HOMEDELVRYFLAG TX_HOMEDELVRYFLAG,--
        TX.INITRKEY TX_INITRKEY,
        TX.SPRVSRKEY TX_SPRVSRKEY, 
        TX.RCRDRKEY TX_RCRDRKEY,
        TX.TXKEY TX_TXKEY,
        TX.RTEOFADMIN_CERXTYPKEY TX_RTEOFADMIN_CERXTYPKEY,--
        TX.DISCNTDRSNTYPKEY TX_DISCNTDRSNTYPKEY, 
        TX.TXRSMRSNTYPKEY TX_TXRSMRSNTYPKEY,
        TX.TXSUBRSNTYPKEY TX_TXSUBRSNTYPKEY, 
        TX.TXTYPKEY TX_TXTYPKEY, 
        TX.INTRCHGBLTYTYPKEY TX_INTRCHGBLTYTYPKEY,
        TX.PKGFRMTYPKEY TX_PKGFRMTYPKEY,
        TX.CMPNDKEY TX_CMPNDKEY,
        TX.PACKKEY TX_PACKKEY, 
        TX.TXNNUM TX_TXNNUM,--
        TX.ENGMNTTYPKEY TX_ENGMNTTYPKEY,
        TX.COMMMODETYPKEY TX_COMMMODETYPKEY, 
        TX.LASTPICKUPTIME TX_LASTPICKUPTIME,
        TXSTAT.TXFILLSTATTYPKEY TXSTAT_TXFILLSTATTYPKEY,
        DSGFRM.DSGFRMFULLNM DSGFRM_DSGFRMFULLNM,
        DSGFRM.DSGFRMID DSGFRM_DSGFRMID,
        DSGFRM.DSGFRMSHORTNM DSGFRM_DSGFRMSHORTNM, 
        PACK.PACKID PACK_PACKID,
        PACK.ALTRNTVPACKSZ PACK_ALTRNTVPACKSZ, 
        PACK.ISACTFLG PACK_ISACTFLG, 
        PACK.CNSMRID PACK_CNSMRID,
        PACK.ISCRNTFLG PACK_ISCRNTFLG,
        PACK.PRDCRID PACK_PRDCRID, 
        PACK.MFCTRDISCNTDDT PACK_MFCTRDISCNTDDT, 
        PACK.ALTRNTVPACKSZUOM PACK_ALTRNTVPACKSZUOM,
        PACK.STRNGTH PACK_STRNGTH, 
        PACK.GTIN PACK_GTIN, 
        PACK.PACKSZ PACK_PACKSZ,
        PACK.MSTRID PACK_MSTRID,
        PACK.PACKKEY PACK_PACKKEY,
        PACK.DRGKEY PACK_DRGKEY, 
        PRFSNLSVC.SVCLOC PRFSNLSVC_SVCLOC,
        PRFSNLSVC.CNSLTTNTIMESTAMP PRFSNLSVC_CNSLTTNTIMESTAMP, 
        PRFSNLSVC.CNSLTTNLENGTH PRFSNLSVC_CNSLTTNLENGTH, 
        PRFSNLSVC.CNSMRID PRFSNLSVC_CNSMRID,
        PRFSNLSVC.PRDCRID  PRFSNLSVC_PRDCRID, 
        PRFSNLSVC.SVCPRVDRKEY PRFSNLSVC_SVCPRVDRKEY, 
        PRFSNLSVC.SPRVSRKEY PRFSNLSVC_SPRVSRKEY,
        PRFSNLSVC.TXKEY PRFSNLSVC_TXKEY,
        PRFSNLSVC.LOCKEY PRFSNLSVC_LOCKEY,
        PRFSNLSVC.PRFSNLSVCKEY PRFSNLSVC_PRFSNLSVCKEY, 
        PACK.PACKSZUOMTYPKEY PACK_PACKSZUOMTYPKEY,
        PACK.STRNGTHUOMTYPKEY PACK_STRNGTHUOMTYPKEY, 
        PRFSNLSVC.PRFSNLSVCTYPKEY PRFSNLSVC_PRFSNLSVCTYPKEY 
        FROM TX TX
        LEFT OUTER JOIN TXSTAT TXSTAT ON TX.TXKEY = TXSTAT.TXKEY 
        LEFT OUTER JOIN PACK PACK ON TX.PACKKEY = PACK.PACKKEY 
        LEFT OUTER JOIN DSGFRM DSGFRM ON TX.DSGFRMKEY = DSGFRM.DSGFRMKEY 
        LEFT OUTER JOIN PRFSNLSVC PRFSNLSVC ON TX.TXKEY = PRFSNLSVC.TXKEY 
        
        TX.PHARMACYCHNLTYPKEY TX_PHARMACYCHNLTYPKEY, TX.TXNSRCECHNLTYPKEY TX_TXNSRCECHNLTYPKEY, 