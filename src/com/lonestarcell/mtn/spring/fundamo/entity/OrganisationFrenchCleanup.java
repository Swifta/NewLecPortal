package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ORGANISATION_FRENCH_CLEANUP database table.
 * 
 */
// @Entity
@Table(name="ORGANISATION_FRENCH_CLEANUP")
@NamedQuery(name="OrganisationFrenchCleanup.findAll", query="SELECT o FROM OrganisationFrenchCleanup o")
public class OrganisationFrenchCleanup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rowcolum;

	@Column(name="TRADE_NAME")
	private String tradeName;

	@Column(name="TRADE_NUMBER")
	private String tradeNumber;

	public OrganisationFrenchCleanup() {
	}

	public String getRowcolum() {
		return this.rowcolum;
	}

	public void setRowcolum(String rowcolum) {
		this.rowcolum = rowcolum;
	}

	public String getTradeName() {
		return this.tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeNumber() {
		return this.tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

}