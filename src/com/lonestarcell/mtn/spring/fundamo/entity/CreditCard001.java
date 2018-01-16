package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CREDIT_CARD001 database table.
 * 
 */
@Entity
@Table(name="CREDIT_CARD001")
@NamedQuery(name="CreditCard001.findAll", query="SELECT c FROM CreditCard001 c")
public class CreditCard001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BANK_OID")
	private BigDecimal bankOid;

	@Column(name="BANK_VERIFICATION_STATUS_OID")
	private BigDecimal bankVerificationStatusOid;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Column(name="CREDIT_CARD_NUMBER")
	private String creditCardNumber;

	@Column(name="CREDIT_CARD_TYPE_OID")
	private BigDecimal creditCardTypeOid;

	private String currency;

	private String cvv;

	@Column(name="EXPIRY_DATE")
	private Timestamp expiryDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="SHORT_NAME")
	private String shortName;

	@Column(name="TRANSFER_IN_ACCOUNT")
	private String transferInAccount;

	public CreditCard001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getBankOid() {
		return this.bankOid;
	}

	public void setBankOid(BigDecimal bankOid) {
		this.bankOid = bankOid;
	}

	public BigDecimal getBankVerificationStatusOid() {
		return this.bankVerificationStatusOid;
	}

	public void setBankVerificationStatusOid(BigDecimal bankVerificationStatusOid) {
		this.bankVerificationStatusOid = bankVerificationStatusOid;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public BigDecimal getCreditCardTypeOid() {
		return this.creditCardTypeOid;
	}

	public void setCreditCardTypeOid(BigDecimal creditCardTypeOid) {
		this.creditCardTypeOid = creditCardTypeOid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Timestamp getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTransferInAccount() {
		return this.transferInAccount;
	}

	public void setTransferInAccount(String transferInAccount) {
		this.transferInAccount = transferInAccount;
	}

}