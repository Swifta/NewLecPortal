package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PRODUCT001 database table.
 * 
 */
@Entity
@NamedQuery(name="Product001.findAll", query="SELECT p FROM Product001 p")
public class Product001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="DEFAULT_PRODUCT")
	private String defaultProduct;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	public Product001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDefaultProduct() {
		return this.defaultProduct;
	}

	public void setDefaultProduct(String defaultProduct) {
		this.defaultProduct = defaultProduct;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}