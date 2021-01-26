package ca.sdm.cdr.common.util;

import java.util.EnumSet;

public class FindEnum {

	
	 public static <E extends Enum<E>> boolean contains(Class<E> _enumClass,
		      String value) {
		    try {
		      return EnumSet.allOf(_enumClass)
		          .contains(Enum.valueOf(_enumClass, value));
		    } catch (Exception e) {
		      return false;
		    }
		  }
}
