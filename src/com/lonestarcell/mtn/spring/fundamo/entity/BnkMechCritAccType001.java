package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BNK_MECH_CRIT_ACC_TYPE001 database table.
 * 
 */
@Entity
@Table(name="BNK_MECH_CRIT_ACC_TYPE001")
@NamedQuery(name="BnkMechCritAccType001.findAll", query="SELECT b FROM BnkMechCritAccType001 b")
public class BnkMechCritAccType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BANK_ACCOUNT_TYPE_NAME")
	private String bankAccountTypeName;

	@Column(name="BANK_MECHANISM_CRITERIA_OID")
	private BigDecimal bankMechanismCriteriaOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public BnkMechCritAccType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBankAccountTypeName() {
		return this.bankAccountTypeName;
	}

	public void setBankAccountTypeName(String bankAccountTypeName) {
		this.bankAccountTypeName = bankAccountTypeName;
	}

	public BigDecimal getBankMechanismCriteriaOid() {
		return this.bankMechanismCriteriaOid;
	}

	public void setBankMechanismCriteriaOid(BigDecimal bankMechanismCriteriaOid) {
		this.bankMechanismCriteriaOid = bankMechanismCriteriaOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}