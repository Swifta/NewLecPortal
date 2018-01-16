package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the GENERIC_TASK_DATA001 database table.
 * 
 */
@Entity
@Table(name="GENERIC_TASK_DATA001")
@NamedQuery(name="GenericTaskData001.findAll", query="SELECT g FROM GenericTaskData001 g")
public class GenericTaskData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private BigDecimal authoriser;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PERSISTENT_OBJECT_CLASS_NAME")
	private String persistentObjectClassName;

	@Column(name="PERSISTENT_OBJECT_OID")
	private BigDecimal persistentObjectOid;

	@Column(name="TASK_TYPE")
	private String taskType;

	public GenericTaskData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAuthoriser() {
		return this.authoriser;
	}

	public void setAuthoriser(BigDecimal authoriser) {
		this.authoriser = authoriser;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
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

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}