package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PROPERTIES_DOMAIN001 database table.
 * 
 */
@Entity
@Table(name="PROPERTIES_DOMAIN001")
@NamedQuery(name="PropertiesDomain001.findAll", query="SELECT p FROM PropertiesDomain001 p")
public class PropertiesDomain001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PERSISTENT_OBJECT_OID")
	private BigDecimal persistentObjectOid;

	@Column(name="PROPERTIES_OID")
	private BigDecimal propertiesOid;

	public PropertiesDomain001() {
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

	public BigDecimal getPersistentObjectOid() {
		return this.persistentObjectOid;
	}

	public void setPersistentObjectOid(BigDecimal persistentObjectOid) {
		this.persistentObjectOid = persistentObjectOid;
	}

	public BigDecimal getPropertiesOid() {
		return this.propertiesOid;
	}

	public void setPropertiesOid(BigDecimal propertiesOid) {
		this.propertiesOid = propertiesOid;
	}

}