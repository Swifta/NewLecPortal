package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FAP_TABLE001 database table.
 * 
 */
@Entity
@Table(name="FAP_TABLE001")
@NamedQuery(name="FapTable001.findAll", query="SELECT f FROM FapTable001 f")
public class FapTable001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="APPLICATION_ID")
	private String applicationId;

	private String category;

	@Column(name="FAP_NAME")
	private String fapName;

	@Column(name="FUNDAMO_ACCOUNT_NUMBER")
	private String fundamoAccountNumber;

	private String furl;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public FapTable001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFapName() {
		return this.fapName;
	}

	public void setFapName(String fapName) {
		this.fapName = fapName;
	}

	public String getFundamoAccountNumber() {
		return this.fundamoAccountNumber;
	}

	public void setFundamoAccountNumber(String fundamoAccountNumber) {
		this.fundamoAccountNumber = fundamoAccountNumber;
	}

	public String getFurl() {
		return this.furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}