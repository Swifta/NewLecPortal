package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SETTLEMENT_BALANCE001 database table.
 * 
 */
@Entity
@Table(name="SETTLEMENT_BALANCE001")
@NamedQuery(name="SettlementBalance001.findAll", query="SELECT s FROM SettlementBalance001 s")
public class SettlementBalance001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double balance;

	@Column(name="FROM_DATE")
	private Timestamp fromDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SETTLEMENT_OID")
	private BigDecimal settlementOid;

	private String status;

	@Column(name="TO_DATE")
	private Timestamp toDate;

	@Column(name="UNAUTHORISED_TRANSACTION_OID")
	private BigDecimal unauthorisedTransactionOid;

	public SettlementBalance001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Timestamp getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getSettlementOid() {
		return this.settlementOid;
	}

	public void setSettlementOid(BigDecimal settlementOid) {
		this.settlementOid = settlementOid;
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

	public BigDecimal getUnauthorisedTransactionOid() {
		return this.unauthorisedTransactionOid;
	}

	public void setUnauthorisedTransactionOid(BigDecimal unauthorisedTransactionOid) {
		this.unauthorisedTransactionOid = unauthorisedTransactionOid;
	}

}