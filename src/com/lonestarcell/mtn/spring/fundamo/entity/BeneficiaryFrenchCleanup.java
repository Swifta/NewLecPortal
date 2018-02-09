package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BENEFICIARY_FRENCH_CLEANUP database table.
 * 
 */
// @Entity
@Table(name="BENEFICIARY_FRENCH_CLEANUP")
@NamedQuery(name="BeneficiaryFrenchCleanup.findAll", query="SELECT b FROM BeneficiaryFrenchCleanup b")
public class BeneficiaryFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String rowcolum;

	public BeneficiaryFrenchCleanup() {
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