/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logicaltiger.exchangeboard.model.Filter;
import com.logicaltiger.exchangeboard.repository.FilterRepository;
import com.logicaltiger.exchangeboard.util.Utilities;

@Repository("filterDao")
public class FilterDaoImpl implements FilterDao {

	@Autowired
	private FilterRepository filterRepository;
	
    public void setEntityManager(EntityManager entityManager) {
    }

    public List<Filter> getFilters() {
    	return Utilities.toList(filterRepository.findAll());
    }

    public List<Filter> getFavors() {
    	return Utilities.toList(filterRepository.findFavors());
    }

    public List<Filter> getTraits() {
    	return Utilities.toList(filterRepository.findTraits());
    	
    }

    public Optional<Filter> get(Long id) {
    	Filter filter = filterRepository.findOne(id);
        return (filter == null) ? Optional.empty() : Optional.ofNullable(filter);
    }

    public Optional<Filter> add(Filter filter) {
    	Filter newFilter = filterRepository.save(filter);
        return (newFilter == null) ? Optional.empty() : Optional.ofNullable(newFilter);
    }

    public Optional<Filter> edit(Filter filter) {
    	// The repository handles both add and edit.
    	return add(filter);
    }

}
