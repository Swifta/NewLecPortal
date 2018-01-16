package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EVENT_LOG001 database table.
 * 
 */
@Entity
@Table(name="EVENT_LOG001")
@NamedQuery(name="EventLog001.findAll", query="SELECT e FROM EventLog001 e")
public class EventLog001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AFFECTED_PARTY_ROLE_OID")
	private BigDecimal affectedPartyRoleOid;

	@Column(name="CREATED_BY_PARTY_ROLE_OID")
	private BigDecimal createdByPartyRoleOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String description;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="\"TYPE\"")
	private String type;

	public EventLog001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAffectedPartyRoleOid() {
		return this.affectedPartyRoleOid;
	}

	public void setAffectedPartyRoleOid(BigDecimal affectedPartyRoleOid) {
		this.affectedPartyRoleOid = affectedPartyRoleOid;
	}

	public BigDecimal getCreatedByPartyRoleOid() {
		return this.createdByPartyRoleOid;
	}

	public void setCreatedByPartyRoleOid(BigDecimal createdByPartyRoleOid) {
		this.createdByPartyRoleOid = createdByPartyRoleOid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}