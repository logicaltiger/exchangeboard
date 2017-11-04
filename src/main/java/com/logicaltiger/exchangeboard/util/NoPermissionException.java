/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.util;

public class NoPermissionException extends RuntimeException {

	private static final long serialVersionUID = 7217171162703482885L;

	public NoPermissionException() {
		super();
	}
	
	public NoPermissionException(String message) {
		super(message);
	}
	
}
