package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CHANNEL_STATUS_PARTY_ROLE001 database table.
 * 
 */
@Entity
@Table(name="CHANNEL_STATUS_PARTY_ROLE001")
@NamedQuery(name="ChannelStatusPartyRole001.findAll", query="SELECT c FROM ChannelStatusPartyRole001 c")
public class ChannelStatusPartyRole001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHANGED_DATE")
	private Timestamp changedDate;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	private String enabled;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="UPDATED_BY_OID")
	private BigDecimal updatedByOid;

	public ChannelStatusPartyRole001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getChangedDate() {
		return this.changedDate;
	}

	public void setChangedDate(Timestamp changedDate) {
		this.changedDate = changedDate;
	}

	public BigDecimal getChannelOid() {
		return this.channelOid;
	}

	public void setChannelOid(BigDecimal channelOid) {
		this.channelOid = channelOid;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
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

	public BigDecimal getUpdatedByOid() {
		return this.updatedByOid;
	}

	public void setUpdatedByOid(BigDecimal updatedByOid) {
		this.updatedByOid = updatedByOid;
	}

}