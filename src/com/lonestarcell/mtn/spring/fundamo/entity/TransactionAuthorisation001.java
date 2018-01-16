package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TRANSACTION_AUTHORISATION001 database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_AUTHORISATION001")
@NamedQuery(name="TransactionAuthorisation001.findAll", query="SELECT t FROM TransactionAuthorisation001 t")
public class TransactionAuthorisation001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AUTHORISATION_EXPIRY")
	private BigDecimal authorisationExpiry;

	@Column(name="AUTHORISATION_ORDER")
	private BigDecimal authorisationOrder;

	private String authoriser;

	@Column(name="CAN_CAPTURE")
	private String canCapture;

	private String currency;

	@Column(name="DUAL_AUTHORISATION_AMOUNT")
	private double dualAuthorisationAmount;

	@Column(name="FIRST_AUTHORISER")
	private String firstAuthoriser;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NEXT_OID")
	private BigDecimal nextOid;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="SINGLE_AUTHORISATION_AMOUNT")
	private double singleAuthorisationAmount;

	@Column(name="TRANSACTION_TYPE")
	private String transactionType;

	public TransactionAuthorisation001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAuthorisationExpiry() {
		return this.authorisationExpiry;
	}

	public void setAuthorisationExpiry(BigDecimal authorisationExpiry) {
		this.authorisationExpiry = authorisationExpiry;
	}

	public BigDecimal getAuthorisationOrder() {
		return this.authorisationOrder;
	}

	public void setAuthorisationOrder(BigDecimal authorisationOrder) {
		this.authorisationOrder = authorisationOrder;
	}

	public String getAuthoriser() {
		return this.authoriser;
	}

	public void setAuthoriser(String authoriser) {
		this.authoriser = authoriser;
	}

	public String getCanCapture() {
		return this.canCapture;
	}

	public void setCanCapture(String canCapture) {
		this.canCapture = canCapture;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getDualAuthorisationAmount() {
		return this.dualAuthorisationAmount;
	}

	public void setDualAuthorisationAmount(double dualAuthorisationAmount) {
		this.dualAuthorisationAmount = dualAuthorisationAmount;
	}

	public String getFirstAuthoriser() {
		return this.firstAuthoriser;
	}

	public void setFirstAuthoriser(String firstAuthoriser) {
		this.firstAuthoriser = firstAuthoriser;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getNextOid() {
		return this.nextOid;
	}

	public void setNextOid(BigDecimal nextOid) {
		this.nextOid = nextOid;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public double getSingleAuthorisationAmount() {
		return this.singleAuthorisationAmount;
	}

	public void setSingleAuthorisationAmount(double singleAuthorisationAmount) {
		this.singleAuthorisationAmount = singleAuthorisationAmount;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}