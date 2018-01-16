package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LOGIN_TIME001 database table.
 * 
 */
@Entity
@Table(name="LOGIN_TIME001")
@NamedQuery(name="LoginTime001.findAll", query="SELECT l FROM LoginTime001 l")
public class LoginTime001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="END_TIME")
	private String endTime;

	@Column(name="GROUP_OID")
	private BigDecimal groupOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="START_TIME")
	private String startTime;

	private BigDecimal weekday;

	public LoginTime001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getGroupOid() {
		return this.groupOid;
	}

	public void setGroupOid(BigDecimal groupOid) {
		this.groupOid = groupOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getWeekday() {
		return this.weekday;
	}

	public void setWeekday(BigDecimal weekday) {
		this.weekday = weekday;
	}

}