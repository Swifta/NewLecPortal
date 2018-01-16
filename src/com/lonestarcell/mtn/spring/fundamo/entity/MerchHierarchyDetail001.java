package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MERCH_HIERARCHY_DETAIL001 database table.
 * 
 */
@Entity
@Table(name="MERCH_HIERARCHY_DETAIL001")
@NamedQuery(name="MerchHierarchyDetail001.findAll", query="SELECT m FROM MerchHierarchyDetail001 m")
public class MerchHierarchyDetail001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CORPORATE_ACCOUNT_HOLDER_OID")
	private BigDecimal corporateAccountHolderOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MERCH_HIERARCHY_DETAIL_OID")
	private BigDecimal merchHierarchyDetailOid;

	@Column(name="MERCH_HIERARCHY_OID")
	private BigDecimal merchHierarchyOid;

	public MerchHierarchyDetail001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getCorporateAccountHolderOid() {
		return this.corporateAccountHolderOid;
	}

	public void setCorporateAccountHolderOid(BigDecimal corporateAccountHolderOid) {
		this.corporateAccountHolderOid = corporateAccountHolderOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMerchHierarchyDetailOid() {
		return this.merchHierarchyDetailOid;
	}

	public void setMerchHierarchyDetailOid(BigDecimal merchHierarchyDetailOid) {
		this.merchHierarchyDetailOid = merchHierarchyDetailOid;
	}

	public BigDecimal getMerchHierarchyOid() {
		return this.merchHierarchyOid;
	}

	public void setMerchHierarchyOid(BigDecimal merchHierarchyOid) {
		this.merchHierarchyOid = merchHierarchyOid;
	}

}