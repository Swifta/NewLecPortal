package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PHYSICAL_ADDRESS_HISTORY001 database table.
 * 
 */
@Entity
@Table(name="PHYSICAL_ADDRESS_HISTORY001")
@NamedQuery(name="PhysicalAddressHistory001.findAll", query="SELECT p FROM PhysicalAddressHistory001 p")
public class PhysicalAddressHistory001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ADDRESS_OID")
	private BigDecimal addressOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PERSON_OID")
	private BigDecimal personOid;

	public PhysicalAddressHistory001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAddressOid() {
		return this.addressOid;
	}

	public void setAddressOid(BigDecimal addressOid) {
		this.addressOid = addressOid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getPersonOid() {
		return this.personOid;
	}

	public void setPersonOid(BigDecimal personOid) {
		this.personOid = personOid;
	}

}