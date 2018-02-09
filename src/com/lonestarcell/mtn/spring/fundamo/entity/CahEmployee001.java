package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CAH_EMPLOYEE001 database table.
 * 
 */
@Entity
@Table(name="CAH_EMPLOYEE001")
@NamedQuery(name="CahEmployee001.findAll", query="SELECT c FROM CahEmployee001 c")
public class CahEmployee001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AUTHORISATION_EXPIRY")
	private BigDecimal authorisationExpiry;

	@Column(name="CORPORATE_ACCOUNT_HOLDER_OID")
	private BigDecimal corporateAccountHolderOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	@Column(name="\"PRIMARY\"")
	private String primary;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	@Column(name="SECRET_CODE")
	private String secretCode;

	private String status;

	public CahEmployee001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAuthorisationExpiry() {
		return this.authorisationExpiry;
	}

	public void setAuthorisationExpiry(BigDecimal authorisationExpiry) {
		this.authorisationExpiry = authorisationExpiry;
	}

	public BigDecimal getCorporateAccountHolderOid() {
		return this.corporateAccountHolderOid;
	}

	public void setCorporateAccountHolderOid(BigDecimal corporateAccountHolderOid) {
		this.corporateAccountHolderOid = corporateAccountHolderOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	}

	public String getPrimary() {
		return this.primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public String getSecretCode() {
		return this.secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}