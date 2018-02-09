package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PROPERTIES_20150401 database table.
 * 
 */
// @Entity
@Table(name="PROPERTIES_20150401")
@NamedQuery(name="Properties20150401.findAll", query="SELECT p FROM Properties20150401 p")
public class Properties20150401 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"KEY\"")
	private String key;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="\"VALUE\"")
	private String value;

	public Properties20150401() {
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}