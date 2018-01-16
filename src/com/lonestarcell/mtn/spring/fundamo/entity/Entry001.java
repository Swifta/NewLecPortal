package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ENTRY001 database table.
 * 
 */
@Entity
@NamedQuery(name="Entry001.findAll", query="SELECT e FROM Entry001 e")
public class Entry001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_OID")
	private BigDecimal accountOid;

	private double amount;

	private String description;

	@Column(name="ENTRY_CODE")
	private String entryCode;

	@Column(name="ENTRY_DATE")
	private Timestamp entryDate;

	@Column(name="ENTRY_TYPE_OID")
	private BigDecimal entryTypeOid;

	private String grouped;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="TRANSACTION_NUMBER")
	private BigDecimal transactionNumber;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	public Entry001() {
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

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntryCode() {
		return this.entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	public Timestamp getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public BigDecimal getEntryTypeOid() {
		return this.entryTypeOid;
	}

	public void setEntryTypeOid(BigDecimal entryTypeOid) {
		this.entryTypeOid = entryTypeOid;
	}

	public String getGrouped() {
		return this.grouped;
	}

	public void setGrouped(String grouped) {
		this.grouped = grouped;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(BigDecimal transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public BigDecimal getTransactionOid() {
		return this.transactionOid;
	}

	public void setTransactionOid(BigDecimal transactionOid) {
		this.transactionOid = transactionOid;
	}

}