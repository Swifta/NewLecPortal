package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BATCH001 database table.
 * 
 */
@Entity
@NamedQuery(name="Batch001.findAll", query="SELECT b FROM Batch001 b")
public class Batch001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BATCH_NUMBER")
	private String batchNumber;

	@Column(name="BATCH_USER_OID")
	private BigDecimal batchUserOid;

	@Column(name="COMPLETED_DATE")
	private Timestamp completedDate;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="CURRENT_RECORD_NUMBER")
	private BigDecimal currentRecordNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NUMBER_OF_RECORDS")
	private BigDecimal numberOfRecords;

	private String status;

	@Column(name="USER_BATCH_NUMBER")
	private String userBatchNumber;

	public Batch001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public BigDecimal getBatchUserOid() {
		return this.batchUserOid;
	}

	public void setBatchUserOid(BigDecimal batchUserOid) {
		this.batchUserOid = batchUserOid;
	}

	public Timestamp getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Timestamp completedDate) {
		this.completedDate = completedDate;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getCurrentRecordNumber() {
		return this.currentRecordNumber;
	}

	public void setCurrentRecordNumber(BigDecimal currentRecordNumber) {
		this.currentRecordNumber = currentRecordNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getNumberOfRecords() {
		return this.numberOfRecords;
	}

	public void setNumberOfRecords(BigDecimal numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserBatchNumber() {
		return this.userBatchNumber;
	}

	public void setUserBatchNumber(String userBatchNumber) {
		this.userBatchNumber = userBatchNumber;
	}

}