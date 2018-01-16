package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PENDING_AMOUNT001 database table.
 * 
 */
@Entity
@Table(name="PENDING_AMOUNT001")
@NamedQuery(name="PendingAmount001.findAll", query="SELECT p FROM PendingAmount001 p")
public class PendingAmount001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="FUND_RESERVATION_OID")
	private BigDecimal fundReservationOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public PendingAmount001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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