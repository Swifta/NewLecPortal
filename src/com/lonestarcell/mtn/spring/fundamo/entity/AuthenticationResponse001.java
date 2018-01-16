package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the AUTHENTICATION_RESPONSE001 database table.
 * 
 */
@Entity
@Table(name="AUTHENTICATION_RESPONSE001")
@NamedQuery(name="AuthenticationResponse001.findAll", query="SELECT a FROM AuthenticationResponse001 a")
public class AuthenticationResponse001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AUTHENTICATION_QUESTION_OID")
	private BigDecimal authenticationQuestionOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="PERSON_OID")
	private BigDecimal personOid;

	public AuthenticationResponse001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAuthenticationQuestionOid() {
		return this.authenticationQuestionOid;
	}

	public void setAuthenticationQuestionOid(BigDecimal authenticationQuestionOid) {
		this.authenticationQuestionOid = authenticationQuestionOid;
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

	public BigDecimal getPersonOid() {
		return this.personOid;
	}

	public void setPersonOid(BigDecimal personOid) {
		this.personOid = personOid;
	}

}