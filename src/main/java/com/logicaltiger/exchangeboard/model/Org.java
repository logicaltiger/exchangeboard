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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.logicaltiger.exchangeboard.util.Utilities;

@Entity
@Table(name="org")
public class Org implements Serializable {
	private static final long serialVersionUID = -2808050088097500043L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = Utilities.INVALID_ID;

    @Column(name="provider", nullable=false)
    @NotNull
    private boolean provider;

    @Column(name="name", length=100, nullable=false)
    @NotEmpty
    @Size(max=100)
    private String name;
    
    @Column(name="address1", length=50, nullable=false)
    @NotEmpty
    @Size(max=50)
    private String address1;

    @Column(name="address2", length=50)
    @Size(max=50)
    private String address2;

    @Column(name="city", length=50, nullable=false)
    @NotEmpty
    @Size(max=50)
    private String city;

    @Column(name="state", length=2, nullable=false)
    @NotEmpty
    @Size(max=2)
    private String state = Utilities.DEFAULT_STATE;

    @Column(name="zip", length=10, nullable=false)
    @NotEmpty
    @Size(max=10)
    private String zip;

    @Column(name="phone", length=50, nullable=false)
    @NotEmpty
    @Size(max=50)
    private String phone;

    @Column(name="email", length=100, nullable=false)
    @NotEmpty
    @Size(max=100)
    private String email;

    @Column(name="prefer_contact_method", length=1, nullable=false)
    @NotEmpty
    @Size(max=1)
    private String preferContactMethod;
    
    @Column(name="accessibility", length=4000)
    @Size(max=4000)
    private String accessibility;

    @Column(name="notes", length=4000)
    @Size(max=4000)
    private String notes;
    
    @Column(name="may_solicit", nullable=false)
    @NotNull
    private boolean maySolicit;

    @Column(name="active", nullable=false)
    @NotNull
    private boolean active;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isProvider() {
        return this.provider;
    }

    public void setProvider(boolean isProvider) {
        this.provider = isProvider;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferContactMethod() {
    	return this.preferContactMethod;
    }
    
    public void setPreferContactMethod(String method) {
    	this.preferContactMethod = method;
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

    public boolean isMaySolicit() {
        return this.maySolicit;
    }

    public void setMaySolicit(boolean isMaySolicit) {
        this.maySolicit = isMaySolicit;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

}
