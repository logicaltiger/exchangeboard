/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.logicaltiger.exchangeboard.model.Offer;
import com.logicaltiger.exchangeboard.model.OfferSearch;

@Repository("offerSearchDao")
public class OfferSearchDaoImpl implements OfferSearchDao {

    @PersistenceContext
    private EntityManager manager;

    public void setEntityManager(EntityManager entityManager) {
    	this.manager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<Offer> getFilteredOffers(OfferSearch criteria) {
        String hql = "from Offer o where o.template = 0 and o.date_closed is null ";
        String typeStart = " and o.type in (";
        String typeEnd = ") ";

        String hqlAllTypes = "";

        if(criteria != null) {
            hqlAllTypes = (criteria.isTypeMusical() ? ("'" + Offer.TYPE_MUSICAL + "', ") : "") +
                    (criteria.isTypeConcert() ? ("'" + Offer.TYPE_CONCERT + "', ") : "") +
                    (criteria.isTypePlay() ? ("'" + Offer.TYPE_PLAY + "', ") : "") +
                    (criteria.isTypeMovie() ? ("'" + Offer.TYPE_MOVIE + "', ") : "") +
                    (criteria.isTypeOther() ? ("'" + Offer.TYPE_OTHER + "', ") : "");

            if(hqlAllTypes.length() > 0) {
                hqlAllTypes = typeStart + hqlAllTypes.substring(0, hqlAllTypes.length() - 2) + typeEnd;
            }

        }

        /*
         * The "open ended" offers can always be listed.  Include date ranges only if the appropriate search criteria is provided.
         */
        boolean filledStartDate = criteria.getStartDate() != null;
        boolean filledEndDate = criteria.getEndDate() != null;
        String hqlDateRange = " and (o.open_ended != 0 or ( ";
        String openEndedDates = " (1 = 1) ";

        if(filledStartDate || filledEndDate) {
            openEndedDates = " (" + (filledStartDate ? " (o.event_date is null or :startDate <= date(o.event_date)) " : "")
                    + (filledStartDate && filledEndDate ? " and " : "")
                    + (filledEndDate ? " (o.event_date is null or date(o.event_date) <= :endDate) " : "") + ") ";

        }

        hqlDateRange += openEndedDates + ") ) ";
        String hqlOrderBy = " order by o.open_ended desc, o.event_date, o.event_start_time, o.title";

        Query query = manager.createQuery(hql + (hqlAllTypes == null ? "" : hqlAllTypes) + hqlDateRange + hqlOrderBy);

        if(filledStartDate) {
            query.setParameter("startDate", criteria.getStartDate());
        }

        if(filledEndDate) {
            query.setParameter("endDate", criteria.getEndDate());
        }

        return (List<Offer>) query.getResultList();
    }
    
}
