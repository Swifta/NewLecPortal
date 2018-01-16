package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the COMMISSION001 database table.
 * 
 */
@Entity
@NamedQuery(name="Commission001.findAll", query="SELECT c FROM Commission001 c")
public class Commission001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String apply;

	@Column(name="CAPTURE_DATE")
	private Timestamp captureDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public Commission001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getApply() {
		return this.apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public Timestamp getCaptureDate() {
		return this.captureDate;
	}

	public void setCaptureDate(Timestamp captureDate) {
		this.captureDate = captureDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}