package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the FICA_PERSON001 database table.
 * 
 */
@Entity
@Table(name="FICA_PERSON001")
@NamedQuery(name="FicaPerson001.findAll", query="SELECT f FROM FicaPerson001 f")
public class FicaPerson001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_PURPOSE")
	private String accountPurpose;

	@Column(name="BIN_NUMBER")
	private String binNumber;

	@Column(name="CARD_DELIVERY")
	private String cardDelivery;

	@Column(name="CARD_REFERENCE_NUMBER")
	private String cardReferenceNumber;

	@Column(name="COMMUNITY_BANKER_AREA")
	private String communityBankerArea;

	@Column(name="COMMUNITY_BANKER_GROUP")
	private String communityBankerGroup;

	@Column(name="COMMUNITY_BANKING_ACCOUNT")
	private String communityBankingAccount;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	@Column(name="DATE_OF_BIRTH")
	private Timestamp dateOfBirth;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="FICA_GUARDIAN_OID")
	private BigDecimal ficaGuardianOid;

	@Column(name="FICA_VERIFICATION_REQUEST_OID")
	private BigDecimal ficaVerificationRequestOid;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="FUNERAL_PLAN_CODE")
	private String funeralPlanCode;

	private String guardian;

	@Column(name="GUARDIAN_RELATIONSHIP")
	private String guardianRelationship;

	@Column(name="ID_NUMBER")
	private String idNumber;

	@Column(name="ID_TYPE")
	private String idType;

	private String initials;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String msisdn;

	private String nationality;

	@Column(name="NEXT_OF_KIN")
	private String nextOfKin;

	@Column(name="NEXT_OF_KIN_CONTACT_NUMBER")
	private String nextOfKinContactNumber;

	@Column(name="PHYSICAL_ADDRESS_PROVEN")
	private String physicalAddressProven;

	@Column(name="PHYSICAL_ADDRESS1")
	private String physicalAddress1;

	@Column(name="PHYSICAL_ADDRESS2")
	private String physicalAddress2;

	@Column(name="PHYSICAL_ADDRESS3")
	private String physicalAddress3;

	@Column(name="PHYSICAL_ADDRESS4")
	private String physicalAddress4;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	private String product;

	private String puk;

	@Column(name="SHARE_INFO_WITH_3RD_PARTY")
	private String shareInfoWith3rdParty;

	@Column(name="SIM_NUMBER")
	private String simNumber;

	private String simswap;

	@Column(name="SOURCE_OF_INCOME")
	private String sourceOfIncome;

	private String surname;

	@Column(name="VALUECARD_MARKETING_INFO")
	private String valuecardMarketingInfo;

	public FicaPerson001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountPurpose() {
		return this.accountPurpose;
	}

	public void setAccountPurpose(String accountPurpose) {
		this.accountPurpose = accountPurpose;
	}

	public String getBinNumber() {
		return this.binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
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

	public String getCommunityBankerArea() {
		return this.communityBankerArea;
	}

	public void setCommunityBankerArea(String communityBankerArea) {
		this.communityBankerArea = communityBankerArea;
	}

	public String getCommunityBankerGroup() {
		return this.communityBankerGroup;
	}

	public void setCommunityBankerGroup(String communityBankerGroup) {
		this.communityBankerGroup = communityBankerGroup;
	}

	public String getCommunityBankingAccount() {
		return this.communityBankingAccount;
	}

	public void setCommunityBankingAccount(String communityBankingAccount) {
		this.communityBankingAccount = communityBankingAccount;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public BigDecimal getFicaGuardianOid() {
		return this.ficaGuardianOid;
	}

	public void setFicaGuardianOid(BigDecimal ficaGuardianOid) {
		this.ficaGuardianOid = ficaGuardianOid;
	}

	public BigDecimal getFicaVerificationRequestOid() {
		return this.ficaVerificationRequestOid;
	}

	public void setFicaVerificationRequestOid(BigDecimal ficaVerificationRequestOid) {
		this.ficaVerificationRequestOid = ficaVerificationRequestOid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFuneralPlanCode() {
		return this.funeralPlanCode;
	}

	public void setFuneralPlanCode(String funeralPlanCode) {
		this.funeralPlanCode = funeralPlanCode;
	}

	public String getGuardian() {
		return this.guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public String getGuardianRelationship() {
		return this.guardianRelationship;
	}

	public void setGuardianRelationship(String guardianRelationship) {
		this.guardianRelationship = guardianRelationship;
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

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
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

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNextOfKin() {
		return this.nextOfKin;
	}

	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}

	public String getNextOfKinContactNumber() {
		return this.nextOfKinContactNumber;
	}

	public void setNextOfKinContactNumber(String nextOfKinContactNumber) {
		this.nextOfKinContactNumber = nextOfKinContactNumber;
	}

	public String getPhysicalAddressProven() {
		return this.physicalAddressProven;
	}

	public void setPhysicalAddressProven(String physicalAddressProven) {
		this.physicalAddressProven = physicalAddressProven;
	}

	public String getPhysicalAddress1() {
		return this.physicalAddress1;
	}

	public void setPhysicalAddress1(String physicalAddress1) {
		this.physicalAddress1 = physicalAddress1;
	}

	public String getPhysicalAddress2() {
		return this.physicalAddress2;
	}

	public void setPhysicalAddress2(String physicalAddress2) {
		this.physicalAddress2 = physicalAddress2;
	}

	public String getPhysicalAddress3() {
		return this.physicalAddress3;
	}

	public void setPhysicalAddress3(String physicalAddress3) {
		this.physicalAddress3 = physicalAddress3;
	}

	public String getPhysicalAddress4() {
		return this.physicalAddress4;
	}

	public void setPhysicalAddress4(String physicalAddress4) {
		this.physicalAddress4 = physicalAddress4;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPuk() {
		return this.puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getShareInfoWith3rdParty() {
		return this.shareInfoWith3rdParty;
	}

	public void setShareInfoWith3rdParty(String shareInfoWith3rdParty) {
		this.shareInfoWith3rdParty = shareInfoWith3rdParty;
	}

	public String getSimNumber() {
		return this.simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getSimswap() {
		return this.simswap;
	}

	public void setSimswap(String simswap) {
		this.simswap = simswap;
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

	public String getValuecardMarketingInfo() {
		return this.valuecardMarketingInfo;
	}

	public void setValuecardMarketingInfo(String valuecardMarketingInfo) {
		this.valuecardMarketingInfo = valuecardMarketingInfo;
	}

}