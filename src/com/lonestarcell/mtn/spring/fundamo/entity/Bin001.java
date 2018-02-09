package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BIN001 database table.
 * 
 */
@Entity
@NamedQuery(name="Bin001.findAll", query="SELECT b FROM Bin001 b")
public class Bin001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_NUMBER_LENGTH")
	private BigDecimal accountNumberLength;

	@Column(name="BIC_NUMBER")
	private String bicNumber;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	@Column(name="COUNTRY_OID")
	private BigDecimal countryOid;

	private String currency;

	private String home;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MOBILE_OPERATOR_OID")
	private BigDecimal mobileOperatorOid;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	@Column(name="OWNER_OID")
	private BigDecimal ownerOid;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	@Column(name="SHORT_CODE")
	private String shortCode;

	@Column(name="SHORT_NAME_PREFIX")
	private String shortNamePrefix;

	private String status;

	public Bin001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountNumberLength() {
		return this.accountNumberLength;
	}

	public void setAccountNumberLength(BigDecimal accountNumberLength) {
		this.accountNumberLength = accountNumberLength;
	}

	public String getBicNumber() {
		return this.bicNumber;
	}

	public void setBicNumber(String bicNumber) {
		this.bicNumber = bicNumber;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public BigDecimal getCountryOid() {
		return this.countryOid;
	}

	public void setCountryOid(BigDecimal countryOid) {
		this.countryOid = countryOid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMobileOperatorOid() {
		return this.mobileOperatorOid;
	}

	public void setMobileOperatorOid(BigDecimal mobileOperatorOid) {
		this.mobileOperatorOid = mobileOperatorOid;
	}

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOwnerOid() {
		return this.ownerOid;
	}

	public void setOwnerOid(BigDecimal ownerOid) {
		this.ownerOid = ownerOid;
	}

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public String getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getShortNamePrefix() {
		return this.shortNamePrefix;
	}

	public void setShortNamePrefix(String shortNamePrefix) {
		this.shortNamePrefix = shortNamePrefix;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}