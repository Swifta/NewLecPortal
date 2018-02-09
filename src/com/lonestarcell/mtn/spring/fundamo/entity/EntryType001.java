package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.sql.Timestamp;


/**
 * The persistent class for the ENTRY_TYPE001 database table.
 * 
 */
@Entity
@Proxy(lazy = false)
@Table(name="ENTRY_TYPE001")
@NamedQuery(name="EntryType001.findAll", query="SELECT e FROM EntryType001 e")
public class EntryType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String code;

	private String grouped;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_APPLICABLE")
	private String limitApplicable;

	//private String name;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn(name="name", referencedColumnName = "code" )
	private Systemcode systemcode;

	public EntryType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrouped() {
		return this.grouped;
	}

	public void setGrouped(String grouped) {
		this.grouped = grouped;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLimitApplicable() {
		return this.limitApplicable;
	}

	public void setLimitApplicable(String limitApplicable) {
		this.limitApplicable = limitApplicable;
	}

	public Systemcode getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(Systemcode systemcode) {
		this.systemcode = systemcode;
	}


	

}