package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CAH_IN_REBATE_VALUE001 database table.
 * 
 */
@Entity
@Table(name="CAH_IN_REBATE_VALUE001")
@NamedQuery(name="CahInRebateValue001.findAll", query="SELECT c FROM CahInRebateValue001 c")
public class CahInRebateValue001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CORPORATE_ACCOUNT_HOLDER_OID")
	private BigDecimal corporateAccountHolderOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="REBATE_VALUE_OID")
	private BigDecimal rebateValueOid;

	public CahInRebateValue001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCorporateAccountHolderOid() {
		return this.corporateAccountHolderOid;
	}

	public void setCorporateAccountHolderOid(BigDecimal corporateAccountHolderOid) {
		this.corporateAccountHolderOid = corporateAccountHolderOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getRebateValueOid() {
		return this.rebateValueOid;
	}

	public void setRebateValueOid(BigDecimal rebateValueOid) {
		this.rebateValueOid = rebateValueOid;
	}

}