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

import com.logicaltiger.exchangeboard.model.Filter;

@RepositoryRestResource(exported=false)
public interface FilterRepository extends CrudRepository<Filter, Long> {
	
	@Query("from Filter f where f.type = 'F' order by f.text")
	public Iterable<Filter> findFavors();

	@Query("from Filter f where f.type = 'T' order by f.text")
	public Iterable<Filter> findTraits();

}
