package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BENEFICIARY_BACKUP database table.
 * 
 */
// @Entity
@Table(name="BENEFICIARY_BACKUP")
@NamedQuery(name="BeneficiaryBackup.findAll", query="SELECT b FROM BeneficiaryBackup b")
public class BeneficiaryBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_IDENTIFIER")
	private String accountIdentifier;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	private String description;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	private BigDecimal oid;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	private String reference;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public BeneficiaryBackup() {
	}

	public String getAccountIdentifier() {
		return this.accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}