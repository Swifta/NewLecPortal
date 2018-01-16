package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MONEY_TRANS_CHURN_250815 database table.
 * 
 */
@Entity
@Table(name="MONEY_TRANS_CHURN_250815")
@NamedQuery(name="MoneyTransChurn250815.findAll", query="SELECT m FROM MoneyTransChurn250815 m")
public class MoneyTransChurn250815 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MONEY_TRANSFER_IN_OID")
	private BigDecimal moneyTransferInOid;

	@Column(name="MONEY_TRANSFER_OUT_OID")
	private BigDecimal moneyTransferOutOid;

	private BigDecimal oid;

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

	public MoneyTransChurn250815() {
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
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