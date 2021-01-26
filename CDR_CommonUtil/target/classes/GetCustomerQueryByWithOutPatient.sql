select 		distinct	c.custkey,
				null as storenum,
				null as patientID,
				null as CareReceiverIndicator,
 				null as assoctyp,
                          c.custid,
                          c.userid,
                          c.REQSRCDESC           REQUEST_SOURCE,
                          c.OPTNUM     OPTIMUM_NUMBER,
                          f.LANGPREF   LANGUAGE_PREFERENCE,
                          c.CRTTIMESTAMP,
                          c.usertyp    userType,
                          c.STATUS,
                                                p.FRSTNM     FIRST_NAME,
                          p.LSTNM      LAST_NAME,
                          c.prsnkey,
                          null as  ptntkey,
                          p.DTOFBIRTH,
                          p.DTOFDECEASE,
                          c.PHARMACYCHNLTYPKEY,
                          c.COMMMODETYPKEY,
                          c.ENGMNTTYPKEY,
                          c.REGREMID          REGISTRATION_REMINDER_ID,
                          C.CRTTIMESTAMP      PHAR_DIG_REC_CRE_DT,
                          TO_CHAR(C.CRTTIMESTAMP,'HH24MISS')      PHAR_DIG_REC_CRE_TMS,
                          c.DEACTIVATIONRSN   DEACTIVATION_REASON,
                          c.DETTAILNT         DETAILED_NOTIFICATION,
                          f.NTFSMS            SMS_NOTIFICATION,
                          f.NTFEML            EMAIL_NOTIFICATION,
                          f.FILLNTFEML        EMAIL_FILL_NOTIFICATION,
                          f.PICKUPNTFEML      EMAIL_PICKUP_NOTIFICATION,
                          f.MRKTEML           MARKETING_EMAIL,
                          f.FILLNTFSMS        SMS_FILL_NOTIFICATION,
                          f.PICKUPNTFSMS      SMS_PICKUP_NOTIFICATION,
                          cust_addr.TELECOMNUM_A as PHONE_TELECOMNUM,
                          cust_addr.EMAILADDR_A as EMAIL_EMAILADDR
                          

                     from cust     c,
                          prsn     p,
                          prsnpref f
                             left outer join (select cm.prsnkey,
                                                     MAX(t.TELECOMNUM) TELECOMNUM_A,
                                                     MAX(e.EMAILADDR)  EMAILADDR_A
                                                from cntctmthd  cm
                                                        left outer join telecom  t
                                                          on cm.cntctmthdtypcd = 'Telecom'    and
                                                             cm.cntctmthdkey   = t.telecomkey and
                                                             t.TELFAXIND in ('T', 'P', 'A')

                                                        left outer join email    e
                                                          on cm.cntctmthdtypcd = 'Email Address' and
                                                             cm.cntctmthdkey   = e.emailkey

                                                where cm.prsnkey = ?
                                                GROUP BY cm.PRSNKEY) cust_addr

                                               ON f.prsnkey = cust_addr.prsnkey

                   where c.prsnkey  = p.prsnkey
                     and c.prsnkey  = f.prsnkey
                     and c.custkey = ?
                     and c.usertyp = ?
