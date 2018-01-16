package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the RESERVE_BANK_CODE001 database table.
 * 
 */
@Entity
@Table(name="RESERVE_BANK_CODE001")
@NamedQuery(name="ReserveBankCode001.findAll", query="SELECT r FROM ReserveBankCode001 r")
public class ReserveBankCode001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String code;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public ReserveBankCode001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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