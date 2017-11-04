/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.util;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static final String DEFAULT_STATE = "IL";
    public static final Long INVALID_ID = Long.valueOf(-1L);
    public static final String PREFERRED_EMAIL = "E";
    public static final String PREFERRED_PHONE = "P";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
	
    public static boolean isEmptyString(String source) {
        return (source == null || source.trim().length() == 0);
    }

    public static String setNotNullTrim(String source) {
       return (isEmptyString(source)) ? "" : source.trim();
    }

    public static <T> List<T> toList(Iterable<T> source) {
    	List<T> target = new ArrayList<>();
    	source.forEach(target::add);
    	return target;
    }

    /**
     * Convenience method to compare IDs.
     *
     * IDs are stored as Long.  The resulting comparisons are messy in code.
     *
     * @param firstId One ID to compare.
     * @param secondId Another ID to compare.
     * @return TRUE if equal in numeric value;  otherwise, FALSE.
     */
    public static boolean equalIds(Long firstId, Long secondId) {

        if(firstId == null || secondId == null) {
            return false;
        }

        return firstId.longValue() == secondId.longValue();
    }

}
