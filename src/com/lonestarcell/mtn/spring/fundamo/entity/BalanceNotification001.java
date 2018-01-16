package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BALANCE_NOTIFICATION001 database table.
 * 
 */
@Entity
@Table(name="BALANCE_NOTIFICATION001")
@NamedQuery(name="BalanceNotification001.findAll", query="SELECT b FROM BalanceNotification001 b")
public class BalanceNotification001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AVAILABLE_BALANCE")
	private double availableBalance;

	private double balance;

	@Column(name="END_TIME")
	private String endTime;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String locale;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	private String payer;

	@Column(name="START_TIME")
	private String startTime;

	@Column(name="TRANSACTING_DETAIL_CLASS_NAME")
	private String transactingDetailClassName;

	@Column(name="TRANSACTING_DETAIL_OID")
	private BigDecimal transactingDetailOid;

	public BalanceNotification001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getAvailableBalance() {
		return this.availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getPayer() {
		return this.payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTransactingDetailClassName() {
		return this.transactingDetailClassName;
	}

	public void setTransactingDetailClassName(String transactingDetailClassName) {
		this.transactingDetailClassName = transactingDetailClassName;
	}

	public BigDecimal getTransactingDetailOid() {
		return this.transactingDetailOid;
	}

	public void setTransactingDetailOid(BigDecimal transactingDetailOid) {
		this.transactingDetailOid = transactingDetailOid;
	}

}