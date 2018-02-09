package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BILL_PAYMENT_DATA001 database table.
 * 
 */
@Entity
@Table(name="BILL_PAYMENT_DATA001")
@NamedQuery(name="BillPaymentData001.findAll", query="SELECT b FROM BillPaymentData001 b")
public class BillPaymentData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_REFERENCE")
	private String accountReference;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SERVICE_PROVIDER")
	private String serviceProvider;

	private String token;

	@Column(name="TRANSACTION_OID")
	private BigDecimal transactionOid;

	public BillPaymentData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountReference() {
		return this.accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getServiceProvider() {
		return this.serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getTransactionOid() {
		return this.transactionOid;
	}

	public void setTransactionOid(BigDecimal transactionOid) {
		this.transactionOid = transactionOid;
	}

}