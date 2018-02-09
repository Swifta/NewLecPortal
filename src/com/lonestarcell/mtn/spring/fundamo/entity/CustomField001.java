package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CUSTOM_FIELD001 database table.
 * 
 */
@Entity
@Table(name="CUSTOM_FIELD001")
@NamedQuery(name="CustomField001.findAll", query="SELECT c FROM CustomField001 c")
public class CustomField001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CLASS_TYPE")
	private String classType;

	@Column(name="FIELD_NUMBER")
	private BigDecimal fieldNumber;

	@Column(name="\"LABEL\"")
	private String label;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String mandatory;

	@Column(name="\"PARAMETER\"")
	private String parameter;

	public CustomField001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public BigDecimal getFieldNumber() {
		return this.fieldNumber;
	}

	public void setFieldNumber(BigDecimal fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMandatory() {
		return this.mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}