/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import com.logicaltiger.exchangeboard.model.OfferSearch;

public interface OfferSearchService {

	/**
     * Retrieves all offers matching the provided search criteria.
     *
     * @param offerSearch The object containing the search criteria.
     * @return A populated search results object.
     */
    public OfferSearch getFilteredOffers(OfferSearch offerSearch);

}
