package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ORDER_REQUEST001 database table.
 * 
 */
@Entity
@Table(name="ORDER_REQUEST001")
@NamedQuery(name="OrderRequest001.findAll", query="SELECT o FROM OrderRequest001 o")
public class OrderRequest001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="ORDER_DATE")
	private Timestamp orderDate;

	@Column(name="PURCHASE_ORDER_NUMBER")
	private String purchaseOrderNumber;

	@Column(name="REJECTION_REASON")
	private String rejectionReason;

	@Column(name="REQUEST_NUMBER")
	private BigDecimal requestNumber;

	private String status;

	public OrderRequest001() {
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

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getPurchaseOrderNumber() {
		return this.purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getRejectionReason() {
		return this.rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public BigDecimal getRequestNumber() {
		return this.requestNumber;
	}

	public void setRequestNumber(BigDecimal requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}