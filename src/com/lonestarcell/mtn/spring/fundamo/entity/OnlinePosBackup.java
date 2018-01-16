package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ONLINE_POS_BACKUP database table.
 * 
 */
@Entity
@Table(name="ONLINE_POS_BACKUP")
@NamedQuery(name="OnlinePosBackup.findAll", query="SELECT o FROM OnlinePosBackup o")
public class OnlinePosBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEVICE_IDENTIFIER")
	private String deviceIdentifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="POS_MERCHANT_OID")
	private BigDecimal posMerchantOid;

	@Column(name="SERVICE_PROVIDER_OID")
	private BigDecimal serviceProviderOid;

	@Column(name="TILL_NUMBER")
	private String tillNumber;

	public OnlinePosBackup() {
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
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