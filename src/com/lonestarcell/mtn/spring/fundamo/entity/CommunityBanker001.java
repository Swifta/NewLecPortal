package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the COMMUNITY_BANKER001 database table.
 * 
 */
@Entity
@Table(name="COMMUNITY_BANKER001")
@NamedQuery(name="CommunityBanker001.findAll", query="SELECT c FROM CommunityBanker001 c")
public class CommunityBanker001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AREA_OID")
	private BigDecimal areaOid;

	@Column(name="GROUP_OID")
	private BigDecimal groupOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	@Column(name="OUTLET_OID")
	private BigDecimal outletOid;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	@Column(name="SECRET_CODE")
	private String secretCode;

	private String status;

	public CommunityBanker001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAreaOid() {
		return this.areaOid;
	}

	public void setAreaOid(BigDecimal areaOid) {
		this.areaOid = areaOid;
	}

	public BigDecimal getGroupOid() {
		return this.groupOid;
	}

	public void setGroupOid(BigDecimal groupOid) {
		this.groupOid = groupOid;
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

	public BigDecimal getOutletOid() {
		return this.outletOid;
	}

	public void setOutletOid(BigDecimal outletOid) {
		this.outletOid = outletOid;
	}

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
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