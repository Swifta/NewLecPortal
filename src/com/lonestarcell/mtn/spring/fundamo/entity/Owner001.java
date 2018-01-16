package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the OWNER001 database table.
 * 
 */
@Entity
@NamedQuery(name="Owner001.findAll", query="SELECT o FROM Owner001 o")
public class Owner001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="INTER_OWNER_ACCOUNT_ALLOWED")
	private String interOwnerAccountAllowed;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String location;

	@Column(name="MPG_OID")
	private BigDecimal mpgOid;

	private String name;

	public Owner001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getInterOwnerAccountAllowed() {
		return this.interOwnerAccountAllowed;
	}

	public void setInterOwnerAccountAllowed(String interOwnerAccountAllowed) {
		this.interOwnerAccountAllowed = interOwnerAccountAllowed;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getMpgOid() {
		return this.mpgOid;
	}

	public void setMpgOid(BigDecimal mpgOid) {
		this.mpgOid = mpgOid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}