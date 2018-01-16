package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SETTLEMENT_RECORD001 database table.
 * 
 */
@Entity
@Table(name="SETTLEMENT_RECORD001")
@NamedQuery(name="SettlementRecord001.findAll", query="SELECT s FROM SettlementRecord001 s")
public class SettlementRecord001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ERROR_REASON")
	private String errorReason;

	@Column(name="FILE_RECORD")
	private String fileRecord;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="RECORD_NUMBER")
	private BigDecimal recordNumber;

	@Column(name="SEQUENCE_NUMBER")
	private BigDecimal sequenceNumber;

	private String status;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	public SettlementRecord001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getErrorReason() {
		return this.errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getFileRecord() {
		return this.fileRecord;
	}

	public void setFileRecord(String fileRecord) {
		this.fileRecord = fileRecord;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getRecordNumber() {
		return this.recordNumber;
	}

	public void setRecordNumber(BigDecimal recordNumber) {
		this.recordNumber = recordNumber;
	}

	public BigDecimal getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(BigDecimal sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

}