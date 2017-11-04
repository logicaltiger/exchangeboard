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

import com.logicaltiger.exchangeboard.model.Org;

public interface OrgDao {

	public void setEntityManager(EntityManager entityManager);

    /**
     * Retrieves all organizations, in alphabetic order of the organization name.
     *
     * @return A list, possibly containing zero elements, of organizations.
     */
    public List<Org> getOrgs();

    /**
     * Retrieves all provider organizations, in alphabetic order of the organization name.
     *
     * @return A list, possibly containing zero elements, of provider organizations.
     */
    public List<Org> getProviders();

    /**
     * Retrieves a single organization by its ID value. 
     *
     * @param id Identifies the organization to fetch.
     * @return If the ID points at an object, returns that object.
     */
    public Optional<Org> get(Long id);

    /**
     * Insert this organization into the table.
     *
     * @param org The organization to save.
     * @return If org is non-null return the organization passed in, 
     * possibly adjusted by the database (added ID, etc.).
     */
    public Optional<Org> add(Org org);

    /**
     * Store the values of "org" into the table.
     *
     * @param org The organization to save.
     * @return If org is non-null return the organization passed in,
     * possibly adjusted by the database.
     */
    public Optional<Org> edit(Org org);

}
