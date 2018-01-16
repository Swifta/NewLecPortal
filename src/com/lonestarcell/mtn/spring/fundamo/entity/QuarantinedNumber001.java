package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the QUARANTINED_NUMBER001 database table.
 * 
 */
@Entity
@Table(name="QUARANTINED_NUMBER001")
@NamedQuery(name="QuarantinedNumber001.findAll", query="SELECT q FROM QuarantinedNumber001 q")
public class QuarantinedNumber001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="EXPIRE_ON")
	private Timestamp expireOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="USER_ACCOUNT_NUMBER")
	private String userAccountNumber;

	public QuarantinedNumber001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserAccountNumber() {
		return this.userAccountNumber;
	}

	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}

}