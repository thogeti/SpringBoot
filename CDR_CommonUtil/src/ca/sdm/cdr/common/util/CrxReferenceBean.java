package ca.sdm.cdr.common.util;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.DosageForm;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.Manufacturer;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.RouteOfAdmin;
import com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination.GPI;

import ca.sdm.cdr.common.util.CommonUtil;

//VLAD CRX_REFERENCE_BEAN
public class CrxReferenceBean {
	   protected static Map<String, Map<String, String>> storage = null; // Din, {<ColName, ColValue>}
	   
	   // Below SQL uses naturalDin = ltrim(trim(c.din), '0') 
	   private final static String SQL =  
			 " select ltrim(trim(c.din), '0') DIN, "
		   + "        c.GEN_CODE_CD  GPINUMBER, "
		   + "        c.GPI_CD,         "     
		   + "        c.ROA_DESC     ADMINISTRATIONROUTECODE, "
		   + "        c.DSG_FORM_CD,    "
		   + "        c.DSG_FORM_NM,    "
		   + "        c.DSG_FORM_NM_FR, "
	       + "        c.MFR_CD       VENDOR_CODE,   "
		   + "        c.MFR_NM,         "
		   + "        c.MFR_NM_FR,      "
		   + "        s.CDDESCR      DRGSCHDDESCR "
		   + "   from crx_reference c "
		   + "            left outer join DRGSCHDLTYP s "
		   + "                    on (c.SCHDL_PROV_CD = s.SHORTCD) "
		   + "  where ltrim(trim(c.din), '0') in (select distinct ltrim(trim(d.din), '0') din "
           + "                                           from rx  r,                "
		   + "                                                drg d                 "
		   + "                                          where r.ptntkey = ?         "
		   + "                                            and r.drgkey  = d.drgkey) "
		   + "    and c.provkey = ?";
	   
	   
       public CrxReferenceBean(Connection connection, Long ptntKey, Long provKey ) throws SQLException {
    	      ResultSet rs = null;
    	      PreparedStatement ps = null;
    	      ResultSetMetaData md = null;
              Map<String, String> row = null;  // <ColName, ColValue>
              
    	      try {
	              ps = connection.prepareStatement(SQL);
	              storage = new HashMap<String, Map<String, String>>();
   
    	    	  CommonUtil.setPsLongParam(ps, 1, ptntKey);
    	    	  CommonUtil.setPsLongParam(ps, 2, provKey);
    	    	  rs = ps.executeQuery();
    	    	  md = rs.getMetaData();
    	    	  int colCount = md.getColumnCount();
    	    	  while (rs.next())  {
    	    		  row = new HashMap<String, String>(); // <ColName, ColValue>
	 				  for (int i = 1; i <= colCount; ++i) {
					  	  row.put(md.getColumnName(i), rs.getString(i));					  
//					      if(logger.isDebugEnabled()) {logger.debug(i + "  ColumnName = " + md.getColumnName(i) + "  " + md.getColumnTypeName(i) + "  value :" + rs.getString(i));}
					  }
	 				  storage.put(rs.getString("DIN"), row);
    	    	  }
	  

    	      } catch (SQLException e) {
  	                   e.printStackTrace();
    	      } finally {
    	    	  CommonUtil.closePreparedStatementResultSet(ps, rs);
    		  }

       }

       
       public void getCrxReference(String din, Object searchResult) throws SQLException, IOException {
    	      String naturalDin = null;
    	   
    	      naturalDin = din.trim();  // removed leading and trailing spaces
    	      naturalDin = naturalDin.replaceFirst("^0+(?!$)", "");  // removed leading zeros
    	      Map<String, String> row = null;   // <ColName, ColValue>
   	    	  row = storage.get(naturalDin); 
   	    	  if ( row != null && storage.size() > 0) {
   	    		 castSearchResult(naturalDin, searchResult);
   	    	  } /*else {
          	     throw new IOException("Row not found for din = " + naturalDin);
   	    	  }*/
       }
       
       public String  getAdminRouteCode(String din, String roa) {
    	   String naturalDin = null;
    	   
 	      naturalDin = din.trim();  // removed leading and trailing spaces
 	      naturalDin = naturalDin.replaceFirst("^0+(?!$)", "");  // removed leading zeros
    	   Map<String, String> row = null;   // <ColName, ColValue>
    	   row = storage.get(naturalDin);
    	   if(row != null) {
    		   
    		   return row.get("ADMINISTRATIONROUTECODE");
    		   
    	   }
    	   return null;
       }
       
       public String getSchedule(String din,String schedule) {
    	   String naturalDin = null;
    	   
  	      naturalDin = din.trim();  // removed leading and trailing spaces
  	      naturalDin = naturalDin.replaceFirst("^0+(?!$)", "");  // removed leading zeros
     	   Map<String, String> row = null;   // <ColName, ColValue>
     	   row = storage.get(naturalDin);
     	  if(row != null) {
   		   
   		   return row.get("DRGSCHDDESCR");
   		   
   	   }
   	   return null;
    	   
       }
       
       
       protected void castSearchResult(String naturalDin, Object searchResult) throws IOException {
    	      boolean expectedObject = false;
    	      Map<String, String> row = null;   // <ColName, ColValue>
    	      
    	      row = storage.get(naturalDin);
    	      if (searchResult instanceof Manufacturer) {
    	    	  if ( row != null) {
    	    		 ((Manufacturer)searchResult).setVendorCode(row.get("VENDOR_CODE"));
    	    	     ((Manufacturer)searchResult).setManufacturerNameEnglish(row.get("MFR_NM"));
    	    	     ((Manufacturer)searchResult).setManufacturerNameFrench(row.get("MFR_NM_FR"));
    	    	     expectedObject = true;
    	    	  } 
              }
    	      
    	      if (searchResult instanceof DosageForm) {
    	    	  if ( row != null) {
    	    		 ((DosageForm)searchResult).setShortName(row.get("DSG_FORM_CD"));
    	    	     ((DosageForm)searchResult).setFullName(row.get("DSG_FORM_NM"));
    	    	     ((DosageForm)searchResult).setFullNameFrench(row.get("DSG_FORM_NM_FR"));
    	    	     expectedObject = true;
    	    	  } 
              }
    	      
    	      
    	      if (searchResult instanceof GPI) {
    	    	  if ( row != null) {
    	    		 ((GPI)searchResult).setGPINumber(row.get("GPINUMBER"));
    	    	     ((GPI)searchResult).setDescriptionEnglish(row.get("GPI_CD"));
    	    	     expectedObject = true;
    	    	  } 
              }
    	      
    	    //  if (searchResult instanceof RouteOfAdmin) {
    	      if (searchResult instanceof String) {
    	    	  if ( row != null) {
    	    		  String routeOfADminCode = row.get("ADMINISTRATIONROUTECODE");
    	    		  char[] roa = routeOfADminCode.toCharArray();
    	    	//	  ((RouteOfAdmin)searchResult).routeO;
    	    		  searchResult= ((String) searchResult).copyValueOf(roa);
    	    		  expectedObject = true;
    	    	  } 
              }
    	     /* 
    	      if (! expectedObject) {
    	    	  throw new IOException("Unexpected Object found or given Object is empty");
              }*/
       }

       
      
       
       
}
       
