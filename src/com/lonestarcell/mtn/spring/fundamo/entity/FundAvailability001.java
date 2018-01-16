package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FUND_AVAILABILITY001 database table.
 * 
 */
@Entity
@Table(name="FUND_AVAILABILITY001")
@NamedQuery(name="FundAvailability001.findAll", query="SELECT f FROM FundAvailability001 f")
public class FundAvailability001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="AVAILABILITY_DATE")
	private Timestamp availabilityDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String status;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	public FundAvailability001() {
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

	public Timestamp getAvailabilityDate() {
		return this.availabilityDate;
	}

	public void setAvailabilityDate(Timestamp availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTransactionOid() {
		return this.transactionOid;
	}

	public void setTransactionOid(BigDecimal transactionOid) {
		this.transactionOid = transactionOid;
	}

}