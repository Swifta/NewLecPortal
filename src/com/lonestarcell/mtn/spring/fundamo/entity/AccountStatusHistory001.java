package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ACCOUNT_STATUS_HISTORY001 database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_STATUS_HISTORY001")
@NamedQuery(name="AccountStatusHistory001.findAll", query="SELECT a FROM AccountStatusHistory001 a")
public class AccountStatusHistory001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_OID")
	private BigDecimal accountOid;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="ACCOUNT_STATUS_REASON_OID")
	private BigDecimal accountStatusReasonOid;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="EFFECTIVE_DATE")
	private Timestamp effectiveDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String reason;

	public AccountStatusHistory001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountOid() {
		return this.accountOid;
	}

	public void setAccountOid(BigDecimal accountOid) {
		this.accountOid = accountOid;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public BigDecimal getAccountStatusReasonOid() {
		return this.accountStatusReasonOid;
	}

	public void setAccountStatusReasonOid(BigDecimal accountStatusReasonOid) {
		this.accountStatusReasonOid = accountStatusReasonOid;
	}

	public BigDecimal getCreatedByOid() {
		return this.createdByOid;
	}

	public void setCreatedByOid(BigDecimal createdByOid) {
		this.createdByOid = createdByOid;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}