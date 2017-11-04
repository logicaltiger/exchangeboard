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

import com.logicaltiger.exchangeboard.model.User;
import com.logicaltiger.exchangeboard.repository.EbUserRepository;
import com.logicaltiger.exchangeboard.util.Utilities;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private EbUserRepository userRepository;
	
    public void setEntityManager(EntityManager entityManager) {
    }

    public List<User> getUsers() {
    	return Utilities.toList(userRepository.findAll());
    }

    public Optional<User> get(Long id) {
    	User user = userRepository.findOne(id);
        return (user == null) ? Optional.empty() : Optional.ofNullable(user);
    }

    public Optional<User> add(User user) {
    	User newUser = userRepository.save(user);
        return (newUser == null) ? Optional.empty() : Optional.ofNullable(newUser);
    }

    public Optional<User> edit(User user) {
    	// The repository handles both add and edit.
    	return add(user);
    }

	@Override
	public Optional<User> getByUserName(String name) {
    	List<User> users = userRepository.findByUsername(name);
   		return (users == null || users.size() == 0) ? Optional.empty() : Optional.ofNullable(users.get(0));
	}

}
