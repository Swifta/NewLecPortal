package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ADDRESS_BACKUP database table.
 * 
 */
@Entity
@Table(name="ADDRESS_BACKUP")
@NamedQuery(name="AddressBackup.findAll", query="SELECT a FROM AddressBackup a")
public class AddressBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private String city;

	@Column(name="COUNTRY_OID")
	private BigDecimal countryOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	private String province;

	private String suburb;

	public AddressBackup() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSuburb() {
		return this.suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

}