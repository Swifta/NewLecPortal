package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the GUARDIAN001 database table.
 * 
 */
@Entity
@NamedQuery(name="Guardian001.findAll", query="SELECT g FROM Guardian001 g")
public class Guardian001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	private String status;

	public Guardian001() {
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

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}