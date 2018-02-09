package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LAST_SETTLEMENT_FILE001 database table.
 * 
 */
@Entity
@Table(name="LAST_SETTLEMENT_FILE001")
@NamedQuery(name="LastSettlementFile001.findAll", query="SELECT l FROM LastSettlementFile001 l")
public class LastSettlementFile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BUSINESS_DATE")
	private Timestamp businessDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SEQUENCE_NUMBER")
	private BigDecimal sequenceNumber;

	public LastSettlementFile001() {
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

	public BigDecimal getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(BigDecimal sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}