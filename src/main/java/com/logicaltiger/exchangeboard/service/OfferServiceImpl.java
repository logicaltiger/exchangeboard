/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logicaltiger.exchangeboard.dao.FilterDao;
import com.logicaltiger.exchangeboard.dao.OfferDao;
import com.logicaltiger.exchangeboard.dao.OfferFilterDao;
import com.logicaltiger.exchangeboard.model.Offer;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@Service("offerService")
public class OfferServiceImpl implements OfferService {

	@Autowired
	private FilterDao filterDao;
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private OfferFilterDao offerFilterDao;
	
	@Autowired 
	private PermissionService permService;

	@Transactional(readOnly = true)
    public List<Offer> getOffers() {
    	return offerDao.getOffers();
    }
    
	@Transactional(readOnly = true)
    public List<Offer> getOrgOffers(Long orgId) {
    	permService.hasPermission(orgId, PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	return offerDao.getOrgOffers(orgId);
    }
    
	/*
	 * Only when editing an individual offer does the Offer object need to
	 * have the offer/filter combos and a full list of trait Filter objects.
	 * 
	 * As described elsewhere, don't worry about Optional.get() returning 
	 * a NoSuchElementException.  The caller handles it OK.
	 */
    @Transactional(readOnly = true)
    public Optional<Offer> get(Long id) {
    	Optional<Offer> oOffer = offerDao.get(id);
    	applyFilters(oOffer.get());
    	return oOffer;
    }

    @Transactional(readOnly = true)
    public Optional<Offer> getTemplate(Long orgId) throws NoPermissionException {
    	permService.hasPermission(orgId, PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	Optional<Offer> oOffer = offerDao.getTemplate(orgId);
    	applyFilters(oOffer.get());
    	return oOffer;
    }

    @Transactional
    public Optional<Offer> add(Offer offer) throws NoPermissionException {
    	
    	/*
    	 * The user has rights to the Offer if the user has rights to the Offer's organization.
    	 */
    	Optional<Offer> oOffer = offerDao.add(offer);
    	offer = oOffer.get();
    	permService.hasPermission(offer.getOrgId(), PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	offerFilterDao.updateOfferFilters(offer.getId(), offer.getOfferFilterIds());
    	return oOffer;
    	
    }

    @Transactional
    public Optional<Offer> edit(Offer offer) throws NoPermissionException {
    	permService.hasPermission(offer.getOrgId(), PermissionService.PERM_ADMIN, PermissionService.PERM_ORG);
    	Optional<Offer> oOffer = offerDao.edit(offer);
    	offer = oOffer.get();
    	offerFilterDao.updateOfferFilters(offer.getId(), offer.getOfferFilterIds());
    	return oOffer;
    }

    private void applyFilters(Offer offer) {
    	offer.setOfferFilterIds(offerFilterDao.getFilterIds(offer.getId()));
    	offer.setFilters(filterDao.getTraits());
    }

}
