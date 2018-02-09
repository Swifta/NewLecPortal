package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MOBILE_IDENTITY001 database table.
 * 
 */
@Entity
@Table(name="MOBILE_IDENTITY001")
@NamedQuery(name="MobileIdentity001.findAll", query="SELECT m FROM MobileIdentity001 m")
public class MobileIdentity001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SECURITY_ID")
	private String securityId;

	@Column(name="SECURITY_ID_NAME")
	private String securityIdName;

	@Column(name="SECURITY_ID_UPDATED")
	private String securityIdUpdated;

	public MobileIdentity001() {
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