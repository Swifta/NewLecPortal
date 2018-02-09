package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the COUNTRY001 database table.
 * 
 */
@Entity
@NamedQuery(name="Country001.findAll", query="SELECT c FROM Country001 c")
public class Country001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String code;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="DIALING_CODE")
	private String dialingCode;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public Country001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDialingCode() {
		return this.dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}