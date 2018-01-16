package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_TRAN_CHAR_SET_VALUE001 database table.
 * 
 */
@Entity
@Table(name="CARD_TRAN_CHAR_SET_VALUE001")
@NamedQuery(name="CardTranCharSetValue001.findAll", query="SELECT c FROM CardTranCharSetValue001 c")
public class CardTranCharSetValue001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CTCSET_OID")
	private BigDecimal ctcsetOid;

	@Column(name="CTCVALUE_OID")
	private BigDecimal ctcvalueOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public CardTranCharSetValue001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCtcsetOid() {
		return this.ctcsetOid;
	}

	public void setCtcsetOid(BigDecimal ctcsetOid) {
		this.ctcsetOid = ctcsetOid;
	}

	public BigDecimal getCtcvalueOid() {
		return this.ctcvalueOid;
	}

	public void setCtcvalueOid(BigDecimal ctcvalueOid) {
		this.ctcvalueOid = ctcvalueOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}