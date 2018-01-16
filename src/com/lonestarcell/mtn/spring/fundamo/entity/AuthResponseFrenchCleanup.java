package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AUTH_RESPONSE_FRENCH_CLEANUP database table.
 * 
 */
@Entity
@Table(name="AUTH_RESPONSE_FRENCH_CLEANUP")
@NamedQuery(name="AuthResponseFrenchCleanup.findAll", query="SELECT a FROM AuthResponseFrenchCleanup a")
public class AuthResponseFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String rowcolum;

	public AuthResponseFrenchCleanup() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRowcolum() {
		return this.rowcolum;
	}

	public void setRowcolum(String rowcolum) {
		this.rowcolum = rowcolum;
	}

}