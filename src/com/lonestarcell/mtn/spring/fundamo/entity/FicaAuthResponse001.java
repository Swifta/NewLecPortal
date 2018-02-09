package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_AUTH_RESPONSE001 database table.
 * 
 */
@Entity
@Table(name="FICA_AUTH_RESPONSE001")
@NamedQuery(name="FicaAuthResponse001.findAll", query="SELECT f FROM FicaAuthResponse001 f")
public class FicaAuthResponse001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AUTHENTICATION_QUESTION_OID")
	private BigDecimal authenticationQuestionOid;

	@Column(name="FICA_PERSON_OID")
	private BigDecimal ficaPersonOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public FicaAuthResponse001() {
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

	public BigDecimal getFicaPersonOid() {
		return this.ficaPersonOid;
	}

	public void setFicaPersonOid(BigDecimal ficaPersonOid) {
		this.ficaPersonOid = ficaPersonOid;
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