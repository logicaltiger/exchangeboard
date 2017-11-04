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

import com.logicaltiger.exchangeboard.model.Org;
import com.logicaltiger.exchangeboard.repository.OrgRepository;
import com.logicaltiger.exchangeboard.util.Utilities;

@Repository("orgDao")
public class OrgDaoImpl implements OrgDao {

	@Autowired
	private OrgRepository orgRepository;
	
    public void setEntityManager(EntityManager entityManager) {
    }

    public List<Org> getOrgs() {
    	return Utilities.toList(orgRepository.findAll());
    }

    public List<Org> getProviders() {
    	return Utilities.toList(orgRepository.findProviders());
    }

    public Optional<Org> get(Long id) {
    	Org org = orgRepository.findOne(id);
        return (org == null) ? Optional.empty() : Optional.ofNullable(org);
    }

    public Optional<Org> add(Org org) {
    	Org newOrg = orgRepository.save(org);
        return (newOrg == null) ? Optional.empty() : Optional.ofNullable(newOrg);
    }

    public Optional<Org> edit(Org org) {
    	// The repository handles both add and edit.
    	return add(org);
    }

}
