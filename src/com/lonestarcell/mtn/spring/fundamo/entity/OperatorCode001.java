package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the OPERATOR_CODE001 database table.
 * 
 */
@Entity
@Table(name="OPERATOR_CODE001")
@NamedQuery(name="OperatorCode001.findAll", query="SELECT o FROM OperatorCode001 o")
public class OperatorCode001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MOBILE_OPERATOR_OID")
	private BigDecimal mobileOperatorOid;

	@Column(name="OPERATOR_CODE_NUMBER")
	private String operatorCodeNumber;

	public OperatorCode001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMobileOperatorOid() {
		return this.mobileOperatorOid;
	}

	public void setMobileOperatorOid(BigDecimal mobileOperatorOid) {
		this.mobileOperatorOid = mobileOperatorOid;
	}

	public String getOperatorCodeNumber() {
		return this.operatorCodeNumber;
	}

	public void setOperatorCodeNumber(String operatorCodeNumber) {
		this.operatorCodeNumber = operatorCodeNumber;
	}

}