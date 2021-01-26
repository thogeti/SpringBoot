                     ) tmp_ptnt
                     LEFT OUTER JOIN ptntid ptntid ON tmp_ptnt.ptntkey = ptntid.ptntkey
                        left outer join
                    (select c.ptntkey,
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
                                       c.cntctmthdkey   = e.emailkey) tmp_addr

   on tmp_ptnt.ptntkey = tmp_addr.ptntkey
order by tmp_ptnt.PTNTKEY ,
 ptntid.ptntidkey desc
