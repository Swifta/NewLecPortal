package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AH_ADRESSLINE_CLEANUP database table.
 * 
 */
@Entity
@Table(name="AH_ADRESSLINE_CLEANUP")
@NamedQuery(name="AhAdresslineCleanup.findAll", query="SELECT a FROM AhAdresslineCleanup a")
public class AhAdresslineCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADDRESS_ROWID")
	private String addressRowid;

	@Column(name="GOOD_ADDRESS")
	private String goodAddress;

	private String username;

	@Column(name="WRONG_ADDRESS")
	private String wrongAddress;

	public AhAdresslineCleanup() {
	}

	public String getAddressRowid() {
		return this.addressRowid;
	}

	public void setAddressRowid(String addressRowid) {
		this.addressRowid = addressRowid;
	}

	public String getGoodAddress() {
		return this.goodAddress;
	}

	public void setGoodAddress(String goodAddress) {
		this.goodAddress = goodAddress;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWrongAddress() {
		return this.wrongAddress;
	}

	public void setWrongAddress(String wrongAddress) {
		this.wrongAddress = wrongAddress;
	}

}