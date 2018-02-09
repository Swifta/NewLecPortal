package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EXCHANGE_RATE_DEFINITION001 database table.
 * 
 */
@Entity
@Table(name="EXCHANGE_RATE_DEFINITION001")
@NamedQuery(name="ExchangeRateDefinition001.findAll", query="SELECT e FROM ExchangeRateDefinition001 e")
public class ExchangeRateDefinition001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String description;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MAJOR_CURRENCY")
	private String majorCurrency;

	@Column(name="MINOR_CURRENCY")
	private String minorCurrency;

	public ExchangeRateDefinition001() {
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

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMajorCurrency() {
		return this.majorCurrency;
	}

	public void setMajorCurrency(String majorCurrency) {
		this.majorCurrency = majorCurrency;
	}

	public String getMinorCurrency() {
		return this.minorCurrency;
	}

	public void setMinorCurrency(String minorCurrency) {
		this.minorCurrency = minorCurrency;
	}

}