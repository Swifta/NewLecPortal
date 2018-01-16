package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LAST_RECONCILIATION001 database table.
 * 
 */
@Entity
@Table(name="LAST_RECONCILIATION001")
@NamedQuery(name="LastReconciliation001.findAll", query="SELECT l FROM LastReconciliation001 l")
public class LastReconciliation001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="LAST_DATE")
	private Timestamp lastDate;

	@Column(name="LAST_TRANSACTION_ID")
	private BigDecimal lastTransactionId;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public LastReconciliation001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Timestamp getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}

	public BigDecimal getLastTransactionId() {
		return this.lastTransactionId;
	}

	public void setLastTransactionId(BigDecimal lastTransactionId) {
		this.lastTransactionId = lastTransactionId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}