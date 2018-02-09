package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the REBATE001 database table.
 * 
 */
@Entity
@NamedQuery(name="Rebate001.findAll", query="SELECT r FROM Rebate001 r")
public class Rebate001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FLAT_FEE")
	private String flatFee;

	private BigDecimal idx;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public Rebate001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getFlatFee() {
		return this.flatFee;
	}

	public void setFlatFee(String flatFee) {
		this.flatFee = flatFee;
	}

	public BigDecimal getIdx() {
		return this.idx;
	}

	public void setIdx(BigDecimal idx) {
		this.idx = idx;
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

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}