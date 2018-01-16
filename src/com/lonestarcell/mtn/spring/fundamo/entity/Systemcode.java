package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYSTEMCODES database table.
 * 
 */
@Entity
@Table(name="SYSTEMCODES")
@NamedQuery(name="Systemcode.findAll", query="SELECT s FROM Systemcode s")
public class Systemcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Column(name="\"VALUE\"")
	private String value;

	public Systemcode() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}