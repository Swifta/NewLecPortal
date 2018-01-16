package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_VERIFICATION_TYPE001 database table.
 * 
 */
@Entity
@Table(name="FICA_VERIFICATION_TYPE001")
@NamedQuery(name="FicaVerificationType001.findAll", query="SELECT f FROM FicaVerificationType001 f")
public class FicaVerificationType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FICA_VERIFICATION_OID")
	private BigDecimal ficaVerificationOid;

	@Column(name="FICA_VERIFICATION_REJ_OID")
	private BigDecimal ficaVerificationRejOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	private String status;

	public FicaVerificationType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getFicaVerificationOid() {
		return this.ficaVerificationOid;
	}

	public void setFicaVerificationOid(BigDecimal ficaVerificationOid) {
		this.ficaVerificationOid = ficaVerificationOid;
	}

	public BigDecimal getFicaVerificationRejOid() {
		return this.ficaVerificationRejOid;
	}

	public void setFicaVerificationRejOid(BigDecimal ficaVerificationRejOid) {
		this.ficaVerificationRejOid = ficaVerificationRejOid;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}