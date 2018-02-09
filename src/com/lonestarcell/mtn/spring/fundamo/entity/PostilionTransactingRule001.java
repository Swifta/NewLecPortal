package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the POSTILION_TRANSACTING_RULE001 database table.
 * 
 */
@Entity
@Table(name="POSTILION_TRANSACTING_RULE001")
@NamedQuery(name="PostilionTransactingRule001.findAll", query="SELECT p FROM PostilionTransactingRule001 p")
public class PostilionTransactingRule001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CLASS_TYPE")
	private BigDecimal classType;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PRODUCT_OID")
	private BigDecimal productOid;

	public PostilionTransactingRule001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getClassType() {
		return this.classType;
	}

	public void setClassType(BigDecimal classType) {
		this.classType = classType;
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

}