package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EXT_PROVIDER_TRANS_TYPE001 database table.
 * 
 */
@Entity
@Table(name="EXT_PROVIDER_TRANS_TYPE001")
@NamedQuery(name="ExtProviderTransType001.findAll", query="SELECT e FROM ExtProviderTransType001 e")
public class ExtProviderTransType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="\"ACTION\"")
	private String action;

	private BigDecimal idx;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LEDGER_ACCOUNT_OID")
	private BigDecimal ledgerAccountOid;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="TRANSACTION_TYPE_OID")
	private BigDecimal transactionTypeOid;

	public ExtProviderTransType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public BigDecimal getLedgerAccountOid() {
		return this.ledgerAccountOid;
	}

	public void setLedgerAccountOid(BigDecimal ledgerAccountOid) {
		this.ledgerAccountOid = ledgerAccountOid;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public BigDecimal getTransactionTypeOid() {
		return this.transactionTypeOid;
	}

	public void setTransactionTypeOid(BigDecimal transactionTypeOid) {
		this.transactionTypeOid = transactionTypeOid;
	}

}