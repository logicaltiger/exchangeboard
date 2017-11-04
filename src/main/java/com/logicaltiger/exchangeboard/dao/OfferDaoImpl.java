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

import com.logicaltiger.exchangeboard.model.Offer;

@Repository("offerDao")
public class OfferDaoImpl implements OfferDao {

    @PersistenceContext
    private EntityManager manager;

    public void setEntityManager(EntityManager entityManager) {
    	this.manager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getOffers() {
    	Query query = manager.createQuery("from Offer o where o.template = 0 order by o.open_ended desc, o.event_date, o.event_start_time, o.title, o.id");
        return (List<Offer>) query.getResultList();
    }
    
    /*
     * Here and in getTemplate(): o.org_id looks right, but only o.orgId works OK.
     */
    @SuppressWarnings("unchecked")
    public List<Offer> getOrgOffers(Long orgId) {
        Query query = manager.createQuery("from Offer o where o.template = 0 and o.orgId = :orgId order by o.open_ended desc, o.event_date, o.event_start_time, o.title, o.id");
        query.setParameter("orgId", orgId);
        return (List<Offer>) query.getResultList();
    }
    
    public Optional<Offer> get(Long id) {

        if(id == null) {
            return Optional.empty();
        }

        return Optional.ofNullable((Offer) manager.find(Offer.class, id));
    }

    @SuppressWarnings("unchecked")
    public Optional<Offer> getTemplate(Long orgId) {

        if(orgId == null) {
            return Optional.empty();
        }

        Query query = manager.createQuery("from Offer o where o.orgId = :orgId and o.template != 0 ");
        query.setParameter("orgId", orgId);
    	List<Offer> offers = query.getResultList();
   		return (offers == null || offers.size() == 0) ? Optional.empty() : Optional.ofNullable(offers.get(0));
    }

    public Optional<Offer> add(Offer offer) {

        if(offer == null) {
            return Optional.empty();
        }

        manager.persist(offer);
        return Optional.ofNullable(offer);
    }

    public Optional<Offer> edit(Offer offer) {

        if(offer == null) {
            return Optional.empty();
        }

        manager.merge(offer);
        return Optional.ofNullable(offer);
    }

}
