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

import com.logicaltiger.exchangeboard.dao.FilterDao;
import com.logicaltiger.exchangeboard.model.Filter;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@Service("filterService")
public class FilterServiceImpl implements FilterService {

	@Autowired
	private FilterDao filterDao;

	@Autowired 
	private PermissionService permService;
	
    @Transactional(readOnly = true)
    public List<Filter> getFilters() {
    	return filterDao.getFilters();
    }
    
    @Transactional(readOnly = true)
    public List<Filter> getFavors() {
    	return filterDao.getFavors();
    }
    
    @Transactional(readOnly = true)
    public List<Filter> getTraits() {
    	return filterDao.getTraits();
    }
    
    @Transactional(readOnly = true)
    public Optional<Filter> get(Long id) {
    	return filterDao.get(id);
    }

    @Transactional
    public Optional<Filter> add(Filter filter) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return filterDao.add(filter);
    }

    @Transactional
    public Optional<Filter> edit(Filter filter) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN);
    	return filterDao.edit(filter);
    }
    
}
