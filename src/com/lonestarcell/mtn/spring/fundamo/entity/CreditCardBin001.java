package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CREDIT_CARD_BIN001 database table.
 * 
 */
@Entity
@Table(name="CREDIT_CARD_BIN001")
@NamedQuery(name="CreditCardBin001.findAll", query="SELECT c FROM CreditCardBin001 c")
public class CreditCardBin001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String allowed;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	private String currency;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	public CreditCardBin001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAllowed() {
		return this.allowed;
	}

	public void setAllowed(String allowed) {
		this.allowed = allowed;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

}