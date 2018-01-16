package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SCHEDULED_SETTLEMENT001 database table.
 * 
 */
@Entity
@Table(name="SCHEDULED_SETTLEMENT001")
@NamedQuery(name="ScheduledSettlement001.findAll", query="SELECT s FROM ScheduledSettlement001 s")
public class ScheduledSettlement001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String currency;

	@Column(name="END_DATE")
	private Timestamp endDate;

	@Column(name="LAST_TRANSACTION_DATE")
	private Timestamp lastTransactionDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NEXT_TRANSACTION_DATE")
	private Timestamp nextTransactionDate;

	private String reference;

	@Column(name="SETTLEMENT_OID")
	private BigDecimal settlementOid;

	@Column(name="START_DATE")
	private Timestamp startDate;

	private String status;

	public ScheduledSettlement001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getLastTransactionDate() {
		return this.lastTransactionDate;
	}

	public void setLastTransactionDate(Timestamp lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getNextTransactionDate() {
		return this.nextTransactionDate;
	}

	public void setNextTransactionDate(Timestamp nextTransactionDate) {
		this.nextTransactionDate = nextTransactionDate;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BigDecimal getSettlementOid() {
		return this.settlementOid;
	}

	public void setSettlementOid(BigDecimal settlementOid) {
		this.settlementOid = settlementOid;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}