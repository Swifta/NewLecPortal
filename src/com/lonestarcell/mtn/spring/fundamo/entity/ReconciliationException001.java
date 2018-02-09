package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RECONCILIATION_EXCEPTION001 database table.
 * 
 */
@Entity
@Table(name="RECONCILIATION_EXCEPTION001")
@NamedQuery(name="ReconciliationException001.findAll", query="SELECT r FROM ReconciliationException001 r")
public class ReconciliationException001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BATCH_ID")
	private String batchId;

	private String code;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String reference;

	@Column(name="\"TYPE\"")
	private String type;

	public ReconciliationException001() {
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}