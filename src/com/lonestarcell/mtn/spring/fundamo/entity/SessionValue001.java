package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SESSION_VALUE001 database table.
 * 
 */
@Entity
@Table(name="SESSION_VALUE001")
@NamedQuery(name="SessionValue001.findAll", query="SELECT s FROM SessionValue001 s")
public class SessionValue001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="SESSION_KEY")
	private String sessionKey;

	@Column(name="\"TIMEOUT\"")
	private BigDecimal timeout;

	@Lob
	@Column(name="\"VALUE\"")
	private String value;

	public SessionValue001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public BigDecimal getTimeout() {
		return this.timeout;
	}

	public void setTimeout(BigDecimal timeout) {
		this.timeout = timeout;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}