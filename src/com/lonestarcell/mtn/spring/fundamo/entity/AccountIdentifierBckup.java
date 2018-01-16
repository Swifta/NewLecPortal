package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ACCOUNT_IDENTIFIER_BCKUP database table.
 * 
 */
// @Entity
@Table(name="ACCOUNT_IDENTIFIER_BCKUP")
@NamedQuery(name="AccountIdentifierBckup.findAll", query="SELECT a FROM AccountIdentifierBckup a")
public class AccountIdentifierBckup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	private BigDecimal oid;

	@Column(name="TYPE_NAME")
	private String typeName;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public AccountIdentifierBckup() {
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}