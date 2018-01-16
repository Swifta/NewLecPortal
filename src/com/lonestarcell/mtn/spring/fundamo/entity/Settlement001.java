package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SETTLEMENT001 database table.
 * 
 */
@Entity
@NamedQuery(name="Settlement001.findAll", query="SELECT s FROM Settlement001 s")
public class Settlement001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CUT_OFF_TIME")
	private Timestamp cutOffTime;

	@Column(name="FROM_BIN")
	private String fromBin;

	@Column(name="LAST_SETTLEMENT_IN")
	private Timestamp lastSettlementIn;

	@Column(name="LAST_SETTLEMENT_OUT")
	private Timestamp lastSettlementOut;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="ROLE_ALLOWED")
	private String roleAllowed;

	@Column(name="TO_BIN")
	private String toBin;

	@Column(name="TRANSFER_ACCOUNT_OID")
	private BigDecimal transferAccountOid;

	public Settlement001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getCutOffTime() {
		return this.cutOffTime;
	}

	public void setCutOffTime(Timestamp cutOffTime) {
		this.cutOffTime = cutOffTime;
	}

	public String getFromBin() {
		return this.fromBin;
	}

	public void setFromBin(String fromBin) {
		this.fromBin = fromBin;
	}

	public Timestamp getLastSettlementIn() {
		return this.lastSettlementIn;
	}

	public void setLastSettlementIn(Timestamp lastSettlementIn) {
		this.lastSettlementIn = lastSettlementIn;
	}

	public Timestamp getLastSettlementOut() {
		return this.lastSettlementOut;
	}

	public void setLastSettlementOut(Timestamp lastSettlementOut) {
		this.lastSettlementOut = lastSettlementOut;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRoleAllowed() {
		return this.roleAllowed;
	}

	public void setRoleAllowed(String roleAllowed) {
		this.roleAllowed = roleAllowed;
	}

	public String getToBin() {
		return this.toBin;
	}

	public void setToBin(String toBin) {
		this.toBin = toBin;
	}

	public BigDecimal getTransferAccountOid() {
		return this.transferAccountOid;
	}

	public void setTransferAccountOid(BigDecimal transferAccountOid) {
		this.transferAccountOid = transferAccountOid;
	}

}