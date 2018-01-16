package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DATED_INCREMENTED_NUMBER001 database table.
 * 
 */
@Entity
@Table(name="DATED_INCREMENTED_NUMBER001")
@NamedQuery(name="DatedIncrementedNumber001.findAll", query="SELECT d FROM DatedIncrementedNumber001 d")
public class DatedIncrementedNumber001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private Timestamp dte;

	@Column(name="INDEX_NAME")
	private String indexName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="\"VALUE\"")
	private BigDecimal value;

	public DatedIncrementedNumber001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getDte() {
		return this.dte;
	}

	public void setDte(Timestamp dte) {
		this.dte = dte;
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