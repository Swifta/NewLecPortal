package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CREDIT_CARD_TRANSFER001 database table.
 * 
 */
@Entity
@Table(name="CREDIT_CARD_TRANSFER001")
@NamedQuery(name="CreditCardTransfer001.findAll", query="SELECT c FROM CreditCardTransfer001 c")
public class CreditCardTransfer001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="BANK_ACCOUNT_NUMBER")
	private String bankAccountNumber;

	@Column(name="BANK_REFERENCE_NUMBER")
	private String bankReferenceNumber;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	@Column(name="CREDIT_CARD_CVV")
	private String creditCardCvv;

	@Column(name="CREDIT_CARD_EXPIRY_DATE")
	private String creditCardExpiryDate;

	private String direction;

	@Column(name="FUNDAMO_BANK_TRANSFER_OID")
	private BigDecimal fundamoBankTransferOid;

	@Column(name="FUNDAMO_TRANSFER_RESPONSE_OID")
	private BigDecimal fundamoTransferResponseOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String mechanism;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	private String status;

	@Column(name="\"TIMESTAMP\"")
	private Timestamp timestamp;

	public CreditCardTransfer001() {
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

	public String getBankReferenceNumber() {
		return this.bankReferenceNumber;
	}

	public void setBankReferenceNumber(String bankReferenceNumber) {
		this.bankReferenceNumber = bankReferenceNumber;
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

	public String getCreditCardCvv() {
		return this.creditCardCvv;
	}

	public void setCreditCardCvv(String creditCardCvv) {
		this.creditCardCvv = creditCardCvv;
	}

	public String getCreditCardExpiryDate() {
		return this.creditCardExpiryDate;
	}

	public void setCreditCardExpiryDate(String creditCardExpiryDate) {
		this.creditCardExpiryDate = creditCardExpiryDate;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getFundamoBankTransferOid() {
		return this.fundamoBankTransferOid;
	}

	public void setFundamoBankTransferOid(BigDecimal fundamoBankTransferOid) {
		this.fundamoBankTransferOid = fundamoBankTransferOid;
	}

	public BigDecimal getFundamoTransferResponseOid() {
		return this.fundamoTransferResponseOid;
	}

	public void setFundamoTransferResponseOid(BigDecimal fundamoTransferResponseOid) {
		this.fundamoTransferResponseOid = fundamoTransferResponseOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMechanism() {
		return this.mechanism;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
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

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}