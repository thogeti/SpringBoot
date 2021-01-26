with
     map_tbl  as (select p.storenum,
                         p.cnsmrid   patientID,
                         p.ptntkey,
                        'N'          CareReceiverIndicator,
                         m.assoctyp,
                         m.custkey,
                         m.VRFCOMMMODETYPKEY,
                          m.VRFENGMNTTYPKEY,
                          m.VRFPHARMACYCHNLTYPKEY
                    from ptnt        p,
                         CDRPTNTMPNG m
                   where p.storenum = ?
                     and p.cnsmrid  = ?
                     and decode(?,'ALL','ALL',m.ASSOCTYP) =?
                     and p.ptntkey  = m.ptntkey ),

     map_cr_tbl  as (SELECT DISTINCT p.storenum,
                                     p.cnsmrid   patientID,
                                     p.ptntkey,
                                    'Y'          CareReceiverIndicator,
                                     m.assoctyp,
                                     m.custkey,
                                     m.VRFCOMMMODETYPKEY,
                          m.VRFENGMNTTYPKEY,
                          m.VRFPHARMACYCHNLTYPKEY
                      from ptnt        p,
                           CDRPTNTMPNG m 
                     WHERE p.ptntkey  = m.ptntkey 
                       and m.assoctyp in ('Caregiver', 'Customer')
                       and m.ASSOCFLAG = 'Y'
                       and m.CUSTKEY IN (select m1.custkey 
                                           from cdrptntmpng m1,
                                                map_tbl     m2
                                          where m1.CARERCVKEY = m2.custkey)),

     total_tab as (select storenum,
                          patientID,
                          ptntkey,
                          CareReceiverIndicator,
                          assoctyp,
                          custkey,
                          VRFCOMMMODETYPKEY,
                          VRFENGMNTTYPKEY,
                          VRFPHARMACYCHNLTYPKEY
                     from map_tbl
                    union
                    select storenum,
                           patientID,
                           ptntkey,
                           CareReceiverIndicator,
                           assoctyp,
                           custkey,
                          VRFCOMMMODETYPKEY,
                          VRFENGMNTTYPKEY,
                          VRFPHARMACYCHNLTYPKEY
                    from map_cr_tbl),                    

     prsn_tbl as (select c.custkey,
                         c.prsnkey,
                         c.usertyp,
                         m.CareReceiverIndicator,
                         m.assoctyp,
                         m.ptntkey,
                         m.storenum,
                         m.patientID,
                          m.VRFCOMMMODETYPKEY,
                          m.VRFENGMNTTYPKEY,
                          m.VRFPHARMACYCHNLTYPKEY
                   from cust      c,
                        total_tab m
                  where c.custkey = m.custkey),

      res_tbl  as (select distinct c.custkey,
                          c.custid,
                          c.userid,
                          c.OPTNUM     OPTIMUM_NUMBER,
                          f.LANGPREF   LANGUAGE_PREFERENCE,
                          c.CRTTIMESTAMP,
                          t.usertyp    userType,
                          c.STATUS,
                       -- p.storenum,
                          p.FRSTNM     FIRST_NAME,
                          p.LSTNM      LAST_NAME,
                          c.REQSRCDESC,
                          c.prsnkey,
                          t.ptntkey,
                          p.DTOFBIRTH,
                          p.DTOFDECEASE,
                          c.PHARMACYCHNLTYPKEY,
                          c.COMMMODETYPKEY,
                          c.ENGMNTTYPKEY,
                          t.VRFCOMMMODETYPKEY,
                          t.VRFENGMNTTYPKEY,
                          t.VRFPHARMACYCHNLTYPKEY,
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
                          cust_addr.TELECOMNUM_A,
                          cust_addr.EMAILADDR_A,
                          t.CareReceiverIndicator,
                          t.assoctyp,
                          t.storenum,
                          t.patientID

                     from cust     c,
                          prsn     p,
                          prsn_tbl t,
                          prsnpref f
                             left outer join (select c.prsnkey,
                                                     MAX(t.TELECOMNUM) TELECOMNUM_A,
                                                     MAX(e.EMAILADDR)  EMAILADDR_A
                                                from cntctmthd  c
                                                        left outer join telecom  t
                                                          on c.cntctmthdtypcd = 'Telecom'    and
                                                             c.cntctmthdkey   = t.telecomkey and
                                                              t.TELFAXIND in ('T', 'P', 'A')

                                                        left outer join email    e
                                                          on c.cntctmthdtypcd = 'Email Address' and
                                                             c.cntctmthdkey   = e.emailkey

                                                where c.prsnkey in (select prsnkey from prsn_tbl)
                                                GROUP BY C.PRSNKEY) cust_addr

                                               ON f.prsnkey = cust_addr.prsnkey

                   where c.custkey  = t.custkey
                     and c.prsnkey  = p.prsnkey
                     and c.prsnkey  = f.prsnkey)

select r.custkey,
       r.prsnkey,
       r.ptntkey,
       r.storenum,
       r.patientID,
       r.FIRST_NAME,
       r.LAST_NAME,
       r.dtofbirth,
       r.DTOFDECEASE,
       r.custid,
       r.userid,
       r.OPTIMUM_NUMBER,
       r.LANGUAGE_PREFERENCE,                                           
       r.CRTTIMESTAMP,
       r.userType,
       r.STATUS,
		r.REQSRCDESC           REQUEST_SOURCE,
       r.PHARMACYCHNLTYPKEY,   ----> cache table lookup
       r.COMMMODETYPKEY,       ----> cache table lookup
       r.ENGMNTTYPKEY,         ----> cache table lookup
		r.VRFCOMMMODETYPKEY,
        r.VRFENGMNTTYPKEY,
        r.VRFPHARMACYCHNLTYPKEY,
       r.REGISTRATION_REMINDER_ID,
       r.PHAR_DIG_REC_CRE_DT,
       r.PHAR_DIG_REC_CRE_TMS,
       r.DEACTIVATION_REASON,   
     --h.CDDESCR           REQUEST_SOURCE,
       r.DETAILED_NOTIFICATION,
       r.SMS_NOTIFICATION,
       r.EMAIL_NOTIFICATION,
       r.EMAIL_FILL_NOTIFICATION,
       r.EMAIL_PICKUP_NOTIFICATION,
       r.MARKETING_EMAIL,
       r.SMS_FILL_NOTIFICATION,
       r.SMS_PICKUP_NOTIFICATION,
       r.CareReceiverIndicator,
       r.assoctyp,
       r.TELECOMNUM_A   as PHONE_TELECOMNUM,
       r.EMAILADDR_A    as EMAIL_EMAILADDR

  from res_tbl r
