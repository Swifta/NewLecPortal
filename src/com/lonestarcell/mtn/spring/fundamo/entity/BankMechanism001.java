package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK_MECHANISM001 database table.
 * 
 */
@Entity
@Table(name="BANK_MECHANISM001")
@NamedQuery(name="BankMechanism001.findAll", query="SELECT b FROM BankMechanism001 b")
public class BankMechanism001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="COMM_PORT")
	private BigDecimal commPort;

	@Column(name="DISPUTE_TIMEFRAME")
	private BigDecimal disputeTimeframe;

	@Column(name="EXPOSURE_LIMIT_TIMEFRAME")
	private BigDecimal exposureLimitTimeframe;

	@Column(name="INCOMING_ZONE_KEY")
	private String incomingZoneKey;

	@Column(name="INCOMING_ZONE_KEY_ENCR_KEY")
	private String incomingZoneKeyEncrKey;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="OUTGOING_ZONE_KEY")
	private String outgoingZoneKey;

	@Column(name="OUTGOING_ZONE_KEY_ENCR_KEY")
	private String outgoingZoneKeyEncrKey;

	@Column(name="PROPERTY_OBJECT")
	private String propertyObject;

	public BankMechanism001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCommPort() {
		return this.commPort;
	}

	public void setCommPort(BigDecimal commPort) {
		this.commPort = commPort;
	}

	public BigDecimal getDisputeTimeframe() {
		return this.disputeTimeframe;
	}

	public void setDisputeTimeframe(BigDecimal disputeTimeframe) {
		this.disputeTimeframe = disputeTimeframe;
	}

	public BigDecimal getExposureLimitTimeframe() {
		return this.exposureLimitTimeframe;
	}

	public void setExposureLimitTimeframe(BigDecimal exposureLimitTimeframe) {
		this.exposureLimitTimeframe = exposureLimitTimeframe;
	}

	public String getIncomingZoneKey() {
		return this.incomingZoneKey;
	}

	public void setIncomingZoneKey(String incomingZoneKey) {
		this.incomingZoneKey = incomingZoneKey;
	}

	public String getIncomingZoneKeyEncrKey() {
		return this.incomingZoneKeyEncrKey;
	}

	public void setIncomingZoneKeyEncrKey(String incomingZoneKeyEncrKey) {
		this.incomingZoneKeyEncrKey = incomingZoneKeyEncrKey;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutgoingZoneKey() {
		return this.outgoingZoneKey;
	}

	public void setOutgoingZoneKey(String outgoingZoneKey) {
		this.outgoingZoneKey = outgoingZoneKey;
	}

	public String getOutgoingZoneKeyEncrKey() {
		return this.outgoingZoneKeyEncrKey;
	}

	public void setOutgoingZoneKeyEncrKey(String outgoingZoneKeyEncrKey) {
		this.outgoingZoneKeyEncrKey = outgoingZoneKeyEncrKey;
	}

	public String getPropertyObject() {
		return this.propertyObject;
	}

	public void setPropertyObject(String propertyObject) {
		this.propertyObject = propertyObject;
	}

}