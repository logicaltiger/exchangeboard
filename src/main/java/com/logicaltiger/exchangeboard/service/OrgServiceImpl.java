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

import com.logicaltiger.exchangeboard.dao.OrgDao;
import com.logicaltiger.exchangeboard.model.Org;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@Service("orgService")
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgDao dao;
	
	@Autowired 
	private PermissionService permService;
	
    @Transactional(readOnly = true)
    public List<Org> getOrgs() throws NoPermissionException {
    	
    	/*
    	 * Global note:
    	 * hasPermission() throws a NoPermissionException() on any failure.
    	 */
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return dao.getOrgs();
    }
    
    @Transactional(readOnly = true)
    public List<Org> getProviders() throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN, PermissionService.PERM_SERVICE);
    	return dao.getProviders();
    }
    
    @Transactional(readOnly = true)
    public Optional<Org> get(Long id) throws NoPermissionException {
    	permService.hasPermission(id, PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	return dao.get(id);
    }

    @Transactional
    public Optional<Org> add(Org org) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return dao.add(org);
    }

    @Transactional
    public Optional<Org> edit(Org org) throws NoPermissionException {
    	permService.hasPermission(org.getId(), PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	return dao.edit(org);
    }
    
}
