package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_CHARGE_BACK_DATA001 database table.
 * 
 */
@Entity
@Table(name="CARD_CHARGE_BACK_DATA001")
@NamedQuery(name="CardChargeBackData001.findAll", query="SELECT c FROM CardChargeBackData001 c")
public class CardChargeBackData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	@Column(name="CARD_CHARGE_BACK_OID")
	private BigDecimal cardChargeBackOid;

	@Column(name="DOCUMENT_INDICATOR")
	private String documentIndicator;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MEMBER_ID")
	private String memberId;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="POS_ENTRY_MODE")
	private String posEntryMode;

	@Column(name="QUERY_NUMBER")
	private String queryNumber;

	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;

	@Column(name="REPLACEMENT_AMOUNT")
	private double replacementAmount;

	public CardChargeBackData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public BigDecimal getCardChargeBackOid() {
		return this.cardChargeBackOid;
	}

	public void setCardChargeBackOid(BigDecimal cardChargeBackOid) {
		this.cardChargeBackOid = cardChargeBackOid;
	}

	public String getDocumentIndicator() {
		return this.documentIndicator;
	}

	public void setDocumentIndicator(String documentIndicator) {
		this.documentIndicator = documentIndicator;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPosEntryMode() {
		return this.posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	public String getQueryNumber() {
		return this.queryNumber;
	}

	public void setQueryNumber(String queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public double getReplacementAmount() {
		return this.replacementAmount;
	}

	public void setReplacementAmount(double replacementAmount) {
		this.replacementAmount = replacementAmount;
	}

}