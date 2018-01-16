package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SERVICE001 database table.
 * 
 */
@Entity
@NamedQuery(name="Service001.findAll", query="SELECT s FROM Service001 s")
public class Service001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FLAT_FEE")
	private String flatFee;

	@Column(name="\"GROUPING\"")
	private String grouping;

	private BigDecimal idx;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public Service001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getFlatFee() {
		return this.flatFee;
	}

	public void setFlatFee(String flatFee) {
		this.flatFee = flatFee;
	}

	public String getGrouping() {
		return this.grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public BigDecimal getIdx() {
		return this.idx;
	}

	public void setIdx(BigDecimal idx) {
		this.idx = idx;
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

}