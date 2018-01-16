package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the INTERNET_LOGIN_ATTEMPT001 database table.
 * 
 */
@Entity
@Table(name="INTERNET_LOGIN_ATTEMPT001")
@NamedQuery(name="InternetLoginAttempt001.findAll", query="SELECT i FROM InternetLoginAttempt001 i")
public class InternetLoginAttempt001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	@Column(name="EXPIRE_ON")
	private Timestamp expireOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MOBILE_PHONE_NUMBER")
	private String mobilePhoneNumber;

	@Column(name="RANDOM_NUMBER")
	private String randomNumber;

	private String success;

	public InternetLoginAttempt001() {
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

	public Timestamp getExpireOn() {
		return this.expireOn;
	}

	public void setExpireOn(Timestamp expireOn) {
		this.expireOn = expireOn;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMobilePhoneNumber() {
		return this.mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getRandomNumber() {
		return this.randomNumber;
	}

	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
	}

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}