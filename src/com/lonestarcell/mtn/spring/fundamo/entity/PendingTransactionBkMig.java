package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PENDING_TRANSACTION_BK_MIG database table.
 * 
 */
@Entity
@Table(name="PENDING_TRANSACTION_BK_MIG")
@NamedQuery(name="PendingTransactionBkMig.findAll", query="SELECT p FROM PendingTransactionBkMig p")
public class PendingTransactionBkMig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_IDENTIFIER")
	private String accessIdentifier;

	@Column(name="BANK_DETAILS_OID")
	private BigDecimal bankDetailsOid;

	private String channel;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="CURRENCY_CODE_TRANSACTION")
	private String currencyCodeTransaction;

	@Column(name="DETAILED_DESCRIPTION")
	private String detailedDescription;

	@Column(name="EASYPAY_POS")
	private String easypayPos;

	@Column(name="EXTERNAL_ACCOUNT_NAME")
	private String externalAccountName;

	@Column(name="EXTERNAL_ACCOUNT_NUMBER")
	private String externalAccountNumber;

	@Column(name="EXTERNAL_ACCOUNT_TYPE")
	private String externalAccountType;

	@Column(name="EXTERNAL_BRANCH_CODE")
	private String externalBranchCode;

	@Column(name="EXTERNAL_REFERENCE_NUMBER")
	private String externalReferenceNumber;

	@Column(name="EXTERNAL_TILL_NUMBER")
	private String externalTillNumber;

	@Column(name="EXTERNAL_TRANSACTION_NUMBER")
	private BigDecimal externalTransactionNumber;

	private String extracted;

	@Column(name="FUND_RESERVATION_OID")
	private BigDecimal fundReservationOid;

	@Column(name="JOURNAL_REASON_OID")
	private BigDecimal journalReasonOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="\"LOCAL\"")
	private String local;

	private BigDecimal oid;

	@Column(name="ON_US")
	private String onUs;

	@Column(name="ORIGINAL_UNCLEARED_AMOUNT")
	private double originalUnclearedAmount;

	@Column(name="PAYEE_ACCOUNT_NUMBER")
	private String payeeAccountNumber;

	@Column(name="PAYEE_ACCOUNT_OID")
	private BigDecimal payeeAccountOid;

	@Column(name="PAYEE_AMOUNT")
	private double payeeAmount;

	@Column(name="PAYEE_CURRENCY")
	private String payeeCurrency;

	@Column(name="PAYER_ACCOUNT_NUMBER")
	private String payerAccountNumber;

	@Column(name="PAYER_ACCOUNT_OID")
	private BigDecimal payerAccountOid;

	@Column(name="PAYER_AMOUNT")
	private double payerAmount;

	@Column(name="PAYER_CURRENCY")
	private String payerCurrency;

	@Column(name="PENDING_PAYEE_AMOUNT")
	private double pendingPayeeAmount;

	@Column(name="PENDING_PAYER_AMOUNT")
	private double pendingPayerAmount;

	@Column(name="RECONCILED_ON")
	private Timestamp reconciledOn;

	private String reference;

	@Column(name="REJECTION_REASON")
	private String rejectionReason;

	@Column(name="REVERSAL_REFERENCE")
	private String reversalReference;

	@Column(name="REVERSED_BY_OID")
	private BigDecimal reversedByOid;

	@Column(name="SCHEDULED_TRANSACTION_OID")
	private BigDecimal scheduledTransactionOid;

	@Column(name="SERVICE_OID")
	private BigDecimal serviceOid;

	private String status;

	@Column(name="TILL_NUMBER")
	private String tillNumber;

	private String token;

	@Column(name="TRACE_ID")
	private String traceId;

	@Column(name="TRANSACTION_DATE")
	private Timestamp transactionDate;

	@Column(name="TRANSACTION_NUMBER")
	private BigDecimal transactionNumber;

	@Column(name="TRANSACTION_REASON_OID")
	private BigDecimal transactionReasonOid;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	@Column(name="TRANSFER_OID")
	private BigDecimal transferOid;

	public PendingTransactionBkMig() {
	}

	public String getAccessIdentifier() {
		return this.accessIdentifier;
	}

	public void setAccessIdentifier(String accessIdentifier) {
		this.accessIdentifier = accessIdentifier;
	}

	public BigDecimal getBankDetailsOid() {
		return this.bankDetailsOid;
	}

	public void setBankDetailsOid(BigDecimal bankDetailsOid) {
		this.bankDetailsOid = bankDetailsOid;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public String getCurrencyCodeTransaction() {
		return this.currencyCodeTransaction;
	}

	public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
		this.currencyCodeTransaction = currencyCodeTransaction;
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getEasypayPos() {
		return this.easypayPos;
	}

	public void setEasypayPos(String easypayPos) {
		this.easypayPos = easypayPos;
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

	public String getExternalAccountType() {
		return this.externalAccountType;
	}

	public void setExternalAccountType(String externalAccountType) {
		this.externalAccountType = externalAccountType;
	}

	public String getExternalBranchCode() {
		return this.externalBranchCode;
	}

	public void setExternalBranchCode(String externalBranchCode) {
		this.externalBranchCode = externalBranchCode;
	}

	public String getExternalReferenceNumber() {
		return this.externalReferenceNumber;
	}

	public void setExternalReferenceNumber(String externalReferenceNumber) {
		this.externalReferenceNumber = externalReferenceNumber;
	}

	public String getExternalTillNumber() {
		return this.externalTillNumber;
	}

	public void setExternalTillNumber(String externalTillNumber) {
		this.externalTillNumber = externalTillNumber;
	}

	public BigDecimal getExternalTransactionNumber() {
		return this.externalTransactionNumber;
	}

	public void setExternalTransactionNumber(BigDecimal externalTransactionNumber) {
		this.externalTransactionNumber = externalTransactionNumber;
	}

	public String getExtracted() {
		return this.extracted;
	}

	public void setExtracted(String extracted) {
		this.extracted = extracted;
	}

	public BigDecimal getFundReservationOid() {
		return this.fundReservationOid;
	}

	public void setFundReservationOid(BigDecimal fundReservationOid) {
		this.fundReservationOid = fundReservationOid;
	}

	public BigDecimal getJournalReasonOid() {
		return this.journalReasonOid;
	}

	public void setJournalReasonOid(BigDecimal journalReasonOid) {
		this.journalReasonOid = journalReasonOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getOnUs() {
		return this.onUs;
	}

	public void setOnUs(String onUs) {
		this.onUs = onUs;
	}

	public double getOriginalUnclearedAmount() {
		return this.originalUnclearedAmount;
	}

	public void setOriginalUnclearedAmount(double originalUnclearedAmount) {
		this.originalUnclearedAmount = originalUnclearedAmount;
	}

	public String getPayeeAccountNumber() {
		return this.payeeAccountNumber;
	}

	public void setPayeeAccountNumber(String payeeAccountNumber) {
		this.payeeAccountNumber = payeeAccountNumber;
	}

	public BigDecimal getPayeeAccountOid() {
		return this.payeeAccountOid;
	}

	public void setPayeeAccountOid(BigDecimal payeeAccountOid) {
		this.payeeAccountOid = payeeAccountOid;
	}

	public double getPayeeAmount() {
		return this.payeeAmount;
	}

	public void setPayeeAmount(double payeeAmount) {
		this.payeeAmount = payeeAmount;
	}

	public String getPayeeCurrency() {
		return this.payeeCurrency;
	}

	public void setPayeeCurrency(String payeeCurrency) {
		this.payeeCurrency = payeeCurrency;
	}

	public String getPayerAccountNumber() {
		return this.payerAccountNumber;
	}

	public void setPayerAccountNumber(String payerAccountNumber) {
		this.payerAccountNumber = payerAccountNumber;
	}

	public BigDecimal getPayerAccountOid() {
		return this.payerAccountOid;
	}

	public void setPayerAccountOid(BigDecimal payerAccountOid) {
		this.payerAccountOid = payerAccountOid;
	}

	public double getPayerAmount() {
		return this.payerAmount;
	}

	public void setPayerAmount(double payerAmount) {
		this.payerAmount = payerAmount;
	}

	public String getPayerCurrency() {
		return this.payerCurrency;
	}

	public void setPayerCurrency(String payerCurrency) {
		this.payerCurrency = payerCurrency;
	}

	public double getPendingPayeeAmount() {
		return this.pendingPayeeAmount;
	}

	public void setPendingPayeeAmount(double pendingPayeeAmount) {
		this.pendingPayeeAmount = pendingPayeeAmount;
	}

	public double getPendingPayerAmount() {
		return this.pendingPayerAmount;
	}

	public void setPendingPayerAmount(double pendingPayerAmount) {
		this.pendingPayerAmount = pendingPayerAmount;
	}

	public Timestamp getReconciledOn() {
		return this.reconciledOn;
	}

	public void setReconciledOn(Timestamp reconciledOn) {
		this.reconciledOn = reconciledOn;
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

	public String getReversalReference() {
		return this.reversalReference;
	}

	public void setReversalReference(String reversalReference) {
		this.reversalReference = reversalReference;
	}

	public BigDecimal getReversedByOid() {
		return this.reversedByOid;
	}

	public void setReversedByOid(BigDecimal reversedByOid) {
		this.reversedByOid = reversedByOid;
	}

	public BigDecimal getScheduledTransactionOid() {
		return this.scheduledTransactionOid;
	}

	public void setScheduledTransactionOid(BigDecimal scheduledTransactionOid) {
		this.scheduledTransactionOid = scheduledTransactionOid;
	}

	public BigDecimal getServiceOid() {
		return this.serviceOid;
	}

	public void setServiceOid(BigDecimal serviceOid) {
		this.serviceOid = serviceOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTillNumber() {
		return this.tillNumber;
	}

	public void setTillNumber(String tillNumber) {
		this.tillNumber = tillNumber;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTraceId() {
		return this.traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Timestamp getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(BigDecimal transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public BigDecimal getTransactionReasonOid() {
		return this.transactionReasonOid;
	}

	public void setTransactionReasonOid(BigDecimal transactionReasonOid) {
		this.transactionReasonOid = transactionReasonOid;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

	public BigDecimal getTransferOid() {
		return this.transferOid;
	}

	public void setTransferOid(BigDecimal transferOid) {
		this.transferOid = transferOid;
	}

}