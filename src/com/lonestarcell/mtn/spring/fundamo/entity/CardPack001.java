package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_PACK001 database table.
 * 
 */
@Entity
@Table(name="CARD_PACK001")
@NamedQuery(name="CardPack001.findAll", query="SELECT c FROM CardPack001 c")
public class CardPack001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	private String activated;

	private String blacklisted;

	@Column(name="CARD_LOCATION_STATUS")
	private String cardLocationStatus;

	@Column(name="CARD_NUMBER")
	private String cardNumber;

	@Column(name="DELIVERY_DATE")
	private Timestamp deliveryDate;

	@Column(name="EASYPAY_NUMBER")
	private String easypayNumber;

	@Column(name="EXPIRY_DATE")
	private Timestamp expiryDate;

	@Column(name="FILE_BATCH_NUMBER")
	private BigDecimal fileBatchNumber;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String offset;

	@Column(name="ORDER_REQUEST_OID")
	private BigDecimal orderRequestOid;

	@Column(name="OUTLET_OID")
	private BigDecimal outletOid;

	@Column(name="PACK_NUMBER")
	private String packNumber;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;

	public CardPack001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getActivated() {
		return this.activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getBlacklisted() {
		return this.blacklisted;
	}

	public void setBlacklisted(String blacklisted) {
		this.blacklisted = blacklisted;
	}

	public String getCardLocationStatus() {
		return this.cardLocationStatus;
	}

	public void setCardLocationStatus(String cardLocationStatus) {
		this.cardLocationStatus = cardLocationStatus;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Timestamp getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEasypayNumber() {
		return this.easypayNumber;
	}

	public void setEasypayNumber(String easypayNumber) {
		this.easypayNumber = easypayNumber;
	}

	public Timestamp getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getFileBatchNumber() {
		return this.fileBatchNumber;
	}

	public void setFileBatchNumber(BigDecimal fileBatchNumber) {
		this.fileBatchNumber = fileBatchNumber;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getOffset() {
		return this.offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public BigDecimal getOrderRequestOid() {
		return this.orderRequestOid;
	}

	public void setOrderRequestOid(BigDecimal orderRequestOid) {
		this.orderRequestOid = orderRequestOid;
	}

	public BigDecimal getOutletOid() {
		return this.outletOid;
	}

	public void setOutletOid(BigDecimal outletOid) {
		this.outletOid = outletOid;
	}

	public String getPackNumber() {
		return this.packNumber;
	}

	public void setPackNumber(String packNumber) {
		this.packNumber = packNumber;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}