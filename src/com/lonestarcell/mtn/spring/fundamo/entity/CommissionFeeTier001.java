package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the COMMISSION_FEE_TIER001 database table.
 * 
 */
@Entity
@Table(name="COMMISSION_FEE_TIER001")
@NamedQuery(name="CommissionFeeTier001.findAll", query="SELECT c FROM CommissionFeeTier001 c")
public class CommissionFeeTier001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="COMMISSION_FEE_OID")
	private BigDecimal commissionFeeOid;

	private double fee;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="TIER_END")
	private double tierEnd;

	@Column(name="TIER_INDEX")
	private BigDecimal tierIndex;

	@Column(name="TIER_START")
	private double tierStart;

	public CommissionFeeTier001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCommissionFeeOid() {
		return this.commissionFeeOid;
	}

	public void setCommissionFeeOid(BigDecimal commissionFeeOid) {
		this.commissionFeeOid = commissionFeeOid;
	}

	public double getFee() {
		return this.fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getTierEnd() {
		return this.tierEnd;
	}

	public void setTierEnd(double tierEnd) {
		this.tierEnd = tierEnd;
	}

	public BigDecimal getTierIndex() {
		return this.tierIndex;
	}

	public void setTierIndex(BigDecimal tierIndex) {
		this.tierIndex = tierIndex;
	}

	public double getTierStart() {
		return this.tierStart;
	}

	public void setTierStart(double tierStart) {
		this.tierStart = tierStart;
	}

}