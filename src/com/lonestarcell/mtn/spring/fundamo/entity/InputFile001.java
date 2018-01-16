package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the INPUT_FILE001 database table.
 * 
 */
@Entity
@Table(name="INPUT_FILE001")
@NamedQuery(name="InputFile001.findAll", query="SELECT i FROM InputFile001 i")
public class InputFile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BUSINESS_DATE")
	private Timestamp businessDate;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String pattern;

	private String reason;

	@Column(name="SEQUENCE_NUMBER")
	private BigDecimal sequenceNumber;

	@Column(name="SOURCE_ID")
	private String sourceId;

	private String status;

	public InputFile001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getBusinessDate() {
		return this.businessDate;
	}

	public void setBusinessDate(Timestamp businessDate) {
		this.businessDate = businessDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(BigDecimal sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

}