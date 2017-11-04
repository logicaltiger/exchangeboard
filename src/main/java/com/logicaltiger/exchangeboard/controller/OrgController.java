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

import com.logicaltiger.exchangeboard.model.Org;
import com.logicaltiger.exchangeboard.service.OrgService;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@RestController
@RequestMapping("orgs")
public class OrgController {

    @Resource(name="orgService")
    private OrgService orgService;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Org>> getOrgs() {
        List<Org> orgs = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            orgs = orgService.getOrgs();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Org>>(orgs, status);
    	} 

    	return new ResponseEntity<List<Org>>(status);
    }    

    @RequestMapping(value = "/providers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Org>> getProviders() {
        List<Org> orgs = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            orgs = orgService.getProviders();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<Org>>(orgs, status);
    	} 

    	return new ResponseEntity<List<Org>>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Org> getOrg(@PathVariable("id") Long id) {
        Optional<Org> oOrg = Optional.empty();
    	HttpStatus status = HttpStatus.OK;
        
    	if(id == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {

    		try {
	            oOrg = orgService.get(id);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
        	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    
    	}
    	
    	if(status == HttpStatus.OK && !oOrg.isPresent()) {
    		status = HttpStatus.NOT_FOUND;
    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<Org>(oOrg.get(), status);
    	} 

    	return new ResponseEntity<Org>(status);
    }
    	
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Org> addOrg(@RequestBody Org org) {
    	Optional<Org> oOrg = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(org == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oOrg = orgService.add(org);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}
    	
    	if(status == HttpStatus.OK && !oOrg.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}

    	if(status == HttpStatus.OK) {
	        HttpHeaders headers = new HttpHeaders();
	        org = oOrg.get();
	        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(org.getId()).toUri());
	        return new ResponseEntity<Org>(org, headers, HttpStatus.CREATED);
    	} 
    	
        return new ResponseEntity<Org>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Org> editOrg(@PathVariable("id") Long id, @RequestBody Org org) {
    	Optional<Org> oOrg = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(id == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(org == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(org.getId() == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(id.longValue() != org.getId().longValue()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oOrg = orgService.edit(org);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK && !oOrg.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}
    	
        if(status == HttpStatus.OK) {
	        return new ResponseEntity<Org>(oOrg.get(), status);
    	} 
    	
        return new ResponseEntity<Org>(status);
    }
     
}
