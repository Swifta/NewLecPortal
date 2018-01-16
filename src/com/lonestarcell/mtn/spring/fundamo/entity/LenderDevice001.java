package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LENDER_DEVICE001 database table.
 * 
 */
@Entity
@Table(name="LENDER_DEVICE001")
@NamedQuery(name="LenderDevice001.findAll", query="SELECT l FROM LenderDevice001 l")
public class LenderDevice001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DEVICE_IDENTIFIER")
	private String deviceIdentifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LENDER_OID")
	private BigDecimal lenderOid;

	public LenderDevice001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDeviceIdentifier() {
		return this.deviceIdentifier;
	}

	public void setDeviceIdentifier(String deviceIdentifier) {
		this.deviceIdentifier = deviceIdentifier;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLenderOid() {
		return this.lenderOid;
	}

	public void setLenderOid(BigDecimal lenderOid) {
		this.lenderOid = lenderOid;
	}

}