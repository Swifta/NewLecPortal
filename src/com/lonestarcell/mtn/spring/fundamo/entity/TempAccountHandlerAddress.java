package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TEMP_ACCOUNT_HANDLER_ADDRESS database table.
 * 
 */
@Entity
@Table(name="TEMP_ACCOUNT_HANDLER_ADDRESS")
@NamedQuery(name="TempAccountHandlerAddress.findAll", query="SELECT t FROM TempAccountHandlerAddress t")
public class TempAccountHandlerAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountnumber;

	private String addressline;

	public TempAccountHandlerAddress() {
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