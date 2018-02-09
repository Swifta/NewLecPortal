package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SUBSCRIBER001 database table.
 * 
 */
@Entity
@Table(name="SUBSCRIBER001")
@NamedQuery(name="Subscriber001.findAll", query="SELECT s FROM Subscriber001 s")
@Proxy(lazy = false)
public class Subscriber001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CAPTURED_BY_OID")
	private BigDecimal capturedByOid;

	@Column(name="CREDIT_CHECK")
	private String creditCheck;

	private String judgements;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	/*
	@Column(name="PARTY_OID")
	private BigDecimal partyOid;
	*/
	
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "subscriber001" )
	private List< UserAccount001 > userAccount001s;
	
	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name="PARTY_OID" )
	private Person001 person001;

	@Column(name="REGISTERED_BY_OID")
	private BigDecimal registeredByOid;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	@Column(name="SECRET_CODE")
	private String secretCode;

	private String status;

	public Subscriber001() {
	}
	
	

	/*
	public UserAccount001 getUserAccount001() {
		return userAccount001;
	}



	public void setUserAccount001(UserAccount001 userAccount001) {
		this.userAccount001 = userAccount001;
	} */
	
	



	public long getOid() {
		return this.oid;
	}

	public List<UserAccount001> getUserAccount001s() {
		return userAccount001s;
	}



	public void setUserAccount001s(List<UserAccount001> userAccount001s) {
		this.userAccount001s = userAccount001s;
	}



	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCapturedByOid() {
		return this.capturedByOid;
	}

	public void setCapturedByOid(BigDecimal capturedByOid) {
		this.capturedByOid = capturedByOid;
	}

	public String getCreditCheck() {
		return this.creditCheck;
	}

	public void setCreditCheck(String creditCheck) {
		this.creditCheck = creditCheck;
	}

	public String getJudgements() {
		return this.judgements;
	}

	public void setJudgements(String judgements) {
		this.judgements = judgements;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	} */
	
	
	

	public BigDecimal getRegisteredByOid() {
		return this.registeredByOid;
	}

	public Person001 getPerson001() {
		return person001;
	}

	public void setPerson001(Person001 person001) {
		this.person001 = person001;
	}

	public void setRegisteredByOid(BigDecimal registeredByOid) {
		this.registeredByOid = registeredByOid;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public String getSecretCode() {
		return this.secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}