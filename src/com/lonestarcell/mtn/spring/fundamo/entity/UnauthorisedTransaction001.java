package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the UNAUTHORISED_TRANSACTION001 database table.
 * 
 */
@Entity
@Table(name="UNAUTHORISED_TRANSACTION001")
@NamedQuery(name="UnauthorisedTransaction001.findAll", query="SELECT u FROM UnauthorisedTransaction001 u")
public class UnauthorisedTransaction001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="AUTHORISER_SPECIFIED")
	private String authoriserSpecified;

	@Column(name="CAPTURED_DATE")
	private Timestamp capturedDate;

	@Column(name="CAPTURER_OID")
	private BigDecimal capturerOid;

	@Column(name="CORPORATE_ACCOUNT_HANDLER_OID")
	private BigDecimal corporateAccountHandlerOid;

	private String currency;

	@Column(name="DECLINED_DATE")
	private Timestamp declinedDate;

	@Column(name="DECLINER_OID")
	private BigDecimal declinerOid;

	@Column(name="DESTINATION_NUMBER")
	private String destinationNumber;

	@Column(name="EXPIRES_ON")
	private Timestamp expiresOn;

	@Column(name="FIRST_AUTH_SKIPPED_BY_OID")
	private BigDecimal firstAuthSkippedByOid;

	@Column(name="FIRST_AUTHORISED_DATE")
	private Timestamp firstAuthorisedDate;

	@Column(name="FIRST_AUTHORISER_OID")
	private BigDecimal firstAuthoriserOid;

	@Column(name="IMMEDIATE_AUTH")
	private String immediateAuth;

	@Column(name="JOURNAL_DESCRIPTION")
	private String journalDescription;

	@Column(name="JOURNAL_REASON")
	private String journalReason;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NOTIFY_PAYEE")
	private String notifyPayee;

	@Column(name="PAYEE_ACCOUNT_IDENTIFIER")
	private String payeeAccountIdentifier;

	@Column(name="PAYER_ACCOUNT_IDENTIFIER")
	private String payerAccountIdentifier;

	private String reference;

	@Column(name="REJECTION_REASON")
	private String rejectionReason;

	@Column(name="SECOND_AUTH_SKIPPED_BY_OID")
	private BigDecimal secondAuthSkippedByOid;

	@Column(name="SECOND_AUTHORISED_DATE")
	private Timestamp secondAuthorisedDate;

	@Column(name="SECOND_AUTHORISER_OID")
	private BigDecimal secondAuthoriserOid;

	private String status;

	@Column(name="TRANSACTION_AUTHORISATION_OID")
	private BigDecimal transactionAuthorisationOid;

	@Column(name="TRANSACTION_NUMBER")
	private String transactionNumber;

	@Column(name="TRANSACTION_TYPE")
	private String transactionType;

	private String unit;

	public UnauthorisedTransaction001() {
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

	public String getAuthoriserSpecified() {
		return this.authoriserSpecified;
	}

	public void setAuthoriserSpecified(String authoriserSpecified) {
		this.authoriserSpecified = authoriserSpecified;
	}

	public Timestamp getCapturedDate() {
		return this.capturedDate;
	}

	public void setCapturedDate(Timestamp capturedDate) {
		this.capturedDate = capturedDate;
	}

	public BigDecimal getCapturerOid() {
		return this.capturerOid;
	}

	public void setCapturerOid(BigDecimal capturerOid) {
		this.capturerOid = capturerOid;
	}

	public BigDecimal getCorporateAccountHandlerOid() {
		return this.corporateAccountHandlerOid;
	}

	public void setCorporateAccountHandlerOid(BigDecimal corporateAccountHandlerOid) {
		this.corporateAccountHandlerOid = corporateAccountHandlerOid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getDeclinedDate() {
		return this.declinedDate;
	}

	public void setDeclinedDate(Timestamp declinedDate) {
		this.declinedDate = declinedDate;
	}

	public BigDecimal getDeclinerOid() {
		return this.declinerOid;
	}

	public void setDeclinerOid(BigDecimal declinerOid) {
		this.declinerOid = declinerOid;
	}

	public String getDestinationNumber() {
		return this.destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public Timestamp getExpiresOn() {
		return this.expiresOn;
	}

	public void setExpiresOn(Timestamp expiresOn) {
		this.expiresOn = expiresOn;
	}

	public BigDecimal getFirstAuthSkippedByOid() {
		return this.firstAuthSkippedByOid;
	}

	public void setFirstAuthSkippedByOid(BigDecimal firstAuthSkippedByOid) {
		this.firstAuthSkippedByOid = firstAuthSkippedByOid;
	}

	public Timestamp getFirstAuthorisedDate() {
		return this.firstAuthorisedDate;
	}

	public void setFirstAuthorisedDate(Timestamp firstAuthorisedDate) {
		this.firstAuthorisedDate = firstAuthorisedDate;
	}

	public BigDecimal getFirstAuthoriserOid() {
		return this.firstAuthoriserOid;
	}

	public void setFirstAuthoriserOid(BigDecimal firstAuthoriserOid) {
		this.firstAuthoriserOid = firstAuthoriserOid;
	}

	public String getImmediateAuth() {
		return this.immediateAuth;
	}

	public void setImmediateAuth(String immediateAuth) {
		this.immediateAuth = immediateAuth;
	}

	public String getJournalDescription() {
		return this.journalDescription;
	}

	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}

	public String getJournalReason() {
		return this.journalReason;
	}

	public void setJournalReason(String journalReason) {
		this.journalReason = journalReason;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNotifyPayee() {
		return this.notifyPayee;
	}

	public void setNotifyPayee(String notifyPayee) {
		this.notifyPayee = notifyPayee;
	}

	public String getPayeeAccountIdentifier() {
		return this.payeeAccountIdentifier;
	}

	public void setPayeeAccountIdentifier(String payeeAccountIdentifier) {
		this.payeeAccountIdentifier = payeeAccountIdentifier;
	}

	public String getPayerAccountIdentifier() {
		return this.payerAccountIdentifier;
	}

	public void setPayerAccountIdentifier(String payerAccountIdentifier) {
		this.payerAccountIdentifier = payerAccountIdentifier;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRejectionReason() {
		return this.rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public BigDecimal getSecondAuthSkippedByOid() {
		return this.secondAuthSkippedByOid;
	}

	public void setSecondAuthSkippedByOid(BigDecimal secondAuthSkippedByOid) {
		this.secondAuthSkippedByOid = secondAuthSkippedByOid;
	}

	public Timestamp getSecondAuthorisedDate() {
		return this.secondAuthorisedDate;
	}

	public void setSecondAuthorisedDate(Timestamp secondAuthorisedDate) {
		this.secondAuthorisedDate = secondAuthorisedDate;
	}

	public BigDecimal getSecondAuthoriserOid() {
		return this.secondAuthoriserOid;
	}

	public void setSecondAuthoriserOid(BigDecimal secondAuthoriserOid) {
		this.secondAuthoriserOid = secondAuthoriserOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTransactionAuthorisationOid() {
		return this.transactionAuthorisationOid;
	}

	public void setTransactionAuthorisationOid(BigDecimal transactionAuthorisationOid) {
		this.transactionAuthorisationOid = transactionAuthorisationOid;
	}

	public String getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}