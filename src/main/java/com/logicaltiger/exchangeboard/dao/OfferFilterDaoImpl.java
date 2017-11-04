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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.logicaltiger.exchangeboard.model.OfferFilter;

@Repository("offerFilterDao")
public class OfferFilterDaoImpl implements OfferFilterDao {

    @PersistenceContext
    private EntityManager manager;

    public void setEntityManager(EntityManager entityManager) {
    	this.manager = entityManager;
    }

    /*
     * Getting the distinct offer/filter IDs has been a problem with createQuery().
     * * A HQL query of "from OfferFilter ..." returns the correct quantity, but all 
     *   records have the same filter_id value.
     * * A SQL query specifying the filter_id field yields what I need.
     */
    @SuppressWarnings("unchecked")
    public List<Long> getFilterIds(Long offerId) {
        Query query = manager.createQuery("select o.filter_id from OfferFilter o where o.offer_id = :offerId");
        query.setParameter("offerId", offerId);
        return (List<Long>) query.getResultList();
    }
    
    public void updateOfferFilters(Long offerId, List<Long> filterIds) {

        if(offerId == null) {
            return;
        }

        deleteOfferFilters(offerId);
        
        if(filterIds != null) {
        
        	for(Long filterId : filterIds) {
        		add(new OfferFilter(offerId, filterId));
        	}
        
        }

    }

    private void deleteOfferFilters(Long offerId) {

        if(offerId == null) {
        	return;
        }

        Query query = manager.createQuery("delete from OfferFilter o where o.offer_id = :offerId");
        query.setParameter("offerId", offerId);
        query.executeUpdate();
    }

    private Optional<OfferFilter> add(OfferFilter offerFilter) {

        if(offerFilter == null) {
            return Optional.empty();
        }

        manager.merge(offerFilter);
        return Optional.ofNullable(offerFilter);
    }

}
