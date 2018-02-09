package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the GUEST_USER001 database table.
 * 
 */
@Entity
@Table(name="GUEST_USER001")
@NamedQuery(name="GuestUser001.findAll", query="SELECT g FROM GuestUser001 g")
public class GuestUser001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String identifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public GuestUser001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}