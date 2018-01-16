package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SUBSCRIBER_EXTENSION001 database table.
 * 
 */
@Entity
@Table(name="SUBSCRIBER_EXTENSION001")
@NamedQuery(name="SubscriberExtension001.findAll", query="SELECT s FROM SubscriberExtension001 s")
public class SubscriberExtension001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PURPOSE_OID")
	private BigDecimal accountPurposeOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SPECIAL_CUSTOMER")
	private String specialCustomer;

	public SubscriberExtension001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountPurposeOid() {
		return this.accountPurposeOid;
	}

	public void setAccountPurposeOid(BigDecimal accountPurposeOid) {
		this.accountPurposeOid = accountPurposeOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSpecialCustomer() {
		return this.specialCustomer;
	}

	public void setSpecialCustomer(String specialCustomer) {
		this.specialCustomer = specialCustomer;
	}

}