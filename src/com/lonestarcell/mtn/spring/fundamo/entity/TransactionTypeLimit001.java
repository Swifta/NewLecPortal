package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TRANSACTION_TYPE_LIMIT001 database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_TYPE_LIMIT001")
@NamedQuery(name="TransactionTypeLimit001.findAll", query="SELECT t FROM TransactionTypeLimit001 t")
public class TransactionTypeLimit001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_OID")
	private BigDecimal limitOid;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public TransactionTypeLimit001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLimitOid() {
		return this.limitOid;
	}

	public void setLimitOid(BigDecimal limitOid) {
		this.limitOid = limitOid;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}