package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ORDER_TYPE001 database table.
 * 
 */
@Entity
@Table(name="ORDER_TYPE001")
@NamedQuery(name="OrderType001.findAll", query="SELECT o FROM OrderType001 o")
public class OrderType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="ORDER_REQUEST_OID")
	private BigDecimal orderRequestOid;

	private BigDecimal quantity;

	public OrderType001() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOrderRequestOid() {
		return this.orderRequestOid;
	}

	public void setOrderRequestOid(BigDecimal orderRequestOid) {
		this.orderRequestOid = orderRequestOid;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}