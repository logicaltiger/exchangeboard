/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface RestrictedRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

	@Override
	@RestResource(exported = false)
	void delete(ID id);

	@Override
	@RestResource(exported = false)
	void delete(T entity);

	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends T> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends T> S save(S entity);	

	@Override
	@RestResource(exported = false)
	<S extends T> Iterable<S> save(Iterable<S> entities);

}
