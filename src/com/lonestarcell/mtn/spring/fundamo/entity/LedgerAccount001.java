package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LEDGER_ACCOUNT001 database table.
 * 
 */
@Entity
@Table(name="LEDGER_ACCOUNT001")
@NamedQuery(name="LedgerAccount001.findAll", query="SELECT l FROM LedgerAccount001 l")
public class LedgerAccount001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PROFILE_OID")
	private BigDecimal accountProfileOid;

	@Column(name="ALLOW_JOURNAL")
	private String allowJournal;

	private double balance;

	@Column(name="BANK_OID")
	private BigDecimal bankOid;

	private String code;

	private String currency;

	@Column(name="DATE_INTEREST_ACCRUED_TO")
	private Timestamp dateInterestAccruedTo;

	@Column(name="DATE_INTEREST_LAST_APPLIED")
	private Timestamp dateInterestLastApplied;

	@Column(name="INTEREST_ACCRUED_TO_DATE")
	private double interestAccruedToDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LEDGER_ACCOUNT_NUMBER")
	private String ledgerAccountNumber;

	private String name;

	@Column(name="NEXT_SUBSCRIPTION_DATE")
	private Timestamp nextSubscriptionDate;

	@Column(name="OWNED_BY_OID")
	private BigDecimal ownedByOid;

	@Column(name="OWNER_EMPLOYEE_VISIBLE")
	private String ownerEmployeeVisible;

	@Column(name="REGISTERED_IN_OID")
	private BigDecimal registeredInOid;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public LedgerAccount001() {
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

	public String getAllowJournal() {
		return this.allowJournal;
	}

	public void setAllowJournal(String allowJournal) {
		this.allowJournal = allowJournal;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public BigDecimal getBankOid() {
		return this.bankOid;
	}

	public void setBankOid(BigDecimal bankOid) {
		this.bankOid = bankOid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getLedgerAccountNumber() {
		return this.ledgerAccountNumber;
	}

	public void setLedgerAccountNumber(String ledgerAccountNumber) {
		this.ledgerAccountNumber = ledgerAccountNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getNextSubscriptionDate() {
		return this.nextSubscriptionDate;
	}

	public void setNextSubscriptionDate(Timestamp nextSubscriptionDate) {
		this.nextSubscriptionDate = nextSubscriptionDate;
	}

	public BigDecimal getOwnedByOid() {
		return this.ownedByOid;
	}

	public void setOwnedByOid(BigDecimal ownedByOid) {
		this.ownedByOid = ownedByOid;
	}

	public String getOwnerEmployeeVisible() {
		return this.ownerEmployeeVisible;
	}

	public void setOwnerEmployeeVisible(String ownerEmployeeVisible) {
		this.ownerEmployeeVisible = ownerEmployeeVisible;
	}

	public BigDecimal getRegisteredInOid() {
		return this.registeredInOid;
	}

	public void setRegisteredInOid(BigDecimal registeredInOid) {
		this.registeredInOid = registeredInOid;
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

}