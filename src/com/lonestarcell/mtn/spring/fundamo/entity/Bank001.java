package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the BANK001 database table.
 * 
 */
@Entity
@NamedQuery(name="Bank001.findAll", query="SELECT b FROM Bank001 b")
public class Bank001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHECK_DIGIT_CLASS")
	private String checkDigitClass;

	@Column(name="CHECK_DIGIT_WEIGHTING")
	private String checkDigitWeighting;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public Bank001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCheckDigitClass() {
		return this.checkDigitClass;
	}

	public void setCheckDigitClass(String checkDigitClass) {
		this.checkDigitClass = checkDigitClass;
	}

	public String getCheckDigitWeighting() {
		return this.checkDigitWeighting;
	}

	public void setCheckDigitWeighting(String checkDigitWeighting) {
		this.checkDigitWeighting = checkDigitWeighting;
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