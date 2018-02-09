package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the INFO_ENTRY001 database table.
 * 
 */
@Entity
@Table(name="INFO_ENTRY001")
@NamedQuery(name="InfoEntry001.findAll", query="SELECT i FROM InfoEntry001 i")
public class InfoEntry001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_OID")
	private BigDecimal accountOid;

	@Column(name="AMOUNT_VALUE")
	private double amountValue;

	@Column(name="DATE_TIME_STAMP")
	private Timestamp dateTimeStamp;

	private String description;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="STRING_VALUE")
	private String stringValue;

	@Column(name="\"TYPE\"")
	private String type;

	public InfoEntry001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountOid() {
		return this.accountOid;
	}

	public void setAccountOid(BigDecimal accountOid) {
		this.accountOid = accountOid;
	}

	public double getAmountValue() {
		return this.amountValue;
	}

	public void setAmountValue(double amountValue) {
		this.amountValue = amountValue;
	}

	public Timestamp getDateTimeStamp() {
		return this.dateTimeStamp;
	}

	public void setDateTimeStamp(Timestamp dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}