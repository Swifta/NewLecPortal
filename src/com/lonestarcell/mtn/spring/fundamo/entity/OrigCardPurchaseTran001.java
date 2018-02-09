package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ORIG_CARD_PURCHASE_TRAN001 database table.
 * 
 */
@Entity
@Table(name="ORIG_CARD_PURCHASE_TRAN001")
@NamedQuery(name="OrigCardPurchaseTran001.findAll", query="SELECT o FROM OrigCardPurchaseTran001 o")
public class OrigCardPurchaseTran001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_CHARGE_BACK_OID")
	private BigDecimal cardChargeBackOid;

	@Column(name="FORWARDING_INSTITUTION")
	private String forwardingInstitution;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIFE_CYCLE_CODE")
	private String lifeCycleCode;

	@Column(name="ORIG_LOCAL_TRANSACTION_TIME")
	private Timestamp origLocalTransactionTime;

	@Column(name="POS_ENTRY_MODE")
	private String posEntryMode;

	public OrigCardPurchaseTran001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCardChargeBackOid() {
		return this.cardChargeBackOid;
	}

	public void setCardChargeBackOid(BigDecimal cardChargeBackOid) {
		this.cardChargeBackOid = cardChargeBackOid;
	}

	public String getForwardingInstitution() {
		return this.forwardingInstitution;
	}

	public void setForwardingInstitution(String forwardingInstitution) {
		this.forwardingInstitution = forwardingInstitution;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLifeCycleCode() {
		return this.lifeCycleCode;
	}

	public void setLifeCycleCode(String lifeCycleCode) {
		this.lifeCycleCode = lifeCycleCode;
	}

	public Timestamp getOrigLocalTransactionTime() {
		return this.origLocalTransactionTime;
	}

	public void setOrigLocalTransactionTime(Timestamp origLocalTransactionTime) {
		this.origLocalTransactionTime = origLocalTransactionTime;
	}

	public String getPosEntryMode() {
		return this.posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

}