/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.logicaltiger.exchangeboard.util.Utilities;

@Entity
@Table(name="offer")
public class Offer implements Serializable {
	private static final long serialVersionUID = 1134366922044727911L;

	public static final String TYPE_MUSICAL = "Musical";
    public static final String TYPE_CONCERT = "Concert";
    public static final String TYPE_PLAY = "Play";
    public static final String TYPE_MOVIE = "Movie";
    public static final String TYPE_OTHER = "Other";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = Utilities.INVALID_ID;

    @Column(name="org_id", nullable=false)
    @NotNull
    private Long orgId = null;

    @Column(name="template", nullable=false)
    @NotNull
    private boolean template = false;

    @Column(name="title", length=100, nullable=false)
    @NotEmpty
    @Size(max=100)
    private String title;

    @Column(name="type", length=50, nullable=false)
    @NotEmpty
    @Size(max=50)
    private String type = TYPE_OTHER;

    @Column(name="address1", length=50, nullable=false)
    @Size(max=50)
    private String address1;

    @Column(name="address2", length=50, nullable=false)
    @Size(max=50)
    private String address2;

    @Column(name="city", length=50, nullable=false)
    @Size(max=50)
    private String city;

    @Column(name="state", length=2, nullable=false)
    @Size(max=2)
    private String state = "IL";

    @Column(name="zip", length=10, nullable=false)
    @Size(max=10)
    private String zip;

    @Column(name="open_ended", nullable=false)
    @NotNull
    private boolean open_ended = false;

    @Column(name="event_date")
    private Date event_date;

    @Column(name="event_start_time")
    @Size(max=50)
    private String event_start_time;

    @Column(name="event_end_time")
    @Size(max=50)
    private String event_end_time;

    @Column(name="date_opened")
    private Date date_opened;

    @Column(name="accessibility", length=4000)
    @Size(max=4000)
    private String accessibility;

    @Column(name="notes", length=4000)
    @Size(max=4000)
    private String notes;

    @Column(name="date_closed")
    private Date date_closed;

    @Column(name="close_notes", length=4000)
    @Size(max=4000)
    private String close_notes;

    @Transient
    private List<Long> offer_filter_ids;    

    @Transient
    private List<Filter> filters;    

    @Transient
    private Org org = null;

    @Transient
    private int qtyAskedFilters = 0;

    @Transient
    private int qtyFoundFilters = 0;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public boolean isTemplate() {
        return this.template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = Utilities.setNotNullTrim(address1);
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = Utilities.setNotNullTrim(address2);
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = Utilities.setNotNullTrim(city);
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = Utilities.setNotNullTrim(state);
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = Utilities.setNotNullTrim(zip);
    }

    public boolean isOpenEnded() {
        return this.open_ended;
    }

    public void setOpenEnded(boolean ended) {
        this.open_ended = ended;
    }

    public Date getEventDate() {
        return this.event_date;
    }

    public void setEventDate(Date eventDate) {
        this.event_date = eventDate;
    }

    public String getEventStartTime() {
        return this.event_start_time;
    }

    public void setEventStartTime(String time) {
        this.event_start_time = time;
    }

    public String getEventEndTime() {
        return this.event_end_time;
    }

    public void setEventEndTime(String time) {
        this.event_end_time = time;
    }

    public Date getDateOpened() {
        return this.date_opened;
    }

    public void setDateOpened(Date dateOpened) {
        this.date_opened = dateOpened;
    }

    public String getAccessibility() {
        return this.accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateClosed() {
        return this.date_closed;
    }

    public void setDateClosed(Date dateClosed) {
        this.date_closed = dateClosed;
    }

    public String getCloseNotes() {
        return this.close_notes;
    }

    public void setCloseNotes(String closeNotes) {
        this.close_notes = closeNotes;
    }

    public List<Long> getOfferFilterIds() {
        return this.offer_filter_ids;
    }

    public void setOfferFilterIds(List<Long> ids) {
        this.offer_filter_ids = ids;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Org getOrg() {
        return this.org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public int getQtyAskedFilters() {
        return this.qtyAskedFilters;
    }

    public void setQtyAskedFilters(int qtyAskedFilters) {
        this.qtyAskedFilters = qtyAskedFilters;
    }

    public int getQtyFoundFilters() {
        return this.qtyFoundFilters;
    }

    public void setQtyFoundFilters(int qtyFoundFilters) {
        this.qtyFoundFilters = qtyFoundFilters;
    }

}
