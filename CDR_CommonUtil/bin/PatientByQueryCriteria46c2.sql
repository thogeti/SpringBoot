
                     ) tmp_ptnt

                      LEFT OUTER JOIN ptntid ptntid ON tmp_ptnt.Ptnt_PTNTKEY= ptntid.ptntkey

                      left outer join
                    (select c.ptntkey,
                            c.CNSMRID          CM_CNSMRID,
                            c.cntctmthdtypcd,
                            COALESCE(c.CNTCTPRPSTYPKEY, 20)       CNTCTPRPSTYPKEY,
                            COALESCE(c.CNTCTPRPSTYPCD, 'PRIMARY') CNTCTPRPSTYPCD,

                            e.EMAILADDR                           EMAIL_EMAILADDR,                                     
                            COALESCE(e.EMAILKEY, -3)              EMAIL_EMAILKEY,
                            e.rowid                               EMAIL_ROWID,

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
                              
                               left outer join email    e
                                    on c.cntctmthdtypcd = 'Email Address' and
                                       c.cntctmthdkey   = e.emailkey) tmp_addr

   on tmp_ptnt.Ptnt_PTNTKEY = tmp_addr.ptntkey

  	
order by tmp_ptnt.Ptnt_PTNTKEY,   
         ptntid.ptntidkey desc
			
