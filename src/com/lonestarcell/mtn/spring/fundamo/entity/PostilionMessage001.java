package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the POSTILION_MESSAGE001 database table.
 * 
 */
@Entity
@Table(name="POSTILION_MESSAGE001")
@NamedQuery(name="PostilionMessage001.findAll", query="SELECT p FROM PostilionMessage001 p")
public class PostilionMessage001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACQUIRING_INSTITUTION_ID_CODE")
	private String acquiringInstitutionIdCode;

	@Column(name="AMOUNT_TRANSACTION")
	private double amountTransaction;

	@Column(name="AMOUNT_TRANSACTION_FEE")
	private double amountTransactionFee;

	@Column(name="AMOUNT_TRANSACTION_PROC_FEE")
	private double amountTransactionProcFee;

	@Column(name="CARD_ACCEPTOR_TERMINAL_ID")
	private String cardAcceptorTerminalId;

	@Column(name="CARD_TRANSACTION_AUTH_OID")
	private BigDecimal cardTransactionAuthOid;

	@Column(name="CARD_TRANSACTION_OID")
	private BigDecimal cardTransactionOid;

	private String cleared;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String direction;

	@Column(name="DUPLICATION_CHK_DATA_ELEMENTS")
	private String duplicationChkDataElements;

	@Column(name="FORWARDING_INSTITUTION_ID_CODE")
	private String forwardingInstitutionIdCode;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LOCAL_TRANSACTION_DATE")
	private String localTransactionDate;

	@Column(name="LOCAL_TRANSACTION_TIME")
	private String localTransactionTime;

	@Column(name="MARKED_OFF")
	private String markedOff;

	@Column(name="MESSAGE_CONTENT_STATUS")
	private String messageContentStatus;

	@Column(name="MESSAGE_TYPE")
	private String messageType;

	@Column(name="ORIGINAL_CARD_TRANSACTION_OID")
	private BigDecimal originalCardTransactionOid;

	@Column(name="POSTILION_MESSAGE_CONTENT_OID")
	private BigDecimal postilionMessageContentOid;

	@Column(name="PRIMARY_ACCOUNT_NUMBER")
	private String primaryAccountNumber;

	@Column(name="PROCESSING_CODE")
	private String processingCode;

	@Column(name="RETRIEVAL_REFERENCE")
	private String retrievalReference;

	private String status;

	@Column(name="SYSTEM_IDENTIFIER")
	private String systemIdentifier;

	@Column(name="SYSTEMS_TRACE_AUDIT_NUMBER")
	private String systemsTraceAuditNumber;

	@Column(name="TRANSMISSION_DATE_AND_TIME")
	private String transmissionDateAndTime;

	public PostilionMessage001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAcquiringInstitutionIdCode() {
		return this.acquiringInstitutionIdCode;
	}

	public void setAcquiringInstitutionIdCode(String acquiringInstitutionIdCode) {
		this.acquiringInstitutionIdCode = acquiringInstitutionIdCode;
	}

	public double getAmountTransaction() {
		return this.amountTransaction;
	}

	public void setAmountTransaction(double amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public double getAmountTransactionFee() {
		return this.amountTransactionFee;
	}

	public void setAmountTransactionFee(double amountTransactionFee) {
		this.amountTransactionFee = amountTransactionFee;
	}

	public double getAmountTransactionProcFee() {
		return this.amountTransactionProcFee;
	}

	public void setAmountTransactionProcFee(double amountTransactionProcFee) {
		this.amountTransactionProcFee = amountTransactionProcFee;
	}

	public String getCardAcceptorTerminalId() {
		return this.cardAcceptorTerminalId;
	}

	public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {
		this.cardAcceptorTerminalId = cardAcceptorTerminalId;
	}

	public BigDecimal getCardTransactionAuthOid() {
		return this.cardTransactionAuthOid;
	}

	public void setCardTransactionAuthOid(BigDecimal cardTransactionAuthOid) {
		this.cardTransactionAuthOid = cardTransactionAuthOid;
	}

	public BigDecimal getCardTransactionOid() {
		return this.cardTransactionOid;
	}

	public void setCardTransactionOid(BigDecimal cardTransactionOid) {
		this.cardTransactionOid = cardTransactionOid;
	}

	public String getCleared() {
		return this.cleared;
	}

	public void setCleared(String cleared) {
		this.cleared = cleared;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDuplicationChkDataElements() {
		return this.duplicationChkDataElements;
	}

	public void setDuplicationChkDataElements(String duplicationChkDataElements) {
		this.duplicationChkDataElements = duplicationChkDataElements;
	}

	public String getForwardingInstitutionIdCode() {
		return this.forwardingInstitutionIdCode;
	}

	public void setForwardingInstitutionIdCode(String forwardingInstitutionIdCode) {
		this.forwardingInstitutionIdCode = forwardingInstitutionIdCode;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocalTransactionDate() {
		return this.localTransactionDate;
	}

	public void setLocalTransactionDate(String localTransactionDate) {
		this.localTransactionDate = localTransactionDate;
	}

	public String getLocalTransactionTime() {
		return this.localTransactionTime;
	}

	public void setLocalTransactionTime(String localTransactionTime) {
		this.localTransactionTime = localTransactionTime;
	}

	public String getMarkedOff() {
		return this.markedOff;
	}

	public void setMarkedOff(String markedOff) {
		this.markedOff = markedOff;
	}

	public String getMessageContentStatus() {
		return this.messageContentStatus;
	}

	public void setMessageContentStatus(String messageContentStatus) {
		this.messageContentStatus = messageContentStatus;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getOriginalCardTransactionOid() {
		return this.originalCardTransactionOid;
	}

	public void setOriginalCardTransactionOid(BigDecimal originalCardTransactionOid) {
		this.originalCardTransactionOid = originalCardTransactionOid;
	}

	public BigDecimal getPostilionMessageContentOid() {
		return this.postilionMessageContentOid;
	}

	public void setPostilionMessageContentOid(BigDecimal postilionMessageContentOid) {
		this.postilionMessageContentOid = postilionMessageContentOid;
	}

	public String getPrimaryAccountNumber() {
		return this.primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getProcessingCode() {
		return this.processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getRetrievalReference() {
		return this.retrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		this.retrievalReference = retrievalReference;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemIdentifier() {
		return this.systemIdentifier;
	}

	public void setSystemIdentifier(String systemIdentifier) {
		this.systemIdentifier = systemIdentifier;
	}

	public String getSystemsTraceAuditNumber() {
		return this.systemsTraceAuditNumber;
	}

	public void setSystemsTraceAuditNumber(String systemsTraceAuditNumber) {
		this.systemsTraceAuditNumber = systemsTraceAuditNumber;
	}

	public String getTransmissionDateAndTime() {
		return this.transmissionDateAndTime;
	}

	public void setTransmissionDateAndTime(String transmissionDateAndTime) {
		this.transmissionDateAndTime = transmissionDateAndTime;
	}

}