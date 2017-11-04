/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.context.SecurityContextHolder;

import com.logicaltiger.exchangeboard.model.Option;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Option.class);
    }

	@Bean
	EvaluationContextExtension securityExtension() {
	    return new EvaluationContextExtensionSupport() {

	        @Override
	        public String getExtensionId() {
	            return "security";
	        }

	        @Override
	        public SecurityExpressionRoot getRootObject() {
	            return new SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication()) {
	            };
	        }
	    };
	}	
}