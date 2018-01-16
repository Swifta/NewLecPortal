package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the INCREMENTED_NUMBERS001 database table.
 * 
 */
@Entity
@Table(name="INCREMENTED_NUMBERS001")
@NamedQuery(name="IncrementedNumbers001.findAll", query="SELECT i FROM IncrementedNumbers001 i")
public class IncrementedNumbers001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="INDEX_NAME")
	private String indexName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="\"VALUE\"")
	private BigDecimal value;

	public IncrementedNumbers001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getIndexName() {
		return this.indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}