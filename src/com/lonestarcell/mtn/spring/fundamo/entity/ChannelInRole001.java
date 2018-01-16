package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CHANNEL_IN_ROLE001 database table.
 * 
 */
@Entity
@Table(name="CHANNEL_IN_ROLE001")
@NamedQuery(name="ChannelInRole001.findAll", query="SELECT c FROM ChannelInRole001 c")
public class ChannelInRole001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	public ChannelInRole001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getChannelOid() {
		return this.channelOid;
	}

	public void setChannelOid(BigDecimal channelOid) {
		this.channelOid = channelOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

}