/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logicaltiger.exchangeboard.model.OfferSearch;
import com.logicaltiger.exchangeboard.service.OfferSearchService;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@RestController
@RequestMapping("offers")
public class OfferSearchController {

    @Resource(name="offerSearchService")
    private OfferSearchService offerSearchService;

    /**
     * Fetch Offer objects matching the user's search criteria.
     * The criteria is provided through a submitted form, so this method is PUT.
     * The call is idempotent -- the same search results are returned each time, with no database changes.
     * 
     * @param offer The search criteria.
     * @return A list, perhaps empty, of matching Offer objects.
     */
    @RequestMapping(value = "/filtered", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferSearch> getFilteredOffers(@RequestBody OfferSearch offerSearch) {
    	HttpStatus status = HttpStatus.OK;
        
    	if(offerSearch == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
	            offerSearch = offerSearchService.getFilteredOffers(offerSearch);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<OfferSearch>(offerSearch, status);
    	} 
    	
        return new ResponseEntity<OfferSearch>(status);
    }    

}
