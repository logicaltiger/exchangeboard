/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logicaltiger.exchangeboard.dao.OfferSearchDao;
import com.logicaltiger.exchangeboard.dao.OrgDao;
import com.logicaltiger.exchangeboard.model.Filter;
import com.logicaltiger.exchangeboard.model.Offer;
import com.logicaltiger.exchangeboard.model.OfferSearch;
import com.logicaltiger.exchangeboard.model.Org;
import com.logicaltiger.exchangeboard.util.NoPermissionException;
import com.logicaltiger.exchangeboard.util.Utilities;

@Service("offerSearchService")
public class OfferSearchServiceImpl implements OfferSearchService {

	@Autowired
	private OfferSearchDao offerSearchDao;
	
	@Autowired
	private OrgDao orgDao;

	@Autowired 
	private PermissionService permService;

	@Transactional(readOnly = true)
    public OfferSearch getFilteredOffers(OfferSearch offerSearch) throws NoPermissionException {
    	permService.hasPermission(PermissionService.PERM_ADMIN, PermissionService.PERM_SERVICE);
    	List<Offer> offers = offerSearchDao.getFilteredOffers(offerSearch);
        offers = applyOrgInfo(sortByFilterMatches(applyFilterMatches(offers, offerSearch)));
        offerSearch.setOffers(offers);
        return offerSearch;
    }
    
    /**
     * Apply Org data to each Offer.
     *
     * @param filteredOffers A list of fetched offers.
     *
     * @return The list of filtered offers fed in, but with extra data in them.
     */
    private List<Offer> applyOrgInfo(List<Offer> filteredOffers) {
    	List<Org> orgs = orgDao.getProviders();
    	
        for(Offer offer : filteredOffers) {
        	Org matchingOrg = null;
        	
            for(Org org : orgs) {

        		if(Utilities.equalIds(org.getId(), offer.getOrgId())) {
        			matchingOrg = org;
        			break;
        		}
        	
            }

            if(matchingOrg == null) {
            	matchingOrg = new Org();
            }
            
            offer.setOrg(matchingOrg);
        }

        return filteredOffers;
    }

    /**
     * Order the offer list by descending quantity of filter matches.
     *
     * The "filteredOffers" list is already sorted by start date,
     * courtesy of the HQL query.  Now apply the primary sort, by
     * the count of filters matched.
     *
     * @param filteredOffers The list of offers returned from the data query.
     * @return The same list of offers, but now arranged in
     * (qty filters matched, start date, title) order.
     */
    private List<Offer> sortByFilterMatches(List<Offer> filteredOffers) {
        List<Offer> sortedOffers = new ArrayList<Offer>();

        int maxAsked = -1;

        for(Offer offer : filteredOffers) {
            maxAsked = Math.max(offer.getQtyAskedFilters(), maxAsked);
        }

        for(int i = maxAsked; i >= 0; i--) {

            for(Offer offer : filteredOffers) {

                if(offer.getQtyFoundFilters() == i) {
                    sortedOffers.add(offer);
                }

            }

        }

        return sortedOffers;
    }

    /**
     * Marks each offer with how well it matches the filters.
     *
     * Each offer perhaps matches the organization's filters.
     * Mark the offer with how many filters it did match.
     *
     * @param filteredOffers A set of offers to modify.
     * @param criteria A set of search criteria.
     * @return a list of offers.
     */
    private List<Offer> applyFilterMatches(List<Offer> filteredOffers, OfferSearch criteria) {
    	List<Filter> filters = criteria.getFilters();
    	
    	if(filters == null || filters.size() == 0) {
    		return filteredOffers;
    	}
    	
    	int qtyAsked = filters.size();
    	
        for(Offer offer : filteredOffers) {
            int qtyFound = 0;

            for(Filter cFilter : filters) {

                for(Long id : offer.getOfferFilterIds()) {

                    if(Utilities.equalIds(id, cFilter.getId())) {
                        qtyFound++;
                        break;
                    }

                }

            }

            offer.setQtyAskedFilters(qtyAsked);
            offer.setQtyFoundFilters(qtyFound);
        }

        return filteredOffers;
    }

}
