/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logicaltiger.exchangeboard.model.Org;
import com.logicaltiger.exchangeboard.model.User;
import com.logicaltiger.exchangeboard.util.NoPermissionException;
import com.logicaltiger.exchangeboard.dao.OrgDao;
import com.logicaltiger.exchangeboard.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private OrgDao orgDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private PermissionService permService;

	@Transactional(readOnly = true)
    public List<User> getUsers() throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return userDao.getUsers();
    }
    
    @Transactional(readOnly = true)
    public Optional<User> get(Long id) throws NoPermissionException {
    	permService.hasPermission(id, PermissionService.PERM_ADMIN, PermissionService.PERM_USER);
    	return userDao.get(id);
    }

    @Transactional
    public Optional<User> add(User user) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return userDao.add(user);
    }

    @Transactional
    public Optional<User> edit(User user) throws NoPermissionException {
    	permService.hasPermission(user.getId(), PermissionService.PERM_ADMIN, PermissionService.PERM_USER);
    	User authUser = permService.getAuthorizedUser();
    	
    	if(!authUser.isAdmin()) {
    		user.setOrgId(authUser.getOrgId());
    		user.setOrgProvider(authUser.isOrgProvider());
    	}
    	
    	return userDao.edit(user);
    }

    @Transactional
    public Optional<User> editAssist(Long orgId) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	User authUser = permService.getAuthorizedUser();
    	
    	/*
    	 * Global note:
    	 * If the E object of Optional<E> isn't "present" then a NoSuchElementException is thrown.
    	 * Since this is a child of RuntimeException the transaction is rolled back. 
    	 */
    	Optional<Org> oOrg = orgDao.get(orgId);
    	Org org = oOrg.get();
    	authUser.setOrgId(org.getId());
    	authUser.setOrgProvider(org.isProvider());
    	
    	return userDao.edit(authUser);
    }

}
