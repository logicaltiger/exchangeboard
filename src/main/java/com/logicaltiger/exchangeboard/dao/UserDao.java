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

import com.logicaltiger.exchangeboard.model.User;

public interface UserDao {

    public void setEntityManager(EntityManager entityManager);

    /**
     * Retrieves all users, in alphabetic (lastname, firstname) order.
     *
     * @return A list, possibly containing zero elements, of users.
     */
    public List<User> getUsers();

    /**
     * Retrieves a single user by its ID value. 
     *
     * @param id Identifies the user to fetch.
     * @return If the ID points at an object, returns that object.
     */
    public Optional<User> get(Long id);

    /**
     * Retrieves a single user by its username value. 
     *
     * @param username Identifies the login of the user to fetch.
     * @return If the username points at an object, returns that object.
     */
    public Optional<User> getByUserName(String name);

    /**
     * Insert this user into the table.
     *
     * @param user The user to save.
     * @return If user is non-null return the user passed in, 
     * possibly adjusted by the database (added ID, etc.).
     */
    public Optional<User> add(User user);

    /**
     * Store the values of "user" into the table.
     *
     * @param user The user to save.
     * @return If user is non-null return the user passed in,
     * possibly adjusted by the database.
     */
    public Optional<User> edit(User user);

}
