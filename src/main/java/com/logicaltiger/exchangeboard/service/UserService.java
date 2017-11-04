/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import java.util.List;
import java.util.Optional;

import com.logicaltiger.exchangeboard.model.User;

public interface UserService {

	/**
     * Retrieves all users.
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

    public Optional<User> editAssist(Long orgId);
    
}
