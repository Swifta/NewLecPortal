package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_PRE_AUTHORISATION001 database table.
 * 
 */
@Entity
@Table(name="CARD_PRE_AUTHORISATION001")
@NamedQuery(name="CardPreAuthorisation001.findAll", query="SELECT c FROM CardPreAuthorisation001 c")
public class CardPreAuthorisation001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="ACCOUNT_HOLDER_OID")
	private BigDecimal accountHolderOid;

	private String available;

	@Column(name="CARD_TRANSACTION_AUTH_NUMBER")
	private BigDecimal cardTransactionAuthNumber;

	@Column(name="EXPIRE_ON")
	private Timestamp expireOn;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public CardPreAuthorisation001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getAccountHolderOid() {
		return this.accountHolderOid;
	}

	public void setAccountHolderOid(BigDecimal accountHolderOid) {
		this.accountHolderOid = accountHolderOid;
	}

	public String getAvailable() {
		return this.available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public BigDecimal getCardTransactionAuthNumber() {
		return this.cardTransactionAuthNumber;
	}

	public void setCardTransactionAuthNumber(BigDecimal cardTransactionAuthNumber) {
		this.cardTransactionAuthNumber = cardTransactionAuthNumber;
	}

	public Timestamp getExpireOn() {
		return this.expireOn;
	}

	public void setExpireOn(Timestamp expireOn) {
		this.expireOn = expireOn;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}