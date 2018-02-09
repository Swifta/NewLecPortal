package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_STATUS_HISTORY001 database table.
 * 
 */
@Entity
@Table(name="CARD_STATUS_HISTORY001")
@NamedQuery(name="CardStatusHistory001.findAll", query="SELECT c FROM CardStatusHistory001 c")
public class CardStatusHistory001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_OID")
	private BigDecimal cardOid;

	@Column(name="CARD_STATUS_TYPE_OID")
	private BigDecimal cardStatusTypeOid;

	@Column(name="DATE_CHANGED")
	private Timestamp dateChanged;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String status;

	public CardStatusHistory001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCardOid() {
		return this.cardOid;
	}

	public void setCardOid(BigDecimal cardOid) {
		this.cardOid = cardOid;
	}

	public BigDecimal getCardStatusTypeOid() {
		return this.cardStatusTypeOid;
	}

	public void setCardStatusTypeOid(BigDecimal cardStatusTypeOid) {
		this.cardStatusTypeOid = cardStatusTypeOid;
	}

	public Timestamp getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Timestamp dateChanged) {
		this.dateChanged = dateChanged;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}