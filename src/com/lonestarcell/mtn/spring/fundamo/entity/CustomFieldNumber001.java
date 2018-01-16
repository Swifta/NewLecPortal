package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CUSTOM_FIELD_NUMBER001 database table.
 * 
 */
@Entity
@Table(name="CUSTOM_FIELD_NUMBER001")
@NamedQuery(name="CustomFieldNumber001.findAll", query="SELECT c FROM CustomFieldNumber001 c")
public class CustomFieldNumber001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CUSTOM_FIELD_OID")
	private BigDecimal customFieldOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	@Column(name="SEQUENCE_NUMBER")
	private BigDecimal sequenceNumber;

	public CustomFieldNumber001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCustomFieldOid() {
		return this.customFieldOid;
	}

	public void setCustomFieldOid(BigDecimal customFieldOid) {
		this.customFieldOid = customFieldOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public BigDecimal getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(BigDecimal sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}