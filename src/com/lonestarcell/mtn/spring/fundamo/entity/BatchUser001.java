package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BATCH_USER001 database table.
 * 
 */
@Entity
@Table(name="BATCH_USER001")
@NamedQuery(name="BatchUser001.findAll", query="SELECT b FROM BatchUser001 b")
public class BatchUser001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_HOLDER_OID")
	private BigDecimal accountHolderOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NEXT_USER_BATCH_NUMBER")
	private String nextUserBatchNumber;

	@Column(name="USER_NUMBER")
	private String userNumber;

	public BatchUser001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountHolderOid() {
		return this.accountHolderOid;
	}

	public void setAccountHolderOid(BigDecimal accountHolderOid) {
		this.accountHolderOid = accountHolderOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNextUserBatchNumber() {
		return this.nextUserBatchNumber;
	}

	public void setNextUserBatchNumber(String nextUserBatchNumber) {
		this.nextUserBatchNumber = nextUserBatchNumber;
	}

	public String getUserNumber() {
		return this.userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

}