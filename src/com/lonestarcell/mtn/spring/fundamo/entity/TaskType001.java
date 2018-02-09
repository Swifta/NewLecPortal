package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TASK_TYPE001 database table.
 * 
 */
@Entity
@Table(name="TASK_TYPE001")
@NamedQuery(name="TaskType001.findAll", query="SELECT t FROM TaskType001 t")
public class TaskType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="INTERFACE_CLASS_NAME")
	private String interfaceClassName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public TaskType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getInterfaceClassName() {
		return this.interfaceClassName;
	}

	public void setInterfaceClassName(String interfaceClassName) {
		this.interfaceClassName = interfaceClassName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}