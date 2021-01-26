-- The only rows affected by the delete clause of the SQL MERGE statement 
-- are those rows in the target table that are updated by the merge operation.

MERGE INTO NOTIFICATION n USING
               (select distinct
                       'RxData'              EVENTNAME,
                       'Rx'                  ENTITYTYPE,
                       a.ENTITYKEY,
                       a.STORENUM,
                       a.NOTIFICATIONTYPE,
                       a.NOTIFICATIONID,
                       b.SRCESYSCD           SOURCESYSTEM,
                       a.NOTIFICATIONDATE
		  from (select r.RXKEY       ENTITYKEY,
			       r.PTNTKEY,
			       s.srcesyskey,
			       r.STORENUM,
			       r.RXNUM       NOTIFICATIONID,
			       decode(l, 2, 'Rx_AdminStartDate', 'Rx_AdminStopDate')  NOTIFICATIONTYPE,
			       decode(l, 2, r.ADMINSTARTDATE, r.ADMINSTOPDATE)        NOTIFICATIONDATE
			  from ptntsubscrptnsrcesys s,
			       rx                   r,
                               lateral(select level l from dual connect by level <= 2)
			 where r.rxkey = ?
			   and r.ptntkey is not null
			   and r.ptntkey = s.ptntkey
			union
			select r.RXKEY        ENTITYKEY,
			       0              PTNTKEY,
			       s.srcesyskey,
			       r.STORENUM,
		  	       r.RXNUM        NOTIFICATIONID,
			       decode(l, 2, 'Rx_AdminStartDate', 'Rx_AdminStopDate')  NOTIFICATIONTYPE,
			       decode(l, 2, r.ADMINSTARTDATE, r.ADMINSTOPDATE)        NOTIFICATIONDATE
			  from rxsubscrptnsrcesys s,
			       rx                 r,
                               lateral(select level l from dual connect by level <= 2)
			 where r.rxkey = ?
			   and r.rxkey = s.rxkey) a,  srcesys b
                where a.srcesyskey = b.srcesyskey ) t

      ON (n.ENTITYKEY        = t.ENTITYKEY        and -- r.RXKEY
          n.EVENTNAME        = t.EVENTNAME        and -- 'RxData', 'DrugData', 'PrescriberData'
          n.NOTIFICATIONTYPE = t.NOTIFICATIONTYPE and -- 'Rx_AdminStartDate', 'Rx_AdminStopDate', 'Rx'
          n.SOURCESYSTEM     = t.SOURCESYSTEM)        -- 'DigitalRx',  'Accuro'

     WHEN MATCHED THEN
          UPDATE SET n.NOTIFICATIONDATE = t.NOTIFICATIONDATE
          DELETE where t.NOTIFICATIONDATE is null

     WHEN NOT MATCHED THEN
          insert (n.NOTIFICATIONKEY,
                  n.EVENTNAME,
                  n.ENTITYTYPE,
                  n.ENTITYKEY,
                  n.STORENUM,
                  n.NOTIFICATIONTYPE,
                  n.NOTIFICATIONID,
                  n.SourceSystem,
                  n.NOTIFICATIONDATE)
          values (NOTIFICATION_SEQ.nextval,
                  t.EVENTNAME,
                  t.ENTITYTYPE,
                  t.ENTITYKEY,
                  t.STORENUM,
                  t.NOTIFICATIONTYPE,
                  t.NOTIFICATIONID,
                  t.SOURCESYSTEM,
                  t.NOTIFICATIONDATE)
            where t.NOTIFICATIONDATE is not null
              and to_char(t.NOTIFICATIONDATE, 'YYYY-MM-DD') >= to_char(sysdate, 'YYYY-MM-DD')