package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_VERIFICATION_REQUEST001 database table.
 * 
 */
@Entity
@Table(name="FICA_VERIFICATION_REQUEST001")
@NamedQuery(name="FicaVerificationRequest001.findAll", query="SELECT f FROM FicaVerificationRequest001 f")
public class FicaVerificationRequest001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AGENT_IDENTIFIER")
	private String agentIdentifier;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="FICA_DETAIL_ID")
	private String ficaDetailId;

	@Column(name="FICA_DETAIL_ID_TYPE")
	private String ficaDetailIdType;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;

	public FicaVerificationRequest001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAgentIdentifier() {
		return this.agentIdentifier;
	}

	public void setAgentIdentifier(String agentIdentifier) {
		this.agentIdentifier = agentIdentifier;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getFicaDetailId() {
		return this.ficaDetailId;
	}

	public void setFicaDetailId(String ficaDetailId) {
		this.ficaDetailId = ficaDetailId;
	}

	public String getFicaDetailIdType() {
		return this.ficaDetailIdType;
	}

	public void setFicaDetailIdType(String ficaDetailIdType) {
		this.ficaDetailIdType = ficaDetailIdType;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}