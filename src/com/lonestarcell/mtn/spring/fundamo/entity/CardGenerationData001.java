package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_GENERATION_DATA001 database table.
 * 
 */
@Entity
@Table(name="CARD_GENERATION_DATA001")
@NamedQuery(name="CardGenerationData001.findAll", query="SELECT c FROM CardGenerationData001 c")
public class CardGenerationData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_END")
	private BigDecimal cardEnd;

	@Column(name="CARD_START")
	private BigDecimal cardStart;

	@Column(name="EASYPAY_END")
	private BigDecimal easypayEnd;

	@Column(name="EASYPAY_START")
	private BigDecimal easypayStart;

	@Column(name="FILE_BATCH_NUMBER")
	private BigDecimal fileBatchNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="ORDER_TYPE_OID")
	private BigDecimal orderTypeOid;

	private String status;

	public CardGenerationData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCardEnd() {
		return this.cardEnd;
	}

	public void setCardEnd(BigDecimal cardEnd) {
		this.cardEnd = cardEnd;
	}

	public BigDecimal getCardStart() {
		return this.cardStart;
	}

	public void setCardStart(BigDecimal cardStart) {
		this.cardStart = cardStart;
	}

	public BigDecimal getEasypayEnd() {
		return this.easypayEnd;
	}

	public void setEasypayEnd(BigDecimal easypayEnd) {
		this.easypayEnd = easypayEnd;
	}

	public BigDecimal getEasypayStart() {
		return this.easypayStart;
	}

	public void setEasypayStart(BigDecimal easypayStart) {
		this.easypayStart = easypayStart;
	}

	public BigDecimal getFileBatchNumber() {
		return this.fileBatchNumber;
	}

	public void setFileBatchNumber(BigDecimal fileBatchNumber) {
		this.fileBatchNumber = fileBatchNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOrderTypeOid() {
		return this.orderTypeOid;
	}

	public void setOrderTypeOid(BigDecimal orderTypeOid) {
		this.orderTypeOid = orderTypeOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}