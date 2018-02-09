package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_VERIFICATION_REJECT001 database table.
 * 
 */
@Entity
@Table(name="FICA_VERIFICATION_REJECT001")
@NamedQuery(name="FicaVerificationReject001.findAll", query="SELECT f FROM FicaVerificationReject001 f")
public class FicaVerificationReject001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FICA_VERIFICATION_REQ_OID")
	private BigDecimal ficaVerificationReqOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="REJECTION_REASON")
	private String rejectionReason;

	public FicaVerificationReject001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getFicaVerificationReqOid() {
		return this.ficaVerificationReqOid;
	}

	public void setFicaVerificationReqOid(BigDecimal ficaVerificationReqOid) {
		this.ficaVerificationReqOid = ficaVerificationReqOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRejectionReason() {
		return this.rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

}