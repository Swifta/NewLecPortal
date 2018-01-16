package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DEVICE_CHANNEL001 database table.
 * 
 */
@Entity
@Table(name="DEVICE_CHANNEL001")
@NamedQuery(name="DeviceChannel001.findAll", query="SELECT d FROM DeviceChannel001 d")
public class DeviceChannel001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	@Column(name="DEVICE_OID")
	private BigDecimal deviceOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MOBILE_IDENTITY_OID")
	private BigDecimal mobileIdentityOid;

	@Column(name="PREFERRED_REQUEST_CHANNEL")
	private String preferredRequestChannel;

	public DeviceChannel001() {
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

	public BigDecimal getDeviceOid() {
		return this.deviceOid;
	}

	public void setDeviceOid(BigDecimal deviceOid) {
		this.deviceOid = deviceOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMobileIdentityOid() {
		return this.mobileIdentityOid;
	}

	public void setMobileIdentityOid(BigDecimal mobileIdentityOid) {
		this.mobileIdentityOid = mobileIdentityOid;
	}

	public String getPreferredRequestChannel() {
		return this.preferredRequestChannel;
	}

	public void setPreferredRequestChannel(String preferredRequestChannel) {
		this.preferredRequestChannel = preferredRequestChannel;
	}

}