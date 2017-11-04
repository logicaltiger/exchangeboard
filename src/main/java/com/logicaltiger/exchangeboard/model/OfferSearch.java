/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.model;

import java.sql.Date;
import java.util.List;

public class OfferSearch {
    private boolean typeMusical;
    private boolean typeConcert;
    private boolean typePlay;
    private boolean typeMovie;
    private boolean typeOther;

    private Date startDate;
    private Date endDate;

    private List<Filter> filters;
    
    private List<Offer> offers;

    public boolean isTypeMusical() {
        return this.typeMusical;
    }

    public void setTypeMusical(boolean typeMusical) {
        this.typeMusical = typeMusical;
    }

    public boolean isTypeConcert() {
        return this.typeConcert;
    }

    public void setTypeConcert(boolean typeConcert) {
        this.typeConcert = typeConcert;
    }

    public boolean isTypePlay() {
        return this.typePlay;
    }

    public void setTypePlay(boolean typePlay) {
        this.typePlay = typePlay;
    }

    public boolean isTypeMovie() {
        return this.typeMovie;
    }

    public void setTypeMovie(boolean typeMovie) {
        this.typeMovie = typeMovie;
    }

    public boolean isTypeOther() {
        return this.typeOther;
    }

    public void setTypeOther(boolean typeOther) {
        this.typeOther = typeOther;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public boolean isAnyType() {
        return this.typeMusical || this.typeConcert || this.typePlay || this.typeMovie || this.typeOther;
    }

}
