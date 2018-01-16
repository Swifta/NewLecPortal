package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the REBATE_VALUE001 database table.
 * 
 */
@Entity
@Table(name="REBATE_VALUE001")
@NamedQuery(name="RebateValue001.findAll", query="SELECT r FROM RebateValue001 r")
public class RebateValue001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PROFILE_OID")
	private BigDecimal accountProfileOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MAX_AMOUNT")
	private double maxAmount;

	@Column(name="MIN_AMOUNT")
	private double minAmount;

	private double percentage;

	@Column(name="REBATE_OID")
	private BigDecimal rebateOid;

	public RebateValue001() {
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

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getRebateOid() {
		return this.rebateOid;
	}

	public void setRebateOid(BigDecimal rebateOid) {
		this.rebateOid = rebateOid;
	}

}