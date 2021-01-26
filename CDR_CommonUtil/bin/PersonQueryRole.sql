select PRSNRL_ISACTFLG,
       PRSNRL_PRSNRLTYPKEY,
       PRSNRL_PRSCBRTYPKEY,
       PRSNRL_PROVKEY,
       PRSNRL_PRSNKEY,
	
       PRSN_CNSMRID,
       PRSN_PRDCRID,
       PRSN_FRSTNM,
       PRSN_LSTNM,
       PRSN_STORENUM,

       EMAIL_EMAILKEY,
       EMAIL_EMAILADDR,

       PHONE_TELECOMKEY,  
       PHONE_TELECOMNUM,

       FAX_TELECOMKEY, 
       FAX_TELECOMNUM,

       ADDR_ADDRLNONE,
       ADDR_ADDRLNTWO,
       ADDR_CITYNM,
       ADDR_PROVCD, 
       ADDR_CNTRYCD,  
       ADDR_POSTALCD,

       CM_PRSNKEY,
       CM_CNSMRID,
       
       NULL AS PRSNRL_USRNM,
       NULL AS PRSN_MDLNM,
       NULL AS ALTERN_TELECOMNUM,
       NULL AS ADDR_TYPE,
       NULL AS ACTVFLG ,
       NULL AS MAILFLG,
       NULL AS PRIMARYFLG,
       NULL AS REAUTHEMAILFLAG,
       NULL AS REAUTHFAXFLAG,
       NULL AS REAUTHPHONEFLAG,
       NULL AS REAUTHVISITFLAG
       
       
       

  from (select r.ISACTFLG     PRSNRL_ISACTFLG,
               r.PRSNRLTYPKEY PRSNRL_PRSNRLTYPKEY,
               r.PRSCBRTYPKEY PRSNRL_PRSCBRTYPKEY,
               r.PROVKEY      PRSNRL_PROVKEY,
               r.PRSNKEY      PRSNRL_PRSNKEY,
				p.PRDCRID	  PRSN_PRDCRID,
               p.CNSMRID      PRSN_CNSMRID,
               p.FRSTNM       PRSN_FRSTNM,
               p.LSTNM        PRSN_LSTNM,
               p.STORENUM     PRSN_STORENUM
               
          from prsnrl r,
               prsn   p
         where r.PRSNRLKEY = ?  ----> rx.PRSCRBRKEY in CDR pre prod
           and p.PRSNKEY = r.PRSNKEY) tmp_prscr

         left outer join

       (select c.PRSNKEY CM_PRSNKEY,
               c.CNSMRID CM_CNSMRID,

               COALESCE(e.EMAILKEY, -3)  EMAIL_EMAILKEY,
               e.EMAILADDR               EMAIL_EMAILADDR,                                     

               decode(t.TELFAXIND, 
                      'T', t.TELECOMKEY,
                      'P', t.TELECOMKEY, -3)                PHONE_TELECOMKEY,
               decode(t.TELFAXIND, 
                      'T', t.TELECOMNUM,
                      'P', t.TELECOMNUM, null)              PHONE_TELECOMNUM,

               decode(t.TELFAXIND, 'A', t.TELECOMKEY, -3)   ALTERN_TELECOMKEY,
               decode(t.TELFAXIND, 'A', t.TELECOMNUM, null) ALTERN_TELECOMNUM,

               decode(t.TELFAXIND, 'F', t.TELECOMKEY, -3)   FAX_TELECOMKEY,
               decode(t.TELFAXIND, 'F', t.TELECOMNUM, null) FAX_TELECOMNUM,

               COALESCE(a.addrkey, -3) ADDR_ADDRKEY, 
               a.ADDRLNONE             ADDR_ADDRLNONE,
               a.ADDRLNTWO             ADDR_ADDRLNTWO,
               a.CITYNM                ADDR_CITYNM,
               a.PROVCD                ADDR_PROVCD,
               a.CNTRYCD               ADDR_CNTRYCD,
               a.POSTALCD              ADDR_POSTALCD

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
          where c.PRSNKEY = ?) tmp_addr

            on tmp_prscr.PRSNRL_PRSNKEY = tmp_addr.CM_PRSNKEY