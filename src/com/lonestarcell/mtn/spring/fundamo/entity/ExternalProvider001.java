package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EXTERNAL_PROVIDER001 database table.
 * 
 */
@Entity
@Table(name="EXTERNAL_PROVIDER001")
@NamedQuery(name="ExternalProvider001.findAll", query="SELECT e FROM ExternalProvider001 e")
public class ExternalProvider001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String code;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public ExternalProvider001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}