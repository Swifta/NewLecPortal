package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_ACCOUNT_LIMIT001 database table.
 * 
 */
@Entity
@Table(name="USER_ACCOUNT_LIMIT001")
@NamedQuery(name="UserAccountLimit001.findAll", query="SELECT u FROM UserAccountLimit001 u")
public class UserAccountLimit001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AMOUNT_REMAINING")
	private double amountRemaining;

	@Column(name="DAILY_LIMIT")
	private String dailyLimit;

	private Timestamp dte;

	@Column(name="INDIVIDUAL_LIMIT_AMOUNT")
	private double individualLimitAmount;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_OID")
	private BigDecimal limitOid;

	@Column(name="PROFILE_LIMIT")
	private String profileLimit;

	private String synchronised;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public UserAccountLimit001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getAmountRemaining() {
		return this.amountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public String getDailyLimit() {
		return this.dailyLimit;
	}

	public void setDailyLimit(String dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public Timestamp getDte() {
		return this.dte;
	}

	public void setDte(Timestamp dte) {
		this.dte = dte;
	}

	public double getIndividualLimitAmount() {
		return this.individualLimitAmount;
	}

	public void setIndividualLimitAmount(double individualLimitAmount) {
		this.individualLimitAmount = individualLimitAmount;
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

	public String getProfileLimit() {
		return this.profileLimit;
	}

	public void setProfileLimit(String profileLimit) {
		this.profileLimit = profileLimit;
	}

	public String getSynchronised() {
		return this.synchronised;
	}

	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}