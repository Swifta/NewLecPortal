package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EXT_PROVIDER_ACC_HOLDER001 database table.
 * 
 */
@Entity
@Table(name="EXT_PROVIDER_ACC_HOLDER001")
@NamedQuery(name="ExtProviderAccHolder001.findAll", query="SELECT e FROM ExtProviderAccHolder001 e")
public class ExtProviderAccHolder001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_REFERENCE_NUMBER")
	private BigDecimal accountReferenceNumber;

	@Column(name="EXTERNAL_PROVIDER_OID")
	private BigDecimal externalProviderOid;

	@Column(name="EXTERNAL_REFERENCE_NUMBER")
	private String externalReferenceNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NATIONAL_ID")
	private String nationalId;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	public ExtProviderAccHolder001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountReferenceNumber() {
		return this.accountReferenceNumber;
	}

	public void setAccountReferenceNumber(BigDecimal accountReferenceNumber) {
		this.accountReferenceNumber = accountReferenceNumber;
	}

	public BigDecimal getExternalProviderOid() {
		return this.externalProviderOid;
	}

	public void setExternalProviderOid(BigDecimal externalProviderOid) {
		this.externalProviderOid = externalProviderOid;
	}

	public String getExternalReferenceNumber() {
		return this.externalReferenceNumber;
	}

	public void setExternalReferenceNumber(String externalReferenceNumber) {
		this.externalReferenceNumber = externalReferenceNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNationalId() {
		return this.nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

}