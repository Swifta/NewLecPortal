package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the AUTHORISATION001 database table.
 * 
 */
@Entity
@NamedQuery(name="Authorisation001.findAll", query="SELECT a FROM Authorisation001 a")
public class Authorisation001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CAN_AUTHORISE")
	private String canAuthorise;

	@Column(name="CAN_PERFORM")
	private String canPerform;

	@Column(name="\"FUNCTION\"")
	private String function;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="REQUIRES_AUTHORISATION")
	private String requiresAuthorisation;

	public Authorisation001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCanAuthorise() {
		return this.canAuthorise;
	}

	public void setCanAuthorise(String canAuthorise) {
		this.canAuthorise = canAuthorise;
	}

	public String getCanPerform() {
		return this.canPerform;
	}

	public void setCanPerform(String canPerform) {
		this.canPerform = canPerform;
	}

	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getRequiresAuthorisation() {
		return this.requiresAuthorisation;
	}

	public void setRequiresAuthorisation(String requiresAuthorisation) {
		this.requiresAuthorisation = requiresAuthorisation;
	}

}