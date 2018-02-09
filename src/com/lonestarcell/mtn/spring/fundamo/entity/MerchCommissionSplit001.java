package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MERCH_COMMISSION_SPLIT001 database table.
 * 
 */
@Entity
@Table(name="MERCH_COMMISSION_SPLIT001")
@NamedQuery(name="MerchCommissionSplit001.findAll", query="SELECT m FROM MerchCommissionSplit001 m")
public class MerchCommissionSplit001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MERCH_HIERARCHY_DETAIL_OID")
	private BigDecimal merchHierarchyDetailOid;

	private double percentage;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public MerchCommissionSplit001() {
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

	public BigDecimal getMerchHierarchyDetailOid() {
		return this.merchHierarchyDetailOid;
	}

	public void setMerchHierarchyDetailOid(BigDecimal merchHierarchyDetailOid) {
		this.merchHierarchyDetailOid = merchHierarchyDetailOid;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}