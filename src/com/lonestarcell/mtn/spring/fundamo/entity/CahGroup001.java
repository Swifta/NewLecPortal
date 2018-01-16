package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CAH_GROUP001 database table.
 * 
 */
@Entity
@Table(name="CAH_GROUP001")
@NamedQuery(name="CahGroup001.findAll", query="SELECT c FROM CahGroup001 c")
public class CahGroup001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_ISSUER")
	private String cardIssuer;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="PAYROLL_USER")
	private String payrollUser;

	public CahGroup001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCardIssuer() {
		return this.cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
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

	public String getPayrollUser() {
		return this.payrollUser;
	}

	public void setPayrollUser(String payrollUser) {
		this.payrollUser = payrollUser;
	}

}