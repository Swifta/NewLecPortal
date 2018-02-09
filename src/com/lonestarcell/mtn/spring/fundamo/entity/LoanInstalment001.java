package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LOAN_INSTALMENT001 database table.
 * 
 */
@Entity
@Table(name="LOAN_INSTALMENT001")
@NamedQuery(name="LoanInstalment001.findAll", query="SELECT l FROM LoanInstalment001 l")
public class LoanInstalment001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	private double amount;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String currency;

	@Column(name="END_WINDOW_PERIOD")
	private Timestamp endWindowPeriod;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LOAN_INSTALMENT_CONTRACT_OID")
	private BigDecimal loanInstalmentContractOid;

	@Column(name="NEXT_PAYMENT_DATE")
	private Timestamp nextPaymentDate;

	@Column(name="OUTSTANDING_AMOUNT")
	private double outstandingAmount;

	@Column(name="START_WINDOW_PERIOD")
	private Timestamp startWindowPeriod;

	private String status;

	public LoanInstalment001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BigDecimal getCreatedByOid() {
		return this.createdByOid;
	}

	public void setCreatedByOid(BigDecimal createdByOid) {
		this.createdByOid = createdByOid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getEndWindowPeriod() {
		return this.endWindowPeriod;
	}

	public void setEndWindowPeriod(Timestamp endWindowPeriod) {
		this.endWindowPeriod = endWindowPeriod;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLoanInstalmentContractOid() {
		return this.loanInstalmentContractOid;
	}

	public void setLoanInstalmentContractOid(BigDecimal loanInstalmentContractOid) {
		this.loanInstalmentContractOid = loanInstalmentContractOid;
	}

	public Timestamp getNextPaymentDate() {
		return this.nextPaymentDate;
	}

	public void setNextPaymentDate(Timestamp nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public double getOutstandingAmount() {
		return this.outstandingAmount;
	}

	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Timestamp getStartWindowPeriod() {
		return this.startWindowPeriod;
	}

	public void setStartWindowPeriod(Timestamp startWindowPeriod) {
		this.startWindowPeriod = startWindowPeriod;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}