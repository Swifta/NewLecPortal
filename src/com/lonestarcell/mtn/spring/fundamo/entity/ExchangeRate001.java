package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EXCHANGE_RATE001 database table.
 * 
 */
@Entity
@Table(name="EXCHANGE_RATE001")
@NamedQuery(name="ExchangeRate001.findAll", query="SELECT e FROM ExchangeRate001 e")
public class ExchangeRate001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BUY_RATE")
	private double buyRate;

	@Column(name="EFFECTIVE_DATE_TIME")
	private Timestamp effectiveDateTime;

	@Column(name="EXCHANGE_RATE_DEFINITION_OID")
	private BigDecimal exchangeRateDefinitionOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="OWNER_OID")
	private BigDecimal ownerOid;

	@Column(name="SELL_RATE")
	private double sellRate;

	public ExchangeRate001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getBuyRate() {
		return this.buyRate;
	}

	public void setBuyRate(double buyRate) {
		this.buyRate = buyRate;
	}

	public Timestamp getEffectiveDateTime() {
		return this.effectiveDateTime;
	}

	public void setEffectiveDateTime(Timestamp effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public BigDecimal getExchangeRateDefinitionOid() {
		return this.exchangeRateDefinitionOid;
	}

	public void setExchangeRateDefinitionOid(BigDecimal exchangeRateDefinitionOid) {
		this.exchangeRateDefinitionOid = exchangeRateDefinitionOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOwnerOid() {
		return this.ownerOid;
	}

	public void setOwnerOid(BigDecimal ownerOid) {
		this.ownerOid = ownerOid;
	}

	public double getSellRate() {
		return this.sellRate;
	}

	public void setSellRate(double sellRate) {
		this.sellRate = sellRate;
	}

}