SELECT DISTINCT ptnt.cnsmrid                  AS ptnt_cnsmrid ,
                ptnt.crttimestamp             AS ptnt_crttimestamp ,
                ptnt.dcsddt                   AS ptnt_dcsddt ,
                ptnt.dtofbirth                AS ptnt_dtofbirth ,
                ptnt.frstnm                   AS ptnt_frstnm ,
                ptnt.gndrtypkey               AS ptnt_gndrtypkey ,
                ptnt.grpmbrshptypkey          AS ptnt_grpmbrshptypkey ,
                ptnt.lstnm                    AS ptnt_lstnm ,
                ptnt.lstupdttimestamp         AS ptnt_lstupdttimestamp ,
                ptnt.mdlnm                    AS ptnt_mdlnm ,
                ptnt.noknwnalrgyflg           AS ptnt_noknwnalrgyflg ,
                ptnt.ptntactind               AS ptnt_ptntactind ,
                ptnt.ptntkey                  AS ptnt_ptntkey ,
                ptnt.ptntoptnopttypkey        AS ptnt_ptntoptnopttypkey ,
                ptnt.ptnttypkey               AS ptnt_ptnttypkey ,
                ptnt.rcrdrkey                 AS ptnt_rcrdrkey ,
                ptnt.sndxfrstnm               AS ptnt_sndxfrstnm ,
                ptnt.sndxlstnm                AS ptnt_sndxlstnm ,
                ptnt.storenum                 AS ptnt_storenum,
                ptnt.mobilenum			      AS ptnt_mobilenum,
                ptnt.languagecorrespondence   AS ptnt_languagecorrespondence, 
                ptnt.siglanguage              AS ptnt_siglanguage,
                ptnt.monographlanguage        AS ptnt_monographlanguage,
                
                
                p_c_vw.addr_addrkey           AS addr_addrkey ,
                p_c_vw.addr_addrlnone         AS addr_addrlnone ,
                p_c_vw.addr_addrlntwo         AS addr_addrlntwo ,
                p_c_vw.addr_citynm            AS addr_citynm ,
                p_c_vw.addr_cntrycd           AS addr_cntrycd ,
                p_c_vw.addr_postalcd          AS addr_postalcd ,
                p_c_vw.addr_provcd            AS addr_provcd ,
                p_c_vw.cm_cnsmrid             AS cm_cnsmrid ,
                p_c_vw.email_emailaddr        AS email_emailaddr ,
                p_c_vw.email_emailkey         AS email_emailkey ,
                p_c_vw.fax_telecomkey         AS fax_telecomkey ,
                p_c_vw.fax_telecomnum         AS fax_telecomnum ,
                p_c_vw.phone_telecomkey       AS phone_telecomkey ,
                p_c_vw.phone_telecomnum       AS phone_telecomnum ,
                p_c_vw.altern_telecomkey      AS altern_telecomkey,  
                p_c_vw.altern_telecomnum      AS altern_telecomnum,
                p_c_vw.ptnt_ptntkey           AS ptnt_ptntkey

       FROM ptnt ptnt
         LEFT OUTER JOIN (select COALESCE(a.addrkey, -3)  addr_addrkey,
                             a.ADDRLNONE              ADDR_ADDRLNONE,
                             a.ADDRLNTWO              ADDR_ADDRLNTWO,
                             a.CITYNM                 ADDR_CITYNM,
                             a.CNTRYCD                ADDR_CNTRYCD,
                             a.POSTALCD               ADDR_POSTALCD,
                             a.PROVCD                 ADDR_PROVCD,
                             c.CNSMRID                CM_CNSMRID,

                             e.EMAILADDR              EMAIL_EMAILADDR,
                             COALESCE(e.EMAILKEY, -3) EMAIL_EMAILKEY,

                             decode(t.TELFAXIND, 'F', t.TELECOMKEY, -3)   FAX_TELECOMKEY,
                             decode(t.TELFAXIND, 'F', t.TELECOMNUM, null) FAX_TELECOMNUM,

                             decode(t.TELFAXIND, 
                             'T', t.TELECOMKEY,
                             'P', t.TELECOMKEY, -3)                PHONE_TELECOMKEY,
                             decode(t.TELFAXIND, 
                             'T', t.TELECOMNUM,
                             'P', t.TELECOMNUM, null)              PHONE_TELECOMNUM,
                              decode(t.TELFAXIND, 'A', t.TELECOMKEY, -3)   ALTERN_TELECOMKEY,
                             decode(t.TELFAXIND, 'A', t.TELECOMNUM, null) ALTERN_TELECOMNUM,

                             c.ptntkey ptnt_ptntkey

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
                       where c.ptntkey = ?) p_c_vw
                 ON ptnt.ptntkey = p_c_vw.ptnt_ptntkey
  

where   	ptnt.ptntKey = ?  	

           