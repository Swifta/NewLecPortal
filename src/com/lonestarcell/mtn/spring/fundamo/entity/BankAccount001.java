package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK_ACCOUNT001 database table.
 * 
 */
@Entity
@Table(name="BANK_ACCOUNT001")
@NamedQuery(name="BankAccount001.findAll", query="SELECT b FROM BankAccount001 b")
public class BankAccount001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BANK_ACCOUNT_NUMBER")
	private String bankAccountNumber;

	@Column(name="BANK_ACCOUNT_TYPE_OID")
	private BigDecimal bankAccountTypeOid;

	@Column(name="BANK_BRANCH_CODE")
	private String bankBranchCode;

	@Column(name="BANK_OID")
	private BigDecimal bankOid;

	@Column(name="BANK_VERIFICATION_STATUS_OID")
	private BigDecimal bankVerificationStatusOid;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	private String currency;

	@Column(name="IS_SETTLEMENT_ACCOUNT")
	private String isSettlementAccount;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="SHORT_NAME")
	private String shortName;

	@Column(name="TRANSFER_IN_ACCOUNT")
	private String transferInAccount;

	public BankAccount001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public BigDecimal getBankAccountTypeOid() {
		return this.bankAccountTypeOid;
	}

	public void setBankAccountTypeOid(BigDecimal bankAccountTypeOid) {
		this.bankAccountTypeOid = bankAccountTypeOid;
	}

	public String getBankBranchCode() {
		return this.bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIsSettlementAccount() {
		return this.isSettlementAccount;
	}

	public void setIsSettlementAccount(String isSettlementAccount) {
		this.isSettlementAccount = isSettlementAccount;
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