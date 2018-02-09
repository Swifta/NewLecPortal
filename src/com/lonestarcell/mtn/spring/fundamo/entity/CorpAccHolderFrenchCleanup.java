package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CORP_ACC_HOLDER_FRENCH_CLEANUP database table.
 * 
 */
// @Entity
@Table(name="CORP_ACC_HOLDER_FRENCH_CLEANUP")
@NamedQuery(name="CorpAccHolderFrenchCleanup.findAll", query="SELECT c FROM CorpAccHolderFrenchCleanup c")
public class CorpAccHolderFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String rowcolum;

	public CorpAccHolderFrenchCleanup() {
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