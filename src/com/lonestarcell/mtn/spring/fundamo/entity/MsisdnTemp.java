package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MSISDN_TEMP database table.
 * 
 */
//@Entity
@Table(name="MSISDN_TEMP")
@NamedQuery(name="MsisdnTemp.findAll", query="SELECT m FROM MsisdnTemp m")
public class MsisdnTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	public MsisdnTemp() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}