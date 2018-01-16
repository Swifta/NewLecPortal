package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CONTACT_DETAIL_BACKUP database table.
 * 
 */
//@Entity
@Table(name="CONTACT_DETAIL_BACKUP")
@NamedQuery(name="ContactDetailBackup.findAll", query="SELECT c FROM ContactDetailBackup c")
public class ContactDetailBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTACT_PHONE_OID")
	private BigDecimal contactPhoneOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="FAX_NUMBER_OID")
	private BigDecimal faxNumberOid;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	private BigDecimal oid;

	@Column(name="ORGANISATION_OID")
	private BigDecimal organisationOid;

	@Column(name="WORK_PHONE_OID")
	private BigDecimal workPhoneOid;

	public ContactDetailBackup() {
	}

	public BigDecimal getContactPhoneOid() {
		return this.contactPhoneOid;
	}

	public void setContactPhoneOid(BigDecimal contactPhoneOid) {
		this.contactPhoneOid = contactPhoneOid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public BigDecimal getFaxNumberOid() {
		return this.faxNumberOid;
	}

	public void setFaxNumberOid(BigDecimal faxNumberOid) {
		this.faxNumberOid = faxNumberOid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getOrganisationOid() {
		return this.organisationOid;
	}

	public void setOrganisationOid(BigDecimal organisationOid) {
		this.organisationOid = organisationOid;
	}

	public BigDecimal getWorkPhoneOid() {
		return this.workPhoneOid;
	}

	public void setWorkPhoneOid(BigDecimal workPhoneOid) {
		this.workPhoneOid = workPhoneOid;
	}

}