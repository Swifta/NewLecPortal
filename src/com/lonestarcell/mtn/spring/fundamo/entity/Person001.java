package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PERSON001 database table.
 * 
 */
@Entity
@NamedQuery(name="Person001.findAll", query="SELECT p FROM Person001 p")
@Proxy(lazy = false)
public class Person001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	private String agent;

	@Column(name="ALTERNATE_CONTACT_NUMBER")
	private String alternateContactNumber;

	@Column(name="BEST_CONTACTABLE_TIME")
	private String bestContactableTime;

	@Column(name="CARD_DELIVERY")
	private String cardDelivery;

	@Column(name="CELLPHONE_MODEL")
	private String cellphoneModel;

	@Column(name="DATE_OF_BIRTH")
	private Timestamp dateOfBirth;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	private String employer;

	@Column(name="FAX_NUMBER_OID")
	private BigDecimal faxNumberOid;

	@Column(name="FICA_VERIFICATION_OID")
	private BigDecimal ficaVerificationOid;

	@Column(name="FIRST_NAME")
	private String firstName;

	private String gender;

	@Column(name="GUARDIAN_OID")
	private BigDecimal guardianOid;

	@Column(name="GUARDIAN_RELATIONSHIP")
	private String guardianRelationship;

	@Column(name="HOME_PHONE_OID")
	private BigDecimal homePhoneOid;

	/*
	@Column(name="ID_NUMBER")
	private String idNumber;
	*/
	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn(name="ID_NUMBER", referencedColumnName = "ID_NUMBER" )
	private RegistrationRequestData001 registrationRequestData001;
	

	@Column(name="ID_TYPE")
	private String idType;

	@Column(name="ID_VERIFICATION_STATUS")
	private String idVerificationStatus;

	@Column(name="INCOME_SOURCE_OID")
	private BigDecimal incomeSourceOid;

	@Column(name="INCOME_TAX_NUMBER")
	private String incomeTaxNumber;

	private String initials;

	@Column(name="\"LANGUAGE\"")
	private String language;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MARITAL_STATUS")
	private String maritalStatus;

	@Column(name="MOBILE_PHONE_OID")
	private BigDecimal mobilePhoneOid;

	@Column(name="MONTHLY_INCOME")
	private double monthlyIncome;

	private String occupation;

	@Column(name="ORIGINAL_ID_NUMBER")
	private String originalIdNumber;

	@Column(name="PHYSICAL_ADDRESS_OID")
	private BigDecimal physicalAddressOid;

	@Column(name="POSTAL_ADDRESS_OID")
	private BigDecimal postalAddressOid;

	@Column(name="PREFERRED_CONTACT_NUMBER")
	private String preferredContactNumber;

	@Column(name="SA_RESIDENT")
	private String saResident;

	@Column(name="SHARE_INFO_WITH_3RD_PARTY")
	private String shareInfoWith3rdParty;

	private String surname;

	@Column(name="TITLE_OID")
	private BigDecimal titleOid;

	@Column(name="VALUECARD_MARKETING_INFO")
	private String valuecardMarketingInfo;

	@Column(name="WORK_PHONE_OID")
	private BigDecimal workPhoneOid;

	public Person001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAlternateContactNumber() {
		return this.alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getBestContactableTime() {
		return this.bestContactableTime;
	}

	public void setBestContactableTime(String bestContactableTime) {
		this.bestContactableTime = bestContactableTime;
	}

	public String getCardDelivery() {
		return this.cardDelivery;
	}

	public void setCardDelivery(String cardDelivery) {
		this.cardDelivery = cardDelivery;
	}

	public String getCellphoneModel() {
		return this.cellphoneModel;
	}

	public void setCellphoneModel(String cellphoneModel) {
		this.cellphoneModel = cellphoneModel;
	}

	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmployer() {
		return this.employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public BigDecimal getFaxNumberOid() {
		return this.faxNumberOid;
	}

	public void setFaxNumberOid(BigDecimal faxNumberOid) {
		this.faxNumberOid = faxNumberOid;
	}

	public BigDecimal getFicaVerificationOid() {
		return this.ficaVerificationOid;
	}

	public void setFicaVerificationOid(BigDecimal ficaVerificationOid) {
		this.ficaVerificationOid = ficaVerificationOid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getGuardianOid() {
		return this.guardianOid;
	}

	public void setGuardianOid(BigDecimal guardianOid) {
		this.guardianOid = guardianOid;
	}

	public String getGuardianRelationship() {
		return this.guardianRelationship;
	}

	public void setGuardianRelationship(String guardianRelationship) {
		this.guardianRelationship = guardianRelationship;
	}

	public BigDecimal getHomePhoneOid() {
		return this.homePhoneOid;
	}

	public void setHomePhoneOid(BigDecimal homePhoneOid) {
		this.homePhoneOid = homePhoneOid;
	}

	/*
	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	*/
	
	

	public String getIdType() {
		return this.idType;
	}

	public RegistrationRequestData001 getRegistrationRequestData001() {
		return registrationRequestData001;
	}

	public void setRegistrationRequestData001(
			RegistrationRequestData001 registrationRequestData001) {
		this.registrationRequestData001 = registrationRequestData001;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdVerificationStatus() {
		return this.idVerificationStatus;
	}

	public void setIdVerificationStatus(String idVerificationStatus) {
		this.idVerificationStatus = idVerificationStatus;
	}

	public BigDecimal getIncomeSourceOid() {
		return this.incomeSourceOid;
	}

	public void setIncomeSourceOid(BigDecimal incomeSourceOid) {
		this.incomeSourceOid = incomeSourceOid;
	}

	public String getIncomeTaxNumber() {
		return this.incomeTaxNumber;
	}

	public void setIncomeTaxNumber(String incomeTaxNumber) {
		this.incomeTaxNumber = incomeTaxNumber;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
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

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public BigDecimal getMobilePhoneOid() {
		return this.mobilePhoneOid;
	}

	public void setMobilePhoneOid(BigDecimal mobilePhoneOid) {
		this.mobilePhoneOid = mobilePhoneOid;
	}

	public double getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOriginalIdNumber() {
		return this.originalIdNumber;
	}

	public void setOriginalIdNumber(String originalIdNumber) {
		this.originalIdNumber = originalIdNumber;
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

	public String getPreferredContactNumber() {
		return this.preferredContactNumber;
	}

	public void setPreferredContactNumber(String preferredContactNumber) {
		this.preferredContactNumber = preferredContactNumber;
	}

	public String getSaResident() {
		return this.saResident;
	}

	public void setSaResident(String saResident) {
		this.saResident = saResident;
	}

	public String getShareInfoWith3rdParty() {
		return this.shareInfoWith3rdParty;
	}

	public void setShareInfoWith3rdParty(String shareInfoWith3rdParty) {
		this.shareInfoWith3rdParty = shareInfoWith3rdParty;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public BigDecimal getTitleOid() {
		return this.titleOid;
	}

	public void setTitleOid(BigDecimal titleOid) {
		this.titleOid = titleOid;
	}

	public String getValuecardMarketingInfo() {
		return this.valuecardMarketingInfo;
	}

	public void setValuecardMarketingInfo(String valuecardMarketingInfo) {
		this.valuecardMarketingInfo = valuecardMarketingInfo;
	}

	public BigDecimal getWorkPhoneOid() {
		return this.workPhoneOid;
	}

	public void setWorkPhoneOid(BigDecimal workPhoneOid) {
		this.workPhoneOid = workPhoneOid;
	}

}