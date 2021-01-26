package ca.sdm.cdr.jdbc.api.util;

public class StringUtil {

	
	public static String emptyIfNull(String srcStr) {
		return (srcStr!=null && srcStr.trim().length() > 0) ? srcStr : "";
	}
	
	public static boolean isEmpty(String srcStr) {
		return (srcStr!=null && srcStr.trim().length() > 0) ? false : true;
	}	
	
}
