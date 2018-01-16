package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SCHEDULED_TRANSACTION001 database table.
 * 
 */
@Entity
@Table(name="SCHEDULED_TRANSACTION001")
@NamedQuery(name="ScheduledTransaction001.findAll", query="SELECT s FROM ScheduledTransaction001 s")
public class ScheduledTransaction001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name="ACCOUNT_REFERENCE")
	private String accountReference;

	private double amount;

	@Column(name="BENEFICIARY_DESCRIPTION")
	private String beneficiaryDescription;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String currency;

	@Column(name="END_DATE")
	private Timestamp endDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NEXT_PAYMENT_DATE")
	private Timestamp nextPaymentDate;

	@Column(name="REPEAT_DAY")
	private BigDecimal repeatDay;

	@Column(name="REPEAT_PATTERN")
	private BigDecimal repeatPattern;

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	@Column(name="SHORT_NAME")
	private String shortName;

	@Column(name="START_DATE")
	private Timestamp startDate;

	private String status;

	public ScheduledTransaction001() {
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

	public String getAccountReference() {
		return this.accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBeneficiaryDescription() {
		return this.beneficiaryDescription;
	}

	public void setBeneficiaryDescription(String beneficiaryDescription) {
		this.beneficiaryDescription = beneficiaryDescription;
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

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getNextPaymentDate() {
		return this.nextPaymentDate;
	}

	public void setNextPaymentDate(Timestamp nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public BigDecimal getRepeatDay() {
		return this.repeatDay;
	}

	public void setRepeatDay(BigDecimal repeatDay) {
		this.repeatDay = repeatDay;
	}

	public BigDecimal getRepeatPattern() {
		return this.repeatPattern;
	}

	public void setRepeatPattern(BigDecimal repeatPattern) {
		this.repeatPattern = repeatPattern;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}