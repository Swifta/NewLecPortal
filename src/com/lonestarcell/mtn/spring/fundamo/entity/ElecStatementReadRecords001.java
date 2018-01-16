package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ELEC_STATEMENT_READ_RECORDS001 database table.
 * 
 */
@Entity
@Table(name="ELEC_STATEMENT_READ_RECORDS001")
@NamedQuery(name="ElecStatementReadRecords001.findAll", query="SELECT e FROM ElecStatementReadRecords001 e")
public class ElecStatementReadRecords001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ERROR_REASON")
	private String errorReason;

	@Column(name="FILE_ID")
	private String fileId;

	@Column(name="FILE_RECORD")
	private String fileRecord;

	@Column(name="INSTITUTION_ID")
	private String institutionId;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="RECORD_ID")
	private BigDecimal recordId;

	private String status;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	@Column(name="UNIQUE_INDICATOR")
	private String uniqueIndicator;

	public ElecStatementReadRecords001() {
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

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileRecord() {
		return this.fileRecord;
	}

	public void setFileRecord(String fileRecord) {
		this.fileRecord = fileRecord;
	}

	public String getInstitutionId() {
		return this.institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getRecordId() {
		return this.recordId;
	}

	public void setRecordId(BigDecimal recordId) {
		this.recordId = recordId;
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

	public String getUniqueIndicator() {
		return this.uniqueIndicator;
	}

	public void setUniqueIndicator(String uniqueIndicator) {
		this.uniqueIndicator = uniqueIndicator;
	}

}