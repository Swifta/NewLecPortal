package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MERCHANT_ADRESSLINE_CLEANUP database table.
 * 
 */
@Entity
@Table(name="MERCHANT_ADRESSLINE_CLEANUP")
@NamedQuery(name="MerchantAdresslineCleanup.findAll", query="SELECT m FROM MerchantAdresslineCleanup m")
public class MerchantAdresslineCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADDRESS_ROWID")
	private String addressRowid;

	@Column(name="CAH_NUMBER")
	private String cahNumber;

	@Column(name="GOOD_ADDRESS")
	private String goodAddress;

	@Column(name="MERCHANT_ROWID")
	private String merchantRowid;

	@Column(name="WRONG_ADDRESS")
	private String wrongAddress;

	public MerchantAdresslineCleanup() {
	}

	public String getAddressRowid() {
		return this.addressRowid;
	}

	public void setAddressRowid(String addressRowid) {
		this.addressRowid = addressRowid;
	}

	public String getCahNumber() {
		return this.cahNumber;
	}

	public void setCahNumber(String cahNumber) {
		this.cahNumber = cahNumber;
	}

	public String getGoodAddress() {
		return this.goodAddress;
	}

	public void setGoodAddress(String goodAddress) {
		this.goodAddress = goodAddress;
	}

	public String getMerchantRowid() {
		return this.merchantRowid;
	}

	public void setMerchantRowid(String merchantRowid) {
		this.merchantRowid = merchantRowid;
	}

	public String getWrongAddress() {
		return this.wrongAddress;
	}

	public void setWrongAddress(String wrongAddress) {
		this.wrongAddress = wrongAddress;
	}

}