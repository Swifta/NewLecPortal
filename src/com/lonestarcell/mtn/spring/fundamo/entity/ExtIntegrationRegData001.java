package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the EXT_INTEGRATION_REG_DATA001 database table.
 * 
 */
@Entity
@Table(name="EXT_INTEGRATION_REG_DATA001")
@NamedQuery(name="ExtIntegrationRegData001.findAll", query="SELECT e FROM ExtIntegrationRegData001 e")
public class ExtIntegrationRegData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String msisdn;

	@Column(name="SUBSCRIBER_OID")
	private BigDecimal subscriberOid;

	public ExtIntegrationRegData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getSubscriberOid() {
		return this.subscriberOid;
	}

	public void setSubscriberOid(BigDecimal subscriberOid) {
		this.subscriberOid = subscriberOid;
	}

}