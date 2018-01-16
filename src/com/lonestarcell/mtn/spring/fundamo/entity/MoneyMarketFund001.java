package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MONEY_MARKET_FUND001 database table.
 * 
 */
@Entity
@Table(name="MONEY_MARKET_FUND001")
@NamedQuery(name="MoneyMarketFund001.findAll", query="SELECT m FROM MoneyMarketFund001 m")
public class MoneyMarketFund001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ALLOWABLE_PERCENTAGE_DIFF")
	private double allowablePercentageDiff;

	@Column(name="BIN_OID")
	private BigDecimal binOid;

	@Column(name="DATE_INTEREST_LAST_ACCRUED")
	private Timestamp dateInterestLastAccrued;

	@Column(name="DATE_INTEREST_LAST_CAPITALISED")
	private Timestamp dateInterestLastCapitalised;

	@Column(name="INTEREST_ACCOUNT_NUMBER")
	private String interestAccountNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public MoneyMarketFund001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getAllowablePercentageDiff() {
		return this.allowablePercentageDiff;
	}

	public void setAllowablePercentageDiff(double allowablePercentageDiff) {
		this.allowablePercentageDiff = allowablePercentageDiff;
	}

	public BigDecimal getBinOid() {
		return this.binOid;
	}

	public void setBinOid(BigDecimal binOid) {
		this.binOid = binOid;
	}

	public Timestamp getDateInterestLastAccrued() {
		return this.dateInterestLastAccrued;
	}

	public void setDateInterestLastAccrued(Timestamp dateInterestLastAccrued) {
		this.dateInterestLastAccrued = dateInterestLastAccrued;
	}

	public Timestamp getDateInterestLastCapitalised() {
		return this.dateInterestLastCapitalised;
	}

	public void setDateInterestLastCapitalised(Timestamp dateInterestLastCapitalised) {
		this.dateInterestLastCapitalised = dateInterestLastCapitalised;
	}

	public String getInterestAccountNumber() {
		return this.interestAccountNumber;
	}

	public void setInterestAccountNumber(String interestAccountNumber) {
		this.interestAccountNumber = interestAccountNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}