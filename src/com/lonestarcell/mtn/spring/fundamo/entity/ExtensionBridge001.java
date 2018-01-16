package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EXTENSION_BRIDGE001 database table.
 * 
 */
@Entity
@Table(name="EXTENSION_BRIDGE001")
@NamedQuery(name="ExtensionBridge001.findAll", query="SELECT e FROM ExtensionBridge001 e")
public class ExtensionBridge001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CORE_OBJECT_OID")
	private BigDecimal coreObjectOid;

	@Column(name="EXTENSION_OBJECT_CLASS_NAME")
	private String extensionObjectClassName;

	@Column(name="EXTENSION_OBJECT_OID")
	private BigDecimal extensionObjectOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public ExtensionBridge001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCoreObjectOid() {
		return this.coreObjectOid;
	}

	public void setCoreObjectOid(BigDecimal coreObjectOid) {
		this.coreObjectOid = coreObjectOid;
	}

	public String getExtensionObjectClassName() {
		return this.extensionObjectClassName;
	}

	public void setExtensionObjectClassName(String extensionObjectClassName) {
		this.extensionObjectClassName = extensionObjectClassName;
	}

	public BigDecimal getExtensionObjectOid() {
		return this.extensionObjectOid;
	}

	public void setExtensionObjectOid(BigDecimal extensionObjectOid) {
		this.extensionObjectOid = extensionObjectOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}