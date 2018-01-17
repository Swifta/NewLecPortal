package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ACCOUNT_IDENTIFIER001 database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_IDENTIFIER001")
@NamedQuery(name="AccountIdentifier001.findAll", query="SELECT a FROM AccountIdentifier001 a")
public class AccountIdentifier001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="TYPE_NAME")
	private String typeName;

	// @Column(name="USER_ACCOUNT_OID")
	// private BigDecimal userAccountOid;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn( name = "USER_ACCOUNT_OID" )
	private UserAccount001 userAccount001;
	
	

	public AccountIdentifier001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public UserAccount001 getUserAccount001() {
		return userAccount001;
	}

	public void setUserAccount001(UserAccount001 userAccount001) {
		this.userAccount001 = userAccount001;
	}

	/*
	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}
	*/
	
	

}