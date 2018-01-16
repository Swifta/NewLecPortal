package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FUND_RESERVATION001 database table.
 * 
 */
@Entity
@Table(name="FUND_RESERVATION001")
@NamedQuery(name="FundReservation001.findAll", query="SELECT f FROM FundReservation001 f")
public class FundReservation001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="AUTHORISATION_CODE")
	private String authorisationCode;

	@Column(name="CARD_ACCEPTOR_NAME_LOCATION")
	private String cardAcceptorNameLocation;

	@Column(name="CLASS_TYPE")
	private BigDecimal classType;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String currency;

	@Column(name="EXTERNAL_ACCOUNT_NAME")
	private String externalAccountName;

	@Column(name="EXTERNAL_ACCOUNT_NUMBER")
	private String externalAccountNumber;

	@Column(name="EXTERNAL_NUMBER")
	private BigDecimal externalNumber;

	@Column(name="FUND_RESERVATION_NUMBER")
	private BigDecimal fundReservationNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LOCAL_TRANSACTION_DATE")
	private Timestamp localTransactionDate;

	@Column(name="PAYEE_ACCOUNT_NAME")
	private String payeeAccountName;

	@Column(name="PAYEE_ACCOUNT_OID")
	private BigDecimal payeeAccountOid;

	@Column(name="PAYER_ACCOUNT_OID")
	private BigDecimal payerAccountOid;

	private String reference;

	private double remaining;

	private String status;

	@Column(name="TRACE_ID")
	private String traceId;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public FundReservation001() {
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

	public String getAuthorisationCode() {
		return this.authorisationCode;
	}

	public void setAuthorisationCode(String authorisationCode) {
		this.authorisationCode = authorisationCode;
	}

	public String getCardAcceptorNameLocation() {
		return this.cardAcceptorNameLocation;
	}

	public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
		this.cardAcceptorNameLocation = cardAcceptorNameLocation;
	}

	public BigDecimal getClassType() {
		return this.classType;
	}

	public void setClassType(BigDecimal classType) {
		this.classType = classType;
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

	public String getExternalAccountName() {
		return this.externalAccountName;
	}

	public void setExternalAccountName(String externalAccountName) {
		this.externalAccountName = externalAccountName;
	}

	public String getExternalAccountNumber() {
		return this.externalAccountNumber;
	}

	public void setExternalAccountNumber(String externalAccountNumber) {
		this.externalAccountNumber = externalAccountNumber;
	}

	public BigDecimal getExternalNumber() {
		return this.externalNumber;
	}

	public void setExternalNumber(BigDecimal externalNumber) {
		this.externalNumber = externalNumber;
	}

	public BigDecimal getFundReservationNumber() {
		return this.fundReservationNumber;
	}

	public void setFundReservationNumber(BigDecimal fundReservationNumber) {
		this.fundReservationNumber = fundReservationNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getLocalTransactionDate() {
		return this.localTransactionDate;
	}

	public void setLocalTransactionDate(Timestamp localTransactionDate) {
		this.localTransactionDate = localTransactionDate;
	}

	public String getPayeeAccountName() {
		return this.payeeAccountName;
	}

	public void setPayeeAccountName(String payeeAccountName) {
		this.payeeAccountName = payeeAccountName;
	}

	public BigDecimal getPayeeAccountOid() {
		return this.payeeAccountOid;
	}

	public void setPayeeAccountOid(BigDecimal payeeAccountOid) {
		this.payeeAccountOid = payeeAccountOid;
	}

	public BigDecimal getPayerAccountOid() {
		return this.payerAccountOid;
	}

	public void setPayerAccountOid(BigDecimal payerAccountOid) {
		this.payerAccountOid = payerAccountOid;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getRemaining() {
		return this.remaining;
	}

	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTraceId() {
		return this.traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}