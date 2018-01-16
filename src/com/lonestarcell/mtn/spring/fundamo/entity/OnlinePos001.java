package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ONLINE_POS001 database table.
 * 
 */
@Entity
@Table(name="ONLINE_POS001")
@NamedQuery(name="OnlinePos001.findAll", query="SELECT o FROM OnlinePos001 o")
public class OnlinePos001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DEVICE_IDENTIFIER")
	private String deviceIdentifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="POS_MERCHANT_OID")
	private BigDecimal posMerchantOid;

	@Column(name="SERVICE_PROVIDER_OID")
	private BigDecimal serviceProviderOid;

	@Column(name="TILL_NUMBER")
	private String tillNumber;

	public OnlinePos001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public BigDecimal getPosMerchantOid() {
		return this.posMerchantOid;
	}

	public void setPosMerchantOid(BigDecimal posMerchantOid) {
		this.posMerchantOid = posMerchantOid;
	}

	public BigDecimal getServiceProviderOid() {
		return this.serviceProviderOid;
	}

	public void setServiceProviderOid(BigDecimal serviceProviderOid) {
		this.serviceProviderOid = serviceProviderOid;
	}

	public String getTillNumber() {
		return this.tillNumber;
	}

	public void setTillNumber(String tillNumber) {
		this.tillNumber = tillNumber;
	}

}