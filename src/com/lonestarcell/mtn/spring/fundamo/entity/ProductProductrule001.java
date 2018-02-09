package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PRODUCT_PRODUCTRULE001 database table.
 * 
 */
@Entity
@Table(name="PRODUCT_PRODUCTRULE001")
@NamedQuery(name="ProductProductrule001.findAll", query="SELECT p FROM ProductProductrule001 p")
public class ProductProductrule001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PRODUCT_OID")
	private BigDecimal productOid;

	@Column(name="PRODUCT_RULE_OID")
	private BigDecimal productRuleOid;

	public ProductProductrule001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public BigDecimal getProductRuleOid() {
		return this.productRuleOid;
	}

	public void setProductRuleOid(BigDecimal productRuleOid) {
		this.productRuleOid = productRuleOid;
	}

}