package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DISPUTE001 database table.
 * 
 */
@Entity
@NamedQuery(name="Dispute001.findAll", query="SELECT d FROM Dispute001 d")
public class Dispute001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_TRANSACTION_AUTH_NUMBER")
	private BigDecimal cardTransactionAuthNumber;

	private String cmment;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="DATE_SENT_TO_CARD_DIVISION")
	private Timestamp dateSentToCardDivision;

	@Column(name="DISPUTE_REASON_OID")
	private BigDecimal disputeReasonOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="TRANSACTION_NUMBER")
	private BigDecimal transactionNumber;

	public Dispute001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCardTransactionAuthNumber() {
		return this.cardTransactionAuthNumber;
	}

	public void setCardTransactionAuthNumber(BigDecimal cardTransactionAuthNumber) {
		this.cardTransactionAuthNumber = cardTransactionAuthNumber;
	}

	public String getCmment() {
		return this.cmment;
	}

	public void setCmment(String cmment) {
		this.cmment = cmment;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getDateSentToCardDivision() {
		return this.dateSentToCardDivision;
	}

	public void setDateSentToCardDivision(Timestamp dateSentToCardDivision) {
		this.dateSentToCardDivision = dateSentToCardDivision;
	}

	public BigDecimal getDisputeReasonOid() {
		return this.disputeReasonOid;
	}

	public void setDisputeReasonOid(BigDecimal disputeReasonOid) {
		this.disputeReasonOid = disputeReasonOid;
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

	public BigDecimal getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(BigDecimal transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

}