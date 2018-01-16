package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the ORGANISATION_BACKUP database table.
 * 
 */
// @Entity
@Table(name="ORGANISATION_BACKUP")
@NamedQuery(name="OrganisationBackup.findAll", query="SELECT o FROM OrganisationBackup o")
public class OrganisationBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agent;

	@Column(name="CONTACT_PERSON_NAME")
	private String contactPersonName;

	@Column(name="CONTACT_PERSON_PHONE_OID")
	private BigDecimal contactPersonPhoneOid;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="FAX_NUMBER_OID")
	private BigDecimal faxNumberOid;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	@Column(name="PHONE_NUMBER_OID")
	private BigDecimal phoneNumberOid;

	@Column(name="PHYSICAL_ADDRESS_OID")
	private BigDecimal physicalAddressOid;

	@Column(name="POSTAL_ADDRESS_OID")
	private BigDecimal postalAddressOid;

	@Column(name="TRADE_NAME")
	private String tradeName;

	@Column(name="TRADE_NUMBER")
	private String tradeNumber;

	@Column(name="VAT_NUMBER")
	private String vatNumber;

	public OrganisationBackup() {
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public BigDecimal getContactPersonPhoneOid() {
		return this.contactPersonPhoneOid;
	}

	public void setContactPersonPhoneOid(BigDecimal contactPersonPhoneOid) {
		this.contactPersonPhoneOid = contactPersonPhoneOid;
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

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getPhoneNumberOid() {
		return this.phoneNumberOid;
	}

	public void setPhoneNumberOid(BigDecimal phoneNumberOid) {
		this.phoneNumberOid = phoneNumberOid;
	}

	public BigDecimal getPhysicalAddressOid() {
		return this.physicalAddressOid;
	}

	public void setPhysicalAddressOid(BigDecimal physicalAddressOid) {
		this.physicalAddressOid = physicalAddressOid;
	}

	public BigDecimal getPostalAddressOid() {
		return this.postalAddressOid;
	}

	public void setPostalAddressOid(BigDecimal postalAddressOid) {
		this.postalAddressOid = postalAddressOid;
	}

	public String getTradeName() {
		return this.tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeNumber() {
		return this.tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getVatNumber() {
		return this.vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

}