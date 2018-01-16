package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MANUAL_BANK_ACC_TRANSFER001 database table.
 * 
 */
//@Entity
@Table(name="MANUAL_BANK_ACC_TRANSFER001")
@NamedQuery(name="ManualBankAccTransfer001.findAll", query="SELECT m FROM ManualBankAccTransfer001 m")
public class ManualBankAccTransfer001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="BANK_ACCOUNT_NUMBER")
	private String bankAccountNumber;

	@Column(name="BANK_ACCOUNT_TYPE")
	private String bankAccountType;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String direction;

	@Column(name="EFFECTIVE_DATE")
	private Timestamp effectiveDate;

	private String exposed;

	@Column(name="\"EXTERNAL\"")
	private String external;

	@Column(name="EXTERNAL_TRANSACTION_ID")
	private String externalTransactionId;

	@Column(name="FUNDAMO_ACCOUNT_NUMBER")
	private String fundamoAccountNumber;

	@Column(name="FUNDAMO_BANK_TRANSFER_ID")
	private String fundamoBankTransferId;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MANUAL_TRANSFER_FILE_OID")
	private BigDecimal manualTransferFileOid;

	private String mechanism;

	private String reference;

	@Column(name="REJECTION_REASON")
	private String rejectionReason;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	private String status;

	public ManualBankAccTransfer001() {
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

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountType() {
		return this.bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExposed() {
		return this.exposed;
	}

	public void setExposed(String exposed) {
		this.exposed = exposed;
	}

	public String getExternal() {
		return this.external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	public String getExternalTransactionId() {
		return this.externalTransactionId;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}

	public String getFundamoAccountNumber() {
		return this.fundamoAccountNumber;
	}

	public void setFundamoAccountNumber(String fundamoAccountNumber) {
		this.fundamoAccountNumber = fundamoAccountNumber;
	}

	public String getFundamoBankTransferId() {
		return this.fundamoBankTransferId;
	}

	public void setFundamoBankTransferId(String fundamoBankTransferId) {
		this.fundamoBankTransferId = fundamoBankTransferId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getManualTransferFileOid() {
		return this.manualTransferFileOid;
	}

	public void setManualTransferFileOid(BigDecimal manualTransferFileOid) {
		this.manualTransferFileOid = manualTransferFileOid;
	}

	public String getMechanism() {
		return this.mechanism;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
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

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}