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

import com.logicaltiger.exchangeboard.model.Filter;

public interface FilterDao {

	public void setEntityManager(EntityManager entityManager);

    /**
     * Retrieves all filters, in alphabetic order of the text description.
     *
     * @return A list, possibly containing zero elements, of filters.
     */
    public List<Filter> getFilters();

    /**
     * Retrieves all favor filters, in alphabetic order of the text description.
     *
     * @return A list, possibly containing zero elements, of favor filters.
     */
    public List<Filter> getFavors();

    /**
     * Retrieves all trait filters, in alphabetic order of the text description.
     *
     * @return A list, possibly containing zero elements, of trait filters.
     */
    public List<Filter> getTraits();

    /**
     * Retrieves a single filter by its ID value. 
     *
     * @param id Identifies the filter to fetch.
     * @return If the ID points at an object, returns that object.
     */
    public Optional<Filter> get(Long id);

    /**
     * Insert this filter into the table.
     *
     * @param filter The filter to save.
     * @return If filter is non-null return the filter passed in, 
     * possibly adjusted by the database (added ID, etc.).
     */
    public Optional<Filter> add(Filter filter);

    /**
     * Store the values of "filter" into the table.
     *
     * @param filter The filter to save.
     * @return If filter is non-null return the filter passed in,
     * possibly adjusted by the database.
     */
    public Optional<Filter> edit(Filter filter);

}
