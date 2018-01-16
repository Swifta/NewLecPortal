package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PARTY_ROLE_FIELD001 database table.
 * 
 */
@Entity
@Table(name="PARTY_ROLE_FIELD001")
@NamedQuery(name="PartyRoleField001.findAll", query="SELECT p FROM PartyRoleField001 p")
public class PartyRoleField001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CUSTOM_FIELD_NUMBER_OID")
	private BigDecimal customFieldNumberOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="\"VALUE\"")
	private String value;

	public PartyRoleField001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCustomFieldNumberOid() {
		return this.customFieldNumberOid;
	}

	public void setCustomFieldNumberOid(BigDecimal customFieldNumberOid) {
		this.customFieldNumberOid = customFieldNumberOid;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}