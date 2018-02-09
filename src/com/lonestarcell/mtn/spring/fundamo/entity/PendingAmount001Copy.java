package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PENDING_AMOUNT001_COPY database table.
 * 
 */
// @Entity
@Table(name="PENDING_AMOUNT001_COPY")
@NamedQuery(name="PendingAmount001Copy.findAll", query="SELECT p FROM PendingAmount001Copy p")
public class PendingAmount001Copy implements Serializable {
	private static final long serialVersionUID = 1L;

	private double amount;

	@Column(name="FUND_RESERVATION_OID")
	private BigDecimal fundReservationOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public PendingAmount001Copy() {
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BigDecimal getFundReservationOid() {
		return this.fundReservationOid;
	}

	public void setFundReservationOid(BigDecimal fundReservationOid) {
		this.fundReservationOid = fundReservationOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getTransactionOid() {
		return this.transactionOid;
	}

	public void setTransactionOid(BigDecimal transactionOid) {
		this.transactionOid = transactionOid;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}