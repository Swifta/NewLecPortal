package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LIMIT_AMOUNT001 database table.
 * 
 */
@Entity
@Table(name="LIMIT_AMOUNT001")
@NamedQuery(name="LimitAmount001.findAll", query="SELECT l FROM LimitAmount001 l")
public class LimitAmount001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_OID")
	private BigDecimal limitOid;

	@Column(name="LIMIT_PROFILE_OID")
	private BigDecimal limitProfileOid;

	@Column(name="MAX_AMOUNT")
	private double maxAmount;

	@Column(name="MIN_AMOUNT")
	private double minAmount;

	public LimitAmount001() {
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

	public BigDecimal getLimitProfileOid() {
		return this.limitProfileOid;
	}

	public void setLimitProfileOid(BigDecimal limitProfileOid) {
		this.limitProfileOid = limitProfileOid;
	}

	public double getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public double getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

}