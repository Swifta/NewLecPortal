package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LIMIT_PROFILE001 database table.
 * 
 */
@Entity
@Table(name="LIMIT_PROFILE001")
@NamedQuery(name="LimitProfile001.findAll", query="SELECT l FROM LimitProfile001 l")
public class LimitProfile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String currency;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public LimitProfile001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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