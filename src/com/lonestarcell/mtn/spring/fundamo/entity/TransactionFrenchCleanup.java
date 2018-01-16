package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TRANSACTION_FRENCH_CLEANUP database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_FRENCH_CLEANUP")
@NamedQuery(name="TransactionFrenchCleanup.findAll", query="SELECT t FROM TransactionFrenchCleanup t")
public class TransactionFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String reference;

	private String rowcolum;

	public TransactionFrenchCleanup() {
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRowcolum() {
		return this.rowcolum;
	}

	public void setRowcolum(String rowcolum) {
		this.rowcolum = rowcolum;
	}

}