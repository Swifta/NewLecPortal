package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CORPORATE_ACCOUNT_HOLDER001 database table.
 * 
 */
@Entity
@Proxy(lazy = false)
@Table(name="CORPORATE_ACCOUNT_HOLDER001")
@NamedQuery(name="CorporateAccountHolder001.findAll", query="SELECT c FROM CorporateAccountHolder001 c")
public class CorporateAccountHolder001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ALLOW_REBATE")
	private String allowRebate;

	@Column(name="APPLICATION_TYPE_OID")
	private BigDecimal applicationTypeOid;

	@Column(name="CAH_NUMBER")
	private String cahNumber;

	private String category;

	@Column(name="CLASS_TYPE")
	private BigDecimal classType;

	/*
	@Column(name="CORPORATE_ACC_HOLDER_G_OID")
	private BigDecimal corporateAccHolderGOid;
	*/
	
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn(name="CORPORATE_ACC_HOLDER_G_OID")
	private CahGroup001 cahGroup001;
	

	@Column(name="CREDIT_CHECK")
	private String creditCheck;

	private String fapname;

	private String furl;

	private String judgements;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	private String name;

	@Column(name="PARENT_OID")
	private BigDecimal parentOid;

	@Column(name="PARTY_OID")
	private BigDecimal partyOid;

	@Column(name="RESERVE_BANK_CODE_OID")
	private BigDecimal reserveBankCodeOid;

	@Column(name="RETAILER_ID")
	private String retailerId;

	@Column(name="ROLE_OID")
	private BigDecimal roleOid;

	private String status;

	public CorporateAccountHolder001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAllowRebate() {
		return this.allowRebate;
	}

	public void setAllowRebate(String allowRebate) {
		this.allowRebate = allowRebate;
	}

	public BigDecimal getApplicationTypeOid() {
		return this.applicationTypeOid;
	}

	public void setApplicationTypeOid(BigDecimal applicationTypeOid) {
		this.applicationTypeOid = applicationTypeOid;
	}

	public String getCahNumber() {
		return this.cahNumber;
	}

	public void setCahNumber(String cahNumber) {
		this.cahNumber = cahNumber;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getClassType() {
		return this.classType;
	}

	public void setClassType(BigDecimal classType) {
		this.classType = classType;
	}

	
	
	/*
	public BigDecimal getCorporateAccHolderGOid() {
		return this.corporateAccHolderGOid;
	}

	public void setCorporateAccHolderGOid(BigDecimal corporateAccHolderGOid) {
		this.corporateAccHolderGOid = corporateAccHolderGOid;
	} */

	public CahGroup001 getCahGroup001() {
		return cahGroup001;
	}

	public void setCahGroup001(CahGroup001 cahGroup001) {
		this.cahGroup001 = cahGroup001;
	}

	public String getCreditCheck() {
		return this.creditCheck;
	}

	public void setCreditCheck(String creditCheck) {
		this.creditCheck = creditCheck;
	}

	public String getFapname() {
		return this.fapname;
	}

	public void setFapname(String fapname) {
		this.fapname = fapname;
	}

	public String getFurl() {
		return this.furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getJudgements() {
		return this.judgements;
	}

	public void setJudgements(String judgements) {
		this.judgements = judgements;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentOid() {
		return this.parentOid;
	}

	public void setParentOid(BigDecimal parentOid) {
		this.parentOid = parentOid;
	}

	public BigDecimal getPartyOid() {
		return this.partyOid;
	}

	public void setPartyOid(BigDecimal partyOid) {
		this.partyOid = partyOid;
	}

	public BigDecimal getReserveBankCodeOid() {
		return this.reserveBankCodeOid;
	}

	public void setReserveBankCodeOid(BigDecimal reserveBankCodeOid) {
		this.reserveBankCodeOid = reserveBankCodeOid;
	}

	public String getRetailerId() {
		return this.retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public BigDecimal getRoleOid() {
		return this.roleOid;
	}

	public void setRoleOid(BigDecimal roleOid) {
		this.roleOid = roleOid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}