package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SERVICE_FEE_TIER001 database table.
 * 
 */
@Entity
@Table(name="SERVICE_FEE_TIER001")
@NamedQuery(name="ServiceFeeTier001.findAll", query="SELECT s FROM ServiceFeeTier001 s")
public class ServiceFeeTier001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double fee;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SERVICE_FEE_OID")
	private BigDecimal serviceFeeOid;

	@Column(name="TIER_END")
	private double tierEnd;

	@Column(name="TIER_INDEX")
	private BigDecimal tierIndex;

	@Column(name="TIER_START")
	private double tierStart;

	public ServiceFeeTier001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public BigDecimal getServiceFeeOid() {
		return this.serviceFeeOid;
	}

	public void setServiceFeeOid(BigDecimal serviceFeeOid) {
		this.serviceFeeOid = serviceFeeOid;
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