package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ACCOUNT_PROFILE001 database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_PROFILE001")
@NamedQuery(name="AccountProfile001.findAll", query="SELECT a FROM AccountProfile001 a")
public class AccountProfile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ATM_DEPOSIT_CLEAR_IN_DAYS")
	private BigDecimal atmDepositClearInDays;

	@Column(name="BRANCH_DEPOSIT_CLEAR_IN_DAYS")
	private BigDecimal branchDepositClearInDays;

	private String currency;

	@Column(name="DEFAULT_ACCOUNT_PROFILE")
	private String defaultAccountProfile;

	@Column(name="INTEREST_RATE")
	private double interestRate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_PROFILE_OID")
	private BigDecimal limitProfileOid;

	@Column(name="MAXIMUM_BALANCE")
	private double maximumBalance;

	@Column(name="MINIMUM_BALANCE")
	private double minimumBalance;

	private String name;

	@Column(name="ORIG_PAYEE_REV_INTEREST_RATE")
	private double origPayeeRevInterestRate;

	@Column(name="ORIG_PAYER_REV_INTEREST_RATE")
	private double origPayerRevInterestRate;

	@Column(name="PRODUCT_NAME")
	private String productName;

	private double subscription;

	public AccountProfile001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAtmDepositClearInDays() {
		return this.atmDepositClearInDays;
	}

	public void setAtmDepositClearInDays(BigDecimal atmDepositClearInDays) {
		this.atmDepositClearInDays = atmDepositClearInDays;
	}

	public BigDecimal getBranchDepositClearInDays() {
		return this.branchDepositClearInDays;
	}

	public void setBranchDepositClearInDays(BigDecimal branchDepositClearInDays) {
		this.branchDepositClearInDays = branchDepositClearInDays;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDefaultAccountProfile() {
		return this.defaultAccountProfile;
	}

	public void setDefaultAccountProfile(String defaultAccountProfile) {
		this.defaultAccountProfile = defaultAccountProfile;
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

	public BigDecimal getLimitProfileOid() {
		return this.limitProfileOid;
	}

	public void setLimitProfileOid(BigDecimal limitProfileOid) {
		this.limitProfileOid = limitProfileOid;
	}

	public double getMaximumBalance() {
		return this.maximumBalance;
	}

	public void setMaximumBalance(double maximumBalance) {
		this.maximumBalance = maximumBalance;
	}

	public double getMinimumBalance() {
		return this.minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOrigPayeeRevInterestRate() {
		return this.origPayeeRevInterestRate;
	}

	public void setOrigPayeeRevInterestRate(double origPayeeRevInterestRate) {
		this.origPayeeRevInterestRate = origPayeeRevInterestRate;
	}

	public double getOrigPayerRevInterestRate() {
		return this.origPayerRevInterestRate;
	}

	public void setOrigPayerRevInterestRate(double origPayerRevInterestRate) {
		this.origPayerRevInterestRate = origPayerRevInterestRate;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSubscription() {
		return this.subscription;
	}

	public void setSubscription(double subscription) {
		this.subscription = subscription;
	}

}