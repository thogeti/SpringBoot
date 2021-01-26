with
   map1_tbl as (select m.CUSTKEY,
                       m.ASSOCTYP,
	               m.ASSOCFLAG,
                       m.VRFCOMMMODETYPKEY,
                       m.VRFENGMNTTYPKEY,
                       m.VRFPHARMACYCHNLTYPKEY,
                       coalesce(m.CARERCVKEY, 0)    CARERCVKEY,
                       0                            tbl_flag,
                       coalesce(m.PTNTKEY, 0)       PTNTKEY,
                       coalesce(p.storenum, '0000') storenum,
                       coalesce(p.cnsmrid,  '0000') patientID

                  from CDRPTNTMPNG m
                           left outer join ptnt p  
                                  on m.ptntkey = p.ptntkey and m.assocflag = 'Y'
                 where m.custkey   = ? ),

   map2_tbl as (select m.CUSTKEY,
                       m.ASSOCTYP,
	               m.ASSOCFLAG,
                       m.VRFCOMMMODETYPKEY,
                       m.VRFENGMNTTYPKEY,
                       m.VRFPHARMACYCHNLTYPKEY,
                       coalesce(m.CARERCVKEY, 0)    CARERCVKEY,
                       1                            tbl_flag,
                       0       PTNTKEY,
                        '0000' storenum,
                       '0000' patientID

                  from CDRPTNTMPNG m
                           
                where m.CARERCVKEY in (select distinct CARERCVKEY from map1_tbl) and m.custkey = ? and m.assocflag = 'Y'),

   cust_tbl as (select c.custkey,
                       c.prsnkey,
                       c.usertyp,
                       tmp_PTNTMP.assoctyp,
                       tmp_PTNTMP.ASSOCFLAG,
                       tmp_PTNTMP.VRFCOMMMODETYPKEY,
                       tmp_PTNTMP.VRFENGMNTTYPKEY,
                       tmp_PTNTMP.VRFPHARMACYCHNLTYPKEY,
                       tmp_PTNTMP.CARERCVKEY,
                       2                            tbl_flag,
                       tmp_PTNTMP.ptntkey,
                       tmp_PTNTMP.storenum,
                       tmp_PTNTMP.patientID

                    from cust c
                          left outer join (select m.custkey,
                                                  m.assoctyp,
                                                  m.ASSOCFLAG,
                                                  m.VRFCOMMMODETYPKEY,
                                                  m.VRFENGMNTTYPKEY,
                                                  m.VRFPHARMACYCHNLTYPKEY,
                                                  coalesce(m.CARERCVKEY, 0)    CARERCVKEY,
                                                  coalesce(p.ptntkey, 0)       ptntkey,
                                                  coalesce(p.storenum, '0000') storenum,
                                                  coalesce(p.cnsmrid,  '0000') patientID

                                             from CDRPTNTMPNG m
                                                   left outer join ptnt p  
                                                           on m.ptntkey = p.ptntkey
                                            where m.custkey in (select custkey from map1_tbl)
                                              and m.ASSOCTYP = 'Customer' and m.ASSOCFLAG = 'Y') tmp_PTNTMP
                                     on c.custkey = tmp_PTNTMP.custkey
                   where c.custkey in (select custkey from map1_tbl)),

   map_tbl  as (select CUSTKEY,
                       PTNTKEY,
                       storenum,
                       patientID,
                    -- CARERCVKEY,
                       ' '         usertyp,
                       ASSOCTYP,
	               ASSOCFLAG,
                       VRFCOMMMODETYPKEY,
                       VRFENGMNTTYPKEY,
                       VRFPHARMACYCHNLTYPKEY,
                       tbl_flag
                  from map1_tbl
                 where ASSOCFLAG in ('Y','N')
               --  and PTNTKEY > 0
                   and ASSOCTYP = 'Caregiver'  ---> don't change this !
                  union
                select CARERCVKEY as CUSTKEY,
                       PTNTKEY,
                       storenum,
                       patientID,
                    -- CARERCVKEY,
                       ' '         usertyp,
                       ASSOCTYP,
	               ASSOCFLAG,
                       VRFCOMMMODETYPKEY,
                       VRFENGMNTTYPKEY,
                       VRFPHARMACYCHNLTYPKEY,
                       tbl_flag
                  from map2_tbl
                 where ASSOCFLAG in ('Y','N')
               --  and PTNTKEY > 0
                   and ASSOCTYP = 'Caregiver'  ---> don't change this !
                  union
                select CUSTKEY,
                       PTNTKEY,
                       storenum,
                       patientID,
                    -- CARERCVKEY,
                       usertyp,
                       ASSOCTYP,
	               ASSOCFLAG,
                       VRFCOMMMODETYPKEY,
                       VRFENGMNTTYPKEY,
                       VRFPHARMACYCHNLTYPKEY,
                       tbl_flag
                  from cust_tbl
                 where ASSOCTYP = 'Customer'  and ASSOCFLAG = 'Y'),  ---> don't change this !

      res_tbl  as (select distinct c.custkey,
                          c.custid,
                          c.userid,
                          c.OPTNUM     OPTIMUM_NUMBER,
                          f.LANGPREF   LANGUAGE_PREFERENCE,
                          c.CRTTIMESTAMP,
                          m.usertyp    userType,
                          c.STATUS,
                          c.REQSRCDESC,
                          p.FRSTNM     FIRST_NAME,
                          p.LSTNM      LAST_NAME,
                          c.prsnkey,
                          m.ptntkey,
                          p.DTOFBIRTH,
                          p.DTOFDECEASE,
                          c.PHARMACYCHNLTYPKEY,
                          c.COMMMODETYPKEY,
                          c.ENGMNTTYPKEY,
                          m.VRFCOMMMODETYPKEY,
                          m.VRFENGMNTTYPKEY,
                          m.VRFPHARMACYCHNLTYPKEY,
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
                          m.assoctyp,
                          m.patientID,
                          m.storenum
                     from cust     c,
                          prsn     p,
                          map_tbl  m,
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

                                                where c.prsnkey in (select prsnkey from cust_tbl)
                                                GROUP BY C.PRSNKEY) cust_addr
                                       ON f.prsnkey = cust_addr.prsnkey
                   where c.custkey  = m.custkey
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
       decode(r.userType,' ','Customer',r.userType) userType,
       r.STATUS,

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
       r.REQSRCDESC           REQUEST_SOURCE,
       r.DETAILED_NOTIFICATION,
       r.SMS_NOTIFICATION,
       r.EMAIL_NOTIFICATION,
       r.EMAIL_FILL_NOTIFICATION,
       r.EMAIL_PICKUP_NOTIFICATION,
       r.MARKETING_EMAIL,
       r.SMS_FILL_NOTIFICATION,
       r.SMS_PICKUP_NOTIFICATION,
       r.assoctyp,
       r.TELECOMNUM_A   as PHONE_TELECOMNUM,
       r.EMAILADDR_A    as EMAIL_EMAILADDR

  from res_tbl r
/* The following block should generate Caregiver not found exception if any: */
 where 1 <= (select count(cdrptntmpngkey)  CARERCVKEY
              from cdrptntmpng m
             where m.custkey  = ?
               and m.ASSOCTYP = 'Caregiver' and m.ASSOCFLAG = 'Y' and (m.CARERCVKEY is not null or m.ptntkey is not null)) order by r.custkey