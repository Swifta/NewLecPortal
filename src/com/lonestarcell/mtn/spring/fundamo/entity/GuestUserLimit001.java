package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the GUEST_USER_LIMIT001 database table.
 * 
 */
@Entity
@Table(name="GUEST_USER_LIMIT001")
@NamedQuery(name="GuestUserLimit001.findAll", query="SELECT g FROM GuestUserLimit001 g")
public class GuestUserLimit001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AMOUNT_REMAINING")
	private double amountRemaining;

	private Timestamp dte;

	@Column(name="GUEST_USER_IDENTIFIER")
	private String guestUserIdentifier;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String payer;

	@Column(name="\"TYPE\"")
	private String type;

	public GuestUserLimit001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getAmountRemaining() {
		return this.amountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public Timestamp getDte() {
		return this.dte;
	}

	public void setDte(Timestamp dte) {
		this.dte = dte;
	}

	public String getGuestUserIdentifier() {
		return this.guestUserIdentifier;
	}

	public void setGuestUserIdentifier(String guestUserIdentifier) {
		this.guestUserIdentifier = guestUserIdentifier;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPayer() {
		return this.payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}