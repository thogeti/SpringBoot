select tmp_ptnt.PTNTKEY                  as Ptnt_PTNTKEY,
       tmp_ptnt.CNSMRID                  as Ptnt_CNSMRID,
       
       ptntid.idnum                      as ptntid_idnum,
       ptntid.ptntidkey                  as ptntid_ptntidkey,
       ptntid.issngathrtynm              AS ptntid_issngathrtynm ,
       ptntid.idtypkey                   AS ptntid_idtypkey ,
       tmp_ptnt.FRSTNM                   as Ptnt_FRSTNM,
       tmp_ptnt.LSTNM                    as Ptnt_LSTNM,
    -- tmp_ptnt.SALUTATION               as Ptnt_SALUTATION,  --TE99
       tmp_ptnt.MDLNM                    as Ptnt_MDLNM,
       tmp_ptnt.CRTTIMESTAMP             as Ptnt_CRTTIMESTAMP,
       tmp_ptnt.LSTUPDTTIMESTAMP         as Ptnt_LSTUPDTTIMESTAMP,
       tmp_ptnt.DTOFBIRTH                as Ptnt_DTOFBIRTH,
       tmp_ptnt.DCSDDT                   as Ptnt_DCSDDT,
       tmp_ptnt.GNDRTYPKEY               as Ptnt_GNDRTYPKEY,
       tmp_ptnt.NOKNWNALRGYFLG           as Ptnt_NOKNWNALRGYFLG,
       tmp_ptnt.PTNTACTIND               as Ptnt_PTNTACTIND,
       tmp_ptnt.PTNTTYPKEY               as Ptnt_PTNTTYPKEY,
       tmp_ptnt.MOBILENUM                as Ptnt_MOBILENUM,   --TE99--Drop56
       tmp_ptnt.GRPMBRSHPTYPKEY          as Ptnt_GRPMBRSHPTYPKEY,
       tmp_ptnt.PTNTOPTNOPTTYPKEY        as Ptnt_PTNTOPTNOPTTYPKEY,
       tmp_ptnt.LANGUAGECORRESPONDENCE   AS ptnt_LANGUAGECORRESPONDENCE, 
       tmp_ptnt.SIGLANGUAGE              AS ptnt_SIGLANGUAGE,
       tmp_ptnt.MONOGRAPHLANGUAGE        AS ptnt_MONOGRAPHLANGUAGE,
       CM_CNSMRID,
       cntctmthdtypcd,
       CNTCTPRPSTYPKEY,
       CNTCTPRPSTYPCD                    as ADDR_TYPE,  --TE99
       EMAIL_EMAILADDR,                                     
       EMAIL_EMAILKEY,
       EMAIL_ROWID,
       PHONE_ROWID,
       PHONE_TELECOMKEY,
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

 from (select p.PTNTTYPKEY,
              p.CRTTIMESTAMP,
              p.CNSMRID,
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
              p.PTNTKEY,
              p.PTNTOPTNOPTTYPKEY,
         --   p.SALUTATION,
              p.MOBILENUM,
              p.LANGUAGECORRESPONDENCE,
              p.SIGLANGUAGE,
              p.MONOGRAPHLANGUAGE
         from ptnt p
        where p.ptntkey in (select NOTIFICATIONKEY ptntkey from tmp_eligibility)  ) tmp_ptnt

                left outer join ptntid ptntid ON ptntid.ptntkey in (select NOTIFICATIONKEY ptntkey from tmp_eligibility) and
                                                 tmp_ptnt.ptntkey = ptntid.ptntkey

                left outer join (select c.ptntkey,
                                        c.CNSMRID          CM_CNSMRID,
                                        c.cntctmthdtypcd, 
                                        COALESCE(c.CNTCTPRPSTYPKEY, 20)       CNTCTPRPSTYPKEY,
                                        COALESCE(c.CNTCTPRPSTYPCD, 'PRIMARY') CNTCTPRPSTYPCD,

                                        e.EMAILADDR                           EMAIL_EMAILADDR,                                     
                                        COALESCE(e.EMAILKEY, -3)              EMAIL_EMAILKEY,
                                        e.rowid                               EMAIL_ROWID,

                                        decode(t.TELFAXIND, 
                                              'T', t.TELECOMKEY,
                                              'P', t.TELECOMKEY, -3)        PHONE_TELECOMKEY,

                                        decode(t.TELFAXIND, 
                                              'T', t.rowid, 
                                              'P', t.rowid, null)           PHONE_ROWID,

                                        decode(t.TELFAXIND, 
                                              'T', 'P',
                                              'P', 'P', null)               PHONE_TELFAXIND,

                                        decode(t.TELFAXIND, 
                                              'T', t.CNTRYCD,
                                              'P', t.CNTRYCD, null)         PHONE_CNTRYCD,

                                        decode(t.TELFAXIND, 
                                              'T', t.TELECOMNUM,
                                              'P', t.TELECOMNUM, null)      PHONE_TELECOMNUM,

                                        decode(t.TELFAXIND, 'A', t.TELECOMKEY, -3)   ALTERN_TELECOMKEY,
                                        decode(t.TELFAXIND, 'A', t.rowid,      null) ALTERN_ROWID,
                                        decode(t.TELFAXIND, 'A', 'A', null)          ALTERN_TELFAXIND,
                                        decode(t.TELFAXIND, 'A', t.CNTRYCD,    null) ALTERN_CNTRYCD,
                                        decode(t.TELFAXIND, 'A', t.TELECOMNUM, null) ALTERN_TELECOMNUM,

                                        decode(t.TELFAXIND, 'F', t.rowid,      null) FAX_ROWID,
                                        decode(t.TELFAXIND, 'F', t.TELECOMKEY, -3)   FAX_TELECOMKEY,
                                        decode(t.TELFAXIND, 'F', 'F', null)          FAX_TELFAXIND,
                                        decode(t.TELFAXIND, 'F', t.CNTRYCD,    null) FAX_CNTRYCD,
                                        decode(t.TELFAXIND, 'F', t.TELECOMNUM, null) FAX_TELECOMNUM,

                                        COALESCE(a.addrkey, -3) addr_addrkey,
                                        a.ADDRLNONE   addr_ADDRLNONE,
                                        a.ADDRLNTWO   addr_ADDRLNTWO,
                                        a.CITYNM      addr_CITYNM,
                                        a.CNTRYCD     addr_CNTRYCD,
                                        a.POSTALCD    addr_POSTALCD,
	                                a.PROVCD      addr_PROVCD,
                                        a.rowid       ADDR_ROWID

                                   from cntctmthd  c
                                          left outer join addr a
                                                  on c.cntctmthdtypcd = 'Postal Address' and
                                                     c.cntctmthdkey   = a.addrkey
                              
                                          left outer join telecom  t
                                                  on c.cntctmthdtypcd = 'Telecom' and
                                                     c.cntctmthdkey   = t.telecomkey

                                          left outer join email    e
                                                  on c.cntctmthdtypcd = 'Email Address' and
                                                     c.cntctmthdkey   = e.emailkey
                                  where c.ptntkey in (select NOTIFICATIONKEY ptntkey from tmp_eligibility) ) tmp_addr
 
                          on tmp_ptnt.ptntkey = tmp_addr.ptntkey

  order by tmp_ptnt.PTNTKEY, ptntid.ptntidkey desc
