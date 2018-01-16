package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TASK001 database table.
 * 
 */
@Entity
@NamedQuery(name="Task001.findAll", query="SELECT t FROM Task001 t")
public class Task001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DUE_DATE")
	private Timestamp dueDate;

	@Column(name="EXPIRE_ASSIGNMENT_ON")
	private Timestamp expireAssignmentOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PERSISTENT_OBJECT_CLASS_NAME")
	private String persistentObjectClassName;

	@Column(name="PERSISTENT_OBJECT_OID")
	private BigDecimal persistentObjectOid;

	private String status;

	@Column(name="TASK_TYPE_OID")
	private BigDecimal taskTypeOid;

	public Task001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getExpireAssignmentOn() {
		return this.expireAssignmentOn;
	}

	public void setExpireAssignmentOn(Timestamp expireAssignmentOn) {
		this.expireAssignmentOn = expireAssignmentOn;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTaskTypeOid() {
		return this.taskTypeOid;
	}

	public void setTaskTypeOid(BigDecimal taskTypeOid) {
		this.taskTypeOid = taskTypeOid;
	}

}