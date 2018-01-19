package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CONTACT_DETAIL001 database table.
 * 
 */
@Entity
@Table(name="CONTACT_DETAIL001")
@NamedQuery(name="ContactDetail001.findAll", query="SELECT c FROM ContactDetail001 c")
public class ContactDetail001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

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

	@Column(name="ORGANISATION_OID")
	private BigDecimal organisationOid;

	@Column(name="WORK_PHONE_OID")
	private BigDecimal workPhoneOid;

	public ContactDetail001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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