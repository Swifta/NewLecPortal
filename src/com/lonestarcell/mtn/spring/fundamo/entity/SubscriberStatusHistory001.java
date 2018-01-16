package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUBSCRIBER_STATUS_HISTORY001 database table.
 * 
 */
@Entity
@Table(name="SUBSCRIBER_STATUS_HISTORY001")
@NamedQuery(name="SubscriberStatusHistory001.findAll", query="SELECT s FROM SubscriberStatusHistory001 s")
public class SubscriberStatusHistory001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="STATUS_UPDATE_DATE")
	private Timestamp statusUpdateDate;

	@Column(name="SUBSCRIBER_OID")
	private BigDecimal subscriberOid;

	@Column(name="SUBSCRIBER_STATUS_OID")
	private BigDecimal subscriberStatusOid;

	public SubscriberStatusHistory001() {
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

	public Timestamp getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Timestamp statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public BigDecimal getSubscriberOid() {
		return this.subscriberOid;
	}

	public void setSubscriberOid(BigDecimal subscriberOid) {
		this.subscriberOid = subscriberOid;
	}

	public BigDecimal getSubscriberStatusOid() {
		return this.subscriberStatusOid;
	}

	public void setSubscriberStatusOid(BigDecimal subscriberStatusOid) {
		this.subscriberStatusOid = subscriberStatusOid;
	}

}