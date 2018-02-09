package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SCRATCH_CARD_FILE001 database table.
 * 
 */
@Entity
@Table(name="SCRATCH_CARD_FILE001")
@NamedQuery(name="ScratchCardFile001.findAll", query="SELECT s FROM ScratchCardFile001 s")
public class ScratchCardFile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NO_OF_RECORDS")
	private BigDecimal noOfRecords;

	private String reason;

	private String status;

	@Column(name="TOTAL_VALUE")
	private double totalValue;

	@Column(name="UPLOAD_DATE")
	private Timestamp uploadDate;

	public ScratchCardFile001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getNoOfRecords() {
		return this.noOfRecords;
	}

	public void setNoOfRecords(BigDecimal noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

}