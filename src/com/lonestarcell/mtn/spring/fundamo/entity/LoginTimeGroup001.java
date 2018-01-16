package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LOGIN_TIME_GROUP001 database table.
 * 
 */
@Entity
@Table(name="LOGIN_TIME_GROUP001")
@NamedQuery(name="LoginTimeGroup001.findAll", query="SELECT l FROM LoginTimeGroup001 l")
public class LoginTimeGroup001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String description;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public LoginTimeGroup001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}