package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LIMIT001 database table.
 * 
 */
@Entity
@NamedQuery(name="Limit001.findAll", query="SELECT l FROM Limit001 l")
public class Limit001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LIMIT_TYPE_OID")
	private BigDecimal limitTypeOid;

	private String name;

	public Limit001() {
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

	public BigDecimal getLimitTypeOid() {
		return this.limitTypeOid;
	}

	public void setLimitTypeOid(BigDecimal limitTypeOid) {
		this.limitTypeOid = limitTypeOid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}