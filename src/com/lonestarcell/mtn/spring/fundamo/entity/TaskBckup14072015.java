package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TASK_BCKUP14072015 database table.
 * 
 */
// @Entity
@Table(name="TASK_BCKUP14072015")
@NamedQuery(name="TaskBckup14072015.findAll", query="SELECT t FROM TaskBckup14072015 t")
public class TaskBckup14072015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DUE_DATE")
	private Timestamp dueDate;

	@Column(name="EXPIRE_ASSIGNMENT_ON")
	private Timestamp expireAssignmentOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="PERSISTENT_OBJECT_CLASS_NAME")
	private String persistentObjectClassName;

	@Column(name="PERSISTENT_OBJECT_OID")
	private BigDecimal persistentObjectOid;

	private String status;

	@Column(name="TASK_TYPE_OID")
	private BigDecimal taskTypeOid;

	public TaskBckup14072015() {
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
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