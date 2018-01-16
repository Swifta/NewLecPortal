package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the POSTILION_TRX_RULE_COUNTRY001 database table.
 * 
 */
@Entity
@Table(name="POSTILION_TRX_RULE_COUNTRY001")
@NamedQuery(name="PostilionTrxRuleCountry001.findAll", query="SELECT p FROM PostilionTrxRuleCountry001 p")
public class PostilionTrxRuleCountry001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="COUNTRY_OID")
	private BigDecimal countryOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="TRANSACTING_RULE_OID")
	private BigDecimal transactingRuleOid;

	public PostilionTrxRuleCountry001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCountryOid() {
		return this.countryOid;
	}

	public void setCountryOid(BigDecimal countryOid) {
		this.countryOid = countryOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getTransactingRuleOid() {
		return this.transactingRuleOid;
	}

	public void setTransactingRuleOid(BigDecimal transactingRuleOid) {
		this.transactingRuleOid = transactingRuleOid;
	}

}