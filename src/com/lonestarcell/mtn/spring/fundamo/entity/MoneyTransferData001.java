package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MONEY_TRANSFER_DATA001 database table.
 * 
 */
@Entity
@Table(name="MONEY_TRANSFER_DATA001")
@NamedQuery(name="MoneyTransferData001.findAll", query="SELECT m FROM MoneyTransferData001 m")
public class MoneyTransferData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MONEY_TRANSFER_IN_OID")
	private BigDecimal moneyTransferInOid;

	@Column(name="MONEY_TRANSFER_OUT_OID")
	private BigDecimal moneyTransferOutOid;

	@Column(name="PAYEE_ID")
	private String payeeId;

	@Column(name="PAYER_ID")
	private String payerId;

	@Column(name="RECOGNITION_CODE")
	private String recognitionCode;

	private String status;

	private String token;

	@Column(name="TOKEN_EXPIRY_DATE")
	private Timestamp tokenExpiryDate;

	public MoneyTransferData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMoneyTransferInOid() {
		return this.moneyTransferInOid;
	}

	public void setMoneyTransferInOid(BigDecimal moneyTransferInOid) {
		this.moneyTransferInOid = moneyTransferInOid;
	}

	public BigDecimal getMoneyTransferOutOid() {
		return this.moneyTransferOutOid;
	}

	public void setMoneyTransferOutOid(BigDecimal moneyTransferOutOid) {
		this.moneyTransferOutOid = moneyTransferOutOid;
	}

	public String getPayeeId() {
		return this.payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getPayerId() {
		return this.payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getRecognitionCode() {
		return this.recognitionCode;
	}

	public void setRecognitionCode(String recognitionCode) {
		this.recognitionCode = recognitionCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTokenExpiryDate() {
		return this.tokenExpiryDate;
	}

	public void setTokenExpiryDate(Timestamp tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}

}