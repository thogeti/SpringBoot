select Ptnt_PTNTKEY,
       Ptnt_CNSMRID,
       ptntid.idnum                as ptntid_idnum,
       ptntid.ptntidkey            as ptntid_ptntidkey,
       ptntid.issngathrtynm        as ptntid_issngathrtynm ,
       ptntid.idtypkey             as ptntid_idtypkey ,
       tmp_ptnt.FRSTNM             as Ptnt_FRSTNM,
       tmp_ptnt.LSTNM              as Ptnt_LSTNM,
       tmp_ptnt.MDLNM              as Ptnt_MDLNM,
       tmp_ptnt.CRTTIMESTAMP       as Ptnt_CRTTIMESTAMP,
       tmp_ptnt.LSTUPDTTIMESTAMP   as Ptnt_LSTUPDTTIMESTAMP,
       tmp_ptnt.DTOFBIRTH          as Ptnt_DTOFBIRTH,
       tmp_ptnt.DCSDDT             as Ptnt_DCSDDT,
       tmp_ptnt.GNDRTYPKEY         as Ptnt_GNDRTYPKEY,
       tmp_ptnt.NOKNWNALRGYFLG     as Ptnt_NOKNWNALRGYFLG,
       tmp_ptnt.PTNTACTIND         as Ptnt_PTNTACTIND,
       tmp_ptnt.PTNTTYPKEY         as Ptnt_PTNTTYPKEY,
       tmp_ptnt.MOBILENUM          as Ptnt_MOBILENUM,   --TE99 
       tmp_ptnt.GRPMBRSHPTYPKEY    as Ptnt_GRPMBRSHPTYPKEY,
       tmp_ptnt.PTNTOPTNOPTTYPKEY  as Ptnt_PTNTOPTNOPTTYPKEY,
       tmp_ptnt.LANGUAGECORRESPONDENCE   AS ptnt_LANGUAGECORRESPONDENCE, 
       tmp_ptnt.SIGLANGUAGE              AS ptnt_SIGLANGUAGE,
       tmp_ptnt.MONOGRAPHLANGUAGE        AS ptnt_MONOGRAPHLANGUAGE,

       CM_CNSMRID,
       cntctmthdtypcd,
       CNTCTPRPSTYPKEY,
       CNTCTPRPSTYPCD,

       EMAIL_EMAILADDR,                                     
       EMAIL_EMAILKEY,
       EMAIL_ROWID,

       PHONE_ROWID,
       decode(cntctmthdtypcd, 'Telecom', PHONE_TELECOMKEY, -3) PHONE_TELECOMKEY,
       PHONE_TELFAXIND,
       PHONE_CNTRYCD,
       PHONE_TELECOMNUM,
       
       ALTERN_TELECOMKEY,
       ALTERN_ROWID,
       ALTERN_TELFAXIND,
       ALTERN_CNTRYCD,
       ALTERN_TELECOMNUM,

       FAX_ROWID,
       FAX_TELECOMKEY,
       FAX_TELFAXIND,
       FAX_CNTRYCD,
       FAX_TELECOMNUM,

       addr_addrkey,
       addr_ADDRLNONE,
       addr_ADDRLNTWO,
       addr_CITYNM,
       addr_CNTRYCD,
       addr_POSTALCD,
       addr_PROVCD,
       ADDR_ROWID

             from   (select p.PTNTKEY       Ptnt_PTNTKEY,
                            p.CNSMRID       Ptnt_CNSMRID,
                            p.CRTTIMESTAMP,
                            p.PTNTTYPKEY,
                            p.DCSDDT,
                            p.DTOFBIRTH,
                            p.FRSTNM,
                            p.GNDRTYPKEY,
                            p.GRPMBRSHPTYPKEY,
                            p.LSTNM,
                            p.LSTUPDTTIMESTAMP,
                            p.MDLNM,
                            p.NOKNWNALRGYFLG,
                            p.PTNTACTIND,
                            p.PTNTOPTNOPTTYPKEY,
                            p.MOBILENUM,          --TE99--Drop56
                             p.LANGUAGECORRESPONDENCE,
                            p.SIGLANGUAGE,
                            p.MONOGRAPHLANGUAGE,

                            null           FAX_ROWID,
                            -3             FAX_TELECOMKEY, 
                            null           FAX_TELFAXIND, 
                            null           FAX_CNTRYCD,
                            null           FAX_TELECOMNUM,

                            decode(t.TELFAXIND, 'T', t.rowid,      null) PHONE_ROWID,
                            decode(t.TELFAXIND, 'T', t.TELECOMKEY, -3)   PHONE_TELECOMKEY,

                            decode(t.TELFAXIND, 'T', 'T', null)          PHONE_TELFAXIND,
                            decode(t.TELFAXIND, 'T', t.CNTRYCD,    null) PHONE_CNTRYCD,
                            decode(t.TELFAXIND, 'T', t.TELECOMNUM, null) PHONE_TELECOMNUM,

                            decode(t.TELFAXIND, 'A', t.rowid,      null) ALTERN_ROWID,
                            decode(t.TELFAXIND, 'A', t.TELECOMKEY, -3)   ALTERN_TELECOMKEY,

                            decode(t.TELFAXIND, 'A', 'A', null)          ALTERN_TELFAXIND,
                            decode(t.TELFAXIND, 'A', t.CNTRYCD,    null) ALTERN_CNTRYCD,
                            decode(t.TELFAXIND, 'A', t.TELECOMNUM, null) ALTERN_TELECOMNUM

                       from ptnt      p,
                            cntctmthd c,
                            telecom   t
                      where p.storeNum  = ?
                        and p.ptntkey   = c.ptntkey
                        and c.cntctmthdkey = t.telecomkey
                        and c.cntctmthdtypcd = 'Telecom'
                        and t.TELFAXIND in ('T','P','A')
                        and t.TELECOMNUM = ?
