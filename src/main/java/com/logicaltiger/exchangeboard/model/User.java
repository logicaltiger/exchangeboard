/**
 * Copyright (c) 2018 Jerome Mrozak All Rights Reserved.
 * 
 * Copyright is per the open MIT license (https://opensource.org/licenses/MIT), whose text
 * is also provided in the file com.logicaltiger.exchangeboard.ExchangeBoardApplication.java.
 */
package com.logicaltiger.exchangeboard.model;

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
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = Utilities.INVALID_ID;

    @Column(name="org_id")
    private Long org_id = Utilities.INVALID_ID;

    @Column(name="org_provider")
    @NotNull
    private boolean org_provider;

    @Column(name = "username", length = 100, unique = true)
    @NotNull
    @Size(min = 4, max = 100)
    private String username;

    @Column(name="admin", nullable=false)
    @NotNull
    private boolean admin;
    
    @Column(name = "full_name", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String full_name;

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

    @Column(name="active", nullable=false)
    @NotNull
    private boolean active;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return this.org_id;
    }

    public void setOrgId(Long id) {
        this.org_id = id;
    }

    public boolean isOrgProvider() {
        return this.org_provider;
    }

    public void setOrgProvider(boolean isProvider) {
        this.org_provider = isProvider;
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public String getFullName() {
        return this.full_name;
    }

    public void setFullName(String name) {
        this.full_name = name;
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
    
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

}
