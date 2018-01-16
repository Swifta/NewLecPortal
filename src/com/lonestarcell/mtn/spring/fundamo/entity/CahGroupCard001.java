package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CAH_GROUP_CARD001 database table.
 * 
 */
@Entity
@Table(name="CAH_GROUP_CARD001")
@NamedQuery(name="CahGroupCard001.findAll", query="SELECT c FROM CahGroupCard001 c")
public class CahGroupCard001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CAH_EMPLOYEE_OID")
	private BigDecimal cahEmployeeOid;

	@Column(name="CAH_GROUP_NAME")
	private String cahGroupName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String num;

	@Column(name="USER_ACCOUNT_OID")
	private BigDecimal userAccountOid;

	public CahGroupCard001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCahEmployeeOid() {
		return this.cahEmployeeOid;
	}

	public void setCahEmployeeOid(BigDecimal cahEmployeeOid) {
		this.cahEmployeeOid = cahEmployeeOid;
	}

	public String getCahGroupName() {
		return this.cahGroupName;
	}

	public void setCahGroupName(String cahGroupName) {
		this.cahGroupName = cahGroupName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public BigDecimal getUserAccountOid() {
		return this.userAccountOid;
	}

	public void setUserAccountOid(BigDecimal userAccountOid) {
		this.userAccountOid = userAccountOid;
	}

}