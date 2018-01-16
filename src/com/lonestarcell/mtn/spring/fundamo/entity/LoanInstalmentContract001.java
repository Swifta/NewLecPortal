package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LOAN_INSTALMENT_CONTRACT001 database table.
 * 
 */
@Entity
@Table(name="LOAN_INSTALMENT_CONTRACT001")
@NamedQuery(name="LoanInstalmentContract001.findAll", query="SELECT l FROM LoanInstalmentContract001 l")
public class LoanInstalmentContract001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private double amount;

	@Column(name="CLIENT_REFERENCE")
	private String clientReference;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LENDER_OID")
	private BigDecimal lenderOid;

	@Column(name="PARTIAL_PAYMENT_ALLOWED")
	private String partialPaymentAllowed;

	@Column(name="PAYEE_ACCOUNT_OID")
	private BigDecimal payeeAccountOid;

	@Column(name="PAYER_ACCOUNT_OID")
	private BigDecimal payerAccountOid;

	@Column(name="PAYMENT_OID")
	private BigDecimal paymentOid;

	private String reference;

	@Column(name="WINDOW_PERIOD")
	private BigDecimal windowPeriod;

	public LoanInstalmentContract001() {
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

	public String getClientReference() {
		return this.clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLenderOid() {
		return this.lenderOid;
	}

	public void setLenderOid(BigDecimal lenderOid) {
		this.lenderOid = lenderOid;
	}

	public String getPartialPaymentAllowed() {
		return this.partialPaymentAllowed;
	}

	public void setPartialPaymentAllowed(String partialPaymentAllowed) {
		this.partialPaymentAllowed = partialPaymentAllowed;
	}

	public BigDecimal getPayeeAccountOid() {
		return this.payeeAccountOid;
	}

	public void setPayeeAccountOid(BigDecimal payeeAccountOid) {
		this.payeeAccountOid = payeeAccountOid;
	}

	public BigDecimal getPayerAccountOid() {
		return this.payerAccountOid;
	}

	public void setPayerAccountOid(BigDecimal payerAccountOid) {
		this.payerAccountOid = payerAccountOid;
	}

	public BigDecimal getPaymentOid() {
		return this.paymentOid;
	}

	public void setPaymentOid(BigDecimal paymentOid) {
		this.paymentOid = paymentOid;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BigDecimal getWindowPeriod() {
		return this.windowPeriod;
	}

	public void setWindowPeriod(BigDecimal windowPeriod) {
		this.windowPeriod = windowPeriod;
	}

}