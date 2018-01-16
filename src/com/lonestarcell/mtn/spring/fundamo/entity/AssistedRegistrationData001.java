package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ASSISTED_REGISTRATION_DATA001 database table.
 * 
 */
@Entity
@Table(name="ASSISTED_REGISTRATION_DATA001")
@NamedQuery(name="AssistedRegistrationData001.findAll", query="SELECT a FROM AssistedRegistrationData001 a")
public class AssistedRegistrationData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="\"ACTION\"")
	private String action;

	private BigDecimal counter;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PERSISTENT_OBJECT_CLASS_NAME")
	private String persistentObjectClassName;

	@Column(name="PERSISTENT_OBJECT_OID")
	private BigDecimal persistentObjectOid;

	@Column(name="REQUEST_TYPE")
	private String requestType;

	@Column(name="TRACE_NUMBER")
	private String traceNumber;

	public AssistedRegistrationData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public BigDecimal getCounter() {
		return this.counter;
	}

	public void setCounter(BigDecimal counter) {
		this.counter = counter;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPersistentObjectClassName() {
		return this.persistentObjectClassName;
	}

	public void setPersistentObjectClassName(String persistentObjectClassName) {
		this.persistentObjectClassName = persistentObjectClassName;
	}

	public BigDecimal getPersistentObjectOid() {
		return this.persistentObjectOid;
	}

	public void setPersistentObjectOid(BigDecimal persistentObjectOid) {
		this.persistentObjectOid = persistentObjectOid;
	}

	public String getRequestType() {
		return this.requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTraceNumber() {
		return this.traceNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

}