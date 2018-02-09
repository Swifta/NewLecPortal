package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_DET_FRENCH_CLEANUP database table.
 * 
 */
// @Entity
@Table(name="CONTACT_DET_FRENCH_CLEANUP")
@NamedQuery(name="ContactDetFrenchCleanup.findAll", query="SELECT c FROM ContactDetFrenchCleanup c")
public class ContactDetFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String rowcolum;

	public ContactDetFrenchCleanup() {
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