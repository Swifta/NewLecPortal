package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MSISDN_TEMP2 database table.
 * 
 */
//@Entity
@Table(name="MSISDN_TEMP2")
@NamedQuery(name="MsisdnTemp2.findAll", query="SELECT m FROM MsisdnTemp2 m")
public class MsisdnTemp2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	public MsisdnTemp2() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}