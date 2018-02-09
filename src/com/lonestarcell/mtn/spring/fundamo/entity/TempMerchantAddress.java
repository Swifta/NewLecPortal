package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TEMP_MERCHANT_ADDRESS database table.
 * 
 */
//@Entity
@Table(name="TEMP_MERCHANT_ADDRESS")
@NamedQuery(name="TempMerchantAddress.findAll", query="SELECT t FROM TempMerchantAddress t")
public class TempMerchantAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountnumber;

	private String addressline;

	public TempMerchantAddress() {
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAddressline() {
		return this.addressline;
	}

	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}

}