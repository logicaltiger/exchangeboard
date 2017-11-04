/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.logicaltiger.exchangeboard.dao.UserDao;
import com.logicaltiger.exchangeboard.model.User;
import com.logicaltiger.exchangeboard.util.NoPermissionException;
import com.logicaltiger.exchangeboard.util.Utilities;

@Service("permissionService")
public class PermissionService {
	public static final String PERM_ADMIN = "PERM_ADMIN";
	public static final String PERM_ORG = "PERM_ORG";
	public static final String PERM_SERVICE = "PERM_SERVICE";
	public static final String PERM_USER = "PERM_USER";
	
	@Autowired 
	private UserDao userDao;
	
	public void hasPermission(String... tests) throws NoPermissionException {
		hasPermission(null, tests);
	}
	
	public void hasPermission(Long id, String... tests) throws NoPermissionException {
    	User authUser = getAuthorizedUser();
    	
    	if(authUser == null) {
    		throw new NoPermissionException("Failed to find the logged-in User record");
    	}
		
    	for(String test : tests) {
			
    		switch(test) {
    		case PERM_ADMIN:
				
				if(hasAdminRight(authUser)) {
					return;
				}

				break;
    		case PERM_SERVICE:
    			
    			if(hasServiceRight(authUser)) {
    				return;
    			}
    			
    			break;
    		case PERM_ORG:
    			
    			if(hasOrgRight(authUser, id)) {
    				return;
    			}
			
    			break;
    		case PERM_USER:
    			
    			if(hasUserRight(authUser, id)) {
    				return;
    			}
			
    			break;
    		}
    	
    	}
		
    	throw new NoPermissionException("No right to do this action");
	}

	private boolean hasAdminRight(User authUser) {
    	return authUser.isAdmin();
    }

    private boolean hasServiceRight(User authUser) {
    	return !authUser.isOrgProvider();
    }
    
    private boolean hasOrgRight(User authUser, Long orgId) {
    	return orgId != null && authUser.getOrgId().longValue() == orgId.longValue();
    }
    
    private boolean hasUserRight(User authUser, Long userId) {
    	return userId != null && authUser.getId().longValue() == userId.longValue();
    }

    public User getAuthorizedUser() {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	if(Utilities.isEmptyString(username)) {
    		return null;
    	}
    	
    	Optional<User> oUser = userDao.getByUserName(username);
    	return oUser.isPresent() ? oUser.get() : null;
    }
    
}
