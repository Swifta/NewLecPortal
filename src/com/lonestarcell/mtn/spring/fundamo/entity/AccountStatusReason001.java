package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ACCOUNT_STATUS_REASON001 database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_STATUS_REASON001")
@NamedQuery(name="AccountStatusReason001.findAll", query="SELECT a FROM AccountStatusReason001 a")
public class AccountStatusReason001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_STATUS")
	private String accountStatus;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public AccountStatusReason001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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

}