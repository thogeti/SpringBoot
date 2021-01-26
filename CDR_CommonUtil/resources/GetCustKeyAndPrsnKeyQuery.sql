select c.custkey,
       c.prsnkey
        from ptnt        p,
       cust        c, 
       CDRPTNTMPNG m
            

 where p.storenum = ?
   and p.cnsmrid  = ?  
   and p.ptntkey  = m.ptntkey 
   and m.custkey  = c.custkey