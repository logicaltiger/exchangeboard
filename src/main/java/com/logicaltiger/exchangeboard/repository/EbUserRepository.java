/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.logicaltiger.exchangeboard.model.User;

@RepositoryRestResource(exported=false)
public interface EbUserRepository extends CrudRepository<User, Long> {
	
	/*
	 * Returning a List instead of Iterable breaks a pattern for CrudRepository.
	 * It is worth it for the caller because it can evade the fuss of processing an Iterable.
	 */
	public List<User> findByUsername(String name);

}
