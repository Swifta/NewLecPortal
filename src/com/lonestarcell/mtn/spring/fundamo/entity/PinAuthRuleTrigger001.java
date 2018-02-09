package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PIN_AUTH_RULE_TRIGGER001 database table.
 * 
 */
@Entity
@Table(name="PIN_AUTH_RULE_TRIGGER001")
@NamedQuery(name="PinAuthRuleTrigger001.findAll", query="SELECT p FROM PinAuthRuleTrigger001 p")
public class PinAuthRuleTrigger001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PRODUCT_OID")
	private BigDecimal productOid;

	@Column(name="RULE_OID")
	private BigDecimal ruleOid;

	public PinAuthRuleTrigger001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getProductOid() {
		return this.productOid;
	}

	public void setProductOid(BigDecimal productOid) {
		this.productOid = productOid;
	}

	public BigDecimal getRuleOid() {
		return this.ruleOid;
	}

	public void setRuleOid(BigDecimal ruleOid) {
		this.ruleOid = ruleOid;
	}

}