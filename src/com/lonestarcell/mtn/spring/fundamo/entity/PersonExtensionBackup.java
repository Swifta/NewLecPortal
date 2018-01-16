package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PERSON_EXTENSION_BACKUP database table.
 * 
 */
@Entity
@Table(name="PERSON_EXTENSION_BACKUP")
@NamedQuery(name="PersonExtensionBackup.findAll", query="SELECT p FROM PersonExtensionBackup p")
public class PersonExtensionBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COUNTRY_OID")
	private BigDecimal countryOid;

	@Column(name="ID_DATE_OF_ISSUE")
	private Timestamp idDateOfIssue;

	@Column(name="ID_ISSUED_AT")
	private String idIssuedAt;

	@Column(name="ID_ISSUED_BY")
	private String idIssuedBy;

	@Column(name="ID_TYPE_OTHER")
	private String idTypeOther;

	@Column(name="INCOME_SOURCE_OTHER")
	private String incomeSourceOther;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MAIDEN_NAME")
	private String maidenName;

	@Column(name="MOTHER_MAIDEN_NAME")
	private String motherMaidenName;

	private BigDecimal oid;

	@Column(name="PERMIT_NUMBER")
	private String permitNumber;

	@Column(name="PLACE_OF_BIRTH")
	private String placeOfBirth;

	@Column(name="POSITION_HELD")
	private String positionHeld;

	private String resident;

	@Column(name="SPOUSE_NAME")
	private String spouseName;

	public PersonExtensionBackup() {
	}

	public BigDecimal getCountryOid() {
		return this.countryOid;
	}

	public void setCountryOid(BigDecimal countryOid) {
		this.countryOid = countryOid;
	}

	public Timestamp getIdDateOfIssue() {
		return this.idDateOfIssue;
	}

	public void setIdDateOfIssue(Timestamp idDateOfIssue) {
		this.idDateOfIssue = idDateOfIssue;
	}

	public String getIdIssuedAt() {
		return this.idIssuedAt;
	}

	public void setIdIssuedAt(String idIssuedAt) {
		this.idIssuedAt = idIssuedAt;
	}

	public String getIdIssuedBy() {
		return this.idIssuedBy;
	}

	public void setIdIssuedBy(String idIssuedBy) {
		this.idIssuedBy = idIssuedBy;
	}

	public String getIdTypeOther() {
		return this.idTypeOther;
	}

	public void setIdTypeOther(String idTypeOther) {
		this.idTypeOther = idTypeOther;
	}

	public String getIncomeSourceOther() {
		return this.incomeSourceOther;
	}

	public void setIncomeSourceOther(String incomeSourceOther) {
		this.incomeSourceOther = incomeSourceOther;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMaidenName() {
		return this.maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getMotherMaidenName() {
		return this.motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public String getPermitNumber() {
		return this.permitNumber;
	}

	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	public String getPlaceOfBirth() {
		return this.placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPositionHeld() {
		return this.positionHeld;
	}

	public void setPositionHeld(String positionHeld) {
		this.positionHeld = positionHeld;
	}

	public String getResident() {
		return this.resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getSpouseName() {
		return this.spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

}