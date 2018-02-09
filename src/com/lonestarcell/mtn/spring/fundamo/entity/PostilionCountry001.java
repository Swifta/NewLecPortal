package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the POSTILION_COUNTRY001 database table.
 * 
 */
@Entity
@Table(name="POSTILION_COUNTRY001")
@NamedQuery(name="PostilionCountry001.findAll", query="SELECT p FROM PostilionCountry001 p")
public class PostilionCountry001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public PostilionCountry001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}