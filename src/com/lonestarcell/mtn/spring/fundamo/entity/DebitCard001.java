package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DEBIT_CARD001 database table.
 * 
 */
@Entity
@Table(name="DEBIT_CARD001")
@NamedQuery(name="DebitCard001.findAll", query="SELECT d FROM DebitCard001 d")
public class DebitCard001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BLOCKED_FOR_POS")
	private String blockedForPos;

	@Column(name="EXPIRY_DATE")
	private Timestamp expiryDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String num;

	private String offset;

	private String status;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public DebitCard001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBlockedForPos() {
		return this.blockedForPos;
	}

	public void setBlockedForPos(String blockedForPos) {
		this.blockedForPos = blockedForPos;
	}

	public Timestamp getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOffset() {
		return this.offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}