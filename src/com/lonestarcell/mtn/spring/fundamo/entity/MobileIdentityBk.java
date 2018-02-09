package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MOBILE_IDENTITY_BK database table.
 * 
 */
// @Entity
@Table(name="MOBILE_IDENTITY_BK")
@NamedQuery(name="MobileIdentityBk.findAll", query="SELECT m FROM MobileIdentityBk m")
public class MobileIdentityBk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEVICE_IDENTIFIER")
	private String deviceIdentifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	private String rowcolumn;

	@Column(name="SECURITY_ID")
	private String securityId;

	@Column(name="SECURITY_ID_NAME")
	private String securityIdName;

	@Column(name="SECURITY_ID_UPDATED")
	private String securityIdUpdated;

	public MobileIdentityBk() {
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getRowcolumn() {
		return this.rowcolumn;
	}

	public void setRowcolumn(String rowcolumn) {
		this.rowcolumn = rowcolumn;
	}

	public String getSecurityId() {
		return this.securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getSecurityIdName() {
		return this.securityIdName;
	}

	public void setSecurityIdName(String securityIdName) {
		this.securityIdName = securityIdName;
	}

	public String getSecurityIdUpdated() {
		return this.securityIdUpdated;
	}

	public void setSecurityIdUpdated(String securityIdUpdated) {
		this.securityIdUpdated = securityIdUpdated;
	}

}