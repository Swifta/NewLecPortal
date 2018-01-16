package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the REGISTRATION_REQUEST_DATA001 database table.
 * 
 */
@Entity
@Table(name="REGISTRATION_REQUEST_DATA001")
@NamedQuery(name="RegistrationRequestData001.findAll", query="SELECT r FROM RegistrationRequestData001 r")
public class RegistrationRequestData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCESS_CHANNEL")
	private String accessChannel;

	@Column(name="ALTERNATE_CONTACT_NUMBER")
	private String alternateContactNumber;

	@Column(name="CARD_DELIVERY")
	private String cardDelivery;

	@Column(name="CARD_REFERENCE_NUMBER")
	private String cardReferenceNumber;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="ID_NUMBER")
	private String idNumber;

	@Column(name="ID_TYPE")
	private String idType;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String msisdn;

	@Column(name="MSISDN_PRIMARY_CONTACT")
	private String msisdnPrimaryContact;

	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;

	@Column(name="SOURCE_OF_INCOME")
	private String sourceOfIncome;

	private String surname;

	public RegistrationRequestData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccessChannel() {
		return this.accessChannel;
	}

	public void setAccessChannel(String accessChannel) {
		this.accessChannel = accessChannel;
	}

	public String getAlternateContactNumber() {
		return this.alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getCardDelivery() {
		return this.cardDelivery;
	}

	public void setCardDelivery(String cardDelivery) {
		this.cardDelivery = cardDelivery;
	}

	public String getCardReferenceNumber() {
		return this.cardReferenceNumber;
	}

	public void setCardReferenceNumber(String cardReferenceNumber) {
		this.cardReferenceNumber = cardReferenceNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
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

	public String getMsisdnPrimaryContact() {
		return this.msisdnPrimaryContact;
	}

	public void setMsisdnPrimaryContact(String msisdnPrimaryContact) {
		this.msisdnPrimaryContact = msisdnPrimaryContact;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getSourceOfIncome() {
		return this.sourceOfIncome;
	}

	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}