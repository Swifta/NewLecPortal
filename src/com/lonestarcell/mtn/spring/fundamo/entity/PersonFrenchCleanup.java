package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PERSON_FRENCH_CLEANUP database table.
 * 
 */
// @Entity
@Table(name="PERSON_FRENCH_CLEANUP")
@NamedQuery(name="PersonFrenchCleanup.findAll", query="SELECT p FROM PersonFrenchCleanup p")
public class PersonFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FIRST_NAME")
	private String firstName;

	private String rowcolum;

	private String surname;

	public PersonFrenchCleanup() {
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRowcolum() {
		return this.rowcolum;
	}

	public void setRowcolum(String rowcolum) {
		this.rowcolum = rowcolum;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}