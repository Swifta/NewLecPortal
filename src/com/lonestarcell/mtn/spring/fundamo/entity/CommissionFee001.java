package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the COMMISSION_FEE001 database table.
 * 
 */
@Entity
@Table(name="COMMISSION_FEE001")
@NamedQuery(name="CommissionFee001.findAll", query="SELECT c FROM CommissionFee001 c")
public class CommissionFee001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PROFILE_OID")
	private BigDecimal accountProfileOid;

	@Column(name="CATEGORY_OID")
	private BigDecimal categoryOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MAX_AMOUNT")
	private double maxAmount;

	@Column(name="MIN_AMOUNT")
	private double minAmount;

	private double percentage;

	@Column(name="SERVICE_OID")
	private BigDecimal serviceOid;

	private String tiered;

	@Column(name="TRANSACTION_AMT_BASED")
	private String transactionAmtBased;

	public CommissionFee001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountProfileOid() {
		return this.accountProfileOid;
	}

	public void setAccountProfileOid(BigDecimal accountProfileOid) {
		this.accountProfileOid = accountProfileOid;
	}

	public BigDecimal getCategoryOid() {
		return this.categoryOid;
	}

	public void setCategoryOid(BigDecimal categoryOid) {
		this.categoryOid = categoryOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public double getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getServiceOid() {
		return this.serviceOid;
	}

	public void setServiceOid(BigDecimal serviceOid) {
		this.serviceOid = serviceOid;
	}

	public String getTiered() {
		return this.tiered;
	}

	public void setTiered(String tiered) {
		this.tiered = tiered;
	}

	public String getTransactionAmtBased() {
		return this.transactionAmtBased;
	}

	public void setTransactionAmtBased(String transactionAmtBased) {
		this.transactionAmtBased = transactionAmtBased;
	}

}