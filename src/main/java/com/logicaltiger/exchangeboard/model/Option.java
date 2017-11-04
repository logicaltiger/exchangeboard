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

import org.hibernate.validator.constraints.NotBlank;

import com.logicaltiger.exchangeboard.util.Utilities;

/*
 * Turns out that "option" is a reserved MySQL word.
 * Rather than change the code, rename the table to an "almost as good" name.
 */
@Entity
@Table(name="choice")
public class Option implements Serializable {
	private static final long serialVersionUID = -2983262110098160474L;
	public static final String TOPIC_STATE = "S";
	public static final String TOPIC_EVENT = "E";
	public static final String TOPIC_ROLE = "R";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = Utilities.INVALID_ID;

    @Column(name="topic", length=3, nullable=false)
    @NotBlank
    @Size(max=3)
    private String topic;

    @Column(name="seq", nullable=false)
    @NotNull
    private int seq;

    @Column(name="value", length=50, nullable=false)
    @NotBlank
    @Size(max=50)
    private String value;

    @Column(name="name", length=100, nullable=false)
    @NotBlank
    @Size(max=100)
    private String name;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
