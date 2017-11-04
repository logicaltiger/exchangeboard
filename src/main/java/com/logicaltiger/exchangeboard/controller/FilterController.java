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

import com.logicaltiger.exchangeboard.model.Filter;
import com.logicaltiger.exchangeboard.service.FilterService;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@RestController
@RequestMapping("filters")
public class FilterController {

    @Resource(name="filterService")
    private FilterService filterService;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Filter>> getFilters() {
        List<Filter> filters = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            filters = filterService.getFilters();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Filter>>(filters, status);
    	} 

    	return new ResponseEntity<List<Filter>>(status);
    }    

    @RequestMapping(value = "/favors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Filter>> getFavors() {
        List<Filter> filters = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            filters = filterService.getFavors();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Filter>>(filters, status);
    	} 

    	return new ResponseEntity<List<Filter>>(status);
    }    

    @RequestMapping(value = "/traits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Filter>> getTraits() {
        List<Filter> filters = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            filters = filterService.getTraits();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Filter>>(filters, status);
    	} 

    	return new ResponseEntity<List<Filter>>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Filter> getFilter(@PathVariable("id") Long id) {
        Optional<Filter> oFilter = Optional.empty();
    	HttpStatus status = HttpStatus.OK;
        
    	if(id == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {

    		try {
	            oFilter = filterService.get(id);
        	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    
    	}
    	
    	if(status == HttpStatus.OK && !oFilter.isPresent()) {
    		status = HttpStatus.NOT_FOUND;
    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<Filter>(oFilter.get(), status);
    	} 

    	return new ResponseEntity<Filter>(status);
    }
    	
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Filter> addFilter(@RequestBody Filter filter) {
    	Optional<Filter> oFilter = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(filter == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oFilter = filterService.add(filter);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}
    	
    	if(status == HttpStatus.OK && !oFilter.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}

    	if(status == HttpStatus.OK) {
	        HttpHeaders headers = new HttpHeaders();
	        filter = oFilter.get();
	        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filter.getId()).toUri());
	        return new ResponseEntity<Filter>(filter, headers, HttpStatus.CREATED);
    	} 
    	
        return new ResponseEntity<Filter>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Filter> editFilter(@PathVariable("id") Long id, @RequestBody Filter filter) {
    	Optional<Filter> oFilter = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(id == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(filter == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(filter.getId() == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(id.longValue() != filter.getId().longValue()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oFilter = filterService.edit(filter);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK && !oFilter.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}
    	
        if(status == HttpStatus.OK) {
	        return new ResponseEntity<Filter>(oFilter.get(), status);
    	} 
        
        return new ResponseEntity<Filter>(status);
    }
     
}
