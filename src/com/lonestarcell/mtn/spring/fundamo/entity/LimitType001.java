package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LIMIT_TYPE001 database table.
 * 
 */
@Entity
@Table(name="LIMIT_TYPE001")
@NamedQuery(name="LimitType001.findAll", query="SELECT l FROM LimitType001 l")
public class LimitType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="OVERRIDE_ALLOWED")
	private String overrideAllowed;

	public LimitType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public String getOverrideAllowed() {
		return this.overrideAllowed;
	}

	public void setOverrideAllowed(String overrideAllowed) {
		this.overrideAllowed = overrideAllowed;
	}

}