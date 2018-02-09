package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MICRO_PAYMENT001 database table.
 * 
 */
@Entity
@Table(name="MICRO_PAYMENT001")
@NamedQuery(name="MicroPayment001.findAll", query="SELECT m FROM MicroPayment001 m")
public class MicroPayment001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PROFILE_OID")
	private BigDecimal accountProfileOid;

	private double amount;

	private double fee;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public MicroPayment001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountProfileOid() {
		return this.accountProfileOid;
	}

	public void setAccountProfileOid(BigDecimal accountProfileOid) {
		this.accountProfileOid = accountProfileOid;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return this.fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}