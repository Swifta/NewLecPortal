package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PHONE_NUMBER001 database table.
 * 
 */
@Entity
@Table(name="PHONE_NUMBER001")
@NamedQuery(name="PhoneNumber001.findAll", query="SELECT p FROM PhoneNumber001 p")
public class PhoneNumber001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String code;

	@Column(name="COUNTRY_OID")
	private BigDecimal countryOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	public PhoneNumber001() {
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

	public BigDecimal getCountryOid() {
		return this.countryOid;
	}

	public void setCountryOid(BigDecimal countryOid) {
		this.countryOid = countryOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}