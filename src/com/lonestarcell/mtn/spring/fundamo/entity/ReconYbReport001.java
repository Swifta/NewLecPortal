package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RECON_YB_REPORT001 database table.
 * 
 */
@Entity
@Table(name="RECON_YB_REPORT001")
@NamedQuery(name="ReconYbReport001.findAll", query="SELECT r FROM ReconYbReport001 r")
public class ReconYbReport001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="BATCH_ID")
	private String batchId;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="TOTAL_BALANCE")
	private double totalBalance;

	@Column(name="TOTAL_CUSTOMER")
	private double totalCustomer;

	@Column(name="\"TYPE\"")
	private String type;

	public ReconYbReport001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getTotalBalance() {
		return this.totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public double getTotalCustomer() {
		return this.totalCustomer;
	}

	public void setTotalCustomer(double totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}