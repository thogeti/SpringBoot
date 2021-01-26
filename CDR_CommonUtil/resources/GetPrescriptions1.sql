select tmp_result.storenum,
       tmp_result.RX_RXKEY,
       tmp_result.RX_RXNUM,
       tmp_result.prscbrkey,

       tmp_result.RX_LINKEDRXKEY,
       tmp_result.RX_ADMINSTARTDATE, 
       tmp_result.RX_ADMINSTOPDATE, 
       tmp_result.RX_ISATHRTV,
       tmp_result.RX_BATCHFLG,
       tmp_result.RX_RXEXPRTNDT,
       tmp_result.RX_RMNGQTY,
       tmp_result.RXSTAT_FILLSTATEFFDT,
       tmp_result.RXSTAT_FILLSTATKEY,
       tmp_result.RXSTAT_SUBFILLSTATKEY,

       tmp_result.RX_DRGKEY,
       tmp_result.PRESCR_DRUG_ENGLISH,
       tmp_result.PRESCR_DRUG_FRENCH,

       tmp_result.RX_CMPNDKEY,
       tmp_result.RX_CMPNDID,
       tmp_result.RX_CMPND_ENG,

       tmp_result.rx_pack_cnsmrid,
       tmp_result.RX_PACKKEY, 
       tmp_result.RX_PACK_DRGKEY,

       tmp_result.txkey,
       tmp_result.TX_TXNNUM,
       tmp_result.TX_TXTIME,
       tmp_result.TX_DAYSINTRVL,
       tmp_result.TX_DAYSSPLY,
       tmp_result.TX_PCKUPTIME,
       tmp_result.TX_LASTPICKUPTIME,
       tmp_result.TX_QTYTXD,
       tmp_result.TX_SIGCD,
       tmp_result.TX_SIGDESCRPTNTLANG,
       tmp_result.TX_PKGFRMTYPKEY,
       tmp_result.TX_PHARMACYCHNLTYPKEY,
       tmp_result.TX_ENGMNTTYPKEY,
       tmp_result.TX_COMMMODETYPKEY,
       tmp_result.TXSTAT_TXFILLSTATTYPKEY,
       tmp_result.TX_PACKKEY, 
       tmp_result.GTIN


  from (select tmp_rx.storenum,
               tmp_rx.rxkey              RX_RXKEY,
               tmp_rx.rxnum              RX_RXNUM,
               tmp_rx.prscbrkey,

               tmp_rx.RX_LINKEDRXKEY,
               tmp_rx.RX_ADMINSTARTDATE, 
               tmp_rx.RX_ADMINSTOPDATE, 
               tmp_rx.RX_ISATHRTV,
               tmp_rx.RX_BATCHFLG,
               tmp_rx.RX_RXEXPRTNDT,
               tmp_rx.RX_RMNGQTY,
               tmp_rx.RXSTAT_FILLSTATEFFDT,
               tmp_rx.RXSTAT_FILLSTATKEY,
               tmp_rx.RXSTAT_SUBFILLSTATKEY,

               tmp_rx.RX_DRGKEY,
               tmp_rx.PRESCR_DRUG_ENGLISH,
               tmp_rx.PRESCR_DRUG_FRENCH,

               tmp_rx.RX_CMPNDKEY,
               tmp_rx.RX_CMPNDID,
               tmp_rx.RX_CMPND_ENG,

               tmp_rx.cnsmrid            rx_pack_cnsmrid,
               tmp_rx.RX_PACKKEY, 
               tmp_rx.RX_PACK_DRGKEY,

               tmp_tx.txkey,
               coalesce(tmp_tx.TX_TXNNUM, '0') TX_TXNNUM,
               tmp_tx.TX_TXTIME,
               tmp_tx.TX_DAYSINTRVL,
	       tmp_tx.TX_DAYSSPLY,
               tmp_tx.TX_PCKUPTIME,
               tmp_tx.TX_LASTPICKUPTIME,
               tmp_tx.TX_QTYTXD,
	       tmp_tx.TX_SIGCD,
	       tmp_tx.TX_SIGDESCRPTNTLANG,
               tmp_tx.TX_PKGFRMTYPKEY,
	       tmp_tx.TX_PHARMACYCHNLTYPKEY,
	       tmp_tx.TX_ENGMNTTYPKEY,
	       tmp_tx.TX_COMMMODETYPKEY,
               tmp_tx.TXSTAT_TXFILLSTATTYPKEY,
               tmp_tx.TX_PACKKEY, 
               coalesce(tmp_rx.rx_pack_gtin, tmp_tx.tx_pack_gtin) gtin

          from (select /*+FIRST_ROWS*/
                       r.storenum,
                       r.prscbrkey,
                       r.rxkey,
                       r.rxnum,

                       coalesce(r.linkedrxkey, 0)  RX_LINKEDRXKEY,
                       r.adminstartdate            RX_ADMINSTARTDATE, 
                       r.adminstopdate             RX_ADMINSTOPDATE, 
                       r.isathrtv                  RX_ISATHRTV,
                       r.batchflg                  RX_BATCHFLG,
                       r.rxexprtndt                RX_RXEXPRTNDT,
                       r.rmngqty                   RX_RMNGQTY,
                       s.FILLSTATEFFDT             RXSTAT_FILLSTATEFFDT,
                       s.FILLSTATKEY               RXSTAT_FILLSTATKEY,
                       s.SUBFILLSTATKEY            RXSTAT_SUBFILLSTATKEY,

                       p.cnsmrid,
                       r.drgkey                    RX_DRGKEY,
                       rd.chmcllblnmeng            PRESCR_DRUG_ENGLISH,
                       rd.chmcllblnmfr             PRESCR_DRUG_FRENCH,

                       r.cmpndkey                  RX_CMPNDKEY,
                       c.cmpndid                   RX_CMPNDID,
                       c.nmeng                     RX_CMPND_ENG,

                       r.packkey                   RX_PACKKEY, 
                       p.drgkey                    RX_PACK_DRGKEY,
                       ltrim(p.gtin,'0')           rx_pack_gtin

                  from rx     r,
                       rxstat s,
                       pack   p,
                       cmpnd  c,
                       drg    rd 
                 where r.rxkey in (select n.notificationkey rxkey from tmp_eligibility n) 
                   and r.rxkey = s.rxkey
                   and r.packkey = p.packkey(+) 
                   and to_char(r.updttimestamp, 'YYYY-MM-DD') >= ?
                   and r.cmpndkey= c.cmpndkey(+)
                   and r.drgkey  = rd.drgkey(+)  ) tmp_rx,

     
               (select /*+FIRST_ROWS*/
                       t.rxkey,
                       t.txkey,
                       t.txnnum                TX_TXNNUM,
                       t.txtime                TX_TXTIME,
		       t.daysintrvl            TX_DAYSINTRVL,
	    	       t.dayssply              TX_DAYSSPLY,
                       t.pckuptime             TX_PCKUPTIME,
                       t.lastpickuptime        TX_LASTPICKUPTIME,
                       t.qtytxd                TX_QTYTXD,
		       t.sigcd                 TX_SIGCD,
		       t.sigdescrptntlang      TX_SIGDESCRPTNTLANG,
                       t.pkgfrmtypkey          TX_PKGFRMTYPKEY,
		       t.pharmacychnltypkey    TX_PHARMACYCHNLTYPKEY,
		       t.engmnttypkey          TX_ENGMNTTYPKEY,
		       t.commmodetypkey        TX_COMMMODETYPKEY,
                       s.txfillstattypkey      TXSTAT_TXFILLSTATTYPKEY,

                       t.packkey               TX_PACKKEY, 
                       ltrim(p.gtin,'0')       tx_pack_gtin

                  from tx     t,
                       txstat s,
                       cmpnd  c,
                       pack   p
                 where t.txkey in (select n.key2 txkey from tmp_eligibility n)
                   and t.txkey    = s.txkey 
                   and t.cmpndkey = c.cmpndkey(+)
                   and to_char(t.lstupdt, 'YYYY-MM-DD') >= ?
                   and t.packkey  = p.packkey(+) ) tmp_tx

            where tmp_rx.rxkey = tmp_tx.rxkey(+) ) tmp_result
  order by tmp_result.RX_RXNUM, tmp_result.TX_TXNNUM desc
