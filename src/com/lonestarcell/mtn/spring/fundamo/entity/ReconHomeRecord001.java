package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RECON_HOME_RECORD001 database table.
 * 
 */
@Entity
@Table(name="RECON_HOME_RECORD001")
@NamedQuery(name="ReconHomeRecord001.findAll", query="SELECT r FROM ReconHomeRecord001 r")
public class ReconHomeRecord001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_IDENTIFIER")
	private String accountIdentifier;

	private double amount;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String reference;

	private String status;

	@Column(name="TO_DATE")
	private Timestamp toDate;

	@Column(name="TRANSACTION_ID")
	private BigDecimal transactionId;

	@Column(name="TRANSACTION_TYPE")
	private String transactionType;

	@Column(name="\"TYPE\"")
	private String type;

	public ReconHomeRecord001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountIdentifier() {
		return this.accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getToDate() {
		return this.toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(BigDecimal transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}