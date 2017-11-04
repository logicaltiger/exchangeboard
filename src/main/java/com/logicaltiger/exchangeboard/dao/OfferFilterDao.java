/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface OfferFilterDao {

	public void setEntityManager(EntityManager entityManager);

	/**
     * Retrieves all filter IDs for an offer.
     *
     * @param offerId The offer for which the org/filter combos exist.
     * @return A list, possibly containing zero elements, of filter ID values.
     */
    public List<Long> getFilterIds(Long offerId);

    /**
     * Replace all existing offer/filter combos built from these values.
     *
     * If the filterIds parameter is null, or is an empty list, then
     * the existing combos are deleted and no new ones are created.
     * 
     * @param offerId The ID for the controlling offer.
     * @param filterIds The IDs of the filters to associate with the offer.
     */
    public void updateOfferFilters(Long offerId, List<Long> filterIds);

}
