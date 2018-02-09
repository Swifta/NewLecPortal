package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MONEY_MARKET_FUND_INTERESTS001 database table.
 * 
 */
@Entity
@Table(name="MONEY_MARKET_FUND_INTERESTS001")
@NamedQuery(name="MoneyMarketFundInterests001.findAll", query="SELECT m FROM MoneyMarketFundInterests001 m")
public class MoneyMarketFundInterests001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DAILY_TOTAL")
	private double dailyTotal;

	@Column(name="INTEREST_DATE")
	private Timestamp interestDate;

	@Column(name="INTEREST_RATE")
	private double interestRate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MONEY_MARKET_FUND_OID")
	private BigDecimal moneyMarketFundOid;

	private String status;

	public MoneyMarketFundInterests001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getDailyTotal() {
		return this.dailyTotal;
	}

	public void setDailyTotal(double dailyTotal) {
		this.dailyTotal = dailyTotal;
	}

	public Timestamp getInterestDate() {
		return this.interestDate;
	}

	public void setInterestDate(Timestamp interestDate) {
		this.interestDate = interestDate;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMoneyMarketFundOid() {
		return this.moneyMarketFundOid;
	}

	public void setMoneyMarketFundOid(BigDecimal moneyMarketFundOid) {
		this.moneyMarketFundOid = moneyMarketFundOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}