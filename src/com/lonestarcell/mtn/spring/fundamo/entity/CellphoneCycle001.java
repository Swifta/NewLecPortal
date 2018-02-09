package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CELLPHONE_CYCLE001 database table.
 * 
 */
@Entity
@Table(name="CELLPHONE_CYCLE001")
@NamedQuery(name="CellphoneCycle001.findAll", query="SELECT c FROM CellphoneCycle001 c")
public class CellphoneCycle001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CELLPHONE_NUMBER")
	private String cellphoneNumber;

	@Column(name="CYCLE_NUMBER")
	private BigDecimal cycleNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public CellphoneCycle001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCellphoneNumber() {
		return this.cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	public BigDecimal getCycleNumber() {
		return this.cycleNumber;
	}

	public void setCycleNumber(BigDecimal cycleNumber) {
		this.cycleNumber = cycleNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}