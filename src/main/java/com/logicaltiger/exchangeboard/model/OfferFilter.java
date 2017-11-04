/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logicaltiger.exchangeboard.util.Utilities;

@Entity
@Table(name="offer_filter")
public class OfferFilter implements Serializable {
	private static final long serialVersionUID = 792871283246139616L;

	public OfferFilter() {
	}
	
	public OfferFilter(Long offerId, Long filterId) {
		this.setOfferId(offerId);
		this.setFilterId(filterId);
	}
	
	@Id
    @Column(name="offer_id")	
    private Long offer_id = Utilities.INVALID_ID;
    
    @Column(name="filter_id")
    private Long filter_id = Utilities.INVALID_ID;
    
    public Long getOfferId() {
        return this.offer_id;
    }

    public void setOfferId(Long id) {
        this.offer_id = id;
    }

    public Long getFilterId() {
        return this.filter_id;
    }

    public void setFilterId(Long id) {
        this.filter_id = id;
    }

}
