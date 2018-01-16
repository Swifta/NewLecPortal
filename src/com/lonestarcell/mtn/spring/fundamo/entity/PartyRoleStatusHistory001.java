package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PARTY_ROLE_STATUS_HISTORY001 database table.
 * 
 */
@Entity
@Table(name="PARTY_ROLE_STATUS_HISTORY001")
@NamedQuery(name="PartyRoleStatusHistory001.findAll", query="SELECT p FROM PartyRoleStatusHistory001 p")
public class PartyRoleStatusHistory001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	private String reason;

	private String status;

	@Column(name="STATUS_UPDATE_DATE")
	private Timestamp statusUpdateDate;

	public PartyRoleStatusHistory001() {
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

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Timestamp statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

}