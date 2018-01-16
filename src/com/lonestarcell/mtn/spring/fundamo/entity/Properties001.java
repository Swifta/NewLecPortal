package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PROPERTIES001 database table.
 * 
 */
@Entity
@NamedQuery(name="Properties001.findAll", query="SELECT p FROM Properties001 p")
public class Properties001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="\"KEY\"")
	private String key;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="\"VALUE\"")
	private String value;

	public Properties001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}