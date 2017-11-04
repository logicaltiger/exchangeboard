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

import com.logicaltiger.exchangeboard.model.User;
import com.logicaltiger.exchangeboard.service.UserService;
import com.logicaltiger.exchangeboard.util.NoPermissionException;

@RestController
@RequestMapping("users")
public class UserController {

    @Resource(name="userService")
    private UserService userService;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = null;
    	HttpStatus status = HttpStatus.OK;
        
    	try {
            users = userService.getUsers();
    	} catch(NoPermissionException e) {
    		status = HttpStatus.UNAUTHORIZED;
    	} catch(RuntimeException e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
        
    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<List<User>>(users, status);
    	} 

    	return new ResponseEntity<List<User>>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> oUser = Optional.empty();
    	HttpStatus status = HttpStatus.OK;
        
    	if(id == null) {
    		status = HttpStatus.NOT_FOUND;
    	} else {

    		try {
	            oUser = userService.get(id);
        	} catch(NoPermissionException e) {
        		status = HttpStatus.UNAUTHORIZED;
        	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
    
    	}
    	
    	if(status == HttpStatus.OK && !oUser.isPresent()) {
    		status = HttpStatus.NOT_FOUND;
    	}

    	if(status == HttpStatus.OK) {
    		return new ResponseEntity<User>(oUser.get(), status);
    	} 

    	return new ResponseEntity<User>(status);
    }
    	
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {
    	Optional<User> oUser = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(user == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oUser = userService.add(user);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}
    	
    	if(status == HttpStatus.OK && !oUser.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}

    	if(status == HttpStatus.OK) {
	        HttpHeaders headers = new HttpHeaders();
	        user = oUser.get();
	        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    	} 
    	
        return new ResponseEntity<User>(status);
    }    

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User user) {
    	Optional<User> oUser = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(id == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(user == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(user.getId() == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else if(id.longValue() != user.getId().longValue()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oUser = userService.edit(user);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK && !oUser.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}
    	
        if(status == HttpStatus.OK) {
	        return new ResponseEntity<User>(oUser.get(), status);
    	} 
    	
        return new ResponseEntity<User>(status);
        
    }
     
    @RequestMapping(value = "/assist/{orgId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUserAssist(@PathVariable("orgId") Long orgId) {
    	Optional<User> oUser = Optional.empty();
    	HttpStatus status = HttpStatus.OK;

    	if(orgId == null) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	} else {
    		
	    	try {
    			oUser = userService.editAssist(orgId);
	    	} catch(NoPermissionException e) {
	    		status = HttpStatus.UNAUTHORIZED;
	    	} catch(RuntimeException e) {
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	}

    	}

    	if(status == HttpStatus.OK && !oUser.isPresent()) {
    		status = HttpStatus.UNPROCESSABLE_ENTITY;
    	}
    	
        if(status == HttpStatus.OK) {
	        return new ResponseEntity<User>(oUser.get(), status);
    	} 
    	
        return new ResponseEntity<User>(status);
        
    }
     
}
