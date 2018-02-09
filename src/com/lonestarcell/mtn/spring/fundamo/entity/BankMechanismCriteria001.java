package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK_MECHANISM_CRITERIA001 database table.
 * 
 */
@Entity
@Table(name="BANK_MECHANISM_CRITERIA001")
@NamedQuery(name="BankMechanismCriteria001.findAll", query="SELECT b FROM BankMechanismCriteria001 b")
public class BankMechanismCriteria001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BANK_MECHANISM_OID")
	private BigDecimal bankMechanismOid;

	@Column(name="BIN_OID")
	private BigDecimal binOid;

	@Column(name="INWARD_LEDGER_ACC_NAME")
	private String inwardLedgerAccName;

	@Column(name="INWARD_UNPAID_LEDGER_ACC_NAME")
	private String inwardUnpaidLedgerAccName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="OUTWARD_LEDGER_ACC_NAME")
	private String outwardLedgerAccName;

	@Column(name="OUTWARD_UNPAID_LEDGER_ACC_NAME")
	private String outwardUnpaidLedgerAccName;

	@Column(name="PROPERTY_FILE")
	private String propertyFile;

	public BankMechanismCriteria001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getBankMechanismOid() {
		return this.bankMechanismOid;
	}

	public void setBankMechanismOid(BigDecimal bankMechanismOid) {
		this.bankMechanismOid = bankMechanismOid;
	}

	public BigDecimal getBinOid() {
		return this.binOid;
	}

	public void setBinOid(BigDecimal binOid) {
		this.binOid = binOid;
	}

	public String getInwardLedgerAccName() {
		return this.inwardLedgerAccName;
	}

	public void setInwardLedgerAccName(String inwardLedgerAccName) {
		this.inwardLedgerAccName = inwardLedgerAccName;
	}

	public String getInwardUnpaidLedgerAccName() {
		return this.inwardUnpaidLedgerAccName;
	}

	public void setInwardUnpaidLedgerAccName(String inwardUnpaidLedgerAccName) {
		this.inwardUnpaidLedgerAccName = inwardUnpaidLedgerAccName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getOutwardLedgerAccName() {
		return this.outwardLedgerAccName;
	}

	public void setOutwardLedgerAccName(String outwardLedgerAccName) {
		this.outwardLedgerAccName = outwardLedgerAccName;
	}

	public String getOutwardUnpaidLedgerAccName() {
		return this.outwardUnpaidLedgerAccName;
	}

	public void setOutwardUnpaidLedgerAccName(String outwardUnpaidLedgerAccName) {
		this.outwardUnpaidLedgerAccName = outwardUnpaidLedgerAccName;
	}

	public String getPropertyFile() {
		return this.propertyFile;
	}

	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

}