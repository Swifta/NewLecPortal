package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the USER_ACCOUNT001 database table.
 * 
 */
@Entity
@Table(name="USER_ACCOUNT001")
@NamedQuery(name="UserAccount001.findAll", query="SELECT u FROM UserAccount001 u")
@Proxy(lazy = false)
public class UserAccount001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	// @Column(name="ACCOUNT_HOLDER_OID")
	// private BigDecimal accountHolderOid;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn(name="ACCOUNT_HOLDER_OID")
	private CorporateAccountHolder001 corporateAccountHolder001;

	@Column(name="ACCOUNT_PROFILE_OID")
	private BigDecimal accountProfileOid;

	private double balance;

	@Column(name="CASHOUT_STATUS")
	private String cashoutStatus;

	@Column(name="CATEGORY_OID")
	private BigDecimal categoryOid;

	private String currency;

	@Column(name="DATE_INTEREST_ACCRUED_TO")
	private Timestamp dateInterestAccruedTo;

	@Column(name="DATE_INTEREST_LAST_APPLIED")
	private Timestamp dateInterestLastApplied;

	@Column(name="INTEREST_ACCRUED_TO_DATE")
	private double interestAccruedToDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MONEY_MARKET_FUND_OID")
	private BigDecimal moneyMarketFundOid;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	@Column(name="NEXT_MONEY_MARKET_FUND_OID")
	private BigDecimal nextMoneyMarketFundOid;

	@Column(name="NEXT_SUBSCRIPTION_DATE")
	private Timestamp nextSubscriptionDate;

	@Column(name="PRIORITY_PAYMENTS_DUE")
	private double priorityPaymentsDue;

	@Column(name="PRIORITY_PAYMENTS_RESERVED")
	private double priorityPaymentsReserved;

	@Column(name="REGISTERED_IN_OID")
	private BigDecimal registeredInOid;

	@Column(name="RESET_DATE")
	private Timestamp resetDate;

	@Column(name="SHORT_NAME")
	private String shortName;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_ACCOUNT_NUMBER")
	private String userAccountNumber;
	
	@OneToMany( mappedBy = "userAccount001", fetch = FetchType.LAZY )
	private List< Entry001 > entries;
	
	@OneToOne( mappedBy = "userAccount001", fetch = FetchType.LAZY )
	private AccountIdentifier001 AccountIdentifier001;

	public UserAccount001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	/*
	public BigDecimal getAccountHolderOid() {
		return this.accountHolderOid;
	}

	public void setAccountHolderOid(BigDecimal accountHolderOid) {
		this.accountHolderOid = accountHolderOid;
	} */
	
	
	
	

	public BigDecimal getAccountProfileOid() {
		return this.accountProfileOid;
	}

	public AccountIdentifier001 getAccountIdentifier001() {
		return AccountIdentifier001;
	}

	public void setAccountIdentifier001(AccountIdentifier001 accountIdentifier001) {
		AccountIdentifier001 = accountIdentifier001;
	}

	public CorporateAccountHolder001 getCorporateAccountHolder001() {
		return corporateAccountHolder001;
	}

	public void setCorporateAccountHolder001(
			CorporateAccountHolder001 corporateAccountHolder001) {
		this.corporateAccountHolder001 = corporateAccountHolder001;
	}

	public void setAccountProfileOid(BigDecimal accountProfileOid) {
		this.accountProfileOid = accountProfileOid;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCashoutStatus() {
		return this.cashoutStatus;
	}

	public void setCashoutStatus(String cashoutStatus) {
		this.cashoutStatus = cashoutStatus;
	}

	public BigDecimal getCategoryOid() {
		return this.categoryOid;
	}

	public void setCategoryOid(BigDecimal categoryOid) {
		this.categoryOid = categoryOid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getDateInterestAccruedTo() {
		return this.dateInterestAccruedTo;
	}

	public void setDateInterestAccruedTo(Timestamp dateInterestAccruedTo) {
		this.dateInterestAccruedTo = dateInterestAccruedTo;
	}

	public Timestamp getDateInterestLastApplied() {
		return this.dateInterestLastApplied;
	}

	public void setDateInterestLastApplied(Timestamp dateInterestLastApplied) {
		this.dateInterestLastApplied = dateInterestLastApplied;
	}

	public double getInterestAccruedToDate() {
		return this.interestAccruedToDate;
	}

	public void setInterestAccruedToDate(double interestAccruedToDate) {
		this.interestAccruedToDate = interestAccruedToDate;
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

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public BigDecimal getNextMoneyMarketFundOid() {
		return this.nextMoneyMarketFundOid;
	}

	public void setNextMoneyMarketFundOid(BigDecimal nextMoneyMarketFundOid) {
		this.nextMoneyMarketFundOid = nextMoneyMarketFundOid;
	}

	public Timestamp getNextSubscriptionDate() {
		return this.nextSubscriptionDate;
	}

	public void setNextSubscriptionDate(Timestamp nextSubscriptionDate) {
		this.nextSubscriptionDate = nextSubscriptionDate;
	}

	public double getPriorityPaymentsDue() {
		return this.priorityPaymentsDue;
	}

	public void setPriorityPaymentsDue(double priorityPaymentsDue) {
		this.priorityPaymentsDue = priorityPaymentsDue;
	}

	public double getPriorityPaymentsReserved() {
		return this.priorityPaymentsReserved;
	}

	public void setPriorityPaymentsReserved(double priorityPaymentsReserved) {
		this.priorityPaymentsReserved = priorityPaymentsReserved;
	}

	public BigDecimal getRegisteredInOid() {
		return this.registeredInOid;
	}

	public void setRegisteredInOid(BigDecimal registeredInOid) {
		this.registeredInOid = registeredInOid;
	}

	public Timestamp getResetDate() {
		return this.resetDate;
	}

	public void setResetDate(Timestamp resetDate) {
		this.resetDate = resetDate;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserAccountNumber() {
		return this.userAccountNumber;
	}

	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}

	public List<Entry001> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry001> entries) {
		this.entries = entries;
	}
	
	

}