package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the INPUT_FILE_RECORD001 database table.
 * 
 */
@Entity
@Table(name="INPUT_FILE_RECORD001")
@NamedQuery(name="InputFileRecord001.findAll", query="SELECT i FROM InputFileRecord001 i")
public class InputFileRecord001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BATCH_NUMBER")
	private BigDecimal batchNumber;

	@Column(name="EFFECTIVE_DATE")
	private Timestamp effectiveDate;

	@Column(name="FUND_RESERVATION_OID")
	private BigDecimal fundReservationOid;

	@Column(name="INPUT_FILE_OID")
	private BigDecimal inputFileOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String reason;

	@Column(name="RECORD_DETAIL")
	private String recordDetail;

	@Column(name="RECORD_NUMBER")
	private BigDecimal recordNumber;

	@Column(name="\"SEQUENCE\"")
	private BigDecimal sequence;

	@Column(name="SOURCE_ID")
	private String sourceId;

	private String status;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	@Column(name="\"TYPE\"")
	private String type;

	public InputFileRecord001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(BigDecimal batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getFundReservationOid() {
		return this.fundReservationOid;
	}

	public void setFundReservationOid(BigDecimal fundReservationOid) {
		this.fundReservationOid = fundReservationOid;
	}

	public BigDecimal getInputFileOid() {
		return this.inputFileOid;
	}

	public void setInputFileOid(BigDecimal inputFileOid) {
		this.inputFileOid = inputFileOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecordDetail() {
		return this.recordDetail;
	}

	public void setRecordDetail(String recordDetail) {
		this.recordDetail = recordDetail;
	}

	public BigDecimal getRecordNumber() {
		return this.recordNumber;
	}

	public void setRecordNumber(BigDecimal recordNumber) {
		this.recordNumber = recordNumber;
	}

	public BigDecimal getSequence() {
		return this.sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTransactionOid() {
		return this.transactionOid;
	}

	public void setTransactionOid(BigDecimal transactionOid) {
		this.transactionOid = transactionOid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}