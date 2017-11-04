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
@Table(name="filter")
public class Filter implements Serializable {
	private static final long serialVersionUID = -4430203130090934612L;

    public static final String TYPE_TRAIT = "T";
    public static final String TYPE_FAVOR = "F";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = Utilities.INVALID_ID;
    
    @Column(name="type", length=1, nullable=false)
    @NotEmpty
    @Size(max=1)
    private String type = TYPE_TRAIT;

    @Column(name="text", length=100, nullable=false)
    @NotEmpty
    @Size(max=100)
    private String text;

    @Column(name="active", nullable=false)
    @NotNull
    private boolean active;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = Utilities.setNotNullTrim(text);
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public boolean isTrait() {
    	return TYPE_TRAIT.equals(this.type);
    }

    public boolean isFavor() {
    	return TYPE_FAVOR.equals(this.type);
    }

}
