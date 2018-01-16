package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SPECIAL_CLEARANCE001 database table.
 * 
 */
@Entity
@Table(name="SPECIAL_CLEARANCE001")
@NamedQuery(name="SpecialClearance001.findAll", query="SELECT s FROM SpecialClearance001 s")
public class SpecialClearance001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="CARD_DEPOSIT_OID")
	private BigDecimal cardDepositOid;

	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	@Column(name="FEE_APPLICABLE")
	private String feeApplicable;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_NAME")
	private String partyRoleName;

	private String reference;

	public SpecialClearance001() {
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

	public BigDecimal getCardDepositOid() {
		return this.cardDepositOid;
	}

	public void setCardDepositOid(BigDecimal cardDepositOid) {
		this.cardDepositOid = cardDepositOid;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getFeeApplicable() {
		return this.feeApplicable;
	}

	public void setFeeApplicable(String feeApplicable) {
		this.feeApplicable = feeApplicable;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPartyRoleName() {
		return this.partyRoleName;
	}

	public void setPartyRoleName(String partyRoleName) {
		this.partyRoleName = partyRoleName;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}