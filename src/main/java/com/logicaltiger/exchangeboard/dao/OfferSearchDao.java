/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.logicaltiger.exchangeboard.model.Offer;
import com.logicaltiger.exchangeboard.model.OfferSearch;

public interface OfferSearchDao {

	public void setEntityManager(EntityManager entityManager);

    /**
     * Retrieves selected offers for event types and a date range.
     * Results are sequenced by date, then title.
     *
     * Fetch offers for all organizations.
     * * It never returns templates.
     * * It never returns closed offers.
     * * If any event types are provided then it filters by them.
     * * If a start date is provided then only offers at or later than that date are kept.
     * * If an end date is provided then only offers at or before that date are kept.
     *
     * @param offerSearch An object containing search criteria.
     * @return A list, possibly containing zero elements, of offers.
     */
    public List<Offer> getFilteredOffers(OfferSearch offerSearch);

}
