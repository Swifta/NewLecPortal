package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MERCH_TRANSACTION_SET001 database table.
 * 
 */
@Entity
@Table(name="MERCH_TRANSACTION_SET001")
@NamedQuery(name="MerchTransactionSet001.findAll", query="SELECT m FROM MerchTransactionSet001 m")
public class MerchTransactionSet001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DEFAULT_PERCENTAGE")
	private double defaultPercentage;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MERCH_HIERARCHY_OID")
	private BigDecimal merchHierarchyOid;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public MerchTransactionSet001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getDefaultPercentage() {
		return this.defaultPercentage;
	}

	public void setDefaultPercentage(double defaultPercentage) {
		this.defaultPercentage = defaultPercentage;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMerchHierarchyOid() {
		return this.merchHierarchyOid;
	}

	public void setMerchHierarchyOid(BigDecimal merchHierarchyOid) {
		this.merchHierarchyOid = merchHierarchyOid;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}