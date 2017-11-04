/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.logicaltiger.exchangeboard.model.Org;

@RepositoryRestResource(exported=false)
public interface OrgRepository extends CrudRepository<Org, Long> {

	@Override
	@Query("from Org o order by o.name")
	public Iterable<Org> findAll();

	@Query("from Org o where o.provider != 0 order by o.name")
	public Iterable<Org> findProviders();

}
