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

import com.logicaltiger.exchangeboard.model.Offer;

public interface OfferDao {

	public void setEntityManager(EntityManager entityManager);

    /**
     * Retrieves all offers, ordered by "relevancy".
     *
     * @return A list, possibly containing zero elements, of offers.
     */
    public List<Offer> getOffers();

	/**
     * Retrieves all offers for an organization, ordered by "relevancy".
     *
     * @param orgId The organization for which the offers exist.
     * @return A list, possibly containing zero elements, of offers.
     */
    public List<Offer> getOrgOffers(Long orgId);

    /**
     * Retrieves a single offer by its ID value. 
     *
     * @param id Identifies the offer to fetch.
     * @return If the ID points at an object, returns that object.
     */
    public Optional<Offer> get(Long id);

    /**
     * Retrieves an organization's template offer by its org ID value. 
     *
     * @param orgId Identifies the organization who's template offer to fetch.
     * @return If the ID points at an object, returns that object.
     */
    public Optional<Offer> getTemplate(Long orgId);

    /**
     * Insert this offer into the table.
     *
     * @param offer The offer to save.
     * @return If offer is non-null return the offer passed in, 
     * possibly adjusted by the database (added ID, etc.).
     */
    public Optional<Offer> add(Offer offer);

    /**
     * Store the values of "offer" into the table.
     *
     * @param offer The offer to save.
     * @return If offer is non-null return the offer passed in,
     * possibly adjusted by the database.
     */
    public Optional<Offer> edit(Offer offer);

}
