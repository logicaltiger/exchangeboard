/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logicaltiger.exchangeboard.model.Offer;
import com.logicaltiger.exchangeboard.service.OfferService;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@RestController
@RequestMapping("offers")
public class OfferController {

    @Resource(name="offerService")
    private OfferService offerService;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffers() {
        List<Offer> offers = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            offers = offerService.getOffers();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Offer>>(offers, status);
    	} 

    	return new ResponseEntity<List<Offer>>(status);
    }    

    @RequestMapping(value = "org/{orgId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOrgOffers(@PathVariable("orgId") Long orgId) {
        List<Offer> offers = null;
    	HttpStatus status = HttpStatus.OK;
        
    	if(orgId == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {
    		
	    	try {
	            offers = offerService.getOrgOffers(orgId);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    	
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Offer>>(offers, status);
    	} 

    	return new ResponseEntity<List<Offer>>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffer(@PathVariable("id") Long id) {
        Optional<Offer> oOffer = Optional.empty();
    	HttpStatus status = HttpStatus.OK;
        
    	if(id == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {

    		try {
	            oOffer = offerService.get(id);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
        	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    
    	}
    	
    	if(status == HttpStatus.OK && !oOffer.isPresent()) {
    		status = HttpStatus.NOT_FOUND;
    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<Offer>(oOffer.get(), status);
    	} 

    	return new ResponseEntity<Offer>(status);
    }
    	
    @RequestMapping(value = "/template/{orgId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOfferTemplate(@PathVariable("orgId") Long orgId) {
        Optional<Offer> oOffer = Optional.empty();
    	HttpStatus status = HttpStatus.OK;
        
    	if(orgId == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {

    		try {
	            oOffer = offerService.getTemplate(orgId);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
        	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    
    	}
    	
    	if(status == HttpStatus.OK && !oOffer.isPresent()) {
    		status = HttpStatus.NOT_FOUND;
    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<Offer>(oOffer.get(), status);
    	} 

    	return new ResponseEntity<Offer>(status);
    }
    	
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
    	Optional<Offer> oOffer = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(offer == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oOffer = offerService.add(offer);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}
    	
    	if(status == HttpStatus.OK && !oOffer.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}

    	if(status == HttpStatus.OK) {
	        HttpHeaders headers = new HttpHeaders();
	        offer = oOffer.get();
	        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(offer.getId()).toUri());
	        return new ResponseEntity<Offer>(offer, headers, HttpStatus.CREATED);
    	} 
    	
        return new ResponseEntity<Offer>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> editOffer(@PathVariable("id") Long id, @RequestBody Offer offer) {
    	Optional<Offer> oOffer = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(id == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(offer == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(offer.getId() == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(id.longValue() != offer.getId().longValue()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oOffer = offerService.edit(offer);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK && !oOffer.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}
    	
        if(status == HttpStatus.OK) {
	        return new ResponseEntity<Offer>(oOffer.get(), status);
    	} 
    	
        return new ResponseEntity<Offer>(status);
    }
     
}
