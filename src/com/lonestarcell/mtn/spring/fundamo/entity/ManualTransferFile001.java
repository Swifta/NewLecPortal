package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MANUAL_TRANSFER_FILE001 database table.
 * 
 */
@Entity
@Table(name="MANUAL_TRANSFER_FILE001")
@NamedQuery(name="ManualTransferFile001.findAll", query="SELECT m FROM ManualTransferFile001 m")
public class ManualTransferFile001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATION_DATE")
	private Timestamp creationDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="INSTALLATION_GENERATION_NUMBER")
	private String installationGenerationNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String mechanism;

	private BigDecimal oid;

	@Column(name="\"TYPE\"")
	private String type;

	public ManualTransferFile001() {
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getInstallationGenerationNumber() {
		return this.installationGenerationNumber;
	}

	public void setInstallationGenerationNumber(String installationGenerationNumber) {
		this.installationGenerationNumber = installationGenerationNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMechanism() {
		return this.mechanism;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}