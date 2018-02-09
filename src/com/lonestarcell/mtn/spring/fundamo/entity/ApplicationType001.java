package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the APPLICATION_TYPE001 database table.
 * 
 */
@Entity
@Table(name="APPLICATION_TYPE001")
@NamedQuery(name="ApplicationType001.findAll", query="SELECT a FROM ApplicationType001 a")
public class ApplicationType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String description;

	@Column(name="\"INDICATOR\"")
	private BigDecimal indicator;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public ApplicationType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getIndicator() {
		return this.indicator;
	}

	public void setIndicator(BigDecimal indicator) {
		this.indicator = indicator;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}