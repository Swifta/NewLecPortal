package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SCRATCH_CARD_RECORD001 database table.
 * 
 */
@Entity
@Table(name="SCRATCH_CARD_RECORD001")
@NamedQuery(name="ScratchCardRecord001.findAll", query="SELECT s FROM ScratchCardRecord001 s")
public class ScratchCardRecord001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="CARD_TYPE")
	private String cardType;

	private String currency;

	@Column(name="EXPIRY_DATE")
	private Timestamp expiryDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String pin;

	private String reason;

	@Column(name="SCRATCH_CARD_FILE_OID")
	private BigDecimal scratchCardFileOid;

	@Column(name="SERIAL_NUMBER")
	private String serialNumber;

	private String status;

	public ScratchCardRecord001() {
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

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getScratchCardFileOid() {
		return this.scratchCardFileOid;
	}

	public void setScratchCardFileOid(BigDecimal scratchCardFileOid) {
		this.scratchCardFileOid = scratchCardFileOid;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}