package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CATEGORY001 database table.
 * 
 */
@Entity
@NamedQuery(name="Category001.findAll", query="SELECT c FROM Category001 c")
public class Category001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String currency;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public Category001() {
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