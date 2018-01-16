package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ALERT_LOG001 database table.
 * 
 */
@Entity
@Table(name="ALERT_LOG001")
@NamedQuery(name="AlertLog001.findAll", query="SELECT a FROM AlertLog001 a")
public class AlertLog001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="ERROR_MESSAGE")
	private String errorMessage;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String reference;

	@Column(name="REFERENCE_TYPE")
	private String referenceType;

	private String status;

	public AlertLog001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}