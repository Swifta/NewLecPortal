package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_VERIFICATION001 database table.
 * 
 */
@Entity
@Table(name="FICA_VERIFICATION001")
@NamedQuery(name="FicaVerification001.findAll", query="SELECT f FROM FicaVerification001 f")
public class FicaVerification001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="AGENT_NAME")
	private String agentName;

	@Column(name="AGENT_NUMBER")
	private String agentNumber;

	@Column(name="DATE_VERIFIED")
	private Timestamp dateVerified;

	@Column(name="DOCUMENTATION_RECEIVED")
	private String documentationReceived;

	@Column(name="\"EXTERNAL\"")
	private String external;

	@Column(name="FIELD_AGENT_OID")
	private BigDecimal fieldAgentOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="OUTLET_OID")
	private BigDecimal outletOid;

	@Column(name="PHYSICAL_ADDRESS_PROVEN")
	private String physicalAddressProven;

	public FicaVerification001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAgentName() {
		return this.agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNumber() {
		return this.agentNumber;
	}

	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}

	public Timestamp getDateVerified() {
		return this.dateVerified;
	}

	public void setDateVerified(Timestamp dateVerified) {
		this.dateVerified = dateVerified;
	}

	public String getDocumentationReceived() {
		return this.documentationReceived;
	}

	public void setDocumentationReceived(String documentationReceived) {
		this.documentationReceived = documentationReceived;
	}

	public String getExternal() {
		return this.external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	public BigDecimal getFieldAgentOid() {
		return this.fieldAgentOid;
	}

	public void setFieldAgentOid(BigDecimal fieldAgentOid) {
		this.fieldAgentOid = fieldAgentOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOutletOid() {
		return this.outletOid;
	}

	public void setOutletOid(BigDecimal outletOid) {
		this.outletOid = outletOid;
	}

	public String getPhysicalAddressProven() {
		return this.physicalAddressProven;
	}

	public void setPhysicalAddressProven(String physicalAddressProven) {
		this.physicalAddressProven = physicalAddressProven;
	}

}