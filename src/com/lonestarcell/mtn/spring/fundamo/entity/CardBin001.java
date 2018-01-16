package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_BIN001 database table.
 * 
 */
@Entity
@Table(name="CARD_BIN001")
@NamedQuery(name="CardBin001.findAll", query="SELECT c FROM CardBin001 c")
public class CardBin001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CREDIT_CARD")
	private String creditCard;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String num;

	public CardBin001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}