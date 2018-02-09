package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADDRESS_FRENCH_CLEANUP database table.
 * 
 */
//@Entity
@Table(name="ADDRESS_FRENCH_CLEANUP")
@NamedQuery(name="AddressFrenchCleanup.findAll", query="SELECT a FROM AddressFrenchCleanup a")
public class AddressFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private String city;

	private String province;

	private String rowcolum;

	public AddressFrenchCleanup() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRowcolum() {
		return this.rowcolum;
	}

	public void setRowcolum(String rowcolum) {
		this.rowcolum = rowcolum;
	}

}