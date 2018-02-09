package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the RECON_YB_GL_RECORD001 database table.
 * 
 */
@Entity
@Table(name="RECON_YB_GL_RECORD001")
@NamedQuery(name="ReconYbGlRecord001.findAll", query="SELECT r FROM ReconYbGlRecord001 r")
public class ReconYbGlRecord001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="DESCRIPTION_CODE")
	private String descriptionCode;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="RECORD_DATE_TIME")
	private String recordDateTime;

	@Column(name="RECORD_NUMBER")
	private BigDecimal recordNumber;

	private String reference;

	private String status;

	public ReconYbGlRecord001() {
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

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getDescriptionCode() {
		return this.descriptionCode;
	}

	public void setDescriptionCode(String descriptionCode) {
		this.descriptionCode = descriptionCode;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRecordDateTime() {
		return this.recordDateTime;
	}

	public void setRecordDateTime(String recordDateTime) {
		this.recordDateTime = recordDateTime;
	}

	public BigDecimal getRecordNumber() {
		return this.recordNumber;
	}

	public void setRecordNumber(BigDecimal recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}